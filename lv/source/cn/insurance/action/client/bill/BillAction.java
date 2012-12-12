package cn.insurance.action.client.bill;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.naming.LimitExceededException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ModelDriven;

import cn.insurance.action.SupportAction;
import cn.insurance.commonwords.BillKey;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbBill;
import cn.insurance.model.TbTravelerInfo;
import cn.insurance.model.TbUser;
import cn.insurance.service.IBillQueryServ;
import cn.insurance.service.ITbBillServ;
import cn.insurance.service.client.IClientBillServ;
import cn.insurance.service.client.IClientMonthFeeServ;
import cn.insurance.utils.BillCheckMd5Code;
import cn.insurance.utils.CommonWords;

public class BillAction extends SupportAction implements ModelDriven{
	private Log logger=LogFactory.getLog(BillAction.class);
	
	public TbBill tbBill = new TbBill() ;
	
	private PageBean pageBean ;
	
	private File travelerFile ;
	
	private String travelerFileFileName ;
	
	private String travelerFileContentType ;
	//保单号
	private String listNumber;



	/*游客信息*/
	private String[] travelerName ;

	private String[] travelerSex;
	
	private String[] travelerBirthday ;
	
	private String[] travelerCountry ;
	
	private String[] travelerIndentity ;
	
	private IClientBillServ clientBillServ ;
	
	private IClientMonthFeeServ clientMonthFeeServ ;
	

	public Object getModel() {
		return tbBill;
	}

	
	/**
	 * 查询该部门所有已等待审核的保单
	 * @return
	 */
	public String getReferedBillList (){
		pageBean = clientBillServ.getReferedBillByPartmentId(pageBean, getUser().getIntPartmentId()) ;
		return SUCCESS ;
	}
	
	
	/**
	 * 查询部门的备案保单
	 * @return
	 */
	public String getBeiAnBillList(){
		pageBean = clientBillServ.getBeiAnBillByPartmentId(pageBean, getUser().getIntPartmentId()) ;
		return SUCCESS ;
		
	}
	
	/**
	 * 根据部门ID和保单状态ID查询保单并分页
	 */
	public String getEffectedBillList(){
		pageBean = clientBillServ.getEffectedBillByPartmentId(pageBean, getUser().getIntPartmentId()) ;
		return SUCCESS ;
	}
	
	/**
	 * 查询该部门所有已退回的保单
	 * @return
	 */
	public String getReturnBillList(){
		pageBean = clientBillServ.getReturnBillByPartmentId(pageBean, getUser().getIntPartmentId());
		return SUCCESS ;
	}
	

	/**预提交保单的时候就在数据库添加一条记录*/
	public String toAddBill(){
		try{
			/*验证该用户能否提交保单*/
			TbUser tbUser = getUser() ;
			/*验证帐户是否被停用*/
			if(tbUser.getTbPartment().getTbAccount().getIntAccountState() == CommonWords.accountState1){
				/*不可用*/
				addActionError("对不起，您的帐户已被停用，不能投保，请联系保险公司！") ;
				return INPUT ;
			}
			/*验证用户所在的旅行社的责任险是否过期*/
			if(new Date().after(tbUser.getTbPartment().getCompany().getDteResEndDate())){
				addActionError("对不起，您所在旅行社的责任险已到期，不能投保！") ;
				return INPUT ;
			}
			/*验证用户的帐户欠费是否超过了保险公司的规定*/
			if(tbUser.getTbPartment().getTbAccount().getDbeResidual() <0 && (-tbUser.getTbPartment().getTbAccount().getDbeResidual())>tbUser.getTbPartment().getTbAccount().getDbeAcceptMaxMoney()){
				addActionError("对不起，您帐户的欠费以超过了限额，交费后才能投保！") ;
				return INPUT ;
			}
			/*验证用户的以前有没有月费没有交费*/
			if(!clientMonthFeeServ.checkTheUserPartmentMonthFee(tbUser.getTbPartment().getTbAccount().getId())){
				addActionError("对不起，月费欠费已超过规定的日期，请先缴纳之前的月费！") ;
				return INPUT ;
			}
			Date date = new Date() ;
			tbBill.setDteApplyDate(date) ;
			tbBill = clientBillServ.addNewBill(tbBill) ;
			//对保单的id进行加密
			limitType="add" ;
			super.encodeId(tbBill.getId()) ;
		}catch(Exception e){
			addActionError(e.getMessage()) ;
//			addActionError("系统错误，请重新申请！") ;
			return INPUT ;
		}
		return SUCCESS ;
	}
	
	
	/**
	 * 用户申请保单，对保单进行检查
	 * 
	 * @return
	 */
	public String toApplyBill(){
		//由于为了加密，这里传的id是用limitId传过来的
		tbBill.setId(limitId) ;
		try{
			tbBill = clientBillServ.getTbBillById(tbBill.getId()) ;
		}catch(Exception e){
			addActionMessage("错误，数据出现错误，请联系管理员！") ;
			return INPUT ;
		}
		if(tbBill.getIntBillStateId() != CommonWords.billState1 && tbBill.getIntBillStateId() != CommonWords.billState3){
			addActionMessage("错误，可能该保单申请已提交了！") ;
			return INPUT ;
		}
		TbUser tbUser = (TbUser)ServletActionContext.getRequest().getSession().getAttribute("tbUser") ;
		if(tbBill.getIntPartmentId().intValue() != tbUser.getIntPartmentId().intValue()){
			addActionMessage("错误！，该保单不属于您所在部门的保单！") ;
			return INPUT ;
		}
		return SUCCESS ;
	}
	

	/**
	 * 算出保单的价格
	 */
	public String getBillPrice(){
		tbBill = clientBillServ.getBillPrice(tbBill) ;
		return SUCCESS ;
	}
	
	/**
	 * 确认正式申请保单
	 * @return
	 */
	public String sureApplybill(){
		tbBill.setId(limitId) ;
		if(!checkUpdateBill()){ //对保单进行验证，如果没有通过验证，则返回错误信息
			
			return INPUT ;
		}
		List<TbTravelerInfo> tbTravelerInfoList = new ArrayList<TbTravelerInfo>() ;
		if(travelerName != null && travelerName.length>0){
			for(int i =0 ;i<travelerName.length ; i ++){
				TbTravelerInfo traveler = new TbTravelerInfo() ;
				traveler.setIntBillId(tbBill.getId()) ;
				traveler.setStrTravelerName(travelerName[i]) ;
				traveler.setStrSex(travelerSex[i]) ;
				traveler.setStrBirthday(travelerBirthday[i]) ;
				traveler.setStrCountry(travelerCountry[i]) ;
				traveler.setStrIndentyNumber(travelerIndentity[i]) ;
				tbTravelerInfoList.add(traveler) ;
			}
		}
		int result = clientBillServ.sureApplyBill(tbBill , travelerFile , travelerFileFileName , tbTravelerInfoList) ;
		if(result ==0){
			addActionMessage("操作失败，该保单之前可能已经提交了，请不要再次提交！") ;
			return SUCCESS ;
		}else if(result ==1){
			addActionMessage("操作失败，上传文件失败！") ;
			return SUCCESS ;
		}else{
			addActionMessage("操作成功！您的申请已提交，请等待确认！") ;
			return SUCCESS ;
		}
	}
	
	/**
	 * 查询保单信息，跳转至可以打印的页面
	 * @return
	 */
	public String getBillInfoToPrint(){
		//对url加密，用limitId替换了id
		tbBill.setId(limitId) ;
		tbBill = clientBillServ.getTbBillById(tbBill.getId()) ;
		//给已确认的保单生成验证码
		//对于已确认的保单，根据保单内容生成验证码
		if(tbBill.getIntBillStateId() == CommonWords.billState4){
			tbBill.setMd5CheckCode(BillCheckMd5Code.getTheMd5Code(tbBill)) ;
		}
		return SUCCESS ;
	}
	
	/**
	 * 根据保单号查询保单信息，跳转至可以打印的页面
	 * @return
	 */
	public String getBillInfoByNumToPrint(){
		
		
		tbBill = clientBillServ.getBillByNumber(this.getListNumber()) ;
		//给已确认的保单生成验证码
		//对于已确认的保单，根据保单内容生成验证码
		if(tbBill.getIntBillStateId() == CommonWords.billState4){
			tbBill.setMd5CheckCode(BillCheckMd5Code.getTheMd5Code(tbBill)) ;
		}
		return SUCCESS ;
	}
	
	
	/**
	 * 用户更新保单
	 * @return
	 */
	public String updateBillByUser(){
		tbBill.setId(limitId) ;
		if(!checkUpdateBill()){ //对保单进行验证，如果没有通过验证，则返回错误信息
			return INPUT ;
		}
		List<TbTravelerInfo> tbTravelerInfoList = new ArrayList<TbTravelerInfo>() ;
		if(travelerName != null && travelerName.length>0){
			for(int i =0 ;i<travelerName.length ; i ++){
				TbTravelerInfo traveler = new TbTravelerInfo() ;
				traveler.setIntBillId(tbBill.getId()) ;
				traveler.setStrTravelerName(travelerName[i]) ;
				traveler.setStrSex(travelerSex[i]) ;
				traveler.setStrBirthday(travelerBirthday[i]) ;
				traveler.setStrCountry(travelerCountry[i]) ;
				traveler.setStrIndentyNumber(travelerIndentity[i]) ;
				tbTravelerInfoList.add(traveler) ;
			}
		}
		int result = clientBillServ.updateBillByUser(tbBill, travelerFile , travelerFileFileName , tbTravelerInfoList) ;
		if(result ==0){
			addActionMessage("操作失败，该保单已被限制修改") ;
			return INPUT ;
		}
		if(result ==1){
			addActionMessage("操作失败，上传文件失败！") ;
			return INPUT ;
		}
		addActionMessage("操作成功，保单已更新！") ;
		return SUCCESS ;
	}
	
	
	
	/**
	 * 修改备案保单之前，查询该保单信息，并进行验证该保单是否是备案保单
	 * @return
	 */
	public String toUpdateBeiAnBill(){
		//date  = new Date() ;
		tbBill.setId(limitId) ;
		tbBill = clientBillServ.getTbBillById(tbBill.getId()) ;
		//检查该保单是否是备案保单
		if(tbBill.getIntBillStateId().intValue() == BillKey.billState7 || tbBill.getIntBillStateId().intValue() == BillKey.billState8){
			addActionError("该保单不属于备案保单，可能已经被确认或退回，请刷新保单数据进行查看！") ;
			return INPUT ;
		}
		return SUCCESS ;
	}
	
	/**
	 * 旅行社更新备案保单信息
	 */
	public String updateBeiAnBillByUser(){
		tbBill.setId(limitId) ;
		List<TbTravelerInfo> tbTravelerInfoList = new ArrayList<TbTravelerInfo>() ;
		if(travelerName != null && travelerName.length>0){
			for(int i =0 ;i<travelerName.length ; i ++){
				TbTravelerInfo traveler = new TbTravelerInfo() ;
				traveler.setIntBillId(tbBill.getId()) ;
				traveler.setStrTravelerName(travelerName[i]) ;
				traveler.setStrSex(travelerSex[i]) ;
				traveler.setStrBirthday(travelerBirthday[i]) ;
				traveler.setStrCountry(travelerCountry[i]) ;
				traveler.setStrIndentyNumber(travelerIndentity[i]) ;
				tbTravelerInfoList.add(traveler) ;
			}
		}
		int result = clientBillServ.updateBeiAnBill(tbBill , travelerFile , travelerFileFileName , tbTravelerInfoList) ;
		if(result ==0){
			addActionMessage("操作失败，该保单已限制修改！") ;
			return SUCCESS ;
		}else if(result ==1){
			addActionMessage("操作失败，上传文件失败！") ;
			return SUCCESS ;
		}else{
			addActionMessage("操作成功！保单已重新提交，请等待确认！") ;
			return SUCCESS ;
		}
	}

	

	/**
	 * 删除保单（在保单没有正式提交之前都可以删除保单）
	 * @return
	 */
	public String deleteBillById(){
		//删除保单都需要对id进行验证
		tbBill.setId(limitId) ;
		tbBill = clientBillServ.getTbBillById(tbBill.getId());
		if(tbBill.getIntBillStateId().intValue() != BillKey.billState1){
			addActionError("该保单不能删除！") ;
			return SUCCESS ;
		}
		clientBillServ.deleteBillById(tbBill.getId()) ;
		addActionMessage("保单删除成功！") ;
		return SUCCESS ;
	}
	
	
	
	/**
	 * 用户修改填写保单会修改保单时，对保单填写的信息进行检查
	 * @return
	 */
	private boolean checkUpdateBill(){
		if(tbBill.getStrTeamNumber() == null || "".equals(tbBill.getStrTeamNumber().trim())){
			addActionError("请填写团队号！") ;
			return false ;
		}
		if(tbBill.getStrPhoneNumber() == null || "".equals(tbBill.getStrPhoneNumber().trim())){
			addActionError("请填写联系电话！") ;
			return false ;
		}
		if(tbBill.getStrTravelRold() == null || "".equals(tbBill.getStrTravelRold())){
			addActionError("请填写旅游线路！") ;
			return false ;
		}
		if(tbBill.getDteStartTime() == null){
			addActionError("请填写起始时间！") ;
			return false ;
		}
		if(tbBill.getDteEndTime() == null){
			addActionError("请填写结束时间！") ;
			return false ;
		}
		//由于没有对时分秒计时，所以这里要对时间定格在前一天的 23：59：59
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1) ;
		calendar.set(Calendar.HOUR_OF_DAY, 23) ;
		calendar.set(Calendar.MINUTE, 59) ;
		calendar.set(Calendar.SECOND, 59) ;
		Date curDate =calendar.getTime();
		if(tbBill.getDteStartTime().before(curDate)){
			addActionError("开始时间填写不正确，不能填写在当前时间之前！") ;
			return false ;
		}
		if(tbBill.getDteEndTime().before(curDate)){
			addActionError("结束时间填写不正确，不能填写在当前时间之前！") ;
			return false ;
		}
		if(tbBill.getDteStartTime().after(tbBill.getDteEndTime())){
			addActionError("开始时间和结束时间填写不正确，结束时间不能在开始时间之前！") ;
			return false ;
		}
		if((tbBill.getIntChinaTravelerNumber() + tbBill.getIntOtherTravelerNumber()) ==0){
			addActionError("请填写内宾人数和外宾人数！") ;
			return false ;
		}
		return true ;
	}
	
	
	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}


	public String[] getTravelerName() {
		return travelerName;
	}


	public void setTravelerName(String[] travelerName) {
		this.travelerName = travelerName;
	}


	public String[] getTravelerSex() {
		return travelerSex;
	}


	public void setTravelerSex(String[] travelerSex) {
		this.travelerSex = travelerSex;
	}


	public String[] getTravelerBirthday() {
		return travelerBirthday;
	}


	public void setTravelerBirthday(String[] travelerBirthday) {
		this.travelerBirthday = travelerBirthday;
	}


	public String[] getTravelerCountry() {
		return travelerCountry;
	}


	public void setTravelerCountry(String[] travelerCountry) {
		this.travelerCountry = travelerCountry;
	}


	public String[] getTravelerIndentity() {
		return travelerIndentity;
	}


	public void setTravelerIndentity(String[] travelerIndentity) {
		this.travelerIndentity = travelerIndentity;
	}


	public File getTravelerFile() {
		return travelerFile;
	}


	public void setTravelerFile(File travelerFile) {
		this.travelerFile = travelerFile;
	}


	public String getTravelerFileFileName() {
		return travelerFileFileName;
	}


	public void setTravelerFileFileName(String travelerFileFileName) {
		this.travelerFileFileName = travelerFileFileName;
	}


	public String getTravelerFileContentType() {
		return travelerFileContentType;
	}


	public void setTravelerFileContentType(String travelerFileContentType) {
		this.travelerFileContentType = travelerFileContentType;
	}


	public IClientBillServ getClientBillServ() {
		return clientBillServ;
	}


	public void setClientBillServ(IClientBillServ clientBillServ) {
		this.clientBillServ = clientBillServ;
	}


	public IClientMonthFeeServ getClientMonthFeeServ() {
		return clientMonthFeeServ;
	}


	public void setClientMonthFeeServ(IClientMonthFeeServ clientMonthFeeServ) {
		this.clientMonthFeeServ = clientMonthFeeServ;
	}



	public String getListNumber() {
		return listNumber;
	}


	public void setListNumber(String listNumber) {
		this.listNumber = listNumber;
	}
	
	
}

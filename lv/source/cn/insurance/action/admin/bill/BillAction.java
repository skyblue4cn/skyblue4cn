package cn.insurance.action.admin.bill;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork.ModelDriven;

import cn.insurance.action.SupportAction;
import cn.insurance.commonwords.BillKey;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbBill;
import cn.insurance.model.TbBillBackup;
import cn.insurance.model.TbTravelerInfo;
import cn.insurance.service.admin.IAdminBillServ;

/**
 * 保险公司对保单的处理和查询
 * 确认、备案、退回
 * @author yqg
 *
 */
public class BillAction extends SupportAction implements ModelDriven{
	
	private static final long serialVersionUID = 1L;

	public TbBill tbBill = new TbBill() ;
	
	private PageBean pageBean ;
	
	private IAdminBillServ adminBillServ ;
	
	private Date date ;
	
	/*游客信息*/
	private String[] travelerName ;
	
	private String[] travelerSex;
	
	private String[] travelerBirthday ;
	
	private String[] travelerCountry ;
	
	private String[] travelerIndentity ;
	
	private File travelerFile ;
	
	private String travelerFileFileName ;
	
	private String travelerFileContentType ;
	
	private TbBillBackup tbBillBackup ;

	public Object getModel() {
		return tbBill;
	}
	
	
	
	/**
	 * 查询需要保险公司审核的保单
	 * @return
	 */
	public String getBillNeedBxSure(){
		
		pageBean = adminBillServ.getBillNeedBxSure(pageBean) ;
		return SUCCESS ;
	}
	
	
	/**
	 * 查询已备案的保单
	 * @return
	 */
	public String getBillForBxBeiAn(){
		pageBean = adminBillServ.getBillForBxBeiAn(pageBean) ;
		return SUCCESS ;
	}
	
	/**
	 * 查询保险公司已审核生效的保单
	 * @return
	 */
	public String getBillForBxHasSure(){
		
		pageBean = adminBillServ.getBillForBxHasSure(pageBean) ;
		return SUCCESS ;
	}
	
	/**
	 * 查询保险公司已退回的保单
	 * @return
	 */
	public String getBillForBxHasReturn(){
		pageBean = adminBillServ.getBillForBxHasReturn(pageBean) ;
		return SUCCESS; 
	}
	
	/**
	 * 查询保单信息，准备确认保单
	 * @return
	 */
	public String toSureBillByBX(){
		tbBill.setId(limitId) ;
		date  = new Date() ;
		tbBill = adminBillServ.getTbBillById(tbBill.getId()) ;
		return SUCCESS ;
	}
	

	/**
	 * 保险公司审查保单通过,
	 * 确认该保单
	 * @return
	 */
	public String sureBillByBxSuccess(){
		int result = adminBillServ.sureBillByBxSuccess(tbBill) ;
		if(result ==1){
			addActionMessage("操作失败，该保单之前已经被审查过了") ;
		}
		if(result == 2){
			addActionMessage("确认成功！该保单保费已从帐户扣除!") ;
		}
		if(result == 3){
			addActionMessage("确认成功！该保单保费已计入月结费用中！") ;
		}
		return SUCCESS ;
	}
	
	/**
	 * 保险公司退回保单,
	 * 退回该保单
	 * @return
	 */
	public String returnBillByBx(){
		int result = adminBillServ.returnBillByBx(tbBill) ;
		if(result ==1){
			addActionMessage("操作失败，该保单之前已经被审查过了!") ;
		}
		if(result == 2){
			addActionMessage("操作成功，该保单已被退回！") ;
		}
		
		return SUCCESS ;
	}
	
	/**
	 * 保险公司将保单作备案处理，
	 * 备案该保单
	 * @return
	 */
	public String beiAnBillByBx(){
		int result = adminBillServ.beiAnBillByBx(tbBill) ;
		if(result == 0){
			addActionMessage("操作失败，该保单之前已经审查过了！") ;
		}else{
			addActionMessage("操作成功，保单已被备案！") ;
		}
		return SUCCESS ;
	}
	
	
	
	
	/**
	 * 根据ID查询保单信息
	 * @return
	 */
	public String getTbBillById(){
		date  = new Date() ;
		tbBill = adminBillServ.getTbBillById(tbBill.getId()) ;
		return SUCCESS ;
	}
	
	/**
	 * 保险公司更新保单
	 * @return
	 */
	public String toUpdateBillByBx(){
		tbBill = adminBillServ.getTbBillById(tbBill.getId()) ;
		if(tbBill.getIntBillStateId().intValue() != BillKey.billState4){
			//如果不是生效的保单，则不能更改
			addActionMessage("该保单不是已确认的有效保单！不需要更改!") ;
			return INPUT ;
		}
		return SUCCESS ;
	}
	
	
	
	/**
	 * 保险公司对保单进行更新
	 * @return
	 */
	public String updateBillByBx(){
		
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
		int result = adminBillServ.updateBillByBx(tbBill, travelerFile , travelerFileFileName , tbTravelerInfoList , tbBillBackup) ;
		if(result ==0){
			addActionMessage("操作失败，该保单已被限制修改") ;
			return INPUT ;
		}
		if(result ==1){
			addActionMessage("操作失败，上传文件失败！") ;
			return INPUT ;
		}
		addActionMessage("操作成功，保单已更新!") ;
		return SUCCESS ;
	}
	
	/**
	 * 保险公司修改确认保单时，可能出了修改外还需要的功能就是让保单作废
	 * 这里就是对已确认的保单，让这个保单作废
	 * @return
	 */
	public String returnSureBillByBx(){
		int result = adminBillServ.returnSureBillByBx(tbBill) ;
		if(result == 0){
			addActionMessage("该保单不是已确认保单，更改失败！") ;
		}else{
			addActionMessage("操作成功，保单已作废！") ;
		}
		return SUCCESS ;
	}
	
	

	public PageBean getPageBean() {
		return pageBean;
	}



	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
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



	public TbBillBackup getTbBillBackup() {
		return tbBillBackup;
	}



	public void setTbBillBackup(TbBillBackup tbBillBackup) {
		this.tbBillBackup = tbBillBackup;
	}



	public IAdminBillServ getAdminBillServ() {
		return adminBillServ;
	}



	public void setAdminBillServ(IAdminBillServ adminBillServ) {
		this.adminBillServ = adminBillServ;
	}

	
	
}

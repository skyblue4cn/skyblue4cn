package cn.insurance.service.client.impl;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import cn.insurance.commonwords.BillKey;
import cn.insurance.dao.ITbBillDao;
import cn.insurance.dao.ITbMessageDao;
import cn.insurance.dao.ITbPartmentDao;
import cn.insurance.dao.ITbTravelerInfoDao;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbBill;
import cn.insurance.model.TbPartment;
import cn.insurance.model.TbTravelerInfo;
import cn.insurance.service.IPriceServ;
import cn.insurance.service.client.IClientBillServ;
import cn.insurance.utils.CommonWords;
import cn.insurance.utils.DateUtil;
import cn.insurance.utils.FileUpload;

public class ClientBillServ implements IClientBillServ{
		
	private ITbBillDao tbBillDao ;
	
	private ITbPartmentDao tbPartmentDao ;
	
	private ITbTravelerInfoDao tbTravelerInfoDao ;
	
	private ITbMessageDao tbMessageDao ;
	
	private IPriceServ priceServ ;
	
	
	/*
	 * 添加新保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.client.IClientBillServ#addNewBill(cn.insurance.model.TbBill)
	 */
	public TbBill addNewBill(TbBill tbBill) throws Exception{
		// TODO Auto-generated method stub
		/*设定所有的保单开始都是预提交*/
		tbBill.setIntBillStateId(BillKey.billState1);
		tbBill.setIntIsPay(-1) ;
		tbBill.setIntIsAddTraveler(-1) ;
		//设置保单号
		tbBill.setStrBillNumber(getBillNumber(tbBill.getIntPartmentId())) ;
		Integer id = tbBillDao.create(tbBill) ;
		return getTbBillById(id) ;
	}
	
	
	/*
	 * 正式提交申请保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.client.IClientBillServ#sureApplyBill(cn.insurance.model.TbBill, java.io.File, java.lang.String, java.util.List)
	 */
	public int sureApplyBill(TbBill tbBill, File file, String fileName,List<TbTravelerInfo> travelerList) {
		/*检查该保单是否已经提交过*/
		TbBill oldBill = tbBillDao.getObjectInfoById(tbBill.getId()) ;
		if(oldBill.getIntBillStateId() != CommonWords.billState1){
			return 0 ;
		}
		/*查询出该用户所在的部门信息*/
		TbPartment tbPartment = (TbPartment)tbPartmentDao.getObjectInfoById(tbBill.getIntPartmentId()) ;
		tbBill.setTbPartment(tbPartment) ;
		/*先看是否有附件需要上传*/
		if(file != null) {
			try{
				uploadTravelFile(tbBill, file ,fileName) ;
			}catch(Exception e){
				return 1 ;
			}
		}
		/*添加游客信息*/
		if(travelerList != null && travelerList.size() >0){
			tbTravelerInfoDao.create(travelerList);
		}
		/*计算该保单需要的费用*/
		priceServ.getBillPrice(tbBill) ;
		/*保单的收费方式预设为月结*/
		tbBill.setIntPayType(CommonWords.payType2) ;
		/*保单费用是否支付设定为初始状态*/
		tbBill.setIntIsPay(-1) ;
		/*判断该用户所在的旅行社是否要求审核保单，根据情况设定保单状态*/
		if(tbPartment.getIntParentId() ==0 || tbPartment.getCompany().getIntIsNeedSureBill() == 0){
			/*如果是旅行社提交的保单，则直接提交到保险公司,或者是如果总社不需要审查，那么保单直接为已通过审查过的状态*/
			tbBill.setIntIsSureByZs(1) ;
		}else{
			/*如果是部门提交并且旅行社需要审查，则保单为未通过总社审查的状态*/
			tbBill.setIntIsSureByZs(0) ;
		}
		/*将保单发往保险公司*/
		tbBill.setIntBillStateId(CommonWords.billState3) ;
		tbBillDao.update(tbBill) ;	
		/*给申请的部门发送一条消息，告诉部门申请保单成功，请等待确认*/
		StringBuffer message = new StringBuffer() ;
		message.append("新申请了一张保单,保单号为:").append(tbBill.getStrBillNumber()) ;
		tbMessageDao.sendMessage(CommonWords.messageType2, tbBill.getIntPartmentId(), message.toString()) ;
		return 2;
	}
	
	/*
	 * 查询保单的价格
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbBillServ#getBillPrice(cn.insurance.model.TbBill)
	 */
	public TbBill getBillPrice(TbBill tbBill){
		return priceServ.getBillPrice(tbBill) ;
	}
	
	/*
	 * 更新保单信息
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbBillServ#updateBill(cn.insurance.model.TbBill)
	 */
	public int updateBillByUser(TbBill tbBill,File file ,String fileName ,List<TbTravelerInfo> travelerList) {
		/*检查该保单是否已经提交过*/
		TbBill oldBill = tbBillDao.getObjectInfoById(tbBill.getId()) ;
		if(oldBill.getIntBillStateId() != CommonWords.billState3){
			return 0 ;
		}
		/*查询出该用户所在的部门信息*/
		TbPartment tbPartment = (TbPartment)tbPartmentDao.getObjectInfoById(tbBill.getIntPartmentId()) ;
		tbBill.setTbPartment(tbPartment) ;
		/*先看是否有附件需要上传*/
		if(file != null) {
			try{
				uploadTravelFile(tbBill, file ,fileName) ;
			}catch(Exception e){
				return 1 ;
			}
		}else{
			tbBill.setStrFileUrl(oldBill.getStrFileUrl()) ;
		}
		/*将以前的游客信息删除*/
		tbTravelerInfoDao.deleteByBillId(tbBill.getId())  ;
		/*添加新的游客信息*/
		if(travelerList != null && travelerList.size() >0){
			tbTravelerInfoDao.create(travelerList);
		}
		/*计算该保单需要的费用*/
		priceServ.getBillPrice(tbBill) ;
		/*保单的收费方式预设为月结*/
		tbBill.setIntPayType(CommonWords.payType2) ;
		/*保单费用是否支付设定为初始状态*/
		tbBill.setIntIsPay(-1) ;
		/*判断该用户所在的旅行社是否要求审核保单，根据情况设定保单状态*/
		if(tbPartment.getIntParentId() ==0 || tbPartment.getCompany().getIntIsNeedSureBill() == 0){
			/*如果是旅行社提交的保单，则直接提交到保险公司,或者是如果总社不需要审查，那么保单直接为已通过审查过的状态*/
			tbBill.setIntIsSureByZs(1) ;
		}else{
			/*如果是部门提交并且旅行社需要审查，则保单为未通过总社审查的状态*/
			tbBill.setIntIsSureByZs(0) ;
		}
		/*将保单发往保险公司*/
		tbBill.setIntBillStateId(CommonWords.billState3) ;
		tbBillDao.update(tbBill) ;	
		/*给申请的部门发送一条消息，告诉部门申请保单成功，请等待确认*/
		StringBuffer message = new StringBuffer() ;
		message.append("更新保单(保单号为:").append(tbBill.getStrBillNumber())
			.append("),已发送审查确认！") ;
		tbMessageDao.sendMessage(CommonWords.messageType2, tbBill.getIntPartmentId(), message.toString()) ;
		return 2;
	}
	
	/*
	 * 更新备案保单
	 * 备案保单只能更新人数和名单（所以这里用oldbill来更新）
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbBillServ#updateBeiAnBill(cn.insurance.model.TbBill, java.io.File, java.lang.String, java.util.List)
	 */
	public int updateBeiAnBill(TbBill tbBill ,File file ,String fileName ,List<TbTravelerInfo> travelerList) {
		/*检查该保单是否是备案保单*/
		TbBill oldBill = tbBillDao.getObjectInfoById(tbBill.getId()) ;
		if(!(oldBill.getIntBillStateId()== CommonWords.billState7 || oldBill.getIntBillStateId()== CommonWords.billState8)){
			return 0 ;
		}
		/*先看是否有附件需要上传*/
		if(file != null) {
			try{
				uploadTravelFile(oldBill, file, fileName) ;
			}catch(Exception e){
				return 1 ;
			}
		}
		/*将以前的游客信息删除*/
		tbTravelerInfoDao.deleteByBillId(tbBill.getId())  ;
		/*添加新的游客信息*/
		if(travelerList != null && travelerList.size() >0){
			tbTravelerInfoDao.create(travelerList);
		}
		/*计算该保单需要的费用*/
		oldBill.setIntChinaTravelerNumber(tbBill.getIntChinaTravelerNumber()) ;
		oldBill.setIntOtherTravelerNumber(tbBill.getIntOtherTravelerNumber()) ;
		priceServ.getBillPrice(oldBill) ;
		/*更新保单信息,备案保单只能更新人数和名单（所以这里用oldbill来更新）*/
		tbBillDao.update(oldBill);
		return 2 ;
	}
	
	/*
	 * 根据ID删除保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.client.IClientBillServ#deleteBillById(java.lang.Integer)
	 */
	public void deleteBillById(Integer id) {
		tbBillDao.delete(id) ;	
	}
	
	/*
	 * 按ID查询保单信息
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbBillServ#getTbBillById(java.lang.Integer)
	 */
	public TbBill getTbBillById(Integer id) {
		return tbBillDao.getObjectInfoById(id) ;
	}
	
	/*
	 * 按保单号查询保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.client.IClientBillServ#getBillByNumber(java.lang.String)
	 */
	public TbBill getBillByNumber(String billNumber){
		return (TbBill)tbBillDao.getBillByNumber(billNumber) ;
	}
	
	
	/*
	 * 根据部门ID查询某个部门已审核的确认的保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getBillByPartmentAndState(cn.insurance.model.TbBill)
	 */
	public PageBean getEffectedBillByPartmentId(PageBean pageBean ,Integer partmentId) {
		// TODO Auto-generated method stub
		return tbBillDao.getEffectedBillByPartmentId(pageBean, partmentId) ;
	}

	/*
	 * 查询该部门所有的待审核的保单
	 * 已提交的保单有2中情况
	 * 旅行社未审查的
	 * 保险公司未审查的
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getReferedBillByPartmentId(cn.insurance.model.PageBean, cn.insurance.model.TbBill)
	 */
	public PageBean getReferedBillByPartmentId(PageBean pageBean , Integer partmentID){
		return tbBillDao.getReferedBillByPartmentId(pageBean, partmentID) ;
	}
	
	/*
	 * 查询部门已备案的保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.client.IClientBillServ#getBeiAnBillByPartmentId(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getBeiAnBillByPartmentId(PageBean pageBean , Integer partmentId){
		return tbBillDao.getBeiAnBillByPartmentId(pageBean, partmentId) ;
	}
	
	
	/*
	 * 查询该部门所有被退回来的保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getReturnBillByPartmentId(cn.insurance.model.PageBean, cn.insurance.model.TbBill)
	 */
	public PageBean getReturnBillByPartmentId(PageBean pageBean , Integer partmentId){
		return tbBillDao.getReturnBillByPartmentId(pageBean, partmentId) ;
	}
	
	
	/*
	 * 部门根据条件查找保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getBillByComditionForUser(cn.insurance.model.PageBean, cn.insurance.model.TbBill, java.lang.Integer, int[], java.lang.String, java.lang.String)
	 */
	public PageBean getBillByComditionForUser(PageBean pageBean , TbBill tbBill , int[] state , String startTime , String endTime){
		return tbBillDao.getBillByComditionForUser(pageBean, tbBill, state, startTime, endTime) ;
	}
	
	
	public PageBean getBillByComditionForUser_2010(PageBean pageBean ,String strTravelerName,String strIndentyNumber, TbBill tbBill , int[] state , String startTime , String endTime){
		return tbBillDao.getBillByComditionForUser_2010(pageBean, strTravelerName, strIndentyNumber, tbBill, state, startTime, endTime);
	}
	
	
	/**
	 * 给保单编号
	 * 保单编号规则：PEDP + 年 + 51010706 + 旅行社ID（三位） + 部门ID（三位） + 部门投保最大数（四位）
	 * @return
	 */
	private String getBillNumber(int partmentId){
		StringBuffer sb = new StringBuffer() ;
		sb.append("PEDP") ;
		sb.append(DateUtil.getFormatDate(new Date(), "yyyy")) ;
		sb.append("51010706") ;
		TbPartment tbPartment = tbPartmentDao.getObjectInfoById(partmentId) ;
		/*旅行社和部门都是三位补0*/
		DecimalFormat df = new DecimalFormat("000") ;
		//旅行社ID
		sb.append(df.format(tbPartment.getCompany().getId())) ;
		//部门ID
		sb.append(df.format(partmentId)) ;
		//这里不能以部门最大的投保数作为最后的四位数字，因为如果有保单被删除，那么就会出现重复的保单号
		//所以这里将这个部门的最大ID的保单号查出来，然后将最后四位数取出来加1 来作为保单号，这样能避免重复
		TbBill maxBill = tbBillDao.getThePartmentMaxIdBill(partmentId) ;
		//如果还没有保单,那么是第一张保单，所以这里就是编号0001
		if(maxBill == null){
			sb.append("0001") ;
		}else{
//			取出保单的最后几位,从22位开始就是编号 例如: PEDP2008510107060010020030
			Integer number = new Integer(maxBill.getStrBillNumber().substring(22)) ;
			df = new DecimalFormat("0000") ;
			sb.append(df.format((number+1))) ;
		}
		return sb.toString() ;
	}
	
	
	
	/**
	 * 上传保单的旅客文件
	 *
	 */
	private void uploadTravelFile(TbBill tbBill , File file , String fileName) throws Exception{
		Calendar calendar = new GregorianCalendar() ;
		String date = DateUtil.getFormatDate(calendar, "-yy-MM-dd-hh-mm-ss") ;
		//保存路径是 truavelerFile/年/旅行社ID/部门ID/保单号/
		StringBuffer filePath = new StringBuffer() ;
		filePath.append("travelerFile").append("/").append(calendar.get(Calendar.YEAR)).append("/") ;
		filePath.append(tbBill.getTbPartment().getCompany().getId()).append("/").append(tbBill.getTbPartment().getId()).append("/");
		filePath.append(tbBill.getStrBillNumber());
		/*保单号+后缀名给文件重新命名（中文名称的文件下载不太好处理）*/
		String reName = tbBill.getStrBillNumber()+ date + fileName.substring(fileName.lastIndexOf("."),fileName.length()) ;
		FileUpload.uploadFile(file, filePath.toString(), reName) ;
		tbBill.setStrFileUrl(filePath + "/" + reName) ;
	}
	
	
	/*
	 * 通过旅行社总社的ID查询该旅行社总社及下属部门的所有已确认保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.client.IClientBillServ#getAllPartmentEffectedBillByCompanyId(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getAllPartmentEffectedBillByCompanyId(PageBean pageBean ,Integer companyId){
		return tbBillDao.getAllPartmentEffectedBillByCompanyId(pageBean, companyId);
	}
	
	
	public ITbBillDao getTbBillDao() {
		return tbBillDao;
	}


	public void setTbBillDao(ITbBillDao tbBillDao) {
		this.tbBillDao = tbBillDao;
	}







	public ITbPartmentDao getTbPartmentDao() {
		return tbPartmentDao;
	}







	public void setTbPartmentDao(ITbPartmentDao tbPartmentDao) {
		this.tbPartmentDao = tbPartmentDao;
	}


	public ITbTravelerInfoDao getTbTravelerInfoDao() {
		return tbTravelerInfoDao;
	}


	public void setTbTravelerInfoDao(ITbTravelerInfoDao tbTravelerInfoDao) {
		this.tbTravelerInfoDao = tbTravelerInfoDao;
	}


	public ITbMessageDao getTbMessageDao() {
		return tbMessageDao;
	}


	public void setTbMessageDao(ITbMessageDao tbMessageDao) {
		this.tbMessageDao = tbMessageDao;
	}


	public IPriceServ getPriceServ() {
		return priceServ;
	}


	public void setPriceServ(IPriceServ priceServ) {
		this.priceServ = priceServ;
	}

}

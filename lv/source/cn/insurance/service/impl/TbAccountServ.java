//package cn.insurance.service.impl;
//
//import java.util.Date;
//import java.util.List;
//
//import cn.insurance.dao.ITbAccountDao;
//import cn.insurance.dao.ITbMessageDao;
//import cn.insurance.dao.ITbMonthPayInfoDao;
//import cn.insurance.dao.ITbPartmentDao;
//import cn.insurance.dao.ITbPayoutInfoDao;
//import cn.insurance.dao.ITbYearPayInfoDao;
//import cn.insurance.model.PageBean;
//import cn.insurance.model.TbAccount;
//import cn.insurance.model.TbBill;
//import cn.insurance.model.TbPartment;
//import cn.insurance.model.TbPayOutInfo;
//import cn.insurance.model.TbYearPayInfo;
//import cn.insurance.service.ITbAccountServ;
//import cn.insurance.utils.CommonWords;
//import cn.insurance.utils.DateUtil;
//
//public class TbAccountServ implements ITbAccountServ {
//
//	private ITbAccountDao tbAccountDao;
//	
//	private ITbPartmentDao tbPartmentDao ; 
//	
//	private ITbPayoutInfoDao tbPayOutInfoDao ;
//	
//	
//	private ITbMonthPayInfoDao tbMonthPayInfoDao ;
//	
//	private ITbYearPayInfoDao tbYearPayInfoDao ;
//	
//	private ITbMessageDao tbMessageDao ;
//	
//	/*
//	 * 根据帐户ID查询帐户信息
//	 * (non-Javadoc)
//	 * @see cn.insurance.service.ITbAccountServ#getAccountById(java.lang.Integer)
//	 */
//	public TbAccount getAccountById(Integer id){
//		return tbAccountDao.getObjectInfoById(id) ;
//	}
//	
//	/*
//	 * 根据部门ID查询帐户信息
//	 * (non-Javadoc)
//	 * @see cn.insurance.service.ITbAccountServ#queryAccountByPartmentId(java.lang.Integer)
//	 */
//	public TbAccount getAccountByPartmentId(Integer intPartmentId) {
//		// TODO Auto-generated method stub
//		List list= tbAccountDao.getAllObjectListByCondition(intPartmentId) ;
//		if(list != null && list.size()>0){
//			return (TbAccount)list.get(0);
//		}
//		return null ;
//	}
//	
//	/*
//	 * 更新帐户设置
//	 * (non-Javadoc)
//	 * @see cn.insurance.service.ITbAccountServ#updateAccount(cn.insurance.model.TbAccount)
//	 */
//	public boolean updateAccount(TbAccount tbAccount){
//		tbAccountDao.update(tbAccount) ;		
//		return true ;
//	}
//	
//	/*
//	 * 更新帐户设置 
//	 * (non-Javadoc)
//	 * @see cn.insurance.service.ITbAccountServ#updateAccountProperty(cn.insurance.model.TbAccount)
//	 */
//	public void updateAccountProperty(TbAccount tbAccount){
//		TbAccount tbAccount_old = getAccountById(tbAccount.getId()) ;
//		tbAccount_old.setIntPayTypeId(tbAccount.getIntPayTypeId()) ;
//		tbAccount_old.setIntAcceptDays(tbAccount.getIntAcceptDays()) ;
//		tbAccount_old.setDbeAcceptMaxMoney(tbAccount.getDbeAcceptMaxMoney()) ;
//		tbAccount_old.setIntAccountState(tbAccount.getIntAccountState()) ;
//		tbAccountDao.update(tbAccount_old) ;
//	}
//	
//	
//	/**
//	 * 从帐户里扣费，并将之前和之后的帐户情况记录起来
//	 * @param tbAccount
//	 * @param allNeedFee
//	 * @param tbPayOutInfo
//	 */
//	private void payByAccount(TbAccount tbAccount , double allNeedFee ,TbPayOutInfo tbPayOutInfo){
//		//记录帐户支付之前的余额
//		tbPayOutInfo.setDbeCurResidual(tbAccount.getDbeResidual()) ;
//		/*帐户余额足够，则直接扣除*/
//		tbAccount.setDbeResidual(tbAccount.getDbeResidual() - allNeedFee) ;
//		tbAccountDao.update(tbAccount) ;
//		tbAccount = (TbAccount)tbAccountDao.getObjectInfoById(tbAccount.getId()) ;
//		
//		/*记录帐户支付之后的余额*/
//		tbPayOutInfo.setDbeAftResidual(tbAccount.getDbeResidual()) ;
//		tbPayOutInfo.setIntAccountId(tbAccount.getId()) ;
//	}
//
//	/*
//	 * 旅行社退保时，退钱给旅行社
//	 * 退钱已保单所记录的扣钱为标准退钱
//	 * (non-Javadoc)
//	 * @see cn.insurance.service.ITbAccountServ#returnAccount(cn.insurance.model.TbBill)
//	 */
//	public void returnAccount(TbBill bill) {
//
//	}
//
//
//	
//	/*
//	 * 根据保单ID找出这个保单的所有记录
//	 * 这个记录一般最多只有扣钱和退保2条记录，所有不分页
//	 * (non-Javadoc)
//	 * @see cn.insurance.service.ITbAccountServ#getAllPayOutInfoByBillId(cn.insurance.model.PageBean, java.lang.Integer)
//	 */
//	public List<TbPayOutInfo> getAllPayOutInfoByBillId(Integer billId){
//		TbPayOutInfo tbPayOutInfo = new TbPayOutInfo() ;
//		tbPayOutInfo.setIntBillId(billId) ;
//		return tbPayOutInfoDao.getAllObjectListByCondition(tbPayOutInfo) ;
//	}
//	
//
//	
//	
//	
//	/*
//	 * 查询某个帐户的所有预存费用的支付信息
//	 * (non-Javadoc)
//	 * @see cn.insurance.service.ITbAccountServ#getYuCunPayLogByAccountId(cn.insurance.model.PageBean, java.lang.Integer)
//	 */
//	public PageBean getYuCunPayLogByAccountId(PageBean pageBean , Integer accountId ,Integer payTypeId){
//		return tbPayOutInfoDao.getYuCunPayLogByAccountId(pageBean, accountId ,payTypeId) ;
//	}
//	
//	/*
//	 * 查询某个帐户的所有年费包干支付信息
//	 * (non-Javadoc)
//	 * @see cn.insurance.service.ITbAccountServ#getYearPayLogByAccountId(cn.insurance.model.PageBean, java.lang.Integer)
//	 */
//	public PageBean getYearPayLogByAccountId(PageBean bean , Integer accountId) {
//		
//		return tbYearPayInfoDao.getYearPayByAccountId(bean, accountId) ;
//	}
//
//	
//	/**
//	 * 根据ID查询年费记录
//	 * @param yearPayInfoId
//	 * @return
//	 */
//	public TbYearPayInfo getYearPayInfoById(Integer yearPayInfoId){
//		return tbYearPayInfoDao.getObjectInfoById(yearPayInfoId) ;
//	}
//	
//	/**
//	 * 根据ID查询预存费记录
//	 * @param payOutInfoId
//	 * @return
//	 */
//	public TbPayOutInfo getPayOutInfoById(Integer payOutInfoId){
//		return tbPayOutInfoDao.getObjectInfoById(payOutInfoId) ;
//	}
//	
//	/*
//	 * 预存帐户收费
//	 * (non-Javadoc)
//	 * @see cn.insurance.service.ITbAccountServ#shouFeeForYuCun(cn.insurance.model.TbPayOutInfo)
//	 */
//	public void  shouFeeForYuCun(TbPayOutInfo tbPayOutInfo){
//		/*先查询帐户余额*/
//		TbAccount tbAccount = getAccountById(tbPayOutInfo.getIntAccountId()) ;
//		/*记录之前的帐户余额*/
//		tbPayOutInfo.setDbeCurResidual(tbAccount.getDbeResidual()) ;
//		
//		/*更新帐户*/
//		tbAccount.setDbeResidual(tbAccount.getDbeResidual() + tbPayOutInfo.getDbePayoutNumber()) ;
//		tbAccountDao.update(tbAccount) ;
//		/*记录之后的帐户余额*/
//		tbPayOutInfo.setDbeAftResidual(tbAccount.getDbeResidual()) ;
//		/*记录时间和类型*/
//		tbPayOutInfo.setIntType(CommonWords.payOutKind1) ;
//		tbPayOutInfo.setDtePayoutTime(new Date()) ;
//		/*添加支付记录*/
//		tbPayOutInfoDao.create(tbPayOutInfo) ;
//		
//		/*往保险公司发送一条消息*/
//		StringBuffer message = new StringBuffer() ;
//		TbPartment tbPartment = tbPartmentDao.getObjectInfoById(tbAccount.getIntPartmentId()) ;
//		message.append(tbPartment.getCompany().getStrPartmentName()).append("下的").append(tbPartment.getStrPartmentName())
//				.append("存款").append(tbPayOutInfo.getDbePayoutNumber()).append("元，操作人：").append(tbPayOutInfo.getStrSaveUserName()) ;
//		tbMessageDao.sendMessage(CommonWords.messageType2, 0, message.toString()) ;
//		/*发送消息给该部门*/
//		message = new StringBuffer() ;
//		message.append("帐户存款").append(tbPayOutInfo.getDbePayoutNumber()).append("元，现有帐户余额：").append(tbAccount.getDbeResidual()).append("元");
//		tbMessageDao.sendMessage(CommonWords.messageType2, tbAccount.getIntPartmentId(), message.toString()) ;
//		
//		
//	}
//	
//
//	
//	/*
//	 * 年费用户交费
//	 * (non-Javadoc)
//	 * @see cn.insurance.service.ITbAccountServ#shouYearFee(cn.insurance.model.TbYearPayInfo)
//	 */
//	public void shouYearFee(TbYearPayInfo tbYearPayInfo){
//		/*先查询帐户余额*/
//		TbAccount tbAccount = getAccountById(tbYearPayInfo.getIntAccountId()) ;
//		/*记录之前的余额*/
//		tbYearPayInfo.setDbeCurResidual(tbAccount.getDbeResidual()) ;
//		/*更新帐户信息*/
//		tbAccount.setDbeResidual(tbAccount.getDbeResidual() + tbYearPayInfo.getDbePayFee()-tbYearPayInfo.getDbeNeedFeeByYear()) ;
//		tbAccountDao.update(tbAccount) ;
//		/*添加支付记录*/
//		tbYearPayInfo.setDtePayDate(new Date()) ;
//		tbYearPayInfo.setDbeAftResidual(tbAccount.getDbeResidual()) ;
//		tbYearPayInfoDao.create(tbYearPayInfo) ;
//		/*往保险公司发送一条消息*/
//		StringBuffer message = new StringBuffer() ;
//		TbPartment tbPartment = tbPartmentDao.getObjectInfoById(tbAccount.getIntPartmentId()) ;
//		message.append(tbPartment.getStrPartmentName())
//				.append("已交付年费")	.append("，年费需要支付:").append(tbYearPayInfo.getDbeNeedFeeByYear())
//				.append("元，实际支付：").append(tbYearPayInfo.getDbePayFee())
//				.append("元，年费有效日期:").append(DateUtil.getFormatDate(tbYearPayInfo.getDteStartTime(),"yyyy年MM月dd日"))
//				.append("至")
//				.append(DateUtil.getFormatDate(tbYearPayInfo.getDteEndTime(),"yyyy年MM月dd日"))
//				.append("，操作人：").append(tbYearPayInfo.getStrUserName()) ;
//		tbMessageDao.sendMessage(CommonWords.messageType2, 0, message.toString()) ;
//		/*发送消息给该旅行社*/
//		message = new StringBuffer() ;
//		message.append("已交付年费")	.append("，年费需要支付:").append(tbYearPayInfo.getDbeNeedFeeByYear())
//			.append("元，实际支付：").append(tbYearPayInfo.getDbePayFee())
//			.append("元，年费有效日期:").append(DateUtil.getFormatDate(tbYearPayInfo.getDteStartTime(),"yyyy年MM月dd日"))
//			.append("至")
//			.append(DateUtil.getFormatDate(tbYearPayInfo.getDteEndTime(),"yyyy年MM月dd日"))
//			.append("。");
//		
//		tbMessageDao.sendMessage(CommonWords.messageType2, tbAccount.getIntPartmentId(), message.toString()) ;
//		
//	}
//	
//
//	
//	
//
//	public ITbAccountDao getTbAccountDao() {
//		return tbAccountDao;
//	}
//
//	public void setTbAccountDao(ITbAccountDao tbAccountDao) {
//		this.tbAccountDao = tbAccountDao;
//	}
//
//	public ITbPayoutInfoDao getTbPayOutInfoDao() {
//		return tbPayOutInfoDao;
//	}
//
//	public void setTbPayOutInfoDao(ITbPayoutInfoDao tbPayOutInfoDao) {
//		this.tbPayOutInfoDao = tbPayOutInfoDao;
//	}
//
//	public ITbPartmentDao getTbPartmentDao() {
//		return tbPartmentDao;
//	}
//
//	public void setTbPartmentDao(ITbPartmentDao tbPartmentDao) {
//		this.tbPartmentDao = tbPartmentDao;
//	}
//
//	public ITbMonthPayInfoDao getTbMonthPayInfoDao() {
//		return tbMonthPayInfoDao;
//	}
//
//	public void setTbMonthPayInfoDao(ITbMonthPayInfoDao tbMonthPayInfoDao) {
//		this.tbMonthPayInfoDao = tbMonthPayInfoDao;
//	}
//
//	public ITbYearPayInfoDao getTbYearPayInfoDao() {
//		return tbYearPayInfoDao;
//	}
//
//	public void setTbYearPayInfoDao(ITbYearPayInfoDao tbYearPayInfoDao) {
//		this.tbYearPayInfoDao = tbYearPayInfoDao;
//	}
//
//	public ITbMessageDao getTbMessageDao() {
//		return tbMessageDao;
//	}
//
//	public void setTbMessageDao(ITbMessageDao tbMessageDao) {
//		this.tbMessageDao = tbMessageDao;
//	}
//
//
//}

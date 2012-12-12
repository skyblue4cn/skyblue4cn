package cn.insurance.service.admin.impl;

import java.util.Date;

import cn.insurance.dao.ITbAccountDao;
import cn.insurance.dao.ITbMessageDao;
import cn.insurance.dao.ITbPartmentDao;
import cn.insurance.dao.ITbPayoutInfoDao;
import cn.insurance.model.TbAccount;
import cn.insurance.model.TbPartment;
import cn.insurance.model.TbPayOutInfo;
import cn.insurance.service.admin.IAdminAccountServ;
import cn.insurance.utils.CommonWords;

public class AdminAccountServ implements IAdminAccountServ{
	
	private ITbAccountDao tbAccountDao ;
	
	private ITbPayoutInfoDao tbPayOutInfoDao ;
	
	private ITbPartmentDao tbPartmentDao ;
	
	private ITbMessageDao tbMessageDao ;
	
	/*
	 * 根据帐户ID查询帐户信息
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbAccountServ#getAccountById(java.lang.Integer)
	 */
	public TbAccount getAccountById(Integer id){
		return tbAccountDao.getObjectInfoById(id) ;
	}
	

	/*
	 * 通过旅行社或部门名称查询该部门的账户信息
	 * (non-Javadoc)
	 * @see cn.insurance.service.admin.IAdminAccountServ#getAccountByPartmentId(java.lang.Integer)
	 */
	public TbAccount getAccountByPartmentId(Integer partmentId){
		return tbAccountDao.getAccountByPartmentId(partmentId);
	}
	
	
	/*
	 * 更新帐户设置 
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbAccountServ#updateAccountProperty(cn.insurance.model.TbAccount)
	 */
	public void updateAccountProperty(TbAccount tbAccount){
		TbAccount tbAccount_old = getAccountById(tbAccount.getId()) ;
		tbAccount_old.setIntPayTypeId(tbAccount.getIntPayTypeId()) ;
		tbAccount_old.setIntAcceptDays(tbAccount.getIntAcceptDays()) ;
		tbAccount_old.setDbeAcceptMaxMoney(tbAccount.getDbeAcceptMaxMoney()) ;
		tbAccount_old.setIntAccountState(tbAccount.getIntAccountState()) ;
		tbAccountDao.update(tbAccount_old) ;
	}
	

	/*
	 * 预存帐户收费
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbAccountServ#shouFeeForYuCun(cn.insurance.model.TbPayOutInfo)
	 */
	public void  shouFeeForYuCun(TbPayOutInfo tbPayOutInfo){
		/*先查询帐户余额*/
		TbAccount tbAccount = getAccountById(tbPayOutInfo.getIntAccountId()) ;
		/*记录之前的帐户余额*/
		tbPayOutInfo.setDbeCurResidual(tbAccount.getDbeResidual()) ;
		
		/*更新帐户*/
		tbAccount.setDbeResidual(tbAccount.getDbeResidual() + tbPayOutInfo.getDbePayoutNumber()) ;
		tbAccountDao.update(tbAccount) ;
		/*记录之后的帐户余额*/
		tbPayOutInfo.setDbeAftResidual(tbAccount.getDbeResidual()) ;
		/*记录时间和类型*/
		tbPayOutInfo.setIntType(CommonWords.payOutKind1) ;
		tbPayOutInfo.setDtePayoutTime(new Date()) ;
		/*添加支付记录*/
		tbPayOutInfoDao.create(tbPayOutInfo) ;
		
		/*往保险公司发送一条消息*/
		StringBuffer message = new StringBuffer() ;
		TbPartment tbPartment = tbPartmentDao.getObjectInfoById(tbAccount.getIntPartmentId()) ;
		message.append(tbPartment.getCompany().getStrPartmentName()).append("下的").append(tbPartment.getStrPartmentName())
				.append("存款").append(tbPayOutInfo.getDbePayoutNumber()).append("元，操作人：").append(tbPayOutInfo.getStrSaveUserName()) ;
		tbMessageDao.sendMessage(CommonWords.messageType2, 0, message.toString()) ;
		/*发送消息给该部门*/
		message = new StringBuffer() ;
		message.append("帐户存款").append(tbPayOutInfo.getDbePayoutNumber()).append("元，现有帐户余额：").append(tbAccount.getDbeResidual()).append("元");
		tbMessageDao.sendMessage(CommonWords.messageType2, tbAccount.getIntPartmentId(), message.toString()) ;
		
	}

	public ITbAccountDao getTbAccountDao() {
		return tbAccountDao;
	}

	public void setTbAccountDao(ITbAccountDao tbAccountDao) {
		this.tbAccountDao = tbAccountDao;
	}

	public ITbPayoutInfoDao getTbPayOutInfoDao() {
		return tbPayOutInfoDao;
	}

	public void setTbPayOutInfoDao(ITbPayoutInfoDao tbPayOutInfoDao) {
		this.tbPayOutInfoDao = tbPayOutInfoDao;
	}

	public ITbPartmentDao getTbPartmentDao() {
		return tbPartmentDao;
	}

	public void setTbPartmentDao(ITbPartmentDao tbPartmentDao) {
		this.tbPartmentDao = tbPartmentDao;
	}

	public ITbMessageDao getTbMessageDao() {
		return tbMessageDao;
	}

	public void setTbMessageDao(ITbMessageDao tbMessageDao) {
		this.tbMessageDao = tbMessageDao;
	}	
}

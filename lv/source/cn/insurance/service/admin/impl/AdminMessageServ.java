package cn.insurance.service.admin.impl;

import cn.insurance.dao.ITbApplyDao;
import cn.insurance.dao.ITbBillDao;
import cn.insurance.dao.ITbMessageDao;
import cn.insurance.model.PageBean;
import cn.insurance.service.admin.IAdminMessageServ;

public class AdminMessageServ implements IAdminMessageServ {
	
	
	private ITbMessageDao tbMessageDao ;
	
	private ITbBillDao tbBillDao ;
	
	private ITbApplyDao tbApplyDao ;
	
	/*
	 * (non-Javadoc)
	 * @see cn.insurance.service.admin.IAdminMessageServ#getMessageForBx(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getMessageForBx(PageBean pageBean, Integer messageType) {
		// TODO Auto-generated method stub
		return tbMessageDao.getMessageForBx(pageBean, messageType);
	}

	/*
	 * (non-Javadoc)
	 * @see cn.insurance.service.admin.IAdminMessageServ#getAllNeedApplyCount()
	 */
	public int getAllNeedApplyCount() {
		return tbApplyDao.getAllNeedApplyCount();
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see cn.insurance.service.admin.IAdminMessageServ#getNewBillNumberNeedSureByBx()
	 */
	public int getNewBillNumberNeedSureByBx() {
		return tbBillDao.getNewBillNumberNeedSureByBx();
	}
	
	public ITbMessageDao getTbMessageDao() {
		return tbMessageDao;
	}

	public void setTbMessageDao(ITbMessageDao tbMessageDao) {
		this.tbMessageDao = tbMessageDao;
	}


	public ITbBillDao getTbBillDao() {
		return tbBillDao;
	}


	public void setTbBillDao(ITbBillDao tbBillDao) {
		this.tbBillDao = tbBillDao;
	}


	public ITbApplyDao getTbApplyDao() {
		return tbApplyDao;
	}


	public void setTbApplyDao(ITbApplyDao tbApplyDao) {
		this.tbApplyDao = tbApplyDao;
	}

}

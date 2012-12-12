package cn.insurance.service.admin.impl;

import cn.insurance.dao.ITbPayoutInfoDao;
import cn.insurance.model.PageBean;
import cn.insurance.service.admin.IAdminYuCunServ;

public class AdminYuCunServ implements IAdminYuCunServ{
	
	private ITbPayoutInfoDao tbPayoutInfoDao ;
	
	/*
	 * 查询预存帐户的支付记录
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbPayOutInfoServ#getYuCunPayLogByAccountId(cn.insurance.model.PageBean, int, int)
	 */
	public PageBean getYuCunPayLogByAccountId(PageBean pageBean , int accountId, int type){
		return tbPayoutInfoDao.getYuCunPayLogByAccountId(pageBean, accountId, type) ;
	}

	public ITbPayoutInfoDao getTbPayoutInfoDao() {
		return tbPayoutInfoDao;
	}

	public void setTbPayoutInfoDao(ITbPayoutInfoDao tbPayoutInfoDao) {
		this.tbPayoutInfoDao = tbPayoutInfoDao;
	}
}

package cn.insurance.service.client.impl;

import cn.insurance.dao.ITbPayoutInfoDao;
import cn.insurance.model.PageBean;
import cn.insurance.service.client.IClientYuCunServ;

public class ClientYuCunServ implements IClientYuCunServ {
	
	private ITbPayoutInfoDao tbPayoutInfoDao ;
	
	/*
	 * (non-Javadoc)
	 * @see cn.insurance.service.client.IClientYuCunServ#getYuCunPayLogByAccountId(cn.insurance.model.PageBean, int, int)
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

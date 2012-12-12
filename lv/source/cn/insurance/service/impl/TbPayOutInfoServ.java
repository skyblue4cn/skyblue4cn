package cn.insurance.service.impl;

import cn.insurance.dao.ITbPayoutInfoDao;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbPayOutInfo;
import cn.insurance.service.ITbPayOutInfoServ;

public class TbPayOutInfoServ implements ITbPayOutInfoServ {
	
	private ITbPayoutInfoDao tbPayOutInfoDao ;
	
	
	/*
	 * 添加帐户支出记录
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbPayOutInfoServ#addPayOuntInfo(cn.insurance.model.TbPayOutInfo)
	 */
	public void addPayOuntInfo(TbPayOutInfo tbPayOutInfo) {
		// TODO Auto-generated method stub
			
		tbPayOutInfoDao.create(tbPayOutInfo) ;
	}

	/*
	 * 查询预存帐户的支付记录
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbPayOutInfoServ#getYuCunPayLogByAccountId(cn.insurance.model.PageBean, int, int)
	 */
	public PageBean getYuCunPayLogByAccountId(PageBean pageBean , int accountId, int type){
		return tbPayOutInfoDao.getYuCunPayLogByAccountId(pageBean, accountId, type) ;
	}
	
	
	
	
	
	
	

	public ITbPayoutInfoDao getTbPayOutInfoDao() {
		return tbPayOutInfoDao;
	}


	public void setTbPayOutInfoDao(ITbPayoutInfoDao tbPayOutInfoDao) {
		this.tbPayOutInfoDao = tbPayOutInfoDao;
	}

}

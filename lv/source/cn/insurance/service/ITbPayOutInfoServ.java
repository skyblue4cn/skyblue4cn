package cn.insurance.service;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbPayOutInfo;

public interface ITbPayOutInfoServ {
	
	/**
	 * 添加帐户支出记录
	 * @param tbPayOutInfo
	 */
	public void addPayOuntInfo(TbPayOutInfo tbPayOutInfo) ;
	
	
	/**
	 * 查询预存帐户的记录
	 * type表示记录的类型（存 和 支付）
	 * @param pageBean
	 * @param accountId
	 * @param type
	 * @return
	 */
	public PageBean getYuCunPayLogByAccountId(PageBean pageBean , int accountId, int type) ;
	
	
}

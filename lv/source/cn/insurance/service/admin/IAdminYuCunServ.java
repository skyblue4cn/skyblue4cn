package cn.insurance.service.admin;

import cn.insurance.model.PageBean;

public interface IAdminYuCunServ {
	
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

package cn.insurance.service.admin;

import cn.insurance.model.PageBean;

public interface IAdminMessageServ {
	
	

	/**
	 * 按类型查询保险公司的消息 
	 * @return
	 */
	public PageBean getMessageForBx(PageBean pageBean ,Integer messageType) ;
	
	/**
	 * 查询新的保单数
	 * @return
	 */
	public int getNewBillNumberNeedSureByBx() ;
	
	/**
	 * 查询新的批改申请
	 * @return
	 */
	public int getAllNeedApplyCount() ;
	
	
}

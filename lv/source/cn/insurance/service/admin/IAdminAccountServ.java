package cn.insurance.service.admin;

import cn.insurance.model.TbAccount;
import cn.insurance.model.TbPayOutInfo;

public interface IAdminAccountServ {
	

	/**
	 * 通过旅行社或部门名称查询该部门的账户信息
	 * @param partmentId
	 * @return
	 */
	public TbAccount getAccountByPartmentId(Integer partmentId);
	
	
	/**
	 * 通过帐户ID查询帐户信息
	 * @param id
	 * @return
	 */
	public TbAccount getAccountById(Integer id) ;
	
	/**
	 * 更新帐户设置
	 * @param tbAccount
	 */
	public void updateAccountProperty(TbAccount tbAccount) ;
	
	
	
	/**
	 * 预存帐户收费
	 */
	public void  shouFeeForYuCun(TbPayOutInfo tbPayOutInfo) ;
}

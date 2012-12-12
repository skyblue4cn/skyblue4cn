package cn.insurance.service.client;

import cn.insurance.model.TbAccount;

public interface IClientAccountServ {
	
	/**
	 * 根据帐户ID查询帐户信息
	 * @param id
	 * @return
	 */
	public TbAccount getAccountById(Integer id) ;
}

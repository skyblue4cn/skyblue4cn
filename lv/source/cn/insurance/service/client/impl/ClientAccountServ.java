package cn.insurance.service.client.impl;

import cn.insurance.dao.ITbAccountDao;
import cn.insurance.model.TbAccount;
import cn.insurance.service.client.IClientAccountServ;

public class ClientAccountServ implements IClientAccountServ{
	
	private ITbAccountDao tbAccountDao ;
	
	/*
	 * (non-Javadoc)
	 * @see cn.insurance.service.client.IClientAccountServ#getAccountById(java.lang.Integer)
	 */
	public TbAccount getAccountById(Integer id){
		return tbAccountDao.getObjectInfoById(id) ;
	}
	
	
	public ITbAccountDao getTbAccountDao() {
		return tbAccountDao;
	}

	public void setTbAccountDao(ITbAccountDao tbAccountDao) {
		this.tbAccountDao = tbAccountDao;
	}
	
}

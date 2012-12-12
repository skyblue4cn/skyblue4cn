package cn.insurance.service.impl;

import java.util.List;

import cn.insurance.dao.ITbRoleDao;
import cn.insurance.model.right.TbRole;
import cn.insurance.service.ITbRoleServ;

public class TbRoleServ implements ITbRoleServ {

	private ITbRoleDao tbRoleDao ;
	
	
	public List<TbRole> getTbRoleListByTypeId(Integer userTypeId) {
		// TODO Auto-generated method stub
		return tbRoleDao.getAllObjectListByType(userTypeId);
	}


	public ITbRoleDao getTbRoleDao() {
		return tbRoleDao;
	}


	public void setTbRoleDao(ITbRoleDao tbRoleDao) {
		this.tbRoleDao = tbRoleDao;
	}



}

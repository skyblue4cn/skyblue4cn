package cn.insurance.service.admin.impl;

import java.util.List;

import cn.insurance.commonwords.RoleKey;
import cn.insurance.dao.ITbRoleDao;
import cn.insurance.model.right.TbRole;
import cn.insurance.service.admin.IAdminRoleServ;

public class AdminRoleServ implements IAdminRoleServ {
	
	private ITbRoleDao tbRoleDao ;
	
	/*
	 * 查询保险公司内部的所有角色
	 * (non-Javadoc)
	 * @see cn.insurance.service.admin.IAdminRoleServ#getAllBxRoleList()
	 */
	public List<TbRole> getAllBxRoleList() {
		return tbRoleDao.getAllObjectListByType(RoleKey.BX_ROLE_TYPE);
	}
	
	/*
	 * 查询旅行社的角色
	 * (non-Javadoc)
	 * @see cn.insurance.service.admin.IAdminRoleServ#getAllLxsRoleList()
	 */
	public List<TbRole> getAllLxsRoleList() {
		return tbRoleDao.getAllObjectListByType(RoleKey.LXS_ROLE_TYPE);
	}
	
	/*
	 * 添加
	 * (non-Javadoc)
	 * @see cn.insurance.service.admin.IAdminRoleServ#addTbRole(cn.insurance.model.TbRole)
	 */
	public int addTbRole(TbRole tbRole) {
		return tbRoleDao.create(tbRole) ;
	}

	/*
	 * 添加
	 * (non-Javadoc)
	 * @see cn.insurance.service.admin.IAdminRoleServ#updateTbRole(cn.insurance.model.TbRole)
	 */
	public int updateTbRole(TbRole tbRole) {
		return tbRoleDao.update(tbRole);
	}
	
	/*
	 * 通过Id查询
	 * (non-Javadoc)
	 * @see cn.insurance.service.admin.IAdminRoleServ#getRoleById(java.lang.Integer)
	 */
	public TbRole getRoleById(Integer roleId){
		return tbRoleDao.getObjectInfoById(roleId) ;
	}
	
	
	
	public ITbRoleDao getTbRoleDao() {
		return tbRoleDao;
	}

	public void setTbRoleDao(ITbRoleDao tbRoleDao) {
		this.tbRoleDao = tbRoleDao;
	}



}

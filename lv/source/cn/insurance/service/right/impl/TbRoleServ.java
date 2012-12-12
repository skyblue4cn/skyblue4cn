package cn.insurance.service.right.impl;

import java.util.List;

import cn.insurance.dao.right.ITbRoleDao;
import cn.insurance.model.right.TbRole;
import cn.insurance.service.right.ITbRoleServ;

public class TbRoleServ implements ITbRoleServ {
	
	private ITbRoleDao tbRoleDao ;
	
	/*
	 * 添加
	 * (non-Javadoc)
	 * @see cn.insurance.service.right.ITbRoleServ#addRole(cn.insurance.model.right.TbRole)
	 */
	public int addRole(TbRole tbRole) {
		return tbRoleDao.addTbRole(tbRole);
	}

	/*
	 * 更新
	 * (non-Javadoc)
	 * @see cn.insurance.service.right.ITbRoleServ#updateRole(cn.insurance.model.right.TbRole)
	 */
	public int updateRole(TbRole tbRole) {
		return tbRoleDao.updateTbRole(tbRole);
	}
	
	/*
	 * 根据ID查询角色
	 * (non-Javadoc)
	 * @see cn.insurance.service.right.ITbRoleServ#getTbRoleById(int)
	 */
	public TbRole getTbRoleById(int id){
		return tbRoleDao.getTbRoleById(id) ;
	}
	
	/*
	 * 查询所有保险公司的角色
	 * (non-Javadoc)
	 * @see cn.insurance.service.right.ITbRoleServ#getAllBxRoleList()
	 */
	public List<TbRole> getAllBxRoleList() {
		return tbRoleDao.getRoleListByType(TbRole.BX_ROLE_TYPE);
	}

	/*
	 * 查询所有旅行社的角色
	 * (non-Javadoc)
	 * @see cn.insurance.service.right.ITbRoleServ#getAllLxsRoleList()
	 */
	public List<TbRole> getAllLxsRoleList() {
		return tbRoleDao.getRoleListByType(TbRole.LXS_ROLE_TYPE);
	}
	
	/*
	 * 
	 * (non-Javadoc)
	 * @see cn.insurance.service.right.ITbRoleServ#getDeleteRoleUserNum(int)
	 */
	public int getDeleteRoleUserNum(int roleId) {
		return tbRoleDao.getDeleteRoleUserNum(roleId);
	}
	
	/*
	 * 删除角色
	 * (non-Javadoc)
	 * @see cn.insurance.service.right.ITbRoleServ#deleteRoleById(int)
	 */
	public int deleteRoleById(int roleId) {
		return tbRoleDao.deleteRoleById(roleId);
	}

	
	public ITbRoleDao getTbRoleDao() {
		return tbRoleDao;
	}

	public void setTbRoleDao(ITbRoleDao tbRoleDao) {
		this.tbRoleDao = tbRoleDao;
	}



}

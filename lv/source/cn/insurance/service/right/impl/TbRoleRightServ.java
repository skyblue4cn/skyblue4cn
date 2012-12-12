package cn.insurance.service.right.impl;

import java.util.List;

import cn.insurance.dao.right.ITbRoleRightDao;
import cn.insurance.model.right.TbRoleRight;
import cn.insurance.service.right.ITbRoleRightServ;

public class TbRoleRightServ implements ITbRoleRightServ{
	
	private ITbRoleRightDao tbRoleRightDao ;
	
	/*
	 * 给角色分配权限
	 * 1. 删除掉该角色以前的权限
	 * 2. 添加新的权限
	 * (non-Javadoc)
	 * @see cn.insurance.service.right.ITbRoleRightServ#assignRoleRight(int, int[])
	 */
	public int assignRoleRight(int roleId, int[] rightIds) {
		//删除掉该角色以前的权限
		tbRoleRightDao.deleteRoleRight(roleId) ;
		//如果有新的权限，则添加新的权限，如果没有，则不添加
		if(rightIds == null || rightIds.length==0){
			return 1 ;
		}
		for(int rightId : rightIds){
			TbRoleRight tbRoleRight = new TbRoleRight();
			tbRoleRight.setIntRightId(rightId) ;
			tbRoleRight.setIntRoleId(roleId) ;
			tbRoleRightDao.addRoleRight(tbRoleRight) ;
		}
		return 1;
	}
	
	/*
	 * 按角色Id查询该角色的所有权限
	 * (non-Javadoc)
	 * @see cn.insurance.service.right.ITbRoleRightServ#getRoleRightListByRoleId(java.lang.Integer)
	 */
	public List<TbRoleRight> getRoleRightListByRoleId(Integer roleId) {
		return tbRoleRightDao.getRoleRightListByRoleId(roleId);
	}

	public ITbRoleRightDao getTbRoleRightDao() {
		return tbRoleRightDao;
	}

	public void setTbRoleRightDao(ITbRoleRightDao tbRoleRightDao) {
		this.tbRoleRightDao = tbRoleRightDao;
	}

}

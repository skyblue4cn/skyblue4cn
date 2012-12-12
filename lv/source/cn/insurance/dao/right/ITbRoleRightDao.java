package cn.insurance.dao.right;

import java.util.List;

import cn.insurance.model.right.TbRoleRight;

public interface ITbRoleRightDao {
	
	/**
	 * 添加角色权限
	 * @param tbRoleRight
	 * @return
	 */
	public int addRoleRight(TbRoleRight tbRoleRight) ;

	/**
	 * 按角色删除角色权限
	 * @param roleId
	 * @return
	 */
	public int deleteRoleRight(Integer roleId) ;
	
	/**
	 * 通过角色ID查询角色的权限
	 * @param roleId
	 * @return
	 */
	public List<TbRoleRight> getRoleRightListByRoleId(Integer roleId) ;
	
	
}

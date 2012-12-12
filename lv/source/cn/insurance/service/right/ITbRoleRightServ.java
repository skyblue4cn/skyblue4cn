package cn.insurance.service.right;

import java.util.List;

import cn.insurance.model.right.TbRoleRight;

public interface ITbRoleRightServ {
	
	/**
	 * 给角色分配权限
	 * @param roleId
	 * @param rightIds
	 * @return
	 */
	public int assignRoleRight(int roleId ,int[] rightIds) ;
	
	/**
	 * 通过角色ID查询角色的权限
	 * @param roleId
	 * @return
	 */
	public List<TbRoleRight> getRoleRightListByRoleId(Integer roleId) ;
}

package cn.insurance.dao.right;

import java.util.List;

import cn.insurance.model.right.TbRole;

public interface ITbRoleDao {
	
	/**
	 * 添加角色
	 * @param tbRole
	 * @return
	 */
	public int addTbRole(TbRole tbRole) ;
	
	/**
	 * 查询角色
	 * @param tbRole
	 * @return
	 */
	public int updateTbRole(TbRole tbRole) ;
	
	
	/**
	 * 根据ID查询角色
	 * @return
	 */
	public TbRole getTbRoleById(int id) ;
	
	/**
	 * 通过角色类型查询该类型的所有角色
	 * @param roleType
	 * @return
	 */
	public List<TbRole> getRoleListByType(int roleType) ;
	
	/**
	 * 删除角色前，查询有多少用户是这个角色
	 * @param roleId
	 * @return
	 */
	public int getDeleteRoleUserNum(int roleId) ;
	
	/**
	 * 按角色ID删除角色
	 * @param roleId
	 * @return
	 */
	public int deleteRoleById(int roleId) ;
	
	
}

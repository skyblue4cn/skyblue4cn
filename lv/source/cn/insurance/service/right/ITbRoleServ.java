package cn.insurance.service.right;

import java.util.List;

import cn.insurance.model.TbNote;
import cn.insurance.model.right.TbRole;

public interface ITbRoleServ {
	
	/**
	 * 添加角色
	 * @param tbRole
	 * @return
	 */
	public int addRole(TbRole tbRole) ;
	
	/**
	 * 添加角色
	 * @param tbRole
	 * @return
	 */
	public int updateRole(TbRole tbRole) ;
	
	/**
	 * 根据ID查询角色
	 * @return
	 */
	public TbRole getTbRoleById(int id) ;
	
	/**
	 * 所有保险公司的角色
	 * @return
	 */
	public List<TbRole> getAllBxRoleList() ;
	
	/**
	 * 所有旅行社的角色
	 * @return
	 */
	public List<TbRole> getAllLxsRoleList();
	
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

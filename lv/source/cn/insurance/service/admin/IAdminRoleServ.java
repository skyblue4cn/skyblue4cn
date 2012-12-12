package cn.insurance.service.admin;

import cn.insurance.model.right.TbRole;

import java.util.List;

public interface IAdminRoleServ {
	
	/**
	 * 查询所有保险公司内部的角色
	 * 
	 * @return
	 */
	public List<TbRole> getAllBxRoleList() ;
	
	/**
	 * 查询所有旅行社的角色 
	 * 
	 * @return
	 */
	public List<TbRole> getAllLxsRoleList() ;
	
	/**
	 * 添加角色
	 * @param tbRole
	 * @return
	 */
	public int addTbRole(TbRole tbRole) ;
	
	/**
	 * 
	 * 修改角色
	 * @param tbRole
	 * @return
	 */
	public int updateTbRole(TbRole tbRole) ;
	
	/**
	 * 通过角色id查询角色信息
	 * @param roleId
	 * @return
	 */
	public TbRole getRoleById(Integer roleId) ;
	
 	
}

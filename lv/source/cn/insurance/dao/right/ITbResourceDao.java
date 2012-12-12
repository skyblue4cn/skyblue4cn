package cn.insurance.dao.right;

import java.util.List;

import cn.insurance.model.right.TbResource;

public interface ITbResourceDao {
	
	/**
	 * 添加资源
	 * @param tbResource
	 * @return
	 */
	public int addResource(TbResource tbResource) ;
	
	/**
	 * 更新权限资源
	 * @param tbResource
	 * @return
	 */
	public int updateResource(TbResource tbResource) ;
	
	/**
	 * 查询所有的权限资源
	 * @return
	 */
	public List<TbResource> getAllResourceList() ;
	
	/**
	 * 查询所有保险公司的权限资源
	 * @return
	 */
	public List<TbResource> getBxResourceList() ;
	
	/**
	 * 查询所有旅行社的权限资源
	 * @return
	 */
	public List<TbResource> getLxsResourceList();
	
	/**
	 * 通过角色查询该角色的所有权限资源
	 * @param roleId
	 * @return
	 */
	public List<TbResource> getResourceListByRoleId(int roleId) ;
	
	
}

package cn.insurance.service.right;

import java.util.List;
import java.util.Map;

import cn.insurance.model.right.TbResource;

public interface ITbResourceServ {

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
	public Map<String, Integer> getAllResourceMap() ;
	
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
	public Map<String, Integer> getResourceMapByRoleId(int roleId) ;
}

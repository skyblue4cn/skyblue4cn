package cn.insurance.service.right.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.insurance.dao.right.ITbResourceDao;
import cn.insurance.model.right.TbResource;
import cn.insurance.service.right.ITbResourceServ;

public class TbResourceServ implements ITbResourceServ{
	
	private ITbResourceDao tbResourceDao ;
	
	private static Map<String, Integer> allResouceMap ;
	
	/*
	 * 添加
	 * (non-Javadoc)
	 * @see cn.insurance.service.right.ITbResourceServ#addResource(cn.insurance.model.right.TbResource)
	 */
	public int addResource(TbResource tbResource) {
		tbResourceDao.addResource(tbResource) ;
		reloadAllResource() ;
		return 1 ;
	}
	
	/*
	 * 更新
	 * (non-Javadoc)
	 * @see cn.insurance.service.right.ITbResourceServ#updateResource(cn.insurance.model.right.TbResource)
	 */
	public int updateResource(TbResource tbResource) {
		tbResourceDao.updateResource(tbResource) ;
		reloadAllResource() ;
		return 1 ;
	}
	
	/*
	 * 查询所有的权限资源
	 * (non-Javadoc)
	 * @see cn.insurance.service.right.ITbResourceServ#getAllResourceList()
	 */
	public Map<String, Integer> getAllResourceMap(){
		if(allResouceMap == null){
			reloadAllResource();
		}
		return allResouceMap;
	}
	
	/**
	 * 重新加载所有的资源
	 */
	private void reloadAllResource(){
		allResouceMap = new HashMap<String, Integer>();
		//查询所有的资源
		List<TbResource> resourceList = tbResourceDao.getAllResourceList();
		if(resourceList != null && resourceList.size() >0){
			for(TbResource tbResource : resourceList){
				allResouceMap.put(tbResource.getStrActionName().trim(), tbResource.getId()) ;
			}
		}
	}

	
	/*
	 * 查询所有保险公司的权限资源
	 * (non-Javadoc)
	 * @see cn.insurance.service.right.ITbResourceServ#getBxResourceList()
	 */
	public List<TbResource> getBxResourceList() {
		return tbResourceDao.getBxResourceList();
	}

	/*
	 * 查询所有旅行社的权限资源
	 * (non-Javadoc)
	 * @see cn.insurance.service.right.ITbResourceServ#getLxsResourceList()
	 */
	public List<TbResource> getLxsResourceList() {
		return tbResourceDao.getLxsResourceList();
	}

	/*
	 * 通过角色ID查询该角色的所有权限资源
	 * (non-Javadoc)
	 * @see cn.insurance.service.right.ITbResourceServ#getResourceListByRoleId(int)
	 */
	public Map<String, Integer> getResourceMapByRoleId(int roleId){
		Map<String, Integer> userResourceMap = new HashMap<String, Integer>() ;
		List<TbResource> resourceList = tbResourceDao.getResourceListByRoleId(roleId) ;
		if(resourceList != null && resourceList.size() >0){
			for(TbResource tbResource : resourceList){
				userResourceMap.put(tbResource.getStrActionName().trim(), tbResource.getId()) ;
			}
		}
		return userResourceMap ;
	}

	public ITbResourceDao getTbResourceDao() {
		return tbResourceDao;
	}

	public void setTbResourceDao(ITbResourceDao tbResourceDao) {
		this.tbResourceDao = tbResourceDao;
	}


}

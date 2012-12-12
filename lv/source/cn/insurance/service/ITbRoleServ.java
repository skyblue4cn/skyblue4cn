package cn.insurance.service;

import java.util.List;

import cn.insurance.model.right.TbRole;

public interface ITbRoleServ {
	
	/**
	 * 根据用户类型（保险公司人员或旅行社人员）来查询所有可以设定的角色
	 * @param userTypeId
	 * @return
	 */
	public List<TbRole> getTbRoleListByTypeId(Integer userTypeId) ;
	
}

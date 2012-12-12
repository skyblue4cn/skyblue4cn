package cn.insurance.dao;

import java.util.List;

import cn.insurance.model.right.TbRole;

public interface ITbRoleDao {
	
	/**
	 * 添加角色
	 * @param tbRole
	 * @return
	 */
	public int create(TbRole tbRole) ;
	
	/**
	 * 更新
	 * @param obj
	 * @return
	 */
	public int update(TbRole tbRole) ;
	
	/**
	 * 根据分类查询角色
	 * @param obj
	 * @return
	 */
	public List getAllObjectListByType(Integer type) ;

	/**
	 * 根据ID查询角色
	 * @param id
	 * @return
	 */
	public TbRole getObjectInfoById(Integer id) ;


	/**
	 * 根据ID删除角色
	 * @param id
	 * @return
	 */
	public int delete(Integer id)  ;
}

package cn.insurance.dao;

import java.util.List;

import cn.insurance.model.TbAccount;

public interface ITbAccountDao {
	/**
	 * 添加
	 * @param obj
	 * @return
	 */
	public int create(TbAccount tbAccount) ;
	
	/**
	 * 更新
	 * @param obj
	 * @return
	 */
	public TbAccount update(TbAccount tbAccount) ;
	
	/**
	 * 查询全部
	 * @return
	 */
	public List<TbAccount> getAllObjectList() ;
	
//	/**
//	 * 按条件查询,实现的时侯根据参数的类型来具体实现查询
//	 * @return
//	 */
//	public List<TbAccount> getAllObjectListByCondition(Object obj) ;
//	
	/**
	 * 通过部门ID查询该部门的账户信息
	 */
	public TbAccount getAccountByPartmentId(Integer partmentId) ;
	
	/**
	 * 根据ID查找对象
	 * @param id
	 * @return
	 */
	public TbAccount getObjectInfoById(Integer id) ;
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public int delete(Integer id) ;
	
	/**
	 * 根据部门ID删除帐户
	 * @param partmentId
	 * @return
	 */
	public int deleteByPartmentId(Integer partmentId) ;
}

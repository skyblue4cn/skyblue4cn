package cn.insurance.dao.right;

import java.util.List;

import cn.insurance.model.right.TbRight;

public interface ITbRightDao {
	
	/**
	 * 添加权限
	 * @param tbRight
	 * @return
	 */
	public int addTbRight(TbRight tbRight) ;
	
	
	/**
	 * 修改权限
	 * @param tbRight
	 * @return
	 */
	public int updateTbRight(TbRight tbRight) ;
	
	/**
	 * 按id查询权限
	 * @param id
	 * @return
	 */
	public TbRight getRightById(Integer id) ;
	
	/**
	 * 通过权限类型来查询该类型的所有权限
	 * @param type
	 * @return
	 */
	public List<TbRight> getRightListByType(int type) ;
	
}

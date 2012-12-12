package cn.insurance.service.right;

import java.util.List;

import cn.insurance.model.right.TbRight;

public interface ITbRightServ {
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
	 * 查询所有保险公司的权限
	 * @return
	 */
	public List<TbRight> getAllBxRightList() ;
	
	/**
	 * 查询所有旅行社的权限设定
	 * @return
	 */
	public List<TbRight> getAllLxsRightList();
}

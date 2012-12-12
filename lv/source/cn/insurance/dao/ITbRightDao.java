package cn.insurance.dao;

import cn.insurance.model.right.TbRight;

public interface ITbRightDao {
	
	/**
	 * 按权限ID查询权限
	 * @param rightId
	 * @return
	 */
	public TbRight getTbRightById(Integer rightId) ;
	
	
}

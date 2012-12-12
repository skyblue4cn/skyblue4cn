package cn.insurance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.insurance.dao.BaseDao;
import cn.insurance.dao.ITbRightDao;
import cn.insurance.model.right.TbRight;

public class TbRightDao extends BaseDao implements ITbRightDao {

	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * 按Id查询权限
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbRightDao#getTbRightById(java.lang.Integer)
	 */
	public TbRight getTbRightById(Integer id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbRight WHERE id=" + id ;
		return (TbRight)super.queryByObj(sql);
	}
	
}

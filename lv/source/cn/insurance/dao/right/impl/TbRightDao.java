package cn.insurance.dao.right.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.insurance.dao.BaseDao;
import cn.insurance.dao.right.ITbRightDao;
import cn.insurance.model.right.TbRight;

public class TbRightDao extends BaseDao implements ITbRightDao{

	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		TbRight tbRight = new TbRight() ;
		tbRight.setId(rs.getInt("id"));
		tbRight.setIntRightType(rs.getInt("intRightType"));
		tbRight.setStrDesc(rs.getString("strDesc"));
		tbRight.setStrName(rs.getString("strName"));
		return tbRight;
	}
	
	/*
	 * 添加
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITbRightDao#addTbRight(cn.insurance.model.right.TbRight)
	 */
	public int addTbRight(TbRight tbRight) {
		String sql = "INSERT INTO tbright(strName,intRightType,strDesc)VALUES(?,?,?)" ;
		Object[] objs = {
				tbRight.getStrName(),
				tbRight.getIntRightType(),
				tbRight.getStrDesc()
		} ;
		return super.jdbcTemplate.update(sql, objs);
	}

	/*
	 * 修改
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITbRightDao#updateTbRight(cn.insurance.model.right.TbRight)
	 */
	public int updateTbRight(TbRight tbRight) {
		String sql = "UPDATE tbright SET strName=?,intRightType=?,strDesc=? WHERE id=?" ;
		Object[] objs = {
				tbRight.getStrName(),
				tbRight.getIntRightType(),
				tbRight.getStrDesc(),
				tbRight.getId()
		} ;
		return super.jdbcTemplate.update(sql, objs);
	}

	/*
	 * 按类型查询
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITbRightDao#getRightListByType(int)
	 */
	public List<TbRight> getRightListByType(int type){
		String sql = "SELECT * FROM tbright WHERE intRightType=" + type ;
		return super.query(sql) ;
	}

	/*
	 * 按ID查询
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITbRightDao#getRightById(java.lang.Integer)
	 */
	public TbRight getRightById(Integer id) {
		String sql = "SELECT * FROM tbright WHERE id="+ id ;
		return (TbRight)super.queryByObj(sql);
	}
}

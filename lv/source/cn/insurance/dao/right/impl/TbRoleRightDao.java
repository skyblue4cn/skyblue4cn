package cn.insurance.dao.right.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.insurance.dao.BaseDao;
import cn.insurance.dao.right.ITbRoleRightDao;
import cn.insurance.model.right.TbRoleRight;

public class TbRoleRightDao extends BaseDao implements ITbRoleRightDao {

	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		TbRoleRight tbRoleRight = new TbRoleRight();
		tbRoleRight.setId(rs.getInt("id"));
		tbRoleRight.setIntRightId(rs.getInt("intRightId"));
		tbRoleRight.setIntRoleId(rs.getInt("intRoleId"));
		return tbRoleRight;
	}
	
	/*
	 * 添加角色权限
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITbRoleRightDao#addRoleRight(cn.insurance.model.right.TbRoleRight)
	 */
	public int addRoleRight(TbRoleRight tbRoleRight) {
		String sql = "INSERT INTO tbroleright(intRoleId,intRightId)VALUES(?,?)" ;
		Object[] objs = {
				tbRoleRight.getIntRoleId() ,
				tbRoleRight.getIntRightId() 
		};
		return super.jdbcTemplate.update(sql, objs);
	}
	
	/*
	 * 删除角色权限
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITbRoleRightDao#deleteRoleRight(java.lang.Integer)
	 */
	public int deleteRoleRight(Integer roleId) {
		String sql = "DELETE FROM tbroleright WHERE intRoleId=" + roleId ;
		return super.update(sql);
	}
	
	/*
	 * 按角色ID查询权限
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITbRoleRightDao#getRoleRightListByRoleId(java.lang.Integer)
	 */
	public List<TbRoleRight> getRoleRightListByRoleId(Integer roleId) {
		String sql ="SELECT * FROM tbroleright WHERE intRoleId=" + roleId ;
		return super.query(sql);
	}

}

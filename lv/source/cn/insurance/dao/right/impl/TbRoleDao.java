package cn.insurance.dao.right.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.insurance.dao.BaseDao;
import cn.insurance.dao.right.ITbRoleDao;
import cn.insurance.model.right.TbRole;

public class TbRoleDao extends BaseDao implements ITbRoleDao{

	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		TbRole  tbRole = new TbRole();
		tbRole.setId(rs.getInt("id"));
		tbRole.setIntTypeId(rs.getInt("intTypeId"));
		tbRole.setStrRoleName(rs.getString("strRoleName"));
		return tbRole;
	}
	
	/*
	 * 添加
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITbRoleDao#addTbRole(cn.insurance.model.right.TbRole)
	 */
	public int addTbRole(TbRole tbRole) {
		String sql = "INSERT INTO tbrole(strRoleName,intTypeId)VALUES(?,?)" ;
		Object[] objs = {
				tbRole.getStrRoleName() ,
				tbRole.getIntTypeId()
		} ;
		return super.jdbcTemplate.update(sql , objs);
	}

	
	/*
	 * 更新
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITbRoleDao#updateTbRole(cn.insurance.model.right.TbRole)
	 */
	public int updateTbRole(TbRole tbRole) {
		String sql = "UPDATE tbrole set strRoleName=?,intTypeId=? WHERE id=?" ;
		Object[] objs = {
				tbRole.getStrRoleName() ,
				tbRole.getIntTypeId(),
				tbRole.getId() 
		} ;
		return super.jdbcTemplate.update(sql , objs);
	}
	
	/*
	 * 根据id查询
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITbRoleDao#getTbRoleById(int)
	 */
	public TbRole getTbRoleById(int id) {
		String sql ="SELECT * FROM tbrole WHERE id=" + id ;
		return (TbRole)super.queryByObj(sql);
	}
	
	/*
	 * 按类型查询
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITbRoleDao#getRoleListByType(int)
	 */
	public List<TbRole> getRoleListByType(int roleType) {
		String sql = "SELECT * FROM tbrole WHERE intTypeId=" + roleType ;
		return super.query(sql);
	}

	
	/*
	 * 删除角色前，查询有多少用户是这个角色
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITbRoleDao#getDeleteRoleUserNum(int)
	 */
	public int getDeleteRoleUserNum(int roleId){
		String sql = "SELECT COUNT(*) FROM tbuser WHERE intRoleId=" + roleId ;
		return super.jdbcTemplate.queryForInt(sql) ;
	}
	
	/*
	 * 按角色ID删除角色
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITbRoleDao#deleteRoleById(int)
	 */
	public int deleteRoleById(int roleId){
		//删除这个角色的所有权限
		String sql = "DELETE FROM tbroleright WHERE intRoleId=" + roleId ;
		super.update(sql) ;
		//删除这个角色
		sql = "DELETE FROM tbrole WHERE id=" + roleId;
		super.update(sql) ;
		return 1;
	}

}

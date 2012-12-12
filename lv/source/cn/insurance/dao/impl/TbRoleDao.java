package cn.insurance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.insurance.dao.BaseDao;
import cn.insurance.dao.ITbRoleDao;
import cn.insurance.model.right.TbRole;

public class TbRoleDao extends BaseDao implements  ITbRoleDao{
	
	
	
	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		TbRole tbRole = new TbRole() ;
		tbRole.setId(rs.getInt("id")) ;
		tbRole.setIntTypeId(rs.getInt("intTypeId")) ;
		tbRole.setStrRoleName(rs.getString("strRoleName")) ;
		return tbRole;
	}
	/*
	 * 添加角色
	 * (non-Javadoc)
	 * @see cn.insurance.dao.IDataDao#create(java.lang.Object)
	 */
	public int create(TbRole tbRole) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO tbRole(strRoleName,intTypeId) VALUES(?,?) " ;
		Object[] objs = {
				tbRole.getStrRoleName() ,
				tbRole.getIntTypeId(),
		} ;
		return super.jdbcTemplate.update(sql, objs);
	}
	/*
	 * 更新角色
	 * (non-Javadoc)
	 * @see cn.insurance.dao.IDataDao#update(java.lang.Object)
	 */
	public int update(TbRole tbRole) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tbRole SET strRoleName=?,intTypeId=? WHERE id=?" ;
		Object[] objs = {
				tbRole.getStrRoleName() ,
				tbRole.getIntTypeId(),
				tbRole.getId()
		} ;
		return super.jdbcTemplate.update(sql,objs);
	}


	/*
	 * 根据条件查询角色属性
	 * (non-Javadoc)
	 * @see cn.insurance.dao.IDataDao#getAllObjectListByCondition(java.lang.Object)
	 */
	public List getAllObjectListByType(Integer type) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbRole WHERE intTypeId=" + type ;
		return super.query(sql);
	}

	/*
	 * 更具角色ID查询角色
	 * (non-Javadoc)
	 * @see cn.insurance.dao.IDataDao#getObjectInfoById(java.lang.Integer)
	 */
	public TbRole getObjectInfoById(Integer id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbRole WHERE id=" + id ;
		return (TbRole)super.queryByObj(sql);
	}


	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

}

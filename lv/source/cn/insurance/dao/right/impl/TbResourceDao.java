package cn.insurance.dao.right.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.insurance.dao.BaseDao;
import cn.insurance.dao.right.ITbResourceDao;
import cn.insurance.dao.right.ITbRightDao;
import cn.insurance.model.right.TbResource;
import cn.insurance.model.right.TbRight;

public class TbResourceDao extends BaseDao implements ITbResourceDao{
	
	private ITbRightDao tbRightDao ;

	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		TbResource tbResource = new TbResource();
		tbResource.setId(rs.getInt("id"));
		tbResource.setIntRightId(rs.getInt("intRightId"));
		tbResource.setStrActionName(rs.getString("strActionName")) ;
		tbResource.setStrModuleName(rs.getString("strModuleName"));
		tbResource.setTbRight(tbRightDao.getRightById(tbResource.getIntRightId()));
		return tbResource;
	}
	
	/*
	 * 添加
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITbResourceDao#addResource(cn.insurance.model.right.TbResource)
	 */
	public int addResource(TbResource tbResource) {
		String sql = "INSERT INTO tbresource(intRightId,strActionName,strModuleName)VALUES(?,?,?)" ;
		Object[]  objs = {
				tbResource.getIntRightId(),
				tbResource.getStrActionName(),
				tbResource.getStrModuleName()
		} ;
		return super.jdbcTemplate.update(sql, objs) ;
	}
	
	/*
	 * 修改
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITbResourceDao#updateResource(cn.insurance.model.right.TbResource)
	 */
	public int updateResource(TbResource tbResource) {
		String sql="UPDATE tbresource set intRightId=?,strActionName=?,strModuleName=? WHERE id=?" ;
		Object[]  objs = {
			tbResource.getIntRightId(),
			tbResource.getStrActionName(),
			tbResource.getStrModuleName(),
			tbResource.getId()
		} ;
		return super.jdbcTemplate.update(sql, objs) ;
	}
	
	/*
	 * 查询所有的权限资源
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITbResourceDao#getAllResourceList()
	 */
	public List<TbResource> getAllResourceList() {
		String sql = "SELECT * FROM tbresource" ;
		return super.query(sql);
	}
	
	/*
	 * 查询所有保险公司的权限资源
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITbResourceDao#getBxResourceList()
	 */
	public List<TbResource> getBxResourceList() {
		String sql = "SELECT * FROM tbresource WHERE intRightId IN(SELECT id FROM tbright WHERE intRightType=" + TbRight.BX_RIGHT_TYPE+")" ;
		return super.query(sql);
	}

	/*
	 * 查询所有旅行社的权限资源
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITbResourceDao#getLxsResourceList()
	 */
	public List<TbResource> getLxsResourceList() {
		String sql = "SELECT * FROM tbresource WHERE intRightId IN(SELECT id FROM tbright WHERE intRightType=" + TbRight.LXS_RIGHT_TYPE+")" ;
		return super.query(sql);
	}
	
	/*
	 * 通过角色ID查询该角色的所有资源
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITbResourceDao#getResourceListByRoleId(int)
	 */
	public List<TbResource> getResourceListByRoleId(int roleId) {
		String sql = "SELECT * FROM tbresource WHERE intRightId IN(SELECT intRightId FROM tbroleright WHERE intRoleId=" + roleId+")" ; ;
		return super.query(sql);
	}

	public ITbRightDao getTbRightDao() {
		return tbRightDao;
	}

	public void setTbRightDao(ITbRightDao tbRightDao) {
		this.tbRightDao = tbRightDao;
	}

	

	
	
}

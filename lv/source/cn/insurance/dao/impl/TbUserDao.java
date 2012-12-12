package cn.insurance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;

import cn.insurance.commonwords.Md5Key;
import cn.insurance.dao.BaseDao;
import cn.insurance.dao.ITbPartmentDao;
import cn.insurance.dao.ITbRoleDao;
import cn.insurance.dao.ITbUserDao;
import cn.insurance.model.TbUser;
import cn.insurance.model.right.TbRole;
import cn.insurance.utils.CommonWords;

public class TbUserDao extends BaseDao implements ITbUserDao{
	
	private ITbRoleDao tbRoleDao ;
	
	private ITbPartmentDao tbPartmentDao ;
	
	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		TbUser tbUser = new TbUser() ;
		tbUser.setId(rs.getInt("id")) ;
		tbUser.setIntPartmentId(rs.getInt("intPartmentId")) ;
		if(tbUser.getIntPartmentId() ==0){
			tbUser.setIntPartmentId(null) ;
		}
		
		tbUser.setIntRoleId(rs.getInt("intRoleId")) ;
		tbUser.setStrEmail(rs.getString("strEmail")) ;
		tbUser.setStrPhoneNumber(rs.getString("strPhoneNumber")) ;
		tbUser.setStrUserLogName(rs.getString("strUserLogName")) ;
		tbUser.setStrUserPassword(rs.getString("strUserPassword")) ;
		tbUser.setStrUserName(rs.getString("strUserName")) ;
		tbUser.setIntUserState(rs.getInt("intUserState")) ;
//		tbUser.setIntUserType(rs.getInt("intUserType")) ;
		
		/*用户角色*/
		tbUser.setTbRole((TbRole)tbRoleDao.getObjectInfoById(tbUser.getIntRoleId())) ;
		
		/*用户所属部门*/
		if(tbUser.getIntPartmentId() != null){
			tbUser.setTbPartment(tbPartmentDao.getObjectInfoById(tbUser.getIntPartmentId())) ;
		}
		return tbUser;
	}
	
	/*添加*/
	public int create(TbUser tbUser) {
		/*对密码进行加密*/
		tbUser.setStrUserPassword(Md5Key.encodeString(tbUser.getStrUserPassword(), Md5Key.User_PASSWORD_KEY)) ;
		String sql = "INSERT INTO tbUser(strUserLogName,strUserPassword,intPartmentId,strUserName,strPhoneNumber,strEmail,intRoleId,intUserState)VALUES(?,?,?,?,?,?,?,?)" ;
		Object[] objs = {
				tbUser.getStrUserLogName(),
				tbUser.getStrUserPassword(),
				tbUser.getIntPartmentId(),
				tbUser.getStrUserName(),
				tbUser.getStrPhoneNumber(),
				tbUser.getStrEmail(),
				tbUser.getIntRoleId(),
				tbUser.getIntUserState() //默认的添加的用户都可以使用
		} ;
		return super.jdbcTemplate.update(sql, objs) ;
	}
	
	/*
	 * 更新用户信息
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbUserDao#update(cn.insurance.model.TbUser)
	 */
	public int update(TbUser tbUser) {
		/*如果密码为null，则不更新密码*/
		if(tbUser.getStrUserPassword() == null){
			String sql = "UPDATE tbUser SET strUserLogName=?,strUserName=?,strPhoneNumber=?,strEmail=?,intRoleId=?,intUserState=? WHERE id=?" ;
			Object[] objs = {
					tbUser.getStrUserLogName(),
					tbUser.getStrUserName(),
					tbUser.getStrPhoneNumber(),
					tbUser.getStrEmail(),
					tbUser.getIntRoleId(),
					tbUser.getIntUserState(),
					tbUser.getId()
			} ;
			return super.jdbcTemplate.update(sql, objs) ;
		}else{
			/*对密码进行加密*/
			tbUser.setStrUserPassword(Md5Key.encodeString(tbUser.getStrUserPassword(), Md5Key.User_PASSWORD_KEY)) ;
			String sql = "UPDATE tbUser SET strUserLogName=?,strUserPassword=?,strUserName=?,strPhoneNumber=?,strEmail=?,intRoleId=?,intUserState=? WHERE id=?" ;
			Object[] objs = {
					tbUser.getStrUserLogName(),
					tbUser.getStrUserPassword(),
					tbUser.getStrUserName(),
					tbUser.getStrPhoneNumber(),
					tbUser.getStrEmail(),
					tbUser.getIntRoleId(),
					tbUser.getIntUserState(),
					tbUser.getId()
			} ;
			return super.jdbcTemplate.update(sql, objs) ;
		}
	}
	
	/*
	 * 通过用户名查询所有的用户信息
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbUserDao#getUserListByUserName(java.lang.String)
	 */
	public List<TbUser> getUserListByUserName(String username){
		String sql = "SELECT * FROM tbUser WHERE strUserLogName='" + username +"'" ;
		return super.query(sql) ;
		
	}
	
	/*
	 * 查询系统全部用户
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbUserDao#getAllObjectList()
	 */
	public List<TbUser> getAllObjectList() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbUser" ;
		return super.query(sql);
	}

	/*
	 * 根据ID查询用户
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbUserDao#getObjectInfoById(java.lang.Integer)
	 */
	public TbUser getObjectInfoById(Integer id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbUser WHERE id=" + id ;
		return (TbUser)super.queryByObj(sql);
	}
	
	/*
	 * 根据ID删除用户
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbUserDao#delete(java.lang.Integer)
	 */
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tbUser WHERE id=" + id  ;
		return super.update(sql);
	}
	
	/*
	 * 验证用户名是否存在
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbUserDao#checkecUser(java.lang.String)
	 */
	public boolean checkecUser(String userName){
		String sql = "SELECT count(*) FROM tbUser WHERE strUserLogName='" + userName +"'" ;
		return super.checkData(sql) ;
	}
	
	/*
	 * 用户登录，验证用户是否存在
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbUserDao#userLogIn(cn.insurance.model.TbUser)
	 */
	public TbUser userLogIn(TbUser tbUser){
		/*先将密码加密*/
//		Md5PasswordEncoder m = new Md5PasswordEncoder() ;
//		tbUser.setStrUserPassword(m.encodePassword(tbUser.getStrUserPassword(), "xiaoma")) ;
		String sql = "SELECT * FROM tbUser WHERE strUserLogName=? " ;
		Object[] objs = {
			tbUser.getStrUserLogName()
		} ;
		Object obj = super.queryForObject(sql, objs) ;
		if(obj != null){
			return (TbUser)obj ;
		}
		return null ;
	}
	
	/*
	 * 根据部门ID查询所有的用户
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbUserDao#getTbUserListByPartmentId(java.lang.Integer)
	 */
	public List<TbUser> getTbUserListByPartmentId(Integer partmentId){
		String sql = "SELECT * FROM tbUser WHERE intPartmentId=" + partmentId ;
		return super.query(sql) ;
	}
	
	/*
	 * 查询保险公司所有的内部用户
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbUserDao#getAllBxUser()
	 */
	public List<TbUser> getAllBxUser(){
		String sql = "SELECT * FROM tbUser WHERE intPartmentId IS NULL" ;
		return super.query(sql) ;
	}
	
	
	public static void main(String [] args){
		Md5PasswordEncoder m = new Md5PasswordEncoder() ;
		System.out.println(m.encodePassword("123", "xiaoma")) ;
		
	}
	
	

	public ITbPartmentDao getTbPartmentDao() {
		return tbPartmentDao;
	}

	public void setTbPartmentDao(ITbPartmentDao tbPartmentDao) {
		this.tbPartmentDao = tbPartmentDao;
	}

	public ITbRoleDao getTbRoleDao() {
		return tbRoleDao;
	}

	public void setTbRoleDao(ITbRoleDao tbRoleDao) {
		this.tbRoleDao = tbRoleDao;
	}



}



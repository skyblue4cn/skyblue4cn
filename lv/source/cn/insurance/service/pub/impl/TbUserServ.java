package cn.insurance.service.pub.impl;

import cn.insurance.dao.ITbUserDao;
import cn.insurance.model.TbUser;
import cn.insurance.service.pub.ITbUserServ;
import cn.insurance.utils.CommonWords;

import java.util.List ;

public class TbUserServ implements ITbUserServ {

	private ITbUserDao tbUserDao  ;
	

	/*
	 * 通过用户名查询所有的用户
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbUserServ#getUserListByUserName(java.lang.String)
	 */
	public List<TbUser> getUserListByUserName(String username) {
		
		return tbUserDao.getUserListByUserName(username) ;
	}
	
	
	/*
	 * 添加用户
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbUserServ#create(cn.insurance.model.TbUser)
	 */
	public int addUser(TbUser tbUser) {
		/*先验证用户是否重名*/
		if(tbUserDao.checkecUser(tbUser.getStrUserLogName())){
			return 0 ;
		}
		/*初始密码都是6个1*/
		tbUser.setStrUserPassword("111111") ;
		tbUser.setIntUserState(1) ;
		tbUserDao.create(tbUser) ;
		return 1;

	}
	
	
	/*
	 * 更新用户信息
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbUserServ#update(cn.insurance.model.TbUser)
	 */
	public int update(TbUser tbUser) {
		// TODO Auto-generated method stub
		/*先看这个用户的用户名改变了没有*/
		TbUser user = tbUserDao.getObjectInfoById(tbUser.getId()) ;
		/*如果改变了，则要查看改变后的用户名是否已存在*/
		if(!user.getStrUserLogName().equals(tbUser.getStrUserLogName())){
			if(tbUserDao.checkecUser(tbUser.getStrUserLogName())){
				return 0 ;
			}
		}
		tbUserDao.update(tbUser) ;
		return 1 ;
		
	}

	/*
	 * 验证用户登录名和密码是否正确，返回用户信息
	 * 如果不正确，则会返回null值
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbUserServ#checkTheLogInfo(cn.insurance.model.TbUser)
	 */
	public TbUser checkTheLogInfo(TbUser tbUser){
		return  tbUserDao.userLogIn(tbUser) ;
		}
	
	/*
	 * 根据用户ID查看用户信息
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbUserServ#queryUserInfoById(java.lang.Integer)
	 */
	public TbUser getUserInfoById(Integer id) {
		// TODO Auto-generated method stub
		return tbUserDao.getObjectInfoById(id) ;
	}
	
	/*
	 * 
	 * 将用户设置为黑名单
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbUserServ#removeUser(java.lang.Integer)
	 */
	public void deleteUser(Integer userId){
		/*先试着删除用户，如果删不掉，则说明有关联数据，则将用户拖入黑名单*/
		try{
			/*删除用户*/
			tbUserDao.delete(userId) ;
		}catch(Exception e){
			/*如果删除出现异常，则说明该用户有关联数据*/
			TbUser tbUser = (TbUser)tbUserDao.getObjectInfoById(userId) ;
			//将用户设定为不可用
			tbUser.setIntUserState(0) ;
			tbUserDao.update(tbUser) ;
		}
	}
	
	/*
	 * 根据部门ID查询所有的用户
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbUserServ#getTbUserListByPartmentId(java.lang.Integer)
	 */
	public List<TbUser> getTbUserListByPartmentId(Integer intPartmentId) {
		// TODO Auto-generated method stub
		return tbUserDao.getTbUserListByPartmentId(intPartmentId) ;
	}

	/*
	 * 查询所有保险公司的内部用户
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbUserServ#getAllBxUser()
	 */
	public List<TbUser> getBxAllUser() {
		return tbUserDao.getAllBxUser() ;
	}


	public ITbUserDao getTbUserDao() {
		return tbUserDao;
	}


	public void setTbUserDao(ITbUserDao tbUserDao) {
		this.tbUserDao = tbUserDao;
	}



}

package cn.insurance.service.pub;

import java.util.List;

import cn.insurance.model.TbUser;

public interface ITbUserServ {
	
	/**
	 * 通过用户名查询所有的用户
	 * @param username
	 * @return
	 */
	public List<TbUser> getUserListByUserName(String username) ;
	
	
	/**
	 * 添加新用户
	 * @param tbUser
	 * @return
	 */
	public int addUser(TbUser tbUser) ;
	
	
	/**
	 * 更新用户信息
	 * @param tbUser
	 * @return boolean
	 */
	public int update(TbUser tbUser) ;
	
	/**
	 * 根据用户ID查看用户信息
	 * @param id
	 * @return
	 */
	public TbUser getUserInfoById(Integer id) ;
	
	
	/**
	 * 用户登录
	 * 验证用户和密码是否匹配
	 * 验证
	 * @param tbUser
	 * @return
	 */
	public TbUser checkTheLogInfo(TbUser tbUser) ;
	
	
	/**
	 * 通过旅行社或部门ID查询出所有的用户信息
	 * @param intPartmentId
	 * @return
	 */
	public List<TbUser> getTbUserListByPartmentId(Integer intPartmentId) ;
	
	/**
	 * 查询所有保险公司的内部用户 
	 * @return
	 */
	public List<TbUser> getBxAllUser() ;
	
	
	
	/**
	 * 将用户设置为黑名单
	 * @param userId
	 */
	public void deleteUser(Integer userId) ;

}

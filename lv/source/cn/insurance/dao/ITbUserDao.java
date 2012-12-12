package cn.insurance.dao;

import java.util.List;

import cn.insurance.model.TbUser;

public interface ITbUserDao {
	/**
		 * 添加
		 * @param obj
		 * @return
		 */
		public int create(TbUser tbUser) ;
		
		/**
		 * 更新
		 * @param obj
		 * @return
		 */
		public int update(TbUser tbUser) ;
		
		/**
		 * 通过用户名查询所有的用户
		 * @param username
		 * @return
		 */
		public List<TbUser> getUserListByUserName(String username) ;
		
		/**
		 * 查询全部
		 * @return
		 */
		public List<TbUser> getAllObjectList() ;

		/**
		 * 根据ID查找对象
		 * @param id
		 * @return
		 */
		public TbUser getObjectInfoById(Integer id) ;
		
		/**
		 * 根据ID删除
		 * @param id
		 * @return
		 */
		public Integer delete(Integer id) ;
		
		/**
		 * 验证用户名是否存在
		 * 存在返回true
		 * 不存在返回false
		 * @param userName
		 * @return
		 */
		public boolean checkecUser(String userName) ;
		
		/**
		 * 用户登录，验证用户是否存在
		 * 不存在则返回null值
		 * 存在则将用户信息返回
		 * @param tbUser
		 * @return
		 */
		public TbUser userLogIn(TbUser tbUser) ;
		
		/**
		 * 
		 * 根据部门ID查询所有的用户
		 * @param partmentId
		 * @return
		 */
		public List<TbUser> getTbUserListByPartmentId(Integer partmentId) ;
		
		/**
		 * 查询保险公司所有的用户
		 * @return
		 */
		public List<TbUser> getAllBxUser() ;
		
}

package cn.insurance.dao;

import java.util.List;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbPartment;

public interface ITbPartmentDao {

	
	/**
	 * 添加
	 * @param obj
	 * @return
	 */
	public Integer create(TbPartment tbPartment) ;
	
	/**
	 * 更新
	 * @param obj
	 * @return
	 */
	public int update(TbPartment tbPartment) ;
	
	/**
	 * 查询全部
	 * @return
	 */
	public List<TbPartment> getAllObjectList() ;
	
	/**
	 * 查询所有的旅行社
	 * @param obj
	 * @return
	 */
	public List<TbPartment> getAllCompanyList() ;

	/**
	 * 根据用户提交的信息，到数据库里面进行模糊查询
	 * @param 用户给定的值
	 * @return
	 * 
	 */
	public List<TbPartment> getAllCompanyList(String value) ;
	
	/**
	 * 根据ID查找对象
	 * @param id
	 * @return
	 */
	public TbPartment getObjectInfoById(Integer id) ;

	/**
	 * 通过帐户ID查询部门
	 * @param accountId
	 * @return
	 */
	public TbPartment getPartmentInfoByAccountId(Integer accountId) ;
	
	/**
	 * 根据帐户ID查询部门
	 * @param accountId
	 * @return
	 */
	public TbPartment getTbPartmentByAccountId(Integer accountId) ;
	
	
	
	
	/**
	 * 根据ID删除
	 * 删除部门或旅行社会非常的复杂，这里将部门或旅行社设置为不可用
	 * 实际就是隐藏起来，也是为了防止数据丢失
	 * @param id
	 * @return
	 */
	public int delete(Integer id) ;
	
	
	/**
	 * 根据旅行社ID查询其所有的部门 并分页
	 * @param pageBean
	 * @param companyId
	 * @return
	 */
	public PageBean getAllPartmentPageBeanByCompanyId(PageBean pageBean , Integer companyId) ;
	
	/**
	 * 根据旅行社ID查询所有的部门
	 * @param companyId
	 * @return
	 */
	public List<TbPartment> getAllPartmentListByCompanyId(Integer companyId) ;
	
	/**
	 * 查询所有旅行社的下级部门(不包括总社)
	 * 用来生成树形结构里
	 * @return
	 */
	public List<TbPartment> getAllCompanyPartmentList();
	
	
	
}

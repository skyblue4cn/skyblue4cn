package cn.insurance.service.admin;

import java.io.File;
import java.util.List;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbPartment;

public interface IAdminPartmentServ {
	
	
	
	
	/**
	 * 添加部门信息
	 * @param tbPartment
	 * @return
	 */
	public boolean addTbPartment(TbPartment tbPartment,File imgFile,String fileName) ;
	
	/**
	 * 更新旅行社或部门信息
	 * @param tbPartment
	 * @return
	 */
	public int updateTbPartment(TbPartment tbPartment ,File imgFile,String fileName) ;
	
	
	/**
	 * 查询全部旅游社信息(只有旅行社，不包括部门，intParentId=0)
	 * @return
	 */
	public List<TbPartment> getAllCompanyList() ;
	
	/**
	 * 根据旅行社ID查询所有的部门，不分页
	 * @param companyId
	 * @return
	 */
	public List<TbPartment> getAllPartmentByParentId(Integer companyId) ;
	
	/**
	 * 按旅行社ID查询旅行社下所有的部门，并分页
	 * @param companyId
	 * @return
	 */
	public PageBean getAllPartmentByCompanyId(PageBean pageBean ,Integer companyId) ;
	
	
	/**
	 * 通过ID查询旅行社或部门信息
	 */
	public TbPartment getPartmentInfoById(Integer id) ;
	
	/**
	 * 通过帐户ID查询部门
	 * @param accountId
	 * @return
	 */
	public TbPartment getPartmentInfoByAccountId(Integer accountId) ;
	
	
	
	/**
	 * 删除部门
	 * 删除实际上时将部门隐藏起来
	 * 并不是真正的删除
	 */
	public int deletePartmentById(Integer id) ;
	
	
}

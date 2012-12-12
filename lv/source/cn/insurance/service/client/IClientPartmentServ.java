package cn.insurance.service.client;

import java.util.List;

import cn.insurance.model.TbPartment;

public interface IClientPartmentServ {
	
	/**
	 * 通过ID查询部门或总社信息
	 * @param id
	 * @return
	 */
	public TbPartment getTbPartmentById(Integer id) ;

	/**
	 * 通过旅行社ID查询该旅行社的下属所有部门(不包括总社)
	 * @param companyId
	 * @return
	 */
	public List<TbPartment> getAllPartmentByCompanyId(Integer companyId) ;
	
	
	
	/**
	 * 通过旅行社ID查询该旅行社的下属所有部门(包括总社)
	 * @param companyId
	 * @return
	 */
	public List<TbPartment> getAllPartmentByCompanyIdWithCompany(Integer companyId) ;
	
	
	
}

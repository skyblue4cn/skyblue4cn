package cn.insurance.service.client.impl;

import java.util.ArrayList;
import java.util.List;

import cn.insurance.dao.ITbPartmentDao;
import cn.insurance.model.TbPartment;
import cn.insurance.service.client.IClientPartmentServ;

public class ClientPartmentServ implements IClientPartmentServ {
	
	private ITbPartmentDao tbPartmentDao ;
	
	/*
	 * 通过ID查询部门或总社信息
	 * (non-Javadoc)
	 * @see cn.insurance.service.client.IClientPartmentServ#getTbPartmentById(java.lang.Integer)
	 */
	public TbPartment getTbPartmentById(Integer id){
		return tbPartmentDao.getObjectInfoById(id);
	}
	
	/*
	 * 通过旅行社ID查询所有的下级部门
	 * (non-Javadoc)
	 * @see cn.insurance.service.client.IClientPartmentServ#getAllPartmentByCompanyId(java.lang.Integer)
	 */
	public List<TbPartment> getAllPartmentByCompanyId(Integer companyId) {
		return tbPartmentDao.getAllPartmentListByCompanyId(companyId);
	}
	
	

	/**
	 * 通过旅行社ID查询该旅行社的下属所有部门(包括总社)
	 * @param companyId
	 * @return
	 */
	public List<TbPartment> getAllPartmentByCompanyIdWithCompany(Integer companyId){
		TbPartment company = tbPartmentDao.getObjectInfoById(companyId);
		company.setStrPartmentName(company.getStrPartmentName() + "(总社)") ;
		List<TbPartment> list = new ArrayList<TbPartment>();
		list.add(company) ;
		list.addAll(tbPartmentDao.getAllPartmentListByCompanyId(companyId));
		return list ;
	}
	

	public ITbPartmentDao getTbPartmentDao() {
		return tbPartmentDao;
	}

	public void setTbPartmentDao(ITbPartmentDao tbPartmentDao) {
		this.tbPartmentDao = tbPartmentDao;
	}

}

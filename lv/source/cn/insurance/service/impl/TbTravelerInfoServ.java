package cn.insurance.service.impl;

import java.util.List;

import cn.insurance.dao.ITbBillDao;
import cn.insurance.dao.ITbTravelerInfoDao;
import cn.insurance.dao.impl.TbBillDao;
import cn.insurance.model.TbBill;
import cn.insurance.model.TbTravelerInfo;
import cn.insurance.service.ITbTravelerServ;

public class TbTravelerInfoServ implements ITbTravelerServ{
	
	private ITbTravelerInfoDao tbTravelerInfoDao ;

	private static final String china_Name = "中国" ;
	

	
	/*
	 * 添加游客信息
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbTravelerServ#addTraveler(cn.insurance.model.TbTravelerInfo)
	 */
	public void addTraveler(TbTravelerInfo tbTravelerInfo){
		tbTravelerInfoDao.create(tbTravelerInfo) ;
	}
	
	
	/*
	 * 根据ID查询游客信息
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbTravelerServ#getTravelerById(java.lang.Integer)
	 */
	public TbTravelerInfo getTravelerById(Integer id){
		return (TbTravelerInfo)tbTravelerInfoDao.getObjectInfoById(id) ;
	}
	
	/*
	 * 更新游客信息
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbTravelerServ#updateTraveler(cn.insurance.model.TbTravelerInfo)
	 */
	public void updateTraveler(TbTravelerInfo tbTravelerInfo){
		tbTravelerInfoDao.update(tbTravelerInfo) ;
	}
	
	
	public void deleteTraveler(Integer id){
		tbTravelerInfoDao.delete(id) ;
	}
	
	/*
	 * 根据保单ID查询出这个保单里的所有游客信息
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbTravelerServ#getListByBillId(java.lang.Integer)
	 */
	public List<TbTravelerInfo> getListByBillId(Integer billId){
		return tbTravelerInfoDao.getAllObjectListByBillId(billId) ;
	}


	public ITbTravelerInfoDao getTbTravelerInfoDao() {
		return tbTravelerInfoDao;
	}


	public void setTbTravelerInfoDao(ITbTravelerInfoDao tbTravelerInfoDao) {
		this.tbTravelerInfoDao = tbTravelerInfoDao;
	}



	
}

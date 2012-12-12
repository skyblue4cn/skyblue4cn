package cn.insurance.service;

import java.util.List;

import cn.insurance.model.TbTravelerInfo;

public interface ITbTravelerServ {
	
	/**
	 * 添加游客信息
	 * @param tbTravelerInfo
	 */
	public void addTraveler(TbTravelerInfo tbTravelerInfo) ;
	
	/**
	 * 根据ID取得游客信息
	 * @param id
	 * @return
	 */
	public TbTravelerInfo getTravelerById(Integer id) ;
	
	/**
	 * 更新游客信息
	 * @param tbTravelerInfo
	 */
	public void updateTraveler(TbTravelerInfo tbTravelerInfo) ;
	
	/**
	 * 按ID删除游客
	 * @param id
	 */
	public void deleteTraveler(Integer id) ;
	
	/**
	 * 根据保单ID查询出这个保单的所有游客信息
	 * @param billId
	 * @return
	 */
	public List<TbTravelerInfo> getListByBillId(Integer billId) ;
	
}

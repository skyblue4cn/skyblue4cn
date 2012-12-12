package cn.insurance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.insurance.model.TbTravelerInfo;

public interface ITbTravelerInfoDao {
	
	/**
	 * 添加
	 * @param tbTravelerInfo
	 * @return
	 */
	public int create(TbTravelerInfo tbTravelerInfo)  ;
		
	/**
	 * 批量添加游客
	 * @param travelerList
	 * @return
	 */
	public int create(List<TbTravelerInfo> travelerList) ;
	
	/**
	 * 更新
	 * @param tbTravelerInfo
	 * @return
	 */
	public int update(TbTravelerInfo tbTravelerInfo) ;

	/**
	 * 根据保单ID查询所有游客信息
	 * @param billId
	 * @return
	 */
	public List getAllObjectListByBillId(Integer billId)  ;

	/**
	 * 根据ID查询游客
	 * @param id
	 * @return
	 */
	public TbTravelerInfo getObjectInfoById(Integer id)  ;

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delete(Integer id)  ;
	
	
	/**
	 * 
	 * 按保单ID删除所有的游客
	 * @param billId
	 * @return
	 */
	public int deleteByBillId(Integer billId) ;
	
	
	public List getBillIdByTravelerInfo(String strName,String strNum);
	
}

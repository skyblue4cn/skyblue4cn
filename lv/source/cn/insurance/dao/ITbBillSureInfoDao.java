package cn.insurance.dao;

import java.util.List;

import cn.insurance.model.TbBill;
import cn.insurance.model.TbBillSureInfo;

public interface ITbBillSureInfoDao {
	
	/**
	 * 添加确认记录
	 * @param tbBillSureInfo
	 * @return
	 */
	public int addTbBillSureInfoDao(TbBillSureInfo tbBillSureInfo) ;
	
	/**
	 * 根据保单ID查询确认记录
	 * @param billId   保单ID
	 * @param type   确认对象
	 * @return
	 */
	public List<TbBillSureInfo> getBillSureInfoByBillId(Integer billId , int type) ;
}

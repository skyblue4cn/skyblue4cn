package cn.insurance.dao;

import java.util.List;

import cn.insurance.model.TbBill;
import cn.insurance.model.TbListForMonthPay;

public interface ITbListForMonthPayDao {
	
	
	public int create(TbListForMonthPay tbListForMonthPay) ;
	
	/**
	 * 批量添加
	 * @param montPayId
	 * @return
	 */
	public int createTbListForMonthPay(Integer monthPayId , Integer[] billIds) ;
	
	
	/**
	 * 查询月费的应付清单
	 * 清单属于该月并且由该月付费
	 * @param monthPayId
	 * @return
	 */
	public List<TbListForMonthPay> getYingFuListForMonthPay(Integer monthPayId) ;
	
	/**
	 * 收费时月费清单里的应付费用的单子全部更新为已交费状态
	 * @param monthPayId
	 * @return
	 */
	public int updateBillStateByMonthPayIdForShouFei(int monthPayId) ;
	
	/**
	 * 通过月费的清单查询所以清单要付费的总费用
	 * @param monthPayId
	 * @return
	 */
	public double getYingFuMonthFeeByMonthId(int monthPayId) ;
	
	/**
	 * 通过保单ID删除该保单在月费中的清单
	 * @param billId
	 * @return
	 */
	public int deleteBillListByBillId(int billId) ;
	
}

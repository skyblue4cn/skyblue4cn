package cn.insurance.service;

import java.util.List;

import cn.insurance.model.TbAccidentalFee;
import cn.insurance.model.TbAdjustFee;
import cn.insurance.model.TbBill;

public interface IPriceServT {
	
	/**
	 * 取得通用的意外险费用
	 * @return
	 */
	public TbAccidentalFee getNormalAccidentalFee() ;
	
	/**
	 * 获取通用的调节费
	 * @return
	 */
	public TbAdjustFee getNormalAdjustFee() ;
	
	
	/**
	 * 按旅行社ID取得该旅行社的意外险费用
	 */
	public TbAccidentalFee getCompanyAccdFeeById(Integer companyId) ;
	
	/**
	 * 按旅行社ID取得该旅行社的调节费
	 * @param companyId
	 * @return
	 */
	public TbAdjustFee getCompanyAdjustFee(Integer companyId) ;
	
	
	/**
	 * 更新通用的意外险价格
	 * 所有旅行社一起更新
	 * @param tbAccidentalFee
	 * @return
	 */
	public int updateNormalAccidentalFee(TbAccidentalFee tbAccidentalFee) ;
	
	/**
	 * 更新通用的调节费价格
	 * 所有旅行社都更新
	 * @param tbAdjustFee
	 * @return
	 */
	public int updateNormalAdjustFee(TbAdjustFee tbAdjustFee) ;
	
	
	/**
	 * 更新旅行社的意外险价格 
	 * @param tbAccidentalFee
	 * @return
	 */
	public int updateLxsAccidentalFee(TbAccidentalFee tbAccidentalFee) ;
	
	/**
	 * 更新责任险调节费
	 * @param tbAdjustFee
	 * @return
	 */
	public int updateLxsAdjustFee(TbAdjustFee tbAdjustFee) ;
	
	/**
	 * 计算出一个保单的价格
	 * @param tbBill
	 * @return
	 */
	public TbBill getBillPrice(TbBill tbBill) ;
	
}

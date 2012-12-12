package cn.insurance.dao;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbPeiKuan;

public interface ITbPeiKuanDao {
	
	/**
	 * 添加保单的赔款记录
	 * 
	 * @param tbPeiKuan
	 * @return
	 */
	public int addPeiKuanLog(TbPeiKuan tbPeiKuan);
	
	
	/**
	 * 按保单ID查询保单的赔款记录
	 * @return
	 */
	public TbPeiKuan getPeiKuanLogByBillId(Integer billId) ;
	
	/**
	 * 查询所有的赔款记录
	 * @param pagebean
	 * @return
	 */
	public PageBean getAllPeiKuanLog(PageBean pagebean) ;
	
}

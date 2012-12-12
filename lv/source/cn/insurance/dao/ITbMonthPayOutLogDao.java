package cn.insurance.dao;

import java.util.List;

import cn.insurance.model.TbMonthPayOutLog;

public interface ITbMonthPayOutLogDao {
	
	/**
	 * 添加月费付费记录
	 * 
	 * @param tbMonthPayOutLog
	 * @return
	 */
	public int addTbMonthPayOutLog(TbMonthPayOutLog tbMonthPayOutLog) ;
	
	
	/**
	 * 通过月费ID查询所有付费记录
	 * 
	 * @param montPayId
	 * @return
	 */
	public TbMonthPayOutLog getMonthPauOutLogByMonthPayId(Integer montPayId) ;
}

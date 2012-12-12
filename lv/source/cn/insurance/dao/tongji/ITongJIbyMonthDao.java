package cn.insurance.dao.tongji;

import java.util.List;

import cn.insurance.model.tongji2.PartmentMonthStat;


public interface ITongJIbyMonthDao {
	
	/**
	 * 统计一年内的所有月份的数据
	 * @param year
	 * @return
	 */
	public List<PartmentMonthStat> getPartmentMonthStatByYear(int partmentId ,int year) ;
	
	/**
	 * 查询该月的统计
	 * @param partmentId
	 * @param year
	 * @param month
	 * @return
	 */
	public PartmentMonthStat getPartmentMonthStatByYearAndMonth(int partmentId , int year , int month) ;
	
	
}

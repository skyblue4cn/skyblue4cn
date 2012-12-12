package cn.insurance.dao.tongji;

import java.util.List;

import cn.insurance.model.tongji2.PartmentYearStat;

/**
 * 对保单费用进行统计
 * @author yqg
 *
 */
public interface ITongJiByYearDao {
	
	/**
	 * 对部门保单按年进行统计
	 * @param partmentId
	 * @return
	 */
	public List<PartmentYearStat> getPartmentYearStat(int partmentId) ;
	
	/**
	 * 查询该年的统计
	 * @param year
	 * @return
	 */
	public PartmentYearStat getPartmentYearStatByYear(int partmentId , int year) ;
	
}

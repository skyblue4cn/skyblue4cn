package cn.insurance.service;

import java.util.List;

import cn.insurance.model.tongji.PartmentDayStat;
import cn.insurance.model.tongji.PartmentMonthStat;
import cn.insurance.model.tongji.PartmentTimeStat;
import cn.insurance.model.tongji.PartmentYearStat;

public interface ITongJiServ {
	
	
	/**
	 * 默认查询的部门的统计
	 * 查询本年从1.1日到当前时间的统计
	 * @param partmentId
	 * @return
	 */
	public PartmentYearStat getPartmentYearStat(Integer partmentId , int year) ;
	
	
	
	
	
	
	/**
	 * 按部门的年月查询该月统计(不包含每天的统计)
	 * 
	 * @return
	 */
	public PartmentMonthStat getPartmentMonthStatByYearAndMonth(PartmentMonthStat pm) ;
	
	
	/**
	 * 查询部门每月里每天的保单统计(包含每天的统计)
	 * @param pm
	 * @return
	 */
	public PartmentMonthStat getPartmentMonthStatByDay(PartmentMonthStat pm) ;
	
	
	/**
	 * 按天查询统计
	 * @param pds
	 * @return
	 */
	public PartmentDayStat getPartmentDayStatByDay(PartmentDayStat pds) ;
	
	
	/**
	 * 按时间段查询统计
	 * @param pts
	 * @return
	 */
	public PartmentTimeStat getPartmentTimeStatByTime(PartmentTimeStat pts) ;
	
}

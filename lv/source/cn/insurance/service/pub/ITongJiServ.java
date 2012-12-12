package cn.insurance.service.pub;

import java.util.List;

import cn.insurance.model.tongji2.AllCompanyYearStat;
import cn.insurance.model.tongji2.CompanyYearStat;
import cn.insurance.model.tongji2.PartmentTimeStat;
import cn.insurance.model.tongji2.PartmentDayStat;
import cn.insurance.model.tongji2.PartmentMonthStat;
import cn.insurance.model.tongji2.PartmentYearStat;
import cn.insurance.model.tongji2.abstractmodel.AbstractPartmentDayStat;
import cn.insurance.model.tongji2.abstractmodel.AbstractPartmentMonthStat;

public interface ITongJiServ {
	
	/**
	 * 对所有旅行社及部门的所有保单按年进行统计
	 * @return
	 */
	public List<AllCompanyYearStat> getAllCompanyYearStat(int year);
	
	
	/**
	 * 根据旅行社ID和年统计这个旅行社的所有部门的数据
	 * @param companyId
	 * @return
	 */
	public List<PartmentYearStat> getPartmentYearStatByCompanyId(int companyId , int year);
	
	/**
	 * 对部门按年进行统计
	 * @param partmentId
	 * @return
	 */
	public List<PartmentYearStat> getPartmentYearStat(int partmentId) ;
	
	/**
	 * 对部门按年进行统计(只统计指定的一年)
	 * @param partmentId
	 * @return
	 */
	public PartmentYearStat getPartmentYearStat(int partmentId , int year) ;
	
	/**
	 * 对部门一年内每月的统计
	 * @param partmentId
	 * @param year
	 * @return
	 */
	public AbstractPartmentMonthStat getPartmentMonthStat(int partmentId , int year) ;
	
	/**
	 * 对部门按天统计
	 * @param partmentId
	 * @param year
	 * @param month
	 * @return
	 */
	public AbstractPartmentDayStat getPartmentDayStat(int partmentId , int year , int month);
	
	/**
	 * 对部门时间段统计
	 * @param partmentId
	 * @param startTimne
	 * @param endTime
	 * @return
	 */
	public PartmentTimeStat getPartmentTimeStat(int partmentId , String startTimne , String endTime) ;
}

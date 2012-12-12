package cn.insurance.model.tongji2.abstractmodel;

import java.util.List;

import cn.insurance.model.TbPartment;
import cn.insurance.model.tongji2.PartmentMonthStat;
import cn.insurance.model.tongji2.PartmentYearStat;

/**
 * @author 系统管理员
 *
 */
public class AbstractPartmentMonthStat {
	
	//年
	private int year ;
	//部门
	private TbPartment tbPartment ;
	
	//每月的统计
	private List<PartmentMonthStat> partmentMonthStatList ;
	
	//一年的统计
	private PartmentYearStat partmentYearStat ;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public TbPartment getTbPartment() {
		return tbPartment;
	}

	public void setTbPartment(TbPartment tbPartment) {
		this.tbPartment = tbPartment;
	}

	public List<PartmentMonthStat> getPartmentMonthStatList() {
		return partmentMonthStatList;
	}

	public void setPartmentMonthStatList(
			List<PartmentMonthStat> partmentMonthStatList) {
		this.partmentMonthStatList = partmentMonthStatList;
	}

	public PartmentYearStat getPartmentYearStat() {
		return partmentYearStat;
	}

	public void setPartmentYearStat(PartmentYearStat partmentYearStat) {
		this.partmentYearStat = partmentYearStat;
	}
	
	
}

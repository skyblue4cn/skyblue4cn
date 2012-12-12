package cn.insurance.model.tongji2.abstractmodel;

import java.util.List;

import cn.insurance.model.TbPartment;
import cn.insurance.model.tongji2.PartmentDayStat;
import cn.insurance.model.tongji2.PartmentMonthStat;

public class AbstractPartmentDayStat {
	
	private int year ;
	
	private int month ;
	
	private TbPartment tbPartment ;
	
	private List<PartmentDayStat> partmentDayStatList ;
	
	private PartmentMonthStat partmentMonthStat ;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public TbPartment getTbPartment() {
		return tbPartment;
	}

	public void setTbPartment(TbPartment tbPartment) {
		this.tbPartment = tbPartment;
	}

	public List<PartmentDayStat> getPartmentDayStatList() {
		return partmentDayStatList;
	}

	public void setPartmentDayStatList(List<PartmentDayStat> partmentDayStatList) {
		this.partmentDayStatList = partmentDayStatList;
	}

	public PartmentMonthStat getPartmentMonthStat() {
		return partmentMonthStat;
	}

	public void setPartmentMonthStat(PartmentMonthStat partmentMonthStat) {
		this.partmentMonthStat = partmentMonthStat;
	}
	
}

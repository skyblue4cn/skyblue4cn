package cn.insurance.action.client.tongji;

import java.util.List;

import cn.insurance.action.SupportAction;
import cn.insurance.model.tongji2.PartmentTimeStat;
import cn.insurance.model.tongji2.PartmentYearStat;
import cn.insurance.model.tongji2.abstractmodel.AbstractPartmentDayStat;
import cn.insurance.model.tongji2.abstractmodel.AbstractPartmentMonthStat;
import cn.insurance.service.pub.ITongJiServ;

/**
 * 旅行社对保单及保费进行统计
 * @author yqg
 *
 */
public class ClientTongjiAction extends SupportAction {

	
	private List<PartmentYearStat> partmentYearStatList ;
	
	private AbstractPartmentMonthStat ams ;
	
	private AbstractPartmentDayStat ads ;
	
	
	private PartmentTimeStat partmentTimeStat ;
	
	private ITongJiServ tongJiServ ;
	
	private int year ;
	
	private int month ;
	
	private String startTime ;
	
	private String endTime ;
	
	/**
	 * 用户点击统计
	 * 进入统计页面显示给用户
	 * @return
	 */
	public String toTongJi(){
		
		return SUCCESS ;
	}
	
	/**
	 * 按年统计
	 * @return
	 */
	public String tongJiByYear(){
		partmentYearStatList = tongJiServ.getPartmentYearStat(getUser().getIntPartmentId()) ;
		return SUCCESS ;
	}
	
	
	/**
	 * 按月统计
	 * @return
	 */
	public String tongJiByMonth(){
		ams = tongJiServ.getPartmentMonthStat(getUser().getIntPartmentId(), year) ;
		return SUCCESS ;
	}
	
	/**
	 * 按天统计
	 * @return
	 */
	public String tongJiByDate(){
		ads = tongJiServ.getPartmentDayStat(getUser().getIntPartmentId(), year, month) ;
		return SUCCESS ;
	}
	
	/**
	 * 按时间段统计
	 * @return
	 */
	public String tongJiByTimeSpace(){
		partmentTimeStat = tongJiServ.getPartmentTimeStat(getUser().getIntPartmentId(), startTime, endTime) ;
		return SUCCESS ;
	}

	public List<PartmentYearStat> getPartmentYearStatList() {
		return partmentYearStatList;
	}

	public void setPartmentYearStatList(List<PartmentYearStat> partmentYearStatList) {
		this.partmentYearStatList = partmentYearStatList;
	}

	public ITongJiServ getTongJiServ() {
		return tongJiServ;
	}

	public void setTongJiServ(ITongJiServ tongJiServ) {
		this.tongJiServ = tongJiServ;
	}


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

	public PartmentTimeStat getPartmentTimeStat() {
		return partmentTimeStat;
	}

	public void setPartmentTimeStat(PartmentTimeStat partmentTimeStat) {
		this.partmentTimeStat = partmentTimeStat;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public AbstractPartmentMonthStat getAms() {
		return ams;
	}

	public void setAms(AbstractPartmentMonthStat ams) {
		this.ams = ams;
	}

	public AbstractPartmentDayStat getAds() {
		return ads;
	}

	public void setAds(AbstractPartmentDayStat ads) {
		this.ads = ads;
	}
	
	
}

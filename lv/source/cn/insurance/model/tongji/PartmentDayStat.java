package cn.insurance.model.tongji;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.insurance.model.TbBill;
import cn.insurance.model.TbPartment;

public class PartmentDayStat {
	
	/*部门*/
	private TbPartment tbPartment ;
	
	/*年*/
	private int year ;
	
	/*月*/
	private int month ;
	
	private int day ;
	
	/*开始时间(用字符串表示为了方便显示)*/
	private String startTime ;
	
	/*结束时间(用字符串表示为了方便显示)*/
	private String endTime ;
	
	
	/*核保通过*/
	private List<TbBill> hasSureBillList = new ArrayList<TbBill>() ;
	
	/*核保未通过*/
	private List<TbBill> returnBillList = new ArrayList<TbBill>() ;
	
	/*核保通过里已付费的保单*/
	private List<TbBill> hasPayBillList = new ArrayList<TbBill>() ;
	
	/*核保通过里未付费的保单*/
	private List<TbBill> notPayBillList = new ArrayList<TbBill>() ;
	
	/*有赔款记录的保单清单*/
	private List<TbBill> hasPeiKuanBillList = new ArrayList<TbBill>() ;
	

	/*所有保单的数目*/
	private int allBillCount ;
	
	/*已付费保单的数量*/
	private int hasPayBillCount ;
	
	/*已付费保单的金额*/
	private double hasPayBillFee ;
	
	/*未付费保单的数量总量*/
	private int notPayBillCount ;
	
	/*未付费保单的金额*/
	private double notPayBillFee ;
	
	
	/*赔款保单的数量*/
	private int peiKuaiBillCount ;
	
	/*赔款的数额*/
	private double peiKuanNumber ;

	public int getAllBillCount() {
		return allBillCount;
	}

	public void setAllBillCount(int allBillCount) {
		this.allBillCount = allBillCount;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHasPayBillCount() {
		return hasPayBillCount;
	}

	public void setHasPayBillCount(int hasPayBillCount) {
		this.hasPayBillCount = hasPayBillCount;
	}

	public List<TbBill> getHasPayBillList() {
		return hasPayBillList;
	}

	public void setHasPayBillList(List<TbBill> hasPayBillList) {
		this.hasPayBillList = hasPayBillList;
	}

	public List<TbBill> getHasPeiKuanBillList() {
		return hasPeiKuanBillList;
	}

	public void setHasPeiKuanBillList(List<TbBill> hasPeiKuanBillList) {
		this.hasPeiKuanBillList = hasPeiKuanBillList;
	}

	public List<TbBill> getHasSureBillList() {
		return hasSureBillList;
	}

	public void setHasSureBillList(List<TbBill> hasSureBillList) {
		this.hasSureBillList = hasSureBillList;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getNotPayBillCount() {
		return notPayBillCount;
	}

	public void setNotPayBillCount(int notPayBillCount) {
		this.notPayBillCount = notPayBillCount;
	}

	public List<TbBill> getNotPayBillList() {
		return notPayBillList;
	}

	public void setNotPayBillList(List<TbBill> notPayBillList) {
		this.notPayBillList = notPayBillList;
	}

	public int getPeiKuaiBillCount() {
		return peiKuaiBillCount;
	}

	public void setPeiKuaiBillCount(int peiKuaiBillCount) {
		this.peiKuaiBillCount = peiKuaiBillCount;
	}

	public double getPeiKuanNumber() {
		return peiKuanNumber;
	}

	public void setPeiKuanNumber(double peiKuanNumber) {
		this.peiKuanNumber = peiKuanNumber;
	}

	public List<TbBill> getReturnBillList() {
		return returnBillList;
	}

	public void setReturnBillList(List<TbBill> returnBillList) {
		this.returnBillList = returnBillList;
	}

	public TbPartment getTbPartment() {
		return tbPartment;
	}

	public void setTbPartment(TbPartment tbPartment) {
		this.tbPartment = tbPartment;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getHasPayBillFee() {
		return hasPayBillFee;
	}

	public void setHasPayBillFee(double hasPayBillFee) {
		this.hasPayBillFee = hasPayBillFee;
	}

	public double getNotPayBillFee() {
		return notPayBillFee;
	}

	public void setNotPayBillFee(double notPayBillFee) {
		this.notPayBillFee = notPayBillFee;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	
	
}

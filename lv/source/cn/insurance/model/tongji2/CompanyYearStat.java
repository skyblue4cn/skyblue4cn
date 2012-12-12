package cn.insurance.model.tongji2;

import cn.insurance.model.TbPartment;

public class CompanyYearStat {

	/*旅行社*/
	private TbPartment tbPartment  ;
	
	/*年*/
	private int year ;
	
	/*开始时间(用字符串表示为了方便显示)*/
	private String startTime ;
	
	/*结束时间(用字符串表示为了方便显示)*/
	private String endTime ;

	/*所有保单的数目总量*/
	private int allBillCount ;
	
	/*所有核得通过的保单数量*/
	private int hasSureBillCount ;
	
	/*所有核得未通过的保单数量*/
	private int returnBillCount ;
	
	/*已付费保单的数量总量*/
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


	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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

	public int getAllBillCount() {
		return allBillCount;
	}

	public void setAllBillCount(int allBillCount) {
		this.allBillCount = allBillCount;
	}

	public int getHasSureBillCount() {
		return hasSureBillCount;
	}

	public void setHasSureBillCount(int hasSureBillCount) {
		this.hasSureBillCount = hasSureBillCount;
	}

	public int getReturnBillCount() {
		return returnBillCount;
	}

	public void setReturnBillCount(int returnBillCount) {
		this.returnBillCount = returnBillCount;
	}

	public int getHasPayBillCount() {
		return hasPayBillCount;
	}

	public void setHasPayBillCount(int hasPayBillCount) {
		this.hasPayBillCount = hasPayBillCount;
	}

	public double getHasPayBillFee() {
		return hasPayBillFee;
	}

	public void setHasPayBillFee(double hasPayBillFee) {
		this.hasPayBillFee = hasPayBillFee;
	}

	public int getNotPayBillCount() {
		return notPayBillCount;
	}

	public void setNotPayBillCount(int notPayBillCount) {
		this.notPayBillCount = notPayBillCount;
	}

	public double getNotPayBillFee() {
		return notPayBillFee;
	}

	public void setNotPayBillFee(double notPayBillFee) {
		this.notPayBillFee = notPayBillFee;
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

	public TbPartment getTbPartment() {
		return tbPartment;
	}

	public void setTbPartment(TbPartment tbPartment) {
		this.tbPartment = tbPartment;
	}
}

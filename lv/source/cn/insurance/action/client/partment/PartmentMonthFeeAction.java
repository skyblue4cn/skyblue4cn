package cn.insurance.action.client.partment;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import cn.insurance.action.SupportAction;
import cn.insurance.model.TbMonthPayInfo;
import cn.insurance.model.TbPartment;
import cn.insurance.service.client.IClientMonthFeeServ;
import cn.insurance.service.client.IClientPartmentServ;

public class PartmentMonthFeeAction extends SupportAction{
	
	private IClientPartmentServ clientPartmentServ ;
	
	private Integer partmentId ;
	
	private int year ;
	
	private int month ;
	
	private List<TbMonthPayInfo> monthPayInfoList ;
	
	private IClientMonthFeeServ clientMonthFeeServ ;
	
	private List<TbPartment> partmentList ;
	
	/**
	 * 查询总社及下属部门的月费
	 * @return
	 */
	public String showPartmentMonthFee(){
		Integer companyId = super.getUser().getIntPartmentId() ;
		partmentList = clientPartmentServ.getAllPartmentByCompanyIdWithCompany(companyId) ;
		if(year ==0 || month == 0){
			Calendar calendar = new GregorianCalendar();
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH) + 1 ;
		}
		if(partmentId == null || partmentId == 0){
			monthPayInfoList = clientMonthFeeServ.getPartmentMonthPayInfoByCompanyId(year, month, companyId) ;
		}else{
			monthPayInfoList = clientMonthFeeServ.getPartmentMonthPayInfoByPartmentId(year, month, partmentId) ;
		}
		return SUCCESS ;
	}
	

	public IClientPartmentServ getClientPartmentServ() {
		return clientPartmentServ;
	}

	public void setClientPartmentServ(IClientPartmentServ clientPartmentServ) {
		this.clientPartmentServ = clientPartmentServ;
	}

	public Integer getPartmentId() {
		return partmentId;
	}

	public void setPartmentId(Integer partmentId) {
		this.partmentId = partmentId;
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


	public List<TbMonthPayInfo> getMonthPayInfoList() {
		return monthPayInfoList;
	}


	public void setMonthPayInfoList(List<TbMonthPayInfo> monthPayInfoList) {
		this.monthPayInfoList = monthPayInfoList;
	}


	public IClientMonthFeeServ getClientMonthFeeServ() {
		return clientMonthFeeServ;
	}


	public void setClientMonthFeeServ(IClientMonthFeeServ clientMonthFeeServ) {
		this.clientMonthFeeServ = clientMonthFeeServ;
	}


	public List<TbPartment> getPartmentList() {
		return partmentList;
	}


	public void setPartmentList(List<TbPartment> partmentList) {
		this.partmentList = partmentList;
	}
	
	
	
}

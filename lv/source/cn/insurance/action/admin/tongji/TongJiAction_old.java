package cn.insurance.action.admin.tongji;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.opensymphony.webwork.ServletActionContext;

import cn.insurance.action.SupportAction;
import cn.insurance.model.TbBill;
import cn.insurance.model.TbPartment;
import cn.insurance.model.TbUser;
import cn.insurance.model.tongji.PartmentDayStat;
import cn.insurance.model.tongji.PartmentMonthStat;
import cn.insurance.model.tongji.PartmentTimeStat;
import cn.insurance.model.tongji.PartmentYearStat;
import cn.insurance.service.ITongJiServ;
import cn.insurance.service.admin.IAdminPartmentServ;

/**
 * 统计功能
 * @author 何青松
 * 修改时间 2012/11/20 14:58
 */
public class TongJiAction_old extends SupportAction{

	private static final long serialVersionUID = 1L;
	private PartmentYearStat py = new PartmentYearStat() ;	
	private PartmentMonthStat partmentMonthStat = new PartmentMonthStat();	
	private PartmentDayStat partmentDayStat = new PartmentDayStat() ;	
	private PartmentTimeStat partmentTimeStat = new PartmentTimeStat() ;
	private List<PartmentMonthStat> pmList = new ArrayList<PartmentMonthStat>() ;
	private ITongJiServ tongJiServ ;
	private int companyId ;
	private int partmentId ;
	private int queryType ;
	private List<TbBill> billList = new ArrayList<TbBill>() ;
	private List<TbPartment> companyList = new ArrayList<TbPartment>() ;
	private List<TbPartment> partmentList = new ArrayList<TbPartment>() ;
	private IAdminPartmentServ adminPartmentServ ;
	
	/**
	 * 查询统计的页面
	 * @return
	 */
	public String toTongJIPartmentStat(){
		TbUser tbUser = (TbUser)ServletActionContext.getRequest().getSession().getAttribute("tbUser") ;
		if(tbUser==null){
			return "login" ;
		}
		if(tbUser.getIntPartmentId()==null || tbUser.getIntPartmentId()==0){
			companyList = adminPartmentServ.getAllCompanyList() ;
			return "baoxian" ;
		}
		/*如果是旅行社的用户，那么查处所有的部门*/
		if(tbUser.getTbPartment().getIntParentId()==0){
			partmentList = adminPartmentServ.getAllPartmentByParentId(tbUser.getTbPartment().getId()) ;
			return "company" ;
		}
		return "partment" ;
	}
	
	/**
	 * 旅行社部门的统计，默认为本年度1月份到现在位置的按月统计
	 * */
	public String getDefaultPartmentYearStat(){
		TbUser tbUser = (TbUser)ServletActionContext.getRequest().getSession().getAttribute("tbUser") ;
		Calendar c = new GregorianCalendar() ;
		py = tongJiServ.getPartmentYearStat(tbUser.getIntPartmentId(), c.get(Calendar.YEAR)) ;
		return SUCCESS ;
	}
	
	/**
	 * 按年进行统计
	 * @return
	 */
	public String getPartmentYearStatByYear(){
		py = tongJiServ.getPartmentYearStat(partmentId,partmentMonthStat.getYear() ) ;
		return SUCCESS ;
	}
	
	/**
	 * 部门的按月统计统计(比如2008年10月)
	 * @return
	 */
	public String queryPartmentMonthStatByMonth(){
		partmentMonthStat.setTbPartment(new TbPartment(partmentId)) ;
		partmentMonthStat = tongJiServ.getPartmentMonthStatByYearAndMonth(partmentMonthStat) ;
		return SUCCESS ;
	}

	/**
	 * 查询部门设定月份的每天的统计
	 * @return
	 */
	public String getPartmentMonthStatForDays(){
		partmentMonthStat.setTbPartment(new TbPartment(partmentId)) ;
		partmentMonthStat = tongJiServ.getPartmentMonthStatByDay(partmentMonthStat) ;
		return SUCCESS ;
	}
	
	/**
	 * 查询时间段类的部门统计
	 * @return
	 */
	public String getPartmentTimeStatByTime(){
		partmentTimeStat.setTbPartment(new TbPartment(partmentId)) ;
		partmentTimeStat = tongJiServ.getPartmentTimeStatByTime(partmentTimeStat) ;
		return SUCCESS ;
	}

	public String getAllStatBillToShow(){
		billList = partmentMonthStat.getHasSureBillList() ;
		return SUCCESS ;
	}
	
	public PartmentMonthStat getPartmentMonthStat() {
		return partmentMonthStat;
	}

	public void setPartmentMonthStat(PartmentMonthStat partmentMonthStat) {
		this.partmentMonthStat = partmentMonthStat;
	}

	public ITongJiServ getTongJiServ() {
		return tongJiServ;
	}

	public void setTongJiServ(ITongJiServ tongJiServ) {
		this.tongJiServ = tongJiServ;
	}

	public List<PartmentMonthStat> getPmList() {
		return pmList;
	}

	public void setPmList(List<PartmentMonthStat> pmList) {
		this.pmList = pmList;
	}

	public PartmentYearStat getPy() {
		return py;
	}

	public void setPy(PartmentYearStat py) {
		this.py = py;
	}

	public PartmentDayStat getPartmentDayStat() {
		return partmentDayStat;
	}

	public void setPartmentDayStat(PartmentDayStat partmentDayStat) {
		this.partmentDayStat = partmentDayStat;
	}

	public int getPartmentId() {
		return partmentId;
	}

	public void setPartmentId(int partmentId) {
		this.partmentId = partmentId;
	}

	public PartmentTimeStat getPartmentTimeStat() {
		return partmentTimeStat;
	}

	public void setPartmentTimeStat(PartmentTimeStat partmentTimeStat) {
		this.partmentTimeStat = partmentTimeStat;
	}

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public List<TbBill> getBillList() {
		return billList;
	}

	public void setBillList(List<TbBill> billList) {
		this.billList = billList;
	}

	public List<TbPartment> getPartmentList() {
		return partmentList;
	}

	public void setPartmentList(List<TbPartment> partmentList) {
		this.partmentList = partmentList;
	}
	
	public List<TbPartment> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<TbPartment> companyList) {
		this.companyList = companyList;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public IAdminPartmentServ getAdminPartmentServ() {
		return adminPartmentServ;
	}
	
	public void setAdminPartmentServ(IAdminPartmentServ adminPartmentServ) {
		this.adminPartmentServ = adminPartmentServ;
	}
}

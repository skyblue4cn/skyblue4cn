package cn.insurance.action.admin.bill;

import java.util.List;
import javax.servlet.http.HttpSession;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ModelDriven;

import cn.insurance.action.SupportAction;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbBill;
import cn.insurance.model.TbPartment;
import cn.insurance.model.TbUser;
import cn.insurance.service.admin.IAdminBillServ;
import cn.insurance.service.admin.IAdminPartmentServ;
import cn.insurance.service.pub.ITbUserServ;
/**
 * 保单查询也页面数据加载
 * @author 何青松
 * 修改时间 2012/11/20 14:31
 */
public class BillSearchAction extends SupportAction implements ModelDriven{
	
	private static final long serialVersionUID = 1L;
	public TbBill tbBill = new TbBill() ;
	private List<TbPartment> companyList ;
	private List<TbUser> userList ;
	private PageBean pageBean ;
	private IAdminBillServ  adminBillServ;
	private IAdminPartmentServ adminPartmentServ ;
	private ITbUserServ tbUserServ ;
	private Integer companyId ;
	private Integer partmentId ;
	private String startTime ;
	private String endTime ;
	private int[] state ;
	private String strTravelerName;
	private String strIndentyNumber;
	
	//private String cstate;
	
	
//	public String getCstate() {
//		return cstate;
//	}
//
//	public void setCstate(String cstate) {
//		this.cstate = cstate;
//	}

	public String getStrTravelerName() {
		return strTravelerName;
	}

	public void setStrTravelerName(String strTravelerName) {
		this.strTravelerName = strTravelerName;
	}

	public String getStrIndentyNumber() {
		return strIndentyNumber;
	}

	public void setStrIndentyNumber(String strIndentyNumber) {
		this.strIndentyNumber = strIndentyNumber;
	}

	public Object getModel() {
		return tbBill;
	}
	
	/*下面都是按条件的综合查询*/
	
	/**
	 * 保险公司进入查询保单页面，初始化一些数据
	 */
	public String toQueryAllBillByBx(){
		/*查询所有的旅行社供保险公司选择*/
		companyList = adminPartmentServ.getAllCompanyList() ;
		/*查询保险公司所有的用户*/
		userList = tbUserServ.getBxAllUser() ;
		return SUCCESS ;
	}
	

	/**
	 * 保险公司按条件查询保单
	 * @return
	 */
	public String queryBillByComditionForBx(){
		/** START修改于2010-6-8*/
		//不包括前面的查询条件
		//包括前面的查询条件
		HttpSession session = ServletActionContext.getRequest().getSession();
		// 如果不是点击submit进到action 就从session里面取值
		if(strIndentyNumber == null)
			strIndentyNumber = session.getAttribute("BX_strIndentyNumber").toString();
		if(strTravelerName == null)
			strTravelerName = session.getAttribute("BX_strTravelerName").toString();
		if((strIndentyNumber.equals("") || strIndentyNumber == null)&&(strTravelerName.equals("")||strTravelerName == null))
			pageBean = adminBillServ.getBillByComditionForBx(pageBean, tbBill, companyId, partmentId, state, startTime, endTime);
		else{
			pageBean = adminBillServ.getBillByComditionForBxNew(pageBean ,strIndentyNumber, strTravelerName, tbBill, companyId, partmentId, state, startTime, endTime);
		}
		//把这2个新添加的功能丢到session
		session.setAttribute("BX_strIndentyNumber", strIndentyNumber);
		session.setAttribute("BX_strTravelerName", strTravelerName);
		/** END*/
		return SUCCESS ;
	}

	public List<TbPartment> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<TbPartment> companyList) {
		this.companyList = companyList;
	}

	public List<TbUser> getUserList() {
		return userList;
	}

	public void setUserList(List<TbUser> userList) {
		this.userList = userList;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public ITbUserServ getTbUserServ() {
		return tbUserServ;
	}

	public void setTbUserServ(ITbUserServ tbUserServ) {
		this.tbUserServ = tbUserServ;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getPartmentId() {
		return partmentId;
	}

	public void setPartmentId(Integer partmentId) {
		this.partmentId = partmentId;
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

	public int[] getState() {
		return state;
	}

	public void setState(int[] state) {
		this.state = state;
	}

	public IAdminPartmentServ getAdminPartmentServ() {
		return adminPartmentServ;
	}

	public void setAdminPartmentServ(IAdminPartmentServ adminPartmentServ) {
		this.adminPartmentServ = adminPartmentServ;
	}

	public IAdminBillServ getAdminBillServ() {
		return adminBillServ;
	}

	public void setAdminBillServ(IAdminBillServ adminBillServ) {
		this.adminBillServ = adminBillServ;
	}


}

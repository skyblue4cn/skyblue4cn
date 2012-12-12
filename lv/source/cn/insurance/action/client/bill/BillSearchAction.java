package cn.insurance.action.client.bill;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ModelDriven;

import cn.insurance.action.SupportAction;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbBill;
import cn.insurance.model.TbUser;
import cn.insurance.service.IBillQueryServ;
import cn.insurance.service.client.IClientBillServ;
import cn.insurance.service.pub.ITbUserServ;

/**
 * 根据条件查询保单
 * @author yqg
 *
 */
public class BillSearchAction extends SupportAction implements ModelDriven{
	
	public TbBill tbBill = new TbBill();
	
	private PageBean pageBean ;
	
	private List<TbUser> userList ;
	
	private ITbUserServ tbUserServ ;
	
	private int[] state ;
	
	private String startTime ;
	
	private String endTime ;
	
	private IClientBillServ clientBillServ ;
	
	private String strTravelerName;
	
	private String strIndentyNumber;

	public Object getModel() {
		return tbBill;
	}
	
	
	
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



	/**
	 * 旅行社部门进入查询保单页面，初始化一些数据
	 * @return
	 */
	public String toQueryAllBillByPartment(){
		userList = tbUserServ.getTbUserListByPartmentId(getUser().getIntPartmentId()) ;
		return SUCCESS ;
	}
	
	/**
	 * 旅行社部门根据条件综合查询保单
	 * @return
	 */
	public String queryBillByComditionForUser(){
		
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
			pageBean = clientBillServ.getBillByComditionForUser(pageBean, tbBill,state, startTime, endTime) ;
		else
			pageBean = clientBillServ.getBillByComditionForUser_2010(pageBean, strTravelerName, strIndentyNumber, tbBill, state, startTime, endTime);
		
		session.setAttribute("BX_strIndentyNumber", strIndentyNumber);
		session.setAttribute("BX_strTravelerName", strTravelerName);
		return SUCCESS;
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


	public int[] getState() {
		return state;
	}



	public void setState(int[] state) {
		this.state = state;
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



	public IClientBillServ getClientBillServ() {
		return clientBillServ;
	}



	public void setClientBillServ(IClientBillServ clientBillServ) {
		this.clientBillServ = clientBillServ;
	}

	
	
	
}

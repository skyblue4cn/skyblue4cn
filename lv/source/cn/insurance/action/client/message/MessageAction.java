package cn.insurance.action.client.message;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;

import cn.insurance.action.SupportAction;
import cn.insurance.model.PageBean;
import cn.insurance.service.ITbMessageServ;
import cn.insurance.service.client.IClientBillServ;
import cn.insurance.service.client.IClientMessageServ;
import cn.insurance.utils.CommonWords;

public class MessageAction extends SupportAction {

	private PageBean pageBean;
	//被退回保单的pagebean
	private PageBean pageBeanReturn;

	public PageBean getPageBeanReturn() {
		return pageBeanReturn;
	}

	public void setPageBeanReturn(PageBean pageBeanReturn) {
		this.pageBeanReturn = pageBeanReturn;
	}

	public IClientBillServ getClientBillServ() {
		return clientBillServ;
	}

	public void setClientBillServ(IClientBillServ clientBillServ) {
		this.clientBillServ = clientBillServ;
	}

	private IClientBillServ clientBillServ;
	
	public List<String> getMsg() {
		return msg;
	}

	public void setMsg(List<String> msg) {
		this.msg = msg;
	}

	private List<String> msg;

	private List<String> msg2;
	
	public List<String> getMsg2() {
		return msg2;
	}

	public void setMsg2(List<String> msg2) {
		this.msg2 = msg2;
	}

	private IClientMessageServ clientMessageServ;

	/**
	 * 查询该用户所在部门的待阅信息
	 * 
	 * @return
	 */
	public String queryTheUserPartmentMessage(){
		pageBean = clientMessageServ.getDYMessage(pageBean, getUser().getIntPartmentId());
		
		msg = this.clientMessageServ.getMessage_2010(getUser().getIntPartmentId());
		pageBeanReturn.setRowsPerPage(4);
		pageBeanReturn=clientBillServ.getReturnBillByPartmentId(pageBeanReturn, getUser().getIntPartmentId());
		System.out.println(pageBeanReturn.getDataList().size()+" --------------------------");
		ActionContext actionContext =ActionContext.getContext();
		Map session = actionContext.getSession();
	    session.put("", 1);
		
		
		return SUCCESS ;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public IClientMessageServ getClientMessageServ() {
		return clientMessageServ;
	}

	public void setClientMessageServ(IClientMessageServ clientMessageServ) {
		this.clientMessageServ = clientMessageServ;
	}

}

package cn.insurance.action.client.account;

import cn.insurance.action.SupportAction;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbPartment;
import cn.insurance.service.ITbPayOutInfoServ;
import cn.insurance.service.client.IClientYuCunServ;

public class YuCunAction extends SupportAction{
	
	private TbPartment tbPartment ;
	
	private PageBean pageBean ;
	
	private IClientYuCunServ clientYuCunServ ;
	
	/**
	 * 查询帐户的预存收款记录
	 * @return
	 */
	public String getYuCunCunLog(){
		tbPartment = getUser().getTbPartment() ;
		pageBean = clientYuCunServ.getYuCunPayLogByAccountId(pageBean,tbPartment.getTbAccount().getId() ,1) ;
		return SUCCESS ;
	}
	
	/**
	 * 查询帐户的预存支付记录记录
	 * @return
	 */
	public String getYuCunPayLog(){
		tbPartment = getUser().getTbPartment() ;
		/*支付记录的intPayTypeId=2*/
		pageBean = clientYuCunServ.getYuCunPayLogByAccountId(pageBean, tbPartment.getTbAccount().getId(),2) ;
		return SUCCESS ;
	}

	public TbPartment getTbPartment() {
		return tbPartment;
	}

	public void setTbPartment(TbPartment tbPartment) {
		this.tbPartment = tbPartment;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public IClientYuCunServ getClientYuCunServ() {
		return clientYuCunServ;
	}

	public void setClientYuCunServ(IClientYuCunServ clientYuCunServ) {
		this.clientYuCunServ = clientYuCunServ;
	}
	
	
	
}

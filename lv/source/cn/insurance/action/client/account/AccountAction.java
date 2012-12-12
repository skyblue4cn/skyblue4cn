package cn.insurance.action.client.account;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ModelDriven;

import cn.insurance.action.SupportAction;
import cn.insurance.model.TbAccount;
import cn.insurance.model.TbUser;
import cn.insurance.service.ITbAccountServ;
import cn.insurance.service.client.IClientAccountServ;

public class AccountAction extends SupportAction implements ModelDriven{
	
	public TbAccount tbAccount = new TbAccount() ;
	
	private IClientAccountServ clientAccountServ;
	
	public Object getModel() {
		return tbAccount;
	}
	
	/**
	 * 旅行社用户结算
	 * 通过用户session中的帐户iD查询帐户信息
	 * @return
	 */
	public String toJieSuanByUser(){
		tbAccount = clientAccountServ.getAccountById(getUser().getTbPartment().getTbAccount().getId()) ;
		return SUCCESS ;
		
	}

	public IClientAccountServ getClientAccountServ() {
		return clientAccountServ;
	}

	public void setClientAccountServ(IClientAccountServ clientAccountServ) {
		this.clientAccountServ = clientAccountServ;
	}


	
	
	
	
	
}

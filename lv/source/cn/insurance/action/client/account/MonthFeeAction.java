package cn.insurance.action.client.account;

import com.opensymphony.xwork.ModelDriven;

import cn.insurance.action.SupportAction;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbMonthPayInfo;
import cn.insurance.model.TbPartment;
import cn.insurance.service.ITbMonthPayInfoServ;
import cn.insurance.service.client.IClientMonthFeeServ;

public class MonthFeeAction extends SupportAction implements ModelDriven{
	
	public TbMonthPayInfo tbMonthPayInfo = new TbMonthPayInfo() ;
	
	private IClientMonthFeeServ clientMonthFeeServ ;
	
	private PageBean pageBean ;
	

	public Object getModel() {
		return tbMonthPayInfo;
	}

	/**
	 * 根据帐户ID查询该帐户的所有月费结算记录
	 * @return
	 */
	public String getMonthPayLog(){
		TbPartment tbPartment = getUser().getTbPartment();
		tbMonthPayInfo.setTbPartment(tbPartment) ;
		pageBean = clientMonthFeeServ.getMonthPayByAccountId(pageBean, tbPartment.getTbAccount().getId()) ;
		return SUCCESS ;
	}

	/**
	 * 按ID查询月结算记录
	 * @return
	 */
	public String getMonthPayInfoById(){
		//对id进行加密
		tbMonthPayInfo.setId(limitId) ;
		tbMonthPayInfo = clientMonthFeeServ.getMonthPayInfoById(tbMonthPayInfo.getId()) ;
		return SUCCESS ;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public IClientMonthFeeServ getClientMonthFeeServ() {
		return clientMonthFeeServ;
	}

	public void setClientMonthFeeServ(IClientMonthFeeServ clientMonthFeeServ) {
		this.clientMonthFeeServ = clientMonthFeeServ;
	}


	
	
}

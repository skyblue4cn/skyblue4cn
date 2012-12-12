package cn.insurance.action.admin.account;

import com.opensymphony.xwork.ModelDriven;

import cn.insurance.action.SupportAction;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbPartment;
import cn.insurance.model.TbPayOutInfo;
import cn.insurance.service.admin.IAdminPartmentServ;
import cn.insurance.service.admin.IAdminYuCunServ;

/**
 * 账户预存操作
 * @author 何青松 
 * 修改时间 2012/11/20 14:27
 */
public class YuCunAction extends SupportAction implements ModelDriven{
	
	private static final long serialVersionUID = 1L;
	public TbPayOutInfo tbPayOutInfo = new TbPayOutInfo() ;
	private IAdminYuCunServ adminYuCunServ ;
	private PageBean pageBean ;
	private TbPartment tbPartment ;
	private IAdminPartmentServ adminPartmentServ ;
	private int nodeid ;
	public Object getModel() {
		return tbPayOutInfo;
	}
	
	/**
	 * 查询帐户的预存收款记录
	 * @return
	 */
	public String getYuCunCunLog(){
		//System.out.println("--");
		tbPartment = adminPartmentServ.getPartmentInfoById(nodeid) ;
		pageBean = adminYuCunServ.getYuCunPayLogByAccountId(pageBean,tbPartment.getTbAccount().getId() ,1) ;
		return SUCCESS ;
	}
	
	/**
	 * 查询帐户的预存支付记录记录
	 * @return
	 */
	public String getYuCunPayLog(){
		tbPartment = adminPartmentServ.getPartmentInfoById(nodeid) ;
		/*支付记录的intPayTypeId=2*/
		pageBean = adminYuCunServ.getYuCunPayLogByAccountId(pageBean,tbPartment.getTbAccount().getId(),2) ;
		return SUCCESS ;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public TbPartment getTbPartment() {
		return tbPartment;
	}

	public void setTbPartment(TbPartment tbPartment) {
		this.tbPartment = tbPartment;
	}

	public IAdminPartmentServ getAdminPartmentServ() {
		return adminPartmentServ;
	}

	public void setAdminPartmentServ(IAdminPartmentServ adminPartmentServ) {
		this.adminPartmentServ = adminPartmentServ;
	}

	public IAdminYuCunServ getAdminYuCunServ() {
		return adminYuCunServ;
	}

	public void setAdminYuCunServ(IAdminYuCunServ adminYuCunServ) {
		this.adminYuCunServ = adminYuCunServ;
	}

	public int getNodeid() {
		return nodeid;
	}

	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}
}

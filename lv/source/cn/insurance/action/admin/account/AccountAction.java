package cn.insurance.action.admin.account;

import com.opensymphony.xwork.ModelDriven;

import cn.insurance.action.SupportAction;
import cn.insurance.model.TbAccount;
import cn.insurance.model.TbPayOutInfo;
import cn.insurance.service.admin.IAdminAccountServ;
/**
 * 存钱与账户管理
 * @author 
 * 修改时间 2012/11/20 14:23
 */
public class AccountAction extends SupportAction implements ModelDriven{
	
	private static final long serialVersionUID = 1L;
	public TbAccount tbAccount = new TbAccount() ;
	private IAdminAccountServ adminAccountServ ;
	private TbPayOutInfo tbPayOutInfo ;
	private double dbeReNumber ;
	private int nodeid ;
	
	public Object getModel() {
		return tbAccount;
	}
	
	/**
	 * 通过旅行社或部门ID查询账户信息
	 * @return
	 */
	public String getAccountByPartmentId(){
		tbAccount = adminAccountServ.getAccountByPartmentId(nodeid);
		return SUCCESS ;
	}
	
	public String getAccountById(){
		tbAccount = adminAccountServ.getAccountById(tbAccount.getId()) ;
		return SUCCESS ;
	}
	
	/**
	 * 帐户更改设置
	 * @return
	 */
	public String updateAccountProperty(){
		adminAccountServ.updateAccountProperty(tbAccount) ;
		addActionMessage("更新成功！") ;
		return SUCCESS ;
	}

	/**
	 * 预存帐户收费存钱
	 * @return
	 */
	public String shouFeeForYuCun(){
		if(dbeReNumber != tbPayOutInfo.getDbePayoutNumber()){
			addActionMessage("两次输入存款金额不一样，操作失败！") ;
			return INPUT ;
		}
		try{
			adminAccountServ.shouFeeForYuCun(tbPayOutInfo) ;
		}catch(Exception e){
			addActionMessage("操作失败！") ;
			return INPUT ;
		}
		addActionMessage("操作成功！") ;
		return SUCCESS ;
	}

	public TbPayOutInfo getTbPayOutInfo() {
		return tbPayOutInfo;
	}


	public void setTbPayOutInfo(TbPayOutInfo tbPayOutInfo) {
		this.tbPayOutInfo = tbPayOutInfo;
	}

	public IAdminAccountServ getAdminAccountServ() {
		return adminAccountServ;
	}

	public void setAdminAccountServ(IAdminAccountServ adminAccountServ) {
		this.adminAccountServ = adminAccountServ;
	}

	public double getDbeReNumber() {
		return dbeReNumber;
	}

	public void setDbeReNumber(double dbeReNumber) {
		this.dbeReNumber = dbeReNumber;
	}

	public int getNodeid() {
		return nodeid;
	}

	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}

	
}

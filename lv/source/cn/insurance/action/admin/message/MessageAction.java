package cn.insurance.action.admin.message;

import cn.insurance.action.SupportAction;
import cn.insurance.model.PageBean;
import cn.insurance.service.admin.IAdminMessageServ;
import cn.insurance.utils.CommonWords;
/**
 * 保险公司的代办代审阅事务
 * @author Administrator
 *
 */
public class MessageAction extends SupportAction{
	
	private static final long serialVersionUID = 1L;
	private int newBillNumber ;
	private int newUpdateApply ;
	private PageBean pageBean ;
	private IAdminMessageServ adminMessageServ ;
	
	/**
	 * 查询保险公司的代办事务消息
	 * @return
	 */
	public String getDbMessageForBx(){
		/*新申请需要确认的保单*/
		newBillNumber = adminMessageServ.getNewBillNumberNeedSureByBx() ;
		/*备案保单用户更新后需要处理*/
		newUpdateApply = adminMessageServ.getAllNeedApplyCount();
		System.out.println(newUpdateApply);
		return SUCCESS ;
	}
	
	/**
	 * 查询保险公司的待阅事务消息
	 * @return
	 */
	public String getDyMessageForBx(){
		pageBean = adminMessageServ.getMessageForBx(pageBean, CommonWords.messageType2) ;
		return SUCCESS ;
	}


	public int getNewBillNumber() {
		return newBillNumber;
	}

	public void setNewBillNumber(int newBillNumber) {
		this.newBillNumber = newBillNumber;
	}

	public int getNewUpdateApply() {
		return newUpdateApply;
	}

	public void setNewUpdateApply(int newUpdateApply) {
		this.newUpdateApply = newUpdateApply;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}


	public IAdminMessageServ getAdminMessageServ() {
		return adminMessageServ;
	}

	public void setAdminMessageServ(IAdminMessageServ adminMessageServ) {
		this.adminMessageServ = adminMessageServ;
	}
	
	
}

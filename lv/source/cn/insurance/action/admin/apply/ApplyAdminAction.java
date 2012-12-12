package cn.insurance.action.admin.apply;

import com.opensymphony.xwork.ModelDriven;

import cn.insurance.action.SupportAction;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbApply;
import cn.insurance.service.admin.IAdminApplyServ;

/**
 * 保险公司对批改申请的处理和查询
 * @author yqg
 * 
 */
public class ApplyAdminAction extends SupportAction implements ModelDriven{
	
	private static final long serialVersionUID = 1L;
	public TbApply tbApply = new TbApply() ;
	private PageBean pageBean ;
	private IAdminApplyServ adminApplyServ ;
	
	public Object getModel() {
		return tbApply;
	}
	
	/**
	 * 查询所有未回复的申请
	 * @return
	 */
	public String getAllNotReplyOfApplyForBx(){
		pageBean = adminApplyServ.getAllNeedApplyPageBean(pageBean) ;
		return SUCCESS ;
	}
	
	/**
	 * 查询所有已回复的申请
	 * @return
	 */
	public String getAllHasReplyOfApplyForBx(){
		pageBean = adminApplyServ.getAllHasApplyPageBean(pageBean) ;
		return SUCCESS ;
	}
	
	/**
	 * 根据ID查询申请内容
	 * @return
	 */
	public String getApplyById(){
		tbApply = adminApplyServ.getApplyById(tbApply.getId()) ;
		return SUCCESS ;
	}
	
	
	/**
	 * 保险公司对申请进行回复
	 */
	public String addReplyForApply(){
		adminApplyServ.addReplyByBx(tbApply) ;
		addActionMessage("回复成功！") ;
		return SUCCESS ;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public IAdminApplyServ getAdminApplyServ() {
		return adminApplyServ;
	}

	public void setAdminApplyServ(IAdminApplyServ adminApplyServ) {
		this.adminApplyServ = adminApplyServ;
	}
	
}

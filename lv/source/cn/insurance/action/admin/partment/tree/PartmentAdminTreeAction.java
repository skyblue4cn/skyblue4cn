package cn.insurance.action.admin.partment.tree;

import cn.insurance.action.SupportAction;
import cn.insurance.service.admin.IAdminPartmentTreeServ;

import com.sunnydale.include.RoleTreeBean;

public class PartmentAdminTreeAction extends SupportAction{
	
	private static final long serialVersionUID = 1L;
	private IAdminPartmentTreeServ adminPartmentTreeServ ;
	private RoleTreeBean partmentRoleTreeBean ;
	private int nodeid ;
	

	private String uservalue;//此变量标记从客户端提交的模糊查询功能
	
	public String getUservalue() {
		return uservalue;
	}

	public void setUservalue(String uservalue) {
		this.uservalue = uservalue;
	}	
	
	/**
	 * 根据用户给定的值进行模糊查询
	 * @return SUCCESS
	 * 何青松
	 */
	public String queryValue() throws Exception{
		this.partmentRoleTreeBean =this.adminPartmentTreeServ.getPartmentTreeUserValue(this.getUservalue());
		return SUCCESS;
	}
	
	/**
	 * 生成旅行社管理的树形结构
	 * @return
	 */
	public String getPartmentAdminTree(){
		partmentRoleTreeBean = adminPartmentTreeServ.getPartmentTree() ;
		return SUCCESS ;
	}
	
	/**
	 * 选择节点
	 * @return
	 */
	public String nodeSelect(){
		return SUCCESS ;
	}

	public IAdminPartmentTreeServ getAdminPartmentTreeServ() {
		return adminPartmentTreeServ;
	}

	public void setAdminPartmentTreeServ(
			IAdminPartmentTreeServ adminPartmentTreeServ) {
		this.adminPartmentTreeServ = adminPartmentTreeServ;
	}

	public RoleTreeBean getPartmentRoleTreeBean() {
		return partmentRoleTreeBean;
	}

	public void setPartmentRoleTreeBean(RoleTreeBean partmentRoleTreeBean) {
		this.partmentRoleTreeBean = partmentRoleTreeBean;
	}

	public int getNodeid() {
		return nodeid;
	}

	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}
}

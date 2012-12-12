package cn.insurance.action.admin.role;

import com.opensymphony.xwork.ActionSupport;

public class RoleRight extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private int roleId ;
		
	public String getRoleRightByRoleId(){	
		return SUCCESS ;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}

package cn.insurance.action.admin.right;


import java.util.List;

import com.opensymphony.xwork.ModelDriven;

import cn.insurance.action.SupportAction;
import cn.insurance.model.right.TbRole;
import cn.insurance.service.right.ITbRoleServ;

/**
 * 角色管理
 * @author 何青松
 * 修改时间 2012/11/20 14:49
 */
public class RoleAction extends SupportAction implements ModelDriven{
	
	private static final long serialVersionUID = 1L;
	public TbRole tbRole = new TbRole();
	private ITbRoleServ tbRoleServ ;
	private List<TbRole> bxRoleList ;
	private List<TbRole> lxsRoleList ;
	
	public Object getModel() {
		return tbRole;
	}
	
	/**
	 * 所有角色类型
	 * @return
	 */
	public String roleList(){
		bxRoleList = tbRoleServ.getAllBxRoleList() ;
		lxsRoleList = tbRoleServ.getAllLxsRoleList() ;
		return SUCCESS ;
	}
	
	public String toAddBxRole(){
		tbRole.setIntTypeId(TbRole.BX_ROLE_TYPE) ;
		return SUCCESS ;
	}
	
	public String toAddLxsRole(){
		tbRole.setIntTypeId(TbRole.LXS_ROLE_TYPE) ;
		return SUCCESS ;
	}

	/**
	 * 添加
	 * @return
	 */
	public String addRole(){
		tbRoleServ.addRole(tbRole) ;
		return SUCCESS ;
	}
	
	/**
	 * 修改角色
	 * @return
	 */
	public String toUpdateRole(){
		tbRole = tbRoleServ.getTbRoleById(tbRole.getId()) ;
		return SUCCESS ;
	}
	
	/**
	 * 修改
	 * @return
	 */
	public String updateRole(){
		tbRoleServ.updateRole(tbRole) ;
		addActionMessage("修改成功!") ;
		return SUCCESS ;
	}
	
	/**
	 * 删除角色
	 * @return
	 */
	public String toDeleteRole(){
		int userNum = tbRoleServ.getDeleteRoleUserNum(tbRole.getId()) ;
		if(userNum > 0){
			String message = "有" +userNum +"用户是这个角色，请先将用户更改为其他角色！" ;
			addActionError(message) ;
		}else{
			addActionMessage("确定删除吗？") ;
		}
		return SUCCESS ;
	}
	/**
	 * 删除角色
	 * @return
	 */
	public String deleteRole(){
		toDeleteRole() ;
		if(hasActionErrors()){
			return INPUT ;
		}
		tbRoleServ.deleteRoleById(tbRole.getId()) ;
		clearErrorsAndMessages() ;
		addActionMessage("操作成功！") ;
		return SUCCESS ;
	}
	
	public ITbRoleServ getTbRoleServ() {
		return tbRoleServ;
	}

	public void setTbRoleServ(ITbRoleServ tbRoleServ) {
		this.tbRoleServ = tbRoleServ;
	}

	public List<TbRole> getBxRoleList() {
		return bxRoleList;
	}

	public void setBxRoleList(List<TbRole> bxRoleList) {
		this.bxRoleList = bxRoleList;
	}

	public List<TbRole> getLxsRoleList() {
		return lxsRoleList;
	}

	public void setLxsRoleList(List<TbRole> lxsRoleList) {
		this.lxsRoleList = lxsRoleList;
	}
	
}

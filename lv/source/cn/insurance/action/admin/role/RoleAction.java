package cn.insurance.action.admin.role;

import java.util.List;

import com.opensymphony.xwork.ModelDriven;

import cn.insurance.action.SupportAction;
import cn.insurance.commonwords.RoleKey;
import cn.insurance.model.right.TbRole;
import cn.insurance.service.admin.IAdminRoleServ;
/**
 * 保险公司内部角色管理
 * @author 何青松
 * 修改时间 2012/11/20 14:53
 */
public class RoleAction extends SupportAction implements ModelDriven{
	
	private static final long serialVersionUID = 1L;
	public TbRole tbRole = new TbRole();
	private IAdminRoleServ adminRoleServ ;
	private List<TbRole> bxRoleList ;

	public Object getModel() {
		return tbRole;
	}
	
	/**
	 * 查询所有保险公司的内部角色
	 * @return
	 */
	public String getAllBxRoleList(){
		bxRoleList = adminRoleServ.getAllBxRoleList();
		return SUCCESS ;
	}
	
	/**
	 * 添加保险公司内部的角色
	 * 
	 * @return
	 */
	public String addBxRole(){
		if(!checkConfig()){
			return INPUT ;
		}
		tbRole.setIntTypeId(RoleKey.BX_ROLE_TYPE);
		adminRoleServ.addTbRole(tbRole) ;
		addActionMessage("添加成功！");
		return SUCCESS ;
	}
	
	/**
	 * 添加旅行社的角色
	 * @return
	 */
	public String addLxsRole(){
		if(!checkConfig()){
			return INPUT ;
		}
		tbRole.setIntTypeId(RoleKey.LXS_ROLE_TYPE);
		adminRoleServ.addTbRole(tbRole) ;
		addActionMessage("添加成功！");
		return SUCCESS ;
	}
	
	/**
	 * 通过id查询角色信息
	 * @return
	 */
	public String getRoleById(){
		tbRole = adminRoleServ.getRoleById(tbRole.getId());
		return SUCCESS ;
	}
	
	/**
	 * 
	 * 修改角色信息
	 * @return
	 */
	public String updateRole(){
		if(!checkConfig()){
			return INPUT ;
		}
		adminRoleServ.updateTbRole(tbRole) ;
		addActionMessage("更新成功！");
		return SUCCESS ;
	}
	
	/**
	 * 添加角色时检查参数
	 * @return
	 */
	private boolean checkConfig(){
		if(tbRole.getStrRoleName()== null || "".equals(tbRole.getStrRoleName().trim())){
			addActionError("请填写角色名称！") ;
			return  false ;
		}
		return true ;
	}
	
	public List<TbRole> getBxRoleList() {
		return bxRoleList;
	}

	public void setBxRoleList(List<TbRole> bxRoleList) {
		this.bxRoleList = bxRoleList;
	}

	public IAdminRoleServ getAdminRoleServ() {
		return adminRoleServ;
	}

	public void setAdminRoleServ(IAdminRoleServ adminRoleServ) {
		this.adminRoleServ = adminRoleServ;
	}
	
	
	
}

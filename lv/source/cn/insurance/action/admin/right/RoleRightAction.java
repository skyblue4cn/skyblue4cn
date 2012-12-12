package cn.insurance.action.admin.right;

import java.util.ArrayList;
import java.util.List;

import cn.insurance.action.SupportAction;
import cn.insurance.model.right.RoleAndRightXref;
import cn.insurance.model.right.TbRight;
import cn.insurance.model.right.TbRole;
import cn.insurance.model.right.TbRoleRight;
import cn.insurance.service.right.ITbRightServ;
import cn.insurance.service.right.ITbRoleRightServ;
import cn.insurance.service.right.ITbRoleServ;

/**
 * 角色权限管理
 * @author 何青松
 * 修改时间 2012/11/20 14:51
 */
public class RoleRightAction extends SupportAction{

	private static final long serialVersionUID = 1L;
	private int intRoleId ;
	private List<RoleAndRightXref> RoleAndRightXrefList ;
	private ITbRoleServ  tbRoleServ;
	private ITbRightServ tbRightServ ;
	private ITbRoleRightServ tbRoleRightServ ;
	private List<TbRight> allRightList ; //根据角色的类型查询，比如保险公司，就是保险公司所有的权限
	private List<TbRoleRight> roleRightList ; //这个角色的权限
	private TbRole tbRole ;
	private int[] rightIds ;
	
	/**
	 * 查询角色权限，为角色附权限查询数据
	 * @return
	 */
	public String toAssignRightToRole(){
		//根据角色类型，查询该角色所有的权限
		tbRole = tbRoleServ.getTbRoleById(intRoleId) ;
		//这个角色的权限
		roleRightList = tbRoleRightServ.getRoleRightListByRoleId(intRoleId) ;
		if(tbRole.getIntTypeId() == TbRole.BX_ROLE_TYPE){
			//如果是保险公司的角色
			allRightList = tbRightServ.getAllBxRightList() ;
		}
		else if(tbRole.getIntTypeId() == TbRole.LXS_ROLE_TYPE){
			//如果是旅行社的角色
			allRightList = tbRightServ.getAllLxsRightList() ;
		}
		//将角色权限和所有权限组合为RoleAndRightXrefList
		setTheRoleRightAndAllRight();
		return SUCCESS ;
	}
	
	/**
	 * 给角色分配权限
	 * @return
	 */
	public String assignRightToRole(){
		tbRoleRightServ.assignRoleRight(intRoleId, rightIds) ;
		addActionMessage("操作成功！") ;
		return SUCCESS ;
	}
	
	/**
	 * 将角色的权限和所有的权限进行组合为RoleAndRightXrefList
	 */
	private void setTheRoleRightAndAllRight(){
		RoleAndRightXrefList = new ArrayList<RoleAndRightXref>();
		for(TbRight right : allRightList){
			RoleAndRightXref rax = new RoleAndRightXref();
			rax.setIntRightId(right.getId()) ;
			rax.setIntRoleId(intRoleId) ;
			rax.setIsHasRight(checkTheRoleIsHasRight(right.getId())) ;
			rax.setTbRight(right) ;
			RoleAndRightXrefList.add(rax) ;
		}
	}

	/**
	 * 检查这个角色是否有这个权限
	 * 有这个权限返回1 
	 * 没有返回0
	 * @param rightId
	 * @return
	 */
	private int checkTheRoleIsHasRight(int rightId){
		if(roleRightList == null || roleRightList.size() ==0){
			return 0 ;
		}
		for(TbRoleRight rr : roleRightList){
			if(rr.getIntRightId().intValue() == rightId){
				return 1 ;
			}
		}
		return 0 ;
	}
	
	public int getIntRoleId() {
		return intRoleId;
	}

	public void setIntRoleId(int intRoleId) {
		this.intRoleId = intRoleId;
	}

	public List<RoleAndRightXref> getRoleAndRightXrefList() {
		return RoleAndRightXrefList;
	}

	public void setRoleAndRightXrefList(List<RoleAndRightXref> roleAndRightXrefList) {
		RoleAndRightXrefList = roleAndRightXrefList;
	}

	public ITbRoleServ getTbRoleServ() {
		return tbRoleServ;
	}

	public void setTbRoleServ(ITbRoleServ tbRoleServ) {
		this.tbRoleServ = tbRoleServ;
	}

	public ITbRightServ getTbRightServ() {
		return tbRightServ;
	}

	public void setTbRightServ(ITbRightServ tbRightServ) {
		this.tbRightServ = tbRightServ;
	}

	public ITbRoleRightServ getTbRoleRightServ() {
		return tbRoleRightServ;
	}

	public void setTbRoleRightServ(ITbRoleRightServ tbRoleRightServ) {
		this.tbRoleRightServ = tbRoleRightServ;
	}

	public List<TbRight> getAllRightList() {
		return allRightList;
	}

	public void setAllRightList(List<TbRight> allRightList) {
		this.allRightList = allRightList;
	}

	public List<TbRoleRight> getRoleRightList() {
		return roleRightList;
	}

	public void setRoleRightList(List<TbRoleRight> roleRightList) {
		this.roleRightList = roleRightList;
	}

	public TbRole getTbRole() {
		return tbRole;
	}

	public void setTbRole(TbRole tbRole) {
		this.tbRole = tbRole;
	}

	public int[] getRightIds() {
		return rightIds;
	}

	public void setRightIds(int[] rightIds) {
		this.rightIds = rightIds;
	}	
}

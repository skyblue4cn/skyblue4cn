package cn.insurance.action.admin.user;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork.ModelDriven;

import cn.insurance.action.SupportAction;
import cn.insurance.model.TbUser;
import cn.insurance.model.right.TbRole;
import cn.insurance.service.admin.IAdminPartmentServ;
import cn.insurance.service.admin.IAdminRoleServ;
import cn.insurance.service.pub.ITbUserServ;

/**
 * 系统加载的第一个Action用于初始化系统数据
 * @author 何青松
 * 修改时间 2012/11/20 15:05
 */
public class UserAtion extends SupportAction implements ModelDriven{
	
	private static final long serialVersionUID = 1L;
	public TbUser tbUser = new TbUser() ;
	private ITbUserServ tbUserServ ;
	private IAdminRoleServ adminRoleServ ;
	private List<TbUser> tbUserList = new ArrayList<TbUser>() ;
	private List<TbRole> tbRoleList = new ArrayList<TbRole>() ;
	private IAdminPartmentServ adminPartmentServ ;
	private int isUpdatePassword ;
	private int nodeid ;
	
	public Object getModel() {
		return tbUser;
	}
	
	public String getUserById(){
		tbUser = tbUserServ.getUserInfoById(tbUser.getId()) ;
		return SUCCESS ;
	}
	
	/**
	 * 通过部门或旅行社的ID取得所有用户
	 * @return
	 */
	public String getAllUserByPartmentId(){
		if(nodeid != 0){
			tbUser.setIntPartmentId(nodeid);
		}
		tbUser.setTbPartment(adminPartmentServ.getPartmentInfoById(tbUser.getIntPartmentId())) ;
		tbUserList = tbUserServ.getTbUserListByPartmentId(tbUser.getIntPartmentId()) ;
		return SUCCESS ;
	}
	
	/**
	 * 查询所有保险公司的内部用户
	 * @return
	 */
	public String getBxAllUser(){
		tbUserList =  tbUserServ.getBxAllUser();
		return SUCCESS ;
	}
	
	/**
	 * 进入添加旅行社用户的界面
	 * @return
	 */
	public String toAddLxsUser(){
		tbUser.setTbPartment(adminPartmentServ.getPartmentInfoById(tbUser.getIntPartmentId())) ;
		/*查询所有适用于旅行社的角色*/
		tbRoleList= adminRoleServ.getAllLxsRoleList() ;
		return SUCCESS ;
	}
	
	/**
	 * 添加旅行社用户时，检查参数
	 * @return
	 */
	private boolean validate_addLxsUser(){
		if(tbUser.getStrUserLogName()==null || "".equals(tbUser.getStrUserLogName().trim())){
			addActionError("请填写用户名！") ;
			return false ;
		}
		if(tbUser.getStrUserName() == null || "".equals(tbUser.getStrUserName().trim())){
			addActionError("请填写用户姓名！") ;
			return false ;
		}
		if(tbUser.getIntRoleId() == 0){
			addActionError("请选择用户角色！") ;
			return false ;
		}
		List<TbUser> ulist = tbUserServ.getUserListByUserName(tbUser.getStrUserLogName()) ;
		if(ulist!= null && ulist.size()>0){
			addActionError("该用户名已存在，请更换用户名！") ;
			return false ;
		}
		return true ;
	}
	
	/**
	 * 添加旅行社用户
	 * @return
	 */
	public String addLxsUser(){
		if(!validate_addLxsUser()){
			tbRoleList= adminRoleServ.getAllLxsRoleList();
			return INPUT ;
		}
		
		int result = tbUserServ.addUser(tbUser) ;
		if(result == 1){
			addActionMessage("添加成功！") ;
			return SUCCESS ;
		}
		addActionError("添加失败！") ;
		return INPUT ;
	}
	
	/**
	 * 进入添加内部人员界面
	 * @return
	 */
	public String toAddBxUser(){
		tbRoleList = adminRoleServ.getAllBxRoleList() ;
		return SUCCESS ;
	}
	
	/**
	 * 添加用户
	 */
	public String addBxUser(){
		tbUser.setIntPartmentId(null) ;
		tbUserServ.addUser(tbUser) ;
		return SUCCESS ;
	}

	/**
	 * 删除用户或将用户拖入黑名单
	 */
	public String removeUser(){
		tbUserServ.deleteUser(tbUser.getId()) ;
		return SUCCESS ;
	
	}
	
	/**
	 * 保险公司修改旅行社用户信息
	 * @return
	 */
	public String toUpdateUserByBx(){
		/*查询所有适用于旅行社的角色*/
		tbRoleList= adminRoleServ.getAllLxsRoleList();
		tbUser = tbUserServ.getUserInfoById(tbUser.getId()) ;
		return SUCCESS ;
	}
	
	/**
	 * 检查更新旅行社用户的参数
	 * @return
	 */
	private boolean validate_updatelxsUserByBx(){
		if(tbUser.getStrUserLogName()==null || "".equals(tbUser.getStrUserLogName().trim())){
			addActionError("请填写用户名！") ;
			return false ;
		}
		if(tbUser.getStrUserName() == null || "".equals(tbUser.getStrUserName().trim())){
			addActionError("请填写用户姓名！") ;
			return false ;
		}
		if(tbUser.getIntRoleId() == 0){
			addActionError("请选择用户角色！") ;
			return false ;
		}
		List<TbUser> ulist = tbUserServ.getUserListByUserName(tbUser.getStrUserLogName()) ;
		if(ulist!= null && ulist.size()>0){
			if(ulist.get(0).getId().intValue() != tbUser.getId().intValue()){
				addActionError("该用户名已存在，请更换用户名！") ;
				return false ;
			}
		}
		return true ;
	}
	
	/**
	 * 更新旅行社的用户信息
	 * @return
	 */
	public String updatelxsUserByBx(){
		tbRoleList= adminRoleServ.getAllLxsRoleList();
		if(! validate_updatelxsUserByBx()){
			return INPUT ;
		}
		return updateUserInfoByBx() ;
	}
		
	/**
	 * 保险公司修改内部用户信息
	 * @return
	 */
	public String toUpdateBxUser(){
		/*查询所有适用于旅行社的角色*/
		tbRoleList= adminRoleServ.getAllBxRoleList();
		tbUser = tbUserServ.getUserInfoById(tbUser.getId()) ;
		return SUCCESS ;
	}
	
	/**
	 * 管理员修改内部用户
	 * @return
	 */
	public String updateBxUserInfoByBx(){
		tbRoleList= adminRoleServ.getAllBxRoleList() ;
		return updateUserInfoByBx();
	}
		
	private String updateUserInfoByBx(){
		if(isUpdatePassword == 1){
			if("".equals(tbUser.getStrUserPassword().trim())){
				addActionMessage("请填写密码!") ;
				tbUser = tbUserServ.getUserInfoById(tbUser.getId()) ;
				return INPUT ;
			}
			if(tbUser.getStrUserPassword().length()<8 || tbUser.getStrUserPassword().length() >16){
				addActionMessage("密码请设定为8-16位！") ;
				tbUser = tbUserServ.getUserInfoById(tbUser.getId()) ;
				return INPUT ;
			}
			if(!tbUser.getStrUserPassword().equals(tbUser.getStrReUserPassword().trim())){
				addActionMessage("两次密码不一致！") ;
				tbUser = tbUserServ.getUserInfoById(tbUser.getId()) ;
				return INPUT ;
			}
		}else{
			tbUser.setStrUserPassword(null) ;
		}
		int result = tbUserServ.update(tbUser) ;
		if(result == 0){
			addActionMessage("该用户名已存在，请更换用户名！") ;
			tbUser = tbUserServ.getUserInfoById(tbUser.getId()) ;
			return INPUT ;
		}
		addActionMessage("修改成功！") ;
		return SUCCESS ;
	}
		/*setter and getter*/
	
	public ITbUserServ getTbUserServ() {
		return tbUserServ;
	}

	public void setTbUserServ(ITbUserServ tbUserServ) {
		this.tbUserServ = tbUserServ;
	}

	public List<TbUser> getTbUserList() {
		return tbUserList;
	}

	public void setTbUserList(List<TbUser> tbUserList) {
		this.tbUserList = tbUserList;
	}

	public List<TbRole> getTbRoleList() {
		return tbRoleList;
	}

	public void setTbRoleList(List<TbRole> tbRoleList) {
		this.tbRoleList = tbRoleList;
	}


	public int getIsUpdatePassword() {
		return isUpdatePassword;
	}

	public void setIsUpdatePassword(int isUpdatePassword) {
		this.isUpdatePassword = isUpdatePassword;
	}

	public IAdminPartmentServ getAdminPartmentServ() {
		return adminPartmentServ;
	}

	public void setAdminPartmentServ(IAdminPartmentServ adminPartmentServ) {
		this.adminPartmentServ = adminPartmentServ;
	}

	public IAdminRoleServ getAdminRoleServ() {
		return adminRoleServ;
	}

	public void setAdminRoleServ(IAdminRoleServ adminRoleServ) {
		this.adminRoleServ = adminRoleServ;
	}

	public int getNodeid() {
		return nodeid;
	}

	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}
}

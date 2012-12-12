package cn.insurance.action.pub;

import java.util.List;
import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.insurance.action.SupportAction;
import cn.insurance.commonwords.Md5Key;
import cn.insurance.commonwords.SessionKey;
import cn.insurance.commonwords.UserKey;
import cn.insurance.commonwords.ValidateKey;
import cn.insurance.model.TbUser;
import cn.insurance.service.pub.ITbUserServ;
import cn.insurance.service.right.ITbResourceServ;

/**
 * 登录Action
 * @author 何青松
 * 修改时间:2012年11月20号 14:12
 */
public class LoginAction extends SupportAction{
	
	private static final long serialVersionUID = 1L;

	private TbUser tbUser ;
	private String j_username ;
	private String j_password ;
	private String j_validate ;
	private ITbUserServ tbUserServ ;
	private ITbResourceServ tbResourceServ ;
	private static final Log log = LogFactory.getLog(LoginAction.class) ;
	
	/**
	 * 登录验证
	 * */
	@SuppressWarnings("unchecked")
	public String login(){
		//先检查验证码
		String validation_code = (String)session.get(ValidateKey.VALIDATE_SESSION_KEY) ;
		if(validation_code == null || !validation_code.equals(j_validate.trim())){
			addActionError("验证码错误！") ;
			return INPUT ;
		}
		
		//先查询所有的用户
		List<TbUser> userList = tbUserServ.getUserListByUserName(j_username.trim()) ;
		if(userList == null || userList.size() == 0){
			addActionError("用户名或密码错误!") ;
			return INPUT ;
		}
		if(userList.size()>1){
			log.error("用户名：" + j_username +"存在多个！") ;
			addActionError("系统错误，请联系管理员！") ;
			return INPUT ;
		}
		tbUser = userList.get(0) ;
		//验证密码是否正确
		String md5Password = new Md5PasswordEncoder().encodePassword(j_password.trim(), Md5Key.User_PASSWORD_KEY) ;
		if(!md5Password.equals(tbUser.getStrUserPassword())){
			//如果密码不匹配，则返回错误信息
			addActionError("用户名或密码错误!") ;
			return INPUT ;
		}
		if(tbUser.getIntUserState() != UserKey.USER_STATE_CAN_LOGIN){
			addActionError("该用户已被限制登录！请联系管理员！") ;
			return INPUT ;
		}
		//查询该用户的权限
		tbUser.setUserResourceMap(tbResourceServ.getResourceMapByRoleId(tbUser.getIntRoleId())) ;
		//将用户保存到session中
		session.put( SessionKey.USER_SESSION_KEY,tbUser) ;
		if(UserKey.INITIALIZE_CODE.equals(j_password.trim())){
			//如果用户密码是初始密码，说明用户是第一次登录，那么跳转到修改信息页面对用户信息进行修改
			log.info("用户:" + j_username +"第一次登录系统，跳转到用户信息修改页面对信息进行修改。") ;
			return "first_log" ;
		}
		
		if(tbUser.getIntPartmentId() !=null && tbUser.getIntPartmentId() != 0 && tbUser.getTbRole().getIntTypeId() == 2){
			log.info("旅行社用户:" + j_username +"登录系统！") ;
			return "lxs_user" ;
		}
		if((tbUser.getIntPartmentId() ==null || tbUser.getIntPartmentId() == 0) && tbUser.getTbRole().getIntTypeId()== 1){
			log.info("保险公司用户:" + j_username +"登录系统！") ;
			return "bx_user" ;
		}
		if((tbUser.getIntPartmentId() ==null || tbUser.getIntPartmentId() == 0) && tbUser.getTbRole().getIntTypeId() == 3){
			log.info("超级管理员:" + j_username +"登录系统！") ;
			return "bx_user" ;
		}
//		//根据用户信息判断该用户是保险公司的用户还是旅行社的用户
//		if(tbUser.getIntUserType() == UserKey.USER_TYPE_LXS){
//			log.info("旅行社用户:" + j_username +"登录系统！") ;
//			return "lxs_user" ;
//		}
//		if(tbUser.getIntUserType() == UserKey.USER_Type_BX){
//			log.info("保险公司用户:" + j_username +"登录系统！") ;
//			return "bx_user" ;
//		}
		return INPUT ;
	}
	
	
	/**
	 * 用户注销
	 * @return
	 */
	public String logout(){
		try{
			if(session != null){
				log.info("用户:" +  getUser().getStrUserLogName() +" 退出系统！");
				session.remove(SessionKey.USER_SESSION_KEY) ;
			}
		}catch(NullPointerException e){
			return SUCCESS ;
		}
		return SUCCESS ;
	}
	
	
	public ITbUserServ getTbUserServ() {
		return tbUserServ;
	}

	public void setTbUserServ(ITbUserServ tbUserServ) {
		this.tbUserServ = tbUserServ;
	}

	public TbUser getTbUser() {
		return tbUser;
	}

	public void setTbUser(TbUser tbUser) {
		this.tbUser = tbUser;
	}

	public String getJ_username() {
		return j_username;
	}

	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}

	public String getJ_password() {
		return j_password;
	}

	public void setJ_password(String j_password) {
		this.j_password = j_password;
	}

	public String getJ_validate() {
		return j_validate;
	}

	public void setJ_validate(String j_validate) {
		this.j_validate = j_validate;
	}

	public ITbResourceServ getTbResourceServ() {
		return tbResourceServ;
	}

	public void setTbResourceServ(ITbResourceServ tbResourceServ) {
		this.tbResourceServ = tbResourceServ;
	}
}

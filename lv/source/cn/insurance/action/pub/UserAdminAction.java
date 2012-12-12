package cn.insurance.action.pub;

import com.opensymphony.xwork.ModelDriven;

import cn.insurance.action.SupportAction;
import cn.insurance.model.TbUser;
import cn.insurance.service.pub.ITbUserServ;

/**
 * 用户修改信息
 * @author yqg
 *
 */
public class UserAdminAction extends SupportAction implements ModelDriven {
	
	private static final long serialVersionUID = 1L;
	public TbUser tbUser = new TbUser() ;
	private ITbUserServ tbUserServ ;
	public Object getModel() {
		return tbUser;
	}
	
	/**
	 * 用户查看信息，进入修改信息页面
	 * @return
	 */
	public String toUpdateUserInfo(){
		tbUser = tbUserServ.getUserInfoById(getUser().getId()) ;
		return SUCCESS ;
	}
	
	/**
	 * 用户更新信息
	 * @return
	 */
	public String updateUserInfo(){
		int result = tbUserServ.update(tbUser) ;
		if(result ==0 ){
			addActionError("该用户名已经存在，请重新设定用户名！") ;
			return INPUT ;
		}
		addActionMessage("用户信息已更改，请重新登录！") ;
		return SUCCESS  ;
	}
	
	public ITbUserServ getTbUserServ() {
		return tbUserServ;
	}

	public void setTbUserServ(ITbUserServ tbUserServ) {
		this.tbUserServ = tbUserServ;
	}
}

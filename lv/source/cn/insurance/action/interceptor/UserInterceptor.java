package cn.insurance.action.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import cn.insurance.model.TbUser;
import cn.insurance.service.right.ITbResourceServ;
import cn.insurance.commonwords.SessionKey;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;

public class UserInterceptor implements Interceptor{
	
	private static final long serialVersionUID = 1L;
	private ITbResourceServ tbResourceServ ;
	
	public void destroy() {}
	public void init() {}
	
	/**
	 * 验证用户是否登录，对未登录用户进行过滤
	 */
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession() ;
		TbUser tbUser = (TbUser)session.getAttribute(SessionKey.USER_SESSION_KEY) ;
		if(tbUser == null){
			//PrintLog.Log("用户没有登录，或用户已失效，页面跳转到登录页面！") ;
			return "notLogin" ;
		}else{
			//得到这个action
			String actionName = actionInvocation.getInvocationContext().getName() ;
			Map<String, Integer> allResourceMap = tbResourceServ.getAllResourceMap() ;
			if(allResourceMap.containsKey(actionName) && !tbUser.getUserResourceMap().containsKey(actionName)){
				//如果在限制权限里面有这个action并且用户没有这个权限，则返回notright
				return "notRight" ;
			}
			return actionInvocation.invoke() ;
			
		}
	}

	public ITbResourceServ getTbResourceServ() {
		return tbResourceServ;
	}

	public void setTbResourceServ(ITbResourceServ tbResourceServ) {
		this.tbResourceServ = tbResourceServ;
	}
	
}

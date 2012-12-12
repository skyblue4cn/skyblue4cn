package cn.insurance.action;

import java.util.Map;

import cn.insurance.commonwords.Md5Key;
import cn.insurance.commonwords.SessionKey;
import cn.insurance.model.TbUser;

import com.opensymphony.webwork.interceptor.SessionAware;
import com.opensymphony.xwork.ActionSupport;

/**
 * 安全验证
 * @author 何青松
 * 修改时间 2012年11月20日 14:19
 */
public class SupportAction extends ActionSupport implements SessionAware{
	
	private static final long serialVersionUID = 1L;
	protected Map session ;
	
	/**为了防止客户端在url里直接写ID来查询保单，这里对所有url可以通过修改id值来访问的都进行加密*/
	protected int limitId = -1;
	
	/**这时对limitId加密之后的值*/
	protected String limitKey ;
	
	protected String limitType ;
	
	public void setSession(Map session) {
		this.session = session ;
		
	}
	
	/**
	 * 
	 * 某些时候路径不太好确定，但与后台有没有什么交互的内容
	 * 返回SUCCESS  来确定跳转页面
	 * @return
	 */
	public String baseMethod(){
		return SUCCESS ;
	}
	
	
	/**
	 * 对所有要验证的url进行验证,
	 * 加密的方法是对重要的id改成limitId在此接收，并且对limitId进行加密
	 */
	public void validate(){
		//如果limitId接收到了值，那么就要对limitId进行验证
		if(limitId != -1){
			//如果加密验证不通过，添加错误信息
			String source = limitType==null?(""+limitId):(limitType+limitId) ;
			if(!Md5Key.encodeString(String.valueOf(source), Md5Key.LIMIT_ID_KEY).equals(limitKey)){
				addActionError("对不起，找不到该页面！") ;
			}
		}
	}
	
	/**
	 * 对需要加密的id进行加密
	 * @param id
	 */
	public void encodeId(int id){
		String source = limitType==null?(""+id):(limitType+id) ;
		limitKey = Md5Key.encodeString(String.valueOf(source), Md5Key.LIMIT_ID_KEY) ;
	}
	
	/**
	 * 对id加密的静态方法，
	 * 重要提供给jsp页面调用
	 * @param id
	 * @return
	 */
	public static String getEencodeIdKey(String type , int id){
		String source = type==null?(""+id):(type+id) ;
		String code = Md5Key.encodeString(String.valueOf(source), Md5Key.LIMIT_ID_KEY) ;
		return code ;
	}
		
	/**
	 * 从session中取得用户的信息
	 * @return
	 */
	public TbUser getUser(){
		TbUser tbUser = null ;
		try{
			tbUser =  (TbUser)session.get(SessionKey.USER_SESSION_KEY);
		}catch(Exception e){
			System.out.println("不能从session中取得值") ;
		}
		return tbUser ;
	}
	
	public int getLimitId() {
		return limitId;
	}
	public void setLimitId(int limitId) {
		this.limitId = limitId;
	}
	public String getLimitKey() {
		return limitKey;
	}
	public void setLimitKey(String limitKey) {
		this.limitKey = limitKey;
	}
	public String getLimitType() {
		return limitType;
	}
	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}

}

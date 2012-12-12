package cn.insurance.commonwords;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;

/**
 * 定义一些加密的key的常熟
 * @author 游客
 *
 */
public class Md5Key {
	
	/**对用户密码加密的key*/
	public static final String User_PASSWORD_KEY = "xiaoma" ;
	
	/**对url中需要验证的关键ID加密的key*/
	public static final String LIMIT_ID_KEY = "limit_id" ;
	
	/**
	 * 加密，采用的acegisecurity的加密算法
	 * @param config
	 * @param key
	 * @return
	 */
	public static String encodeString(String config , String key){
		return new Md5PasswordEncoder().encodePassword(config, key);
	}
	
	
	public static void main(String[] args){
		System.out.println(new Md5PasswordEncoder().encodePassword("35485b28f2e0d6138b72bcfebc69d7bd", User_PASSWORD_KEY));
		System.out.println(new Md5PasswordEncoder().encodePassword("123", User_PASSWORD_KEY));
	}
	
}

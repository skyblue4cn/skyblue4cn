package cn.insurance.model;

import java.util.Map;

import cn.insurance.model.right.TbRole;



/**
 * TbUser generated by MyEclipse - Hibernate Tools
 */

public class TbUser  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String strUserLogName;
     private String strUserPassword;
     private Integer intPartmentId;
     private String strUserName;
     private String strPhoneNumber;
     private String strEmail;
     private Integer intRoleId;
     private Integer intUserState ;
     
    // private Integer intUserType ;

     /*添加用户时重复密码*/
     private String strReUserPassword ;
     
     private TbRole tbRole ;
     
     private TbPartment tbPartment ;
     
     private Map<String, Integer> userResourceMap ; //用户所在角色的权限
     
    // Constructors

    /** default constructor */
    public TbUser() {
    }
    
    /**
     * 定义一个系统用户，主要用在记录系统自动执行时的用户记录时用
     * @return
     */
    public static TbUser sysUser(){
    	TbUser u = new TbUser();
    	u.setId(1) ;
    	u.setStrUserName("系统") ;
    	return u ;
    }

	/** minimal constructor */
    public TbUser(Integer id) {
        this.id = id;
    }
    
    
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrUserLogName() {
        return this.strUserLogName;
    }
    
    public void setStrUserLogName(String strUserLogName) {
        this.strUserLogName = strUserLogName;
    }

    public String getStrUserPassword() {
        return this.strUserPassword;
    }
    
    public void setStrUserPassword(String strUserPassword) {
        this.strUserPassword = strUserPassword;
    }

    public Integer getIntPartmentId() {
        return this.intPartmentId;
    }
    
    public void setIntPartmentId(Integer intPartmentId) {
        this.intPartmentId = intPartmentId;
    }

    public String getStrUserName() {
        return this.strUserName;
    }
    
    public void setStrUserName(String strUserName) {
        this.strUserName = strUserName;
    }

    public String getStrPhoneNumber() {
        return this.strPhoneNumber;
    }
    
    public void setStrPhoneNumber(String strPhoneNumber) {
        this.strPhoneNumber = strPhoneNumber;
    }

    public String getStrEmail() {
        return this.strEmail;
    }
    
    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    public Integer getIntRoleId() {
        return this.intRoleId;
    }
    
    public void setIntRoleId(Integer intRoleId) {
        this.intRoleId = intRoleId;
    }



	public String getStrReUserPassword() {
		return strReUserPassword;
	}

	public void setStrReUserPassword(String strReUserPassword) {
		this.strReUserPassword = strReUserPassword;
	}

	public TbPartment getTbPartment() {
		return tbPartment;
	}

	public void setTbPartment(TbPartment tbPartment) {
		this.tbPartment = tbPartment;
	}

	public TbRole getTbRole() {
		return tbRole;
	}

	public void setTbRole(TbRole tbRole) {
		this.tbRole = tbRole;
	}

	public Integer getIntUserState() {
		return intUserState;
	}

	public void setIntUserState(Integer intUserState) {
		this.intUserState = intUserState;
	}

	public Map<String, Integer> getUserResourceMap() {
		return userResourceMap;
	}

	public void setUserResourceMap(Map<String, Integer> userResourceMap) {
		this.userResourceMap = userResourceMap;
	}

//	public Integer getIntUserType() {
//		return intUserType;
//	}
//
//	public void setIntUserType(Integer intUserType) {
//		this.intUserType = intUserType;
//	}
//   








}
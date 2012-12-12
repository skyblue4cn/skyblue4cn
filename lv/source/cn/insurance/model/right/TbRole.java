package cn.insurance.model.right;



/**
 * TbRole generated by MyEclipse - Hibernate Tools
 */

public class TbRole  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String strRoleName;
     private int intTypeId;
     
     /**
      * 保险公司的角色类型为1
      */
     public static final int BX_ROLE_TYPE = 1 ;
     
     /**
      * 旅行社的角色类型为2
      */
     public static final int LXS_ROLE_TYPE = 2 ;
     

    // Constructors

    /** default constructor */
    public TbRole() {
    }

	/** minimal constructor */
    public TbRole(Integer id) {
        this.id = id;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrRoleName() {
        return this.strRoleName;
    }
    
    public void setStrRoleName(String strRoleName) {
        this.strRoleName = strRoleName;
    }

	public int getIntTypeId() {
		return intTypeId;
	}

	public void setIntTypeId(int intTypeId) {
		this.intTypeId = intTypeId;
	}









}
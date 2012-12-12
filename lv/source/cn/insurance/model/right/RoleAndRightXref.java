package cn.insurance.model.right;

/**
 * 角色与所有权限的
 * @author yqg
 *
 */
public class RoleAndRightXref implements java.io.Serializable{

	private int intRoleId ;
	
	
	private int intRightId ;
	
	
	private int isHasRight  ; //0 表示没有，1表示有
	
	private TbRight tbRight ;


	public int getIntRoleId() {
		return intRoleId;
	}


	public void setIntRoleId(int intRoleId) {
		this.intRoleId = intRoleId;
	}


	public int getIntRightId() {
		return intRightId;
	}


	public void setIntRightId(int intRightId) {
		this.intRightId = intRightId;
	}


	public int getIsHasRight() {
		return isHasRight;
	}


	public void setIsHasRight(int isHasRight) {
		this.isHasRight = isHasRight;
	}


	public TbRight getTbRight() {
		return tbRight;
	}


	public void setTbRight(TbRight tbRight) {
		this.tbRight = tbRight;
	}
	
}

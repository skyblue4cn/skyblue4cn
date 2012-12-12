package cn.insurance.model.right;

import java.io.Serializable;

public class TbResource implements Serializable {
	
	private Integer id ;
	
	private Integer intRightId ;
	
	private String strActionName ;
	
	private String strModuleName ;
	
	private TbRight tbRight ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIntRightId() {
		return intRightId;
	}

	public void setIntRightId(Integer intRightId) {
		this.intRightId = intRightId;
	}

	public String getStrActionName() {
		return strActionName;
	}

	public void setStrActionName(String strActionName) {
		this.strActionName = strActionName;
	}

	public String getStrModuleName() {
		return strModuleName;
	}

	public void setStrModuleName(String strModuleName) {
		this.strModuleName = strModuleName;
	}

	public TbRight getTbRight() {
		return tbRight;
	}

	public void setTbRight(TbRight tbRight) {
		this.tbRight = tbRight;
	}
	
	
	
}

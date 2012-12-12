package cn.insurance.model;

import java.util.Date;


/**
 * TbPayOutInfo generated by MyEclipse - Hibernate Tools
 */

public class TbPayOutInfo  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer intAccountId;
     private Integer intType;
     private Double dbePayoutNumber;
     private Date dtePayoutTime;
     private Double dbeCurResidual;
     private Double dbeAftResidual;
     private String strDesc;
     private Integer intFromUserId;
//     private Integer intBillId;
     private String strSaveUserName;
     
     /*经手人*/
    private TbUser fromUser ;
    
    /*从帐户中扣钱的保单*/
    private TbBill tbBill ;


    // Constructors

    /** default constructor */
    public TbPayOutInfo() {
    }

	/** minimal constructor */
    public TbPayOutInfo(Integer id) {
        this.id = id;
    }
    

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIntAccountId() {
        return this.intAccountId;
    }
    
    public void setIntAccountId(Integer intAccountId) {
        this.intAccountId = intAccountId;
    }

    public Integer getIntType() {
        return this.intType;
    }
    
    public void setIntType(Integer intType) {
        this.intType = intType;
    }

    public Double getDbePayoutNumber() {
        return this.dbePayoutNumber;
    }
    
    public void setDbePayoutNumber(Double dbePayoutNumber) {
        this.dbePayoutNumber = dbePayoutNumber;
    }

    public Date getDtePayoutTime() {
        return this.dtePayoutTime;
    }
    
    public void setDtePayoutTime(Date dtePayoutTime) {
        this.dtePayoutTime = dtePayoutTime;
    }

    public Double getDbeCurResidual() {
        return this.dbeCurResidual;
    }
    
    public void setDbeCurResidual(Double dbeCurResidual) {
        this.dbeCurResidual = dbeCurResidual;
    }

    public Double getDbeAftResidual() {
        return this.dbeAftResidual;
    }
    
    public void setDbeAftResidual(Double dbeAftResidual) {
        this.dbeAftResidual = dbeAftResidual;
    }

    public String getStrDesc() {
        return this.strDesc;
    }
    
    public void setStrDesc(String strDesc) {
        this.strDesc = strDesc;
    }

    public Integer getIntFromUserId() {
        return this.intFromUserId;
    }
    
    public void setIntFromUserId(Integer intFromUserId) {
        this.intFromUserId = intFromUserId;
    }

    public String getStrSaveUserName() {
        return this.strSaveUserName;
    }
    
    public void setStrSaveUserName(String strSaveUserName) {
        this.strSaveUserName = strSaveUserName;
    }

	public TbUser getFromUser() {
		return fromUser;
	}

	public void setFromUser(TbUser fromUser) {
		this.fromUser = fromUser;
	}

	public TbBill getTbBill() {
		return tbBill;
	}

	public void setTbBill(TbBill tbBill) {
		this.tbBill = tbBill;
	}
   








}
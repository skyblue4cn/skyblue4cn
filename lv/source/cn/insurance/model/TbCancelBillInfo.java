package cn.insurance.model;



/**
 * TbCancelBillInfo generated by MyEclipse - Hibernate Tools
 */

public class TbCancelBillInfo  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer intBillId;
     private Integer intUserId;
     private String strReason;


    // Constructors

    /** default constructor */
    public TbCancelBillInfo() {
    }

	/** minimal constructor */
    public TbCancelBillInfo(Integer id) {
        this.id = id;
    }
    
    /** full constructor */
    public TbCancelBillInfo(Integer id, Integer intBillId, Integer intUserId, String strReason) {
        this.id = id;
        this.intBillId = intBillId;
        this.intUserId = intUserId;
        this.strReason = strReason;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIntBillId() {
        return this.intBillId;
    }
    
    public void setIntBillId(Integer intBillId) {
        this.intBillId = intBillId;
    }

    public Integer getIntUserId() {
        return this.intUserId;
    }
    
    public void setIntUserId(Integer intUserId) {
        this.intUserId = intUserId;
    }

    public String getStrReason() {
        return this.strReason;
    }
    
    public void setStrReason(String strReason) {
        this.strReason = strReason;
    }
   








}
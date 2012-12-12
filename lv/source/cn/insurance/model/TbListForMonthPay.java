package cn.insurance.model;



/**
 * TbListForMonthPay generated by MyEclipse - Hibernate Tools
 */

public class TbListForMonthPay  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer intMonthPayId;
     private Integer intBillId;
     
     private Integer intPayMonthFeeId ;
     
     private Integer intBillState ;
     
     private TbBill tbBill ;

    // Constructors

    /** default constructor */
    public TbListForMonthPay() {
    }

	/** minimal constructor */
    public TbListForMonthPay(Integer id) {
        this.id = id;
    }
    
    /** full constructor */
    public TbListForMonthPay(Integer id, Integer intMonthPayId, Integer intBillId) {
        this.id = id;
        this.intMonthPayId = intMonthPayId;
        this.intBillId = intBillId;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIntMonthPayId() {
        return this.intMonthPayId;
    }
    
    public void setIntMonthPayId(Integer intMonthPayId) {
        this.intMonthPayId = intMonthPayId;
    }

    public Integer getIntBillId() {
        return this.intBillId;
    }
    
    public void setIntBillId(Integer intBillId) {
        this.intBillId = intBillId;
    }

	public TbBill getTbBill() {
		return tbBill;
	}

	public void setTbBill(TbBill tbBill) {
		this.tbBill = tbBill;
	}

	public Integer getIntBillState() {
		return intBillState;
	}

	public void setIntBillState(Integer intBillState) {
		this.intBillState = intBillState;
	}

	public Integer getIntPayMonthFeeId() {
		return intPayMonthFeeId;
	}

	public void setIntPayMonthFeeId(Integer intPayMonthFeeId) {
		this.intPayMonthFeeId = intPayMonthFeeId;
	}
   








}
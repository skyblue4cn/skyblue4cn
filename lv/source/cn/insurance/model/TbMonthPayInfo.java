package cn.insurance.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * TbMonthPayInfo generated by MyEclipse - Hibernate Tools
 */

public class TbMonthPayInfo  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer intAccountId; //账户ID
     private String strYear; //年
     private String strMonth;//月
     private Date dteStartTime;//结算的开始时间
     private Date dteEndTime ;//结算的结束时间
     private Double dbeNeedToPay;//应付费用
     private Double dbePayNumber;//实际付费
     private Integer intIsPay;//是否支付了费用
     
//     private Double dbeHfFee ; //缓付费用，这个会给系统收费带来很大的复杂度，暂时不用
     
     private TbPartment tbPartment ; //该保单所属的部门
     
     //支付之前的账户余额
     private Double dbeCurResidual ;
     
     //支付之后的账户余额
     private Double dbeAftResidual ;
     
     //支付方式
     private int payType ; //月付（去旅行社收费） 预存（从账户扣除）  年费(不扣除账户的)
     
     /*该月的应付保单清单*/
     private List<TbListForMonthPay> yingFuListForMonthPay = new ArrayList<TbListForMonthPay>() ;
//     
//     /*该月的缓付清单*/
//     private List<TbListForMonthPay> huanFuListForMonthPay = new ArrayList<TbListForMonthPay>() ;
//     
//     /*以前的缓付的单子在该月进行交费的清单*/
//     private List<TbListForMonthPay> qianFeiListForMonthPay = new ArrayList<TbListForMonthPay>() ;
     
     /*付款记录*/
     private TbMonthPayOutLog tbMonthPayOutLog = new TbMonthPayOutLog()  ;
     
     
     /*是否需要支付费用，规定每个月21号后才支付当月的费用，在此之前不能支付该月的费用，
      * 1 表示需要支付，-1表示暂时不需要支付
      */
     private int isNeedPay ;
     
     /**
      * 收费方式为去旅行社收费（月结）
      */
     public static final int JIAOFEI_PAY_TYPE = 1 ;
     
     /**
      * 收费方式为从账户里扣除（预存）
      */
     public static final int ACCOUNT_PAY_TYPE = 2 ;
     
     /**
      * 包年
      */
     public static final int YEAR_PAY_TYPE = 3 ;
     
    // Constructors

    /** default constructor */
    public TbMonthPayInfo() {
    }

	/** minimal constructor */
    public TbMonthPayInfo(Integer id) {
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

    public String getStrYear() {
        return this.strYear;
    }
    
    public void setStrYear(String strYear) {
        this.strYear = strYear;
    }

    public String getStrMonth() {
        return this.strMonth;
    }
    
    public void setStrMonth(String strMonth) {
        this.strMonth = strMonth;
    }

    public Double getDbeNeedToPay() {
        return this.dbeNeedToPay;
    }
    
    public void setDbeNeedToPay(Double dbeNeedToPay) {
        this.dbeNeedToPay = dbeNeedToPay;
    }

    public Double getDbePayNumber() {
        return this.dbePayNumber;
    }
    
    public void setDbePayNumber(Double dbePayNumber) {
        this.dbePayNumber = dbePayNumber;
    }


    public Integer getIntIsPay() {
        return this.intIsPay;
    }
    
    public void setIntIsPay(Integer intIsPay) {
        this.intIsPay = intIsPay;
    }

	public TbPartment getTbPartment() {
		return tbPartment;
	}

	public void setTbPartment(TbPartment tbPartment) {
		this.tbPartment = tbPartment;
	}

	public Date getDteEndTime() {
		return dteEndTime;
	}

	public void setDteEndTime(Date dteEndTime) {
		this.dteEndTime = dteEndTime;
	}

	public Date getDteStartTime() {
		return dteStartTime;
	}

	public void setDteStartTime(Date dteStartTime) {
		this.dteStartTime = dteStartTime;
	}

	public int getIsNeedPay() {
		return isNeedPay;
	}

	public void setIsNeedPay(int isNeedPay) {
		this.isNeedPay = isNeedPay;
	}

//	public Double getDbeHfFee() {
//		return dbeHfFee;
//	}
//
//	public void setDbeHfFee(Double dbeHfFee) {
//		this.dbeHfFee = dbeHfFee;
//	}


	public List<TbListForMonthPay> getYingFuListForMonthPay() {
		return yingFuListForMonthPay;
	}

	public void setYingFuListForMonthPay(
			List<TbListForMonthPay> yingFuListForMonthPay) {
		this.yingFuListForMonthPay = yingFuListForMonthPay;
	}


	public TbMonthPayOutLog getTbMonthPayOutLog() {
		return tbMonthPayOutLog;
	}

	public void setTbMonthPayOutLog(TbMonthPayOutLog tbMonthPayOutLog) {
		this.tbMonthPayOutLog = tbMonthPayOutLog;
	}

	public Double getDbeCurResidual() {
		return dbeCurResidual;
	}

	public void setDbeCurResidual(Double dbeCurResidual) {
		this.dbeCurResidual = dbeCurResidual;
	}

	public Double getDbeAftResidual() {
		return dbeAftResidual;
	}

	public void setDbeAftResidual(Double dbeAftResidual) {
		this.dbeAftResidual = dbeAftResidual;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}










}
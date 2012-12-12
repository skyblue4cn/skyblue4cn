package cn.insurance.model;



/**
 * 意外险
 */

@SuppressWarnings("serial")
public class TbAccidentalFee  implements java.io.Serializable {

    // Fields    
	/*当ID为1表示通用费率*/
	private Integer id;
	private Integer intPartmentId;
	private double dbeOneDayFee;
	private double dbeTwoDayFee;
	private double dbeThreeDayFee;
	private double dbeFourDayFee;
	private double dbeFiveDayFee;
	private double dbeSixDayFee;
	private double dbeSevenDayFee;
	private double dbeEightDayFee;
	private double dbeNineDayFee;
	private double dbeTenDayFee;
	private double dbeElevenDayFee;
	private double dbeTwelveDayFee;
	private double dbeAboveTwelveDayFee;
	private double dbeThirtyDayFee;
	private double dbeAboveThirtyDayFee;
	private double dbeFreeManFeeRate;
	private double dbeSpecialItemFeeRate;
	private Integer dbeMoneyTypeID;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIntPartmentId() {
		return intPartmentId;
	}
	public void setIntPartmentId(Integer intPartmentId) {
		this.intPartmentId = intPartmentId;
	}
	public double getDbeOneDayFee() {
		return dbeOneDayFee;
	}
	public void setDbeOneDayFee(double dbeOneDayFee) {
		this.dbeOneDayFee = dbeOneDayFee;
	}
	public double getDbeTwoDayFee() {
		return dbeTwoDayFee;
	}
	public void setDbeTwoDayFee(double dbeTwoDayFee) {
		this.dbeTwoDayFee = dbeTwoDayFee;
	}
	public double getDbeThreeDayFee() {
		return dbeThreeDayFee;
	}
	public void setDbeThreeDayFee(double dbeThreeDayFee) {
		this.dbeThreeDayFee = dbeThreeDayFee;
	}
	public double getDbeFourDayFee() {
		return dbeFourDayFee;
	}
	public void setDbeFourDayFee(double dbeFourDayFee) {
		this.dbeFourDayFee = dbeFourDayFee;
	}
	public double getDbeFiveDayFee() {
		return dbeFiveDayFee;
	}
	public void setDbeFiveDayFee(double dbeFiveDayFee) {
		this.dbeFiveDayFee = dbeFiveDayFee;
	}
	public double getDbeSixDayFee() {
		return dbeSixDayFee;
	}
	public void setDbeSixDayFee(double dbeSixDayFee) {
		this.dbeSixDayFee = dbeSixDayFee;
	}
	public double getDbeSevenDayFee() {
		return dbeSevenDayFee;
	}
	public void setDbeSevenDayFee(double dbeSevenDayFee) {
		this.dbeSevenDayFee = dbeSevenDayFee;
	}
	public double getDbeEightDayFee() {
		return dbeEightDayFee;
	}
	public void setDbeEightDayFee(double dbeEightDayFee) {
		this.dbeEightDayFee = dbeEightDayFee;
	}
	public double getDbeNineDayFee() {
		return dbeNineDayFee;
	}
	public void setDbeNineDayFee(double dbeNineDayFee) {
		this.dbeNineDayFee = dbeNineDayFee;
	}
	public double getDbeTenDayFee() {
		return dbeTenDayFee;
	}
	public void setDbeTenDayFee(double dbeTenDayFee) {
		this.dbeTenDayFee = dbeTenDayFee;
	}
	public double getDbeElevenDayFee() {
		return dbeElevenDayFee;
	}
	public void setDbeElevenDayFee(double dbeElevenDayFee) {
		this.dbeElevenDayFee = dbeElevenDayFee;
	}
	public double getDbeTwelveDayFee() {
		return dbeTwelveDayFee;
	}
	public void setDbeTwelveDayFee(double dbeTwelveDayFee) {
		this.dbeTwelveDayFee = dbeTwelveDayFee;
	}
	public double getDbeAboveTwelveDayFee() {
		return dbeAboveTwelveDayFee;
	}
	public void setDbeAboveTwelveDayFee(double dbeAboveTwelveDayFee) {
		this.dbeAboveTwelveDayFee = dbeAboveTwelveDayFee;
	}
	public double getDbeThirtyDayFee() {
		return dbeThirtyDayFee;
	}
	public void setDbeThirtyDayFee(double dbeThirtyDayFee) {
		this.dbeThirtyDayFee = dbeThirtyDayFee;
	}
	public double getDbeAboveThirtyDayFee() {
		return dbeAboveThirtyDayFee;
	}
	public void setDbeAboveThirtyDayFee(double dbeAboveThirtyDayFee) {
		this.dbeAboveThirtyDayFee = dbeAboveThirtyDayFee;
	}
	public double getDbeFreeManFeeRate() {
		return dbeFreeManFeeRate;
	}
	public void setDbeFreeManFeeRate(double dbeFreeManFeeRate) {
		this.dbeFreeManFeeRate = dbeFreeManFeeRate;
	}
	public double getDbeSpecialItemFeeRate() {
		return dbeSpecialItemFeeRate;
	}
	public void setDbeSpecialItemFeeRate(double dbeSpecialItemFeeRate) {
		this.dbeSpecialItemFeeRate = dbeSpecialItemFeeRate;
	}
	public Integer getDbeMoneyTypeID() {
		return dbeMoneyTypeID;
	}
	public void setDbeMoneyTypeID(Integer dbeMoneyTypeID) {
		this.dbeMoneyTypeID = dbeMoneyTypeID;
	}
	
}
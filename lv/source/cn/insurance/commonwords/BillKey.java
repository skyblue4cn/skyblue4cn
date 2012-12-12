package cn.insurance.commonwords;

/**
 * 关于保单的一些常量定义
 * @author yqg
 *
 */
public class BillKey {
	
	
	/**定义保单的状态及说名*/
	
	/**保单预提交状态*/
	public static final int billState1 = 1 ;
	/**保单已正式提交，需要旅行社总社审查*/
	public static final int billState2 = 2 ;
	/**保单通过旅行社审查，正式提交到保险公司，需要保险公司审查*/
	public static final int billState3 = 3 ;
	/**保单通过保险公司审查，保单生效*/
	public static final int billState4 = 4 ;
	/**保单未通过旅行社总社审查，保单被退回*/
	public static final int billState5 = 5 ;
	/**保单未通过保险公司审查，保单被退回*/
	public static final int billState6 = 6 ;
	/**保单被设置为备案处理*/
	public static final int billState7 = 7 ;
	/**备案处理的保单被更新*/
	public static final int billState8 = 8 ;
	
	
	/**
	 * 对保单是否收费的定义
	 * 
	 */
	
	/**保单还没有正式确认，保单的费用不能收取*/
	public static final int NOT_NEED_PAY_BILL = -1 ;
	
	/**保单已正式确认，并且保单的费用还没有交费*/
	public static final int NOT_PAY_BILL = 0 ;
	
	/**保单已缴费*/
	public static final int HAS_PAY_BILL = 1 ;
}

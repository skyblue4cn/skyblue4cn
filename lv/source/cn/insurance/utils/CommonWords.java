package cn.insurance.utils;


/**
 * 定义一些静态变量
 * @author zhuyan
 *
 */
public class CommonWords {
	
	/**定义session的名称*/
	public static final String USER_OBJ = "tbUser" ;
	
	/**md5加密key*/
//	public static final String MD5KEY = "xiaoma" ;
	
	/*定义用户类型的ID值,对应的表适 tbUserType*/
	
	/**设定保险公司的用户类型在数据库ID为1*/
	public static final Integer userTypeBx = 1 ;
	/**设定旅行社的用户类型在数据库ID为2*/
	public static final Integer userTypeLxs = 2 ;
	/**设定超级用户类型在数据库中ID为3*/
	public static final Integer userTypeSuper = 3 ;
	/**黑名单角色ID设置为1，黑名单人员不具备任何权限*/
	//public static final Integer hmdRoleId =1 ;
	
	
	/*定义用户状态，来设置用户是否可以用这个帐户来操作*/
	/**该用户可以使用登录*/
	public static final Integer userState1 = 0 ;
	
	/**该用户不可以使用*/
	public static final Integer userState2 = 1 ;
	
	
	
	/**责任险保单的种类ID*/
	public static final int responseKindId = 1 ;
	
	/**责任险申请表的名称*/
	public static final String responseBillName = "旅行社责任保险人员调节确认表" ;
	
	/**意外先保单的种类ID*/
	public static final int accidentalKindId = 2 ;
	
	/**意外险申请表的名称*/
	public static final String accidentalBillName= "境内、境外旅游团队人身伤害保险预约投保申请表" ;
	
	/**
	 * 根据保单种类ID取得这个申请表的名称
	 * @param billKindId
	 * @return
	 */
	public static String getBillName(int billKindId){
		if(billKindId == responseKindId){
			return responseBillName ;
		}else if(billKindId == accidentalKindId){
			return accidentalBillName ;
		}
		return null ;
	}
	
	
	public static String getBeiAnReason(int intBeiAnReason){
		String reason = "" ;
		if(intBeiAnReason == 1){
			reason = "散拼团人员名单不确定" ; 
		}else if(intBeiAnReason == 2){
			reason = "部分游客名单不确定" ;
		}else if(intBeiAnReason ==3){
			reason = "无持密码狗负责人签章" ;
		}
		return reason ;
	}
	
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
	
	/*定义保单交费情况*/
	/**保单还没有正式提交，保单费用不计入费用*/
	public static final int billIsPay1 = -1 ;
	/**未付费*/
	public static final int billIsPay2 = 0 ;
	/**已付费*/
	public static final int billIsPay3 = 1 ;
	
	
	
	/*定义帐户的支付方式*/
	/**预存费用支付*/
	public static final int payType1 = 1 ;
	/**月结算支付*/
	public static final int payType2 = 2 ;
	/**年费包干支付*/
	public static final int payType3 = 3 ;
	
	
	/*定义预存帐户的帐户变动方式*/
	/**表示往帐户里存钱*/
	public static final int payOutKind1 = 1 ;
	/**表示从账户里扣钱交费*/
	public static final int payOutKind2 = 2 ;
	/**表示退钱给帐户*/
	public static final int payOutKind3 = 3 ;
	
	/*定义帐户是否可用*/
	/**帐户不可用*/
	public static final int accountState1 = 0 ;
	/**帐户可用*/
	public static final int accountState2 = 1 ;
	
	/*定义信息message类型*/
	/**待办事务消息*/
	public static final int messageType1 = 1 ;
	/**待阅事务消息*/
	public static final int messageType2 = 2 ;
	
	
	/**申请缓交的时间限制(表示10之类都可以申请缓交)*/
	public static final int hjDate = 10  ;
	
	
	/*定义月费清单中保单的状态*/
	/**保单未交费*/
	public static final int billMonthState1 =0 ;
	/**保单已交费*/
	public static final int billMonthState2 =1 ;
	
	
	/*定义月费的收费状态  intisPay*/
	/**月费未交费*/
	public static final int monthPayState1 =0 ; 
	/**月费已交费*/
	public static final int monthPayState2 =1 ; 

	
	//确认方
	
	/**表示有旅行社方面确认保单*/
	public static final int sureType1 =1 ;
	
	/**表示由保险公司方面确认保单*/
	public static final int sureType2 = 2 ;
	
	
	//赔款
	/**保单没有赔款*/
	public static final int peiKuan0 = 0 ;
	
	/**保单有赔款*/
	public static final int peiKuan1 = 1 ;
}

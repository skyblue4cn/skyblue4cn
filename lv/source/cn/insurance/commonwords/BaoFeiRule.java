package cn.insurance.commonwords;

/**
 * 保单的保费规则
 * @author yqg
 *
 */
public class BaoFeiRule {
	
	/*责任险的保费规则*/
	
	/**
	 * 责任险国内旅游内宾保险金额描述
	 */
	public static final String ZRX_GN_NB = "16" ;
	
	/**
	 * 责任险国内旅游外宾保险金额描述
	 */
	public static final String ZRX_GN_WB = "30" ;
	
	/**
	 * 责任险国内旅游未成年保险金额描述
	 */
	public static final String ZRX_GN_WCN = "" ;
	
	/**
	 *  责任险出入境旅游内宾保险金额描述
	 */
	public static final String ZRX_CRJ_NB = "16" ;
	
	/**
	 * 责任险出入境旅游外宾保险金额描述
	 */
	public static final String ZRX_CRJ_WB = "30" ;
	
	/**
	 * 责任险出入境旅游未成年保险金额描述
	 */
	public static final String ZRX_CRJ_WCN = "" ;
	
	/**
	 * 责任险的丧葬费表述
	 */
	public static final String ZRX_SZF = "医疗费用限额4万元，丧葬费1万元。" ;
	
	
	/*意外险保险规则*/
	/**
	 * 责任险国内旅游内宾保险金额描述  
	 */
	public static final String YWX_GN_NB = "10/意外死亡伤残+2/意外医疗+0.5/丧葬" ;
	
	public static final String YWX_GN_NB2 = "20/意外死亡伤残+2/意外医疗+0.5/丧葬" ;
	
	/**
	 * 责任险国内旅游外宾保险金额描述
	 */
	public static final String YWX_GN_WB = "30/意外死亡伤残+3/意外医疗+2/丧葬" ;
	
	public static final String YWX_GN_WB2 = "40/意外死亡伤残+3/意外医疗+2/丧葬" ;
	
	/**
	 * 责任险国内旅游未成年保险金额描述 
	 */
	public static final String YWX_GN_WCN = "5/意外死亡伤残+1/意外医疗+0.5/丧葬" ;
	
//	public static final String YWX_GN_WCN = "10万意外死亡伤残+2万元意外医疗+0.5万元/丧葬" ;
	
	/**
	 *  责任险出入境旅游内宾保险金额描述
	 */
	public static final String YWX_CRJ_NB = "30/意外死亡伤残+3/意外医疗+2/丧葬" ;
	
	public static final String YWX_CRJ_NB2 = "40/意外死亡伤残+3/意外医疗+2/丧葬" ;
	
//	public static final String YWX_CRJ_NB = "40/意外死亡伤残+5/意外医疗+2/丧葬" ;
	
	/**
	 * 责任险出入境旅游外宾保险金额描述
	 */
	public static final String YWX_CRJ_WB = "30/意外死亡伤残+3/意外医疗+2/丧葬" ;
	
	public static final String YWX_CRJ_WB2 = "40/意外死亡伤残+3/意外医疗+2/丧葬" ;
	
//	public static final String YWX_CRJ_WB = "50/意外死亡伤残+5/意外医疗+2/丧葬" ;
	
	/**
	 * 责任险出入境旅游未成年保险金额描述
	 */
	public static final String YWX_CRJ_WCN = "5/意外死亡伤残+1/意外医疗+0.5/丧葬" ;
	
//	public static final String YWX_CRJ_WCN = "30/意外死亡伤残+3/意外医疗+2/丧葬" ;
	
	/**
	 * 意外险的丧葬费描述
	 */
	public static final String YWX_SZF = "" ;
	
	
	/**
	 * 通过保险种类（责任险、意外险）和旅游类型（国内旅游、出入境旅游）来查询内宾的保费描述
	 * @param billKind
	 * @param billProperty
	 * @return
	 */
	public static String getNBBaoFeiDesc(int billKind , int billProperty){
		if(billKind == 1){
			//如果是责任险
			if(billProperty == 0){
				//如果是国内旅游
				return ZRX_GN_NB ;
			}
			if(billProperty  == 1){
				//如果是出入境旅游
				return ZRX_CRJ_NB ;
			}
		}
		else if(billKind == 2){
			//如果是意外险
			if(billProperty == 0){
				//如果是国内旅游
				return YWX_GN_NB ;
			}
			if(billProperty  == 1){
				//如果是出入境旅游
				return YWX_CRJ_NB ;
			}
		}
		return null ;
	}
	
	public static String getNBBaoFeiDesc2(int billKind , int billProperty){
		if(billKind == 1){
			//如果是责任险
			if(billProperty == 0){
				//如果是国内旅游
				return ZRX_GN_NB ;
			}
			if(billProperty  == 1){
				//如果是出入境旅游
				return ZRX_CRJ_NB ;
			}
		}
		else if(billKind == 2){
			//如果是意外险
			if(billProperty == 0){
				//如果是国内旅游
				return YWX_GN_NB2 ;
			}
			if(billProperty  == 1){
				//如果是出入境旅游
				return YWX_CRJ_NB2 ;
			}
		}
		return null ;
	}
	
	
	/**
	 * 通过保险种类（责任险、意外险）和旅游类型（国内旅游、出入境旅游）来查询外宾的保费描述
	 * @param billKind
	 * @param billProperty
	 * @return
	 */
	public static String getWBBaoFeiDesc(int billKind , int billProperty){
		if(billKind == 1){
			//如果是责任险
			if(billProperty == 0){
				//如果是国内旅游
				return ZRX_GN_WB;
			}
			if(billProperty  == 1){
				//如果是出入境旅游
				return ZRX_CRJ_WB;
			}
		}
		else if(billKind == 2){
			//如果是意外险
			if(billProperty == 0){
				//如果是国内旅游
				return YWX_GN_WB;
			}
			if(billProperty  == 1){
				//如果是出入境旅游
				return YWX_CRJ_WB;
			}
		}
		return null ;
	}
	
	public static String getWBBaoFeiDesc2(int billKind , int billProperty){
		if(billKind == 1){
			//如果是责任险
			if(billProperty == 0){
				//如果是国内旅游
				return ZRX_GN_WB;
			}
			if(billProperty  == 1){
				//如果是出入境旅游
				return ZRX_CRJ_WB;
			}
		}
		else if(billKind == 2){
			//如果是意外险
			if(billProperty == 0){
				//如果是国内旅游
				return YWX_GN_WB2;
			}
			if(billProperty  == 1){
				//如果是出入境旅游
				return YWX_CRJ_WB2;
			}
		}
		return null ;
	}
	
	
	/**
	 * 通过保险种类（责任险、意外险）和旅游类型（国内旅游、出入境旅游）来查询未成年的保费描述
	 * @param billKind
	 * @param billProperty
	 * @return
	 */
	public static String getWCNBaoFeiDesc(int billKind , int billProperty){
		if(billKind == 1){
			//如果是责任险
			if(billProperty == 0){
				//如果是国内旅游
				return ZRX_GN_WCN ;
			}
			if(billProperty  == 1){
				//如果是出入境旅游
				return ZRX_CRJ_WCN ;
			}
		}
		else if(billKind == 2){
			//如果是意外险
			if(billProperty == 0){
				//如果是国内旅游
				return YWX_GN_WCN ;
			}
			if(billProperty  == 1){
				//如果是出入境旅游
				return YWX_CRJ_WCN ;
			}
		}
		return null ;
	}
	
	public static String getSZFBaoFeiDesc(int billKind , int billProperty){
		if(billKind == 1){
			//如果是责任险
			return ZRX_SZF ;
		}
		else if(billKind == 2){
			//如果是意外险
			return YWX_SZF ;
		}
		return null ;
	}
	
	
}

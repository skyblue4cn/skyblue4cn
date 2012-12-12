package cn.insurance.utils;

/**
 * 将数字转换为中文的计数方式
 * @author zhuyan
 *
 */
public class NumberUtil {
	
	public static void main(String[] args){
		System.out.println(getNumberByChinaType("一万三千五百六十七"));
	}
	
	
	public static double getNumberByChinaType(String chinaNumber){
		int sum =0 ;
		String str1 = null ;
		if(chinaNumber.indexOf("万")>0){
			str1 = chinaNumber.substring(0,chinaNumber.indexOf("万")) ;
			sum += getNumber(str1)*10000;	
		}
		if(chinaNumber.indexOf("万")+1!=chinaNumber.length()){
			sum += getNumber(chinaNumber.substring(chinaNumber.indexOf("万")+1)) ;
		}
		return sum ;
	}
	
	private static int getNumber(String str){
		int index1 = str.indexOf("千") ;
		int index2 = str.indexOf("百") ;
		int index3 = str.indexOf("十") ;
		int index4 = str.indexOf("角") ;
		int sum = 0 ;
		if(index1 >0){
			char ch1 = str.charAt(index1-1) ; //千
			sum += getArlNumber(ch1)*1000 ;
		}
		if(index2 >0){
			char ch2 = str.charAt(index2-1) ; //百
			sum += getArlNumber(ch2)*100 ;
		}
		if(index3 >0){
			char ch3 = str.charAt(index3-1) ; //十
			sum += getArlNumber(ch3)*10 ;
		}
		char ch4 = str.charAt(str.length()-1) ;
		sum += getArlNumber(ch4) ; ;
		
		if(index4 > 0){
			
		}
		return sum ;
	}
	
	private static int getArlNumber(char str){
		System.out.println(str) ;
		switch(str){
			case '一':return 1 ;
			case '二':return 2 ;
			case '三':return 3 ;
			case '四':return 4 ;
			case '五':return 5 ;
			case '六':return 6 ;
			case '七':return 7 ;
			case '八':return 8 ;
			case '九':return 9 ;
			default : return 0 ;
		}
	}
	
	
}

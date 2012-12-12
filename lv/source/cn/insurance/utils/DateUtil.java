package cn.insurance.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar ;
import java.util.Date;
import java.util.GregorianCalendar;


/**时间转换工具*/
public class DateUtil {
	
	/**将Calendar对象转换成指定的*/
	public static String getFormatDate(Calendar calendar , String type){
		
		  /**
         * 
         * 
         * dd/MMM/yyyy 
             06/Mar/1974 
             "dd-MM-yyyy" 
             06-03-1974 
             "dd MMMMMMMMM yyyy" 
             06 March 1974 
             "EEEEEEEEE, MMMMMMMMM dd, yyyy" 
             Wednesday, March 06, 1974 

         */
    SimpleDateFormat formatter = new SimpleDateFormat(type); 
    return (formatter.format(calendar.getTime() )); 
	}
	
	/**
	 * 将date格式化
	 * @param date
	 * @param type
	 * @return
	 */
	public static String getFormatDate(Date date , String type){
		SimpleDateFormat format = new SimpleDateFormat(type) ;
		return format.format(date) ;
	}

	
	/**
	 * 算出两个时间段的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getNumberFormDate(Date date1  ,Date date2){
		Calendar d1 = Calendar.getInstance() ;
		Calendar d2 = Calendar.getInstance() ;
		d1.setTime(date1) ;
		d2.setTime(date2) ;
		  if (d1.after(d2)) {  // swap dates so that d1 is start and d2 is end
		        System.out.println("日期错误!") ;
		        return 0 ;
		    }
		    int days = d2.get(Calendar.DAY_OF_YEAR) -
		               d1.get(Calendar.DAY_OF_YEAR);
		    int y2 = d2.get(Calendar.YEAR);
		    if (d1.get(Calendar.YEAR) != y2) {
		        d1 = (Calendar) d1.clone();
		        do {
		            days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
		            d1.add(Calendar.YEAR, 1);
		        } while (d1.get(Calendar.YEAR) != y2);
		    }
		 return days+1 ;
	}
	
	
	public static void main(String[] args){
		System.out.println(getFormatDate(new Date(),"-yyyy-MM-dd-hh-mm-ss")) ;
	}
	
}

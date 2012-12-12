package cn.insurance.utils;

import java.util.ArrayList;
import java.util.List;

public class HourUtil {
	
	
	public static List<Integer> getHoursList(){
		List<Integer> list = new ArrayList<Integer>() ;
		for(int i=0; i<24; i++){
			list.add(i) ;
		}
		return list ;
	}
	
	
}

package cn.insurance.utils;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log ; 

public class PrintLog {
	
	private static boolean isWrite = true ;
	
	private static Log log ;
	
	public static void Log(String message){
		
		if(isWrite){
		  	log = LogFactory.getLog("") ;
			log.info(message) ;
		}
		
		
	}
	
	/**
	 * 缺的日志对象
	 * @return
	 */
	public static Log getLog(){
		return LogFactory.getLog("insurance") ;
	}
	
}

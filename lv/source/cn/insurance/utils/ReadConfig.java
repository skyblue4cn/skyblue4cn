package cn.insurance.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ReadConfig {
	 private static final String BUNDLE_NAME = "config";

	    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
	            .getBundle(BUNDLE_NAME);

	    private ReadConfig()
	    {
	    }

	    public static String getString(String key)
	    {
	        // TODO Auto-generated method stub
	        try
	        {
	            return RESOURCE_BUNDLE.getString(key);
	        } catch (MissingResourceException e)
	        {
	            return '!' + key + '!';
	        }
	    }
	    
	    public static void main(String[] args){
	    	System.out.println(getString("UPLOAD_PATH")) ;
	    }
}

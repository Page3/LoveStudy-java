package com.changxue.util;

public class CheckURL {
	
	public static boolean checkurl(String url,String time,String signature){
		
		Long ltime = Long.parseLong(time);
		String time2 = Long.toString(ltime*2+1);
		String temp = MD5.md5(url+time2);
		
		if(temp.equals(signature)){
			return true;
		}else{
			return false;
		}
		
	}
	
}

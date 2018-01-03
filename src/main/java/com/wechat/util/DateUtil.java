package com.wechat.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static long getCurrentMilliSecond(){
		Calendar c = Calendar.getInstance();
		return c.getTimeInMillis();
	}
	
	public static String MilliSecondFormat(long millis){
		String str="";
		Date d=new Date(millis);			
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		str=sdf.format(d);
		return str;
	}
	
	public static long DateToMilliSecond(String strDate){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		long milli=0;
		try {
			milli = sdf.parse(strDate).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return milli;
	}
	
	public static long getCurrentSecond(){
		Date date = new Date(System.currentTimeMillis());
		return date.getTime()/1000;
	}
	
	public static String getCurrentDate(){
		return MilliSecondFormat(getCurrentMilliSecond());
	}
}

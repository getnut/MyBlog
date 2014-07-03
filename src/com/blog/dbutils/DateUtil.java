package com.blog.dbutils;

import java.text.SimpleDateFormat;
import java.util.Date;



public class DateUtil
{
	//得到日期字符串
	public static String getDateString(Date date)
	{	
		if(null == date)
		{
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = format.format(date);
		return str;
	}
	//得到年份和月份字符串
	public static String getPartDateString(Date date)
	{	
		if(null == date)
		{
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("/yyyy/MM/");
		String str = format.format(date);
		return str;
	}
}

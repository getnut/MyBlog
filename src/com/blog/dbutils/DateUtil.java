package com.blog.dbutils;

import java.text.SimpleDateFormat;
import java.util.Date;



public class DateUtil
{
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
}

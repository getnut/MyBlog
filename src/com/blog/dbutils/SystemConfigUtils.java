package com.blog.dbutils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
//加载系统的配置文件
public class SystemConfigUtils
{
	private static  Properties config = null;
	
	static
	{
		 config = new Properties();
		InputStream in = null;
		try
		{
			 in = SystemConfigUtils.class.getClassLoader().getResourceAsStream("config.properties");
			config.load(in);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			if(null != in)
			{
				try
				{
					in.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public static String getSystemConfigValue(String key)
	{
		return config.getProperty(key);
	}
}

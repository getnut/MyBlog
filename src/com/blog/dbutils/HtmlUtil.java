package com.blog.dbutils;



/**
 * 
 *	<   &lt;
 *	> &gt;
 * 	&&amp;
 **/
public class HtmlUtil {
	public static String[]flag = {"&","<",">"};
	public static String[]entities={"&amp;","&lt;","&gt;"};
	//encode html
	public static String encodeHtml(String html)
	{
		
		for(int i = 0;i< entities.length;i++)
		{
			html = html.replaceAll(flag[i],entities[i]);
		}
		return html;
	}
	
	//decode html
	public static String decodeHtml(String html)
	{
		
		for(int i = 0;i < entities.length;i++)
		{
			html = html.replaceAll(entities[i], flag[i]);
		}
		return html;
		
	}
	
	//去除空行
	
}

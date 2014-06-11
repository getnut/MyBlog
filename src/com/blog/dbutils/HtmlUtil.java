package com.blog.dbutils;

public class HtmlUtil {
	public static String encodeHtml(String html)
	{
		String regex = "s";
		html = html.replaceAll("&lt;", "<");
		html = html.replaceAll("&gt;", ">");
		html = html.replaceAll("&quot;", "\"");
		html = html.replaceAll("&amp;", "&");
		return html;
	}
}

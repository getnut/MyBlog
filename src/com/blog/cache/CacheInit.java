package com.blog.cache;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

@SuppressWarnings("serial")
public class CacheInit extends HttpServlet {
	public void destroy() {
		CacheData.cm.clearAll();
	}
	public void init(ServletConfig config) throws ServletException {
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("ehcache.xml");
		CacheData.cm = CacheManager.create(input);
		try
		{
			input.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CacheData.classCache = CacheData.cm.getCache("classes");
	}
}

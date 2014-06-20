package com.blog.cache;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

@SuppressWarnings("serial")
public class CacheContainer extends HttpServlet {
	private static CacheManager cm = null;
	public void destroy() {
		
	}
	
	public void init(ServletConfig config) throws ServletException {
		String cacheConfig = config.getInitParameter("cache");
		CacheManager.create(cacheConfig);
	}
	public static Cache getCache(String cache)
	{
		return cm.getCache(cache);
	}
}

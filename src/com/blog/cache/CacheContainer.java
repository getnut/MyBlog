package com.blog.cache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

@SuppressWarnings("serial")
public class CacheContainer extends HttpServlet {
	private static CacheManager cm = CacheManager.create("src/ehcache.xml");
	private Cache cache = cm.getCache("classes");
	static{
		
	}
	public void destroy() {
		
	}
	public void init() throws ServletException {
		
	}
}

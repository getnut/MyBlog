package com.blog.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.blog.dbutils.SystemConfigUtils;

public class Init extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException
	{
		super.init();
		ServletContext sc = this.getServletContext();
		sc.setAttribute("context", sc.getContextPath());
		String realPath = sc.getRealPath("/");
		SystemConfigUtils.setSystemConfigValue("realPath", realPath);
		SystemConfigUtils.setSystemConfigValue("context", sc.getContextPath());
	}
}

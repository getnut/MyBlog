package com.blog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.dbutils.MD5Util;
import com.blog.dbutils.SystemConfigUtils;

public class LoginFilter implements Filter
{
	
	@Override
	public void destroy()
	{
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain fc) throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		Cookie[]cookies = request.getCookies();
		String value = null;
		if(cookies == null)
		{
			cookies = new Cookie[0];
		}
		
		for(int i = 0;i < cookies.length;i++)
		{
			Cookie c = cookies[i];
			if("userInfo".equals(c.getName()))
			{
				value = c.getValue();
				break;
			}
		}
		if(null == value)
		{
			response.sendRedirect("/MyBlog/sp/login.htm");
		}else
		{
			//验证cookie的值是否被篡改
			String array[] = value.split("\\|");
			String secu = SystemConfigUtils.getSystemConfigValue("token");
			if(array.length == 3 && MD5Util.getMd5(array[0]+"|"+array[1]+secu).equals(array[2]))//没有被篡改
			{
				fc.doFilter(req, res);
			}else//被篡改
			{
				Cookie cookie = new Cookie("userInfo",null);//是cookie失效
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				response.sendRedirect("/MyBlog/sp/login.htm");
			}
		}
	}

	@Override
	public void init(FilterConfig params) throws ServletException
	{
		
	}

}

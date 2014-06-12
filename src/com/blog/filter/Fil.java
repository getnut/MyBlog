package com.blog.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

public class Fil extends UrlRewriteFilter
{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		System.out.println("urlrewrite invoke start!");
		super.doFilter(request, response, chain);
		System.out.println("urlrewirte invoke end!");
	}
}

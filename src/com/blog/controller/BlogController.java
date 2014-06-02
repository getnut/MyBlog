package com.blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.common.BlogActionType;
import com.blog.common.Message;

public class BlogController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("text/html");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String action = req.getParameter("action");
		if(action == null)
		{
			req.setAttribute("message", Message.REQUEST_PARAMETERS_ERROR);
			req.getRequestDispatcher("dp/error.jsp").forward(req, resp);
		}else if(BlogActionType.BLOG_LIST.equalsIgnoreCase(action))//所有文章类表
		{
			
		}else if(BlogActionType.CLASS_BLOG_LIST.equalsIgnoreCase(action))//某个标签文章列表
		{
			
		}
	}
}

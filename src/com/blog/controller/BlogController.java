package com.blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.blog.common.BlogActionType;
import com.blog.common.Message;
import com.blog.dao.PageDao;
import com.blog.dao.UserDao;
import com.blog.dao.impl.PageDaoImpl;
import com.blog.dao.impl.UserDaoImpl;
import com.blog.dbutils.DataSourceFactory;
import com.blog.dbutils.Validation;
import com.blog.entity.PageSplitResult;
import com.blog.service.UserService;
import com.blog.service.impl.PageServiceImpl;
import com.blog.service.impl.UserServiceImpl;

public class BlogController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private DataSource dataSource = null;
	private PageDao pageDao = null;
	private PageServiceImpl ps = null;
	
	public BlogController()
	{
		this.dataSource = DataSourceFactory.createDataSource();
		this.pageDao = new PageDaoImpl(dataSource);
		this.ps = new PageServiceImpl();
	}
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
			String tmp = req.getParameter("page");
			//如果tmp为null就为设置值为1
			tmp = Validation.NotNull(tmp, "1");
			boolean canToD = Validation.CanChangeToDigit(tmp);
			if(!canToD)//不可能转化为数值
			{
				req.setAttribute("message", Message.REQUEST_PARAMETERS_ERROR);
				req.getRequestDispatcher("dp/error.jsp").forward(req, resp);
				return;
			}
			int currentPage = Integer.parseInt(tmp);
			PageSplitResult psr = this.ps.getPages(currentPage);
			req.setAttribute("psr", psr);
			req.getRequestDispatcher("/dp/main.jsp").forward(req, resp);
		}else if(BlogActionType.CLASS_BLOG_LIST.equalsIgnoreCase(action))//某个标签文章列表
		{
			req.getRequestDispatcher("/dp/main.jsp").forward(req, resp);
		}
	}
}

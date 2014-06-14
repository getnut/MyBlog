package com.blog.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.blog.common.ActionType;
import com.blog.common.Message;
import com.blog.dao.PageDao;
import com.blog.dao.impl.PageDaoImpl;
import com.blog.dbutils.DataSourceFactory;
import com.blog.dbutils.Validation;
import com.blog.entity.PageSplitResult;
import com.blog.service.PageService;
import com.blog.service.impl.PageServiceImpl;

@SuppressWarnings("serial")
public class ManageController extends HttpServlet {

	private DataSource dataSource = null;
	private PageDao pageDao = null;
	private PageService ps = null;
	
	public ManageController()
	{
		this.dataSource = DataSourceFactory.createDataSource();
		this.pageDao = new PageDaoImpl(dataSource);
		this.ps = new PageServiceImpl(this.dataSource,this.pageDao);
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		String action = req.getParameter("action");
		if(action == null)
		{
			req.setAttribute("message", Message.REQUEST_PARAMETERS_ERROR);
			req.getRequestDispatcher("dp/error.jsp").forward(req, resp);
			
		}else if(ActionType.LIST.equalsIgnoreCase(action))//所有文章类表
		{
			this.listPage(req, resp);
			
		}else if(ActionType.DELETE.equalsIgnoreCase(action)){
			this.deletePage(req, resp);
		}
	}
	
	private void deletePage(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		String tmp = req.getParameter("pageId");
		long pageId = Long.parseLong(tmp);
		boolean result = this.ps.deletePage(pageId);
		if(result)
		{
			resp.sendRedirect("/MyBlog/manage/list/1");
		}
	}
	private void listPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("ManageController invoke!!!!!!!!!!");
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
		req.getRequestDispatcher("/dp/manage/manage-pages.jsp").forward(req, resp);
	}
}

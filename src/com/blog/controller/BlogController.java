package com.blog.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.blog.common.BlogActionType;
import com.blog.common.Message;
import com.blog.dao.PageDao;
import com.blog.dao.impl.PageDaoImpl;
import com.blog.dbutils.DataSourceFactory;
import com.blog.dbutils.DateUtil;
import com.blog.dbutils.HtmlUtil;
import com.blog.dbutils.JsonUtil;
import com.blog.dbutils.Validation;
import com.blog.entity.AjaxResponse;
import com.blog.entity.Classes;
import com.blog.entity.Page;
import com.blog.entity.PageSplitResult;
import com.blog.entity.ResponseType;
import com.blog.entity.StatusCode;
import com.blog.service.PageService;
import com.blog.service.impl.PageServiceImpl;

public class BlogController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private DataSource dataSource = null;
	private PageDao pageDao = null;
	private PageService ps = null;
	
	public BlogController()
	{
		this.dataSource = DataSourceFactory.createDataSource();
		this.pageDao = new PageDaoImpl(dataSource);
		this.ps = new PageServiceImpl(this.dataSource,this.pageDao);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if(BlogActionType.BLOG_ADD.equalsIgnoreCase(action))//添加博客
		{
			this.handlerBlogAdd(request, response);
		}
	}
	
	//处理增加文章的操作
	private void handlerBlogAdd(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String title = request.getParameter("pageTitle");
		String content = request.getParameter("pageContent");
		String summary = request.getParameter("summary");
		long classes = Long.parseLong(request.getParameter("cls"));
		Page page = new Page();
		Classes clss = new Classes();
		clss.setClassId(classes);
		page.setPageTitle(title);
		page.setPageContent(content);
		page.setWriteTime(DateUtil.getDateString(new Date()));
		page.setClss(clss);
		page.setSummary(summary);
		boolean result = this.ps.addPage(page);
		AjaxResponse ar = AjaxResponse.getInstance();
		if(result)
		{
			ar.setStatus(StatusCode.SUCCESS);
			ar.setResponseType(ResponseType.ADD_BLOG);
		}else
		{
			ar.setStatus(StatusCode.FAIL);
			ar.setResponseType(ResponseType.ADD_BLOG);
		}
		response.getWriter().write(JsonUtil.toJson(ar));
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
		}else if(BlogActionType.BLOG_SHOW.equalsIgnoreCase(action))
		{
			this.showPageDetail(req, resp);
		}
	}
	private void showPageDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String tmp = req.getParameter("pageId");
		if(tmp == null || !Validation.CanChangeToDigit(tmp) || Long.parseLong(tmp) >= Long.MAX_VALUE)
		{
			req.setAttribute("message", Message.REQUEST_PARAMETERS_ERROR);
			req.getRequestDispatcher("dp/error.jsp").forward(req, resp);
			return;
		}
		long pageId = Long.parseLong(tmp);
		Page page = this.ps.getPage(pageId);
		page.setPageContent(HtmlUtil.encodeHtml(page.getPageContent()));
		req.setAttribute("page", page);
		req.getRequestDispatcher("dp/page-detail.jsp").forward(req, resp);
	}
}

package com.blog.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.blog.cache.CacheData;
import com.blog.common.ActionType;
import com.blog.common.Message;
import com.blog.dao.impl.ClassDaoImpl;
import com.blog.dao.impl.PageClassDaoImpl;
import com.blog.dao.impl.PageDaoImpl;
import com.blog.dao.impl.PageQueryImpl;
import com.blog.dbutils.DataSourceFactory;
import com.blog.dbutils.DateUtil;
import com.blog.dbutils.HtmlUtil;
import com.blog.dbutils.JsonUtil;
import com.blog.dbutils.ToHtml;
import com.blog.dbutils.TransactionManager;
import com.blog.dbutils.Validation;
import com.blog.entity.AjaxResponse;
import com.blog.entity.Classes;
import com.blog.entity.Page;
import com.blog.entity.PageSplitResult;
import com.blog.entity.ResponseType;
import com.blog.entity.StatusCode;
import com.blog.service.impl.ClassServiceImpl;
import com.blog.service.impl.PageServiceImpl;
import com.blog.dao.impl.ClassQueryImpl;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
public class BlogController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private DataSource dataSource = null;
	private PageDaoImpl pageDao = null;
	private PageClassDaoImpl pageClassDao = null;
	private ClassDaoImpl classDao = null;
	private PageQueryImpl pageQuery = null;
	private PageServiceImpl ps = null;
	private TransactionManager tm = null;
	private ClassServiceImpl cs = null;
	private ClassQueryImpl classQuery = null;
	//组装对象
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		
		this.dataSource = DataSourceFactory.createDataSource();
		tm = new TransactionManager(dataSource);
		//dao
		this.pageDao = new PageDaoImpl();
		this.classDao = new ClassDaoImpl();
		this.pageQuery = new PageQueryImpl();
		this.pageClassDao = new PageClassDaoImpl();
		this.classQuery = new ClassQueryImpl();
		//设置dataSource
		this.pageDao.setDataSource(this.dataSource);
		this.classDao.setDataSource(dataSource);
		this.pageQuery.setDataSource(dataSource);
		this.pageClassDao.setDataSource(dataSource);
		this.classQuery.setDataSource(this.dataSource);
		//service
		this.ps = new PageServiceImpl();
		this.ps.setDataSource(this.dataSource);
		this.ps.setPageClassDao(pageClassDao);
		this.ps.setPageDao(pageDao);
		this.ps.setPageQuery(pageQuery);
		this.ps.setClassDao(classDao);
		this.ps.setTransaction(tm);
		this.cs = new ClassServiceImpl();
		this.cs.setDataSource(this.dataSource);
		this.cs.setCd(this.classDao);
		this.cs.setClassQuery(classQuery);
	}
	
	public BlogController()
	{
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if(ActionType.ADD.equalsIgnoreCase(action))//添加博客
		{
			this.addBlog(request, response);
		}
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
		}else if(ActionType.LIST.equalsIgnoreCase(action))//所有文章类表
		{
			this.listAllPage(req, resp);
			
		}else if(ActionType.DETAIL.equalsIgnoreCase(action))
		{
			this.showPageDetail(req, resp);
		}else if(ActionType.ADD.equalsIgnoreCase(action))
		{
			this.showAddPage(req, resp);
		}
	}
	//处理添加页面显示
	private void showAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("classes", this.cs.getAllClasses());
		request.getRequestDispatcher("/dp/manage/page-add.jsp").forward(request, response);
	}
	//处理增加文章的操作
	private void addBlog(HttpServletRequest request, HttpServletResponse response) throws IOException
	{	
		String title = request.getParameter("pageTitle");
		String content = request.getParameter("pageContent");
		String summary = request.getParameter("summary");
		String classes[] = request.getParameter("cls").split(":+");
		System.out.println(Arrays.toString(classes));
		Page page = new Page();
		for(int i = 0;i < classes.length;i++)
		{
			Classes cls = new Classes();
			cls.setClassId(Long.parseLong(classes[i]));
			page.getClses().add(cls);
		}
		page.setPageTitle(title);
		page.setPageContent(content);
		page.setWriteTime(DateUtil.getDateString(new Date()));
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
	//显示文章列表
	private void listAllPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
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
		/*分页查询的结果*/
		req.setAttribute("psr", psr);
		req.getRequestDispatcher("/dp/main.jsp").forward(req, resp);
	}
	//显示文章
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

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
import com.blog.dao.impl.ClassDaoImpl;
import com.blog.dao.impl.ClassQueryImpl;
import com.blog.dao.impl.PageClassDaoImpl;
import com.blog.dao.impl.PageDaoImpl;
import com.blog.dao.impl.PageQueryImpl;
import com.blog.dbutils.DataSourceFactory;
import com.blog.dbutils.TransactionManager;
import com.blog.dbutils.Validation;
import com.blog.entity.PageSplitResult;
import com.blog.service.PageService;
import com.blog.service.impl.ClassServiceImpl;
import com.blog.service.impl.PageServiceImpl;

@SuppressWarnings("serial")
public class ManageController extends HttpServlet {

	private DataSource dataSource = null;
	private PageDaoImpl pageDao = null;
	private PageClassDaoImpl pageClassDao = null;
	private ClassDaoImpl classDao = null;
	private PageQueryImpl pageQuery = null;
	private PageServiceImpl ps = null;
	private TransactionManager tm = null;
	private ClassServiceImpl cs = null;
	private ClassQueryImpl classQuery = null;
	
	public ManageController()
	{
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
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		String action = req.getParameter("action");
		//列出所有的文章
		if(ActionType.LIST.equalsIgnoreCase(action))//所有文章类表
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
			resp.sendRedirect("/MyBlog/manage/list-1.html");
		}
	}
	private void listPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String tmp = req.getParameter("page");
		int currentPage = Integer.parseInt(tmp);
		PageSplitResult psr = this.ps.getPages(currentPage);
		req.setAttribute("psr", psr);
		req.getRequestDispatcher("/dp/manage/pages.jsp").forward(req, resp);
	}
}

package com.blog.controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import com.blog.common.ActionType;
import com.blog.dao.impl.ClassDaoImpl;
import com.blog.dao.impl.ClassQueryImpl;
import com.blog.dao.impl.PageClassDaoImpl;
import com.blog.dao.impl.PageDaoImpl;
import com.blog.dao.impl.PageQueryImpl;
import com.blog.dbutils.DataSourceFactory;
import com.blog.dbutils.TransactionManager;
import com.blog.entity.Classes;
import com.blog.service.impl.ClassServiceImpl;
import com.blog.service.impl.PageServiceImpl;

@SuppressWarnings("serial")
public class ClassesController extends HttpServlet
{
	private DataSource dataSource = null;
	private PageDaoImpl pageDao = null;
	private PageClassDaoImpl pageClassDao = null;
	private ClassDaoImpl classDao = null;
	private PageQueryImpl pageQuery = null;
	private PageServiceImpl ps = null;
	private TransactionManager tm = null;
	private ClassServiceImpl cs = null;
	private ClassQueryImpl classQuery = null;
	
	public ClassesController()
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
			
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String action = req.getParameter("action");
		if(ActionType.LIST.equalsIgnoreCase(action))
		{
			this.listClass(req, resp);
		}else if(ActionType.DELETE.equalsIgnoreCase(action))
		{
			System.out.println("classes delete!!!");
			
			this.deleteClass(req, resp);
		}
	}
	
	private void listClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		List<Classes> lists = this.cs.getAllClasses();
		req.setAttribute("classes", lists);
		req.getRequestDispatcher("/dp/manage/manage-classes.jsp").forward(req, resp);
	}
	private void deleteClass(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		Long classId = Long.parseLong(req.getParameter("classId"));
		this.cs.removeClass(classId);
		resp.sendRedirect("/MyBlog/manage/class/list");
	}
}

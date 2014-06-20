package com.blog.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.blog.common.ActionType;
import com.blog.dao.ClassDao;
import com.blog.dao.impl.ClassDaoImpl;
import com.blog.dbutils.DataSourceFactory;
import com.blog.entity.Classes;
import com.blog.service.ClassService;
import com.blog.service.impl.ClassServiceImpl;

@SuppressWarnings("serial")
public class ClassesController extends HttpServlet
{
	private DataSource dataSource = null;
	
	private ClassDao cd = null;
	
	private ClassService cs = null;
	
	public ClassesController()
	{
		dataSource = DataSourceFactory.createDataSource();
		cd = new ClassDaoImpl();
		cs = new ClassServiceImpl();
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

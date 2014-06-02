package com.blog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.blog.common.Message;
import com.blog.common.UserActionType;
import com.blog.dao.UserDao;
import com.blog.dao.impl.UserDaoImpl;
import com.blog.dbutils.DataSourceFactory;
import com.blog.entity.User;
import com.blog.service.UserService;
import com.blog.service.impl.UserServiceImpl;

@SuppressWarnings("serial")
public class UserController extends HttpServlet {

	private DataSource dataSource = null;
	private UserDao userDao = null;
	private UserService userService = null;
	
	public UserController()
	{
		this.dataSource = DataSourceFactory.createDataSource();
		this.userDao = new UserDaoImpl(dataSource);
		this.userService = new UserServiceImpl(dataSource, userDao);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String action = request.getParameter("action");
		if(null == action)
		{
			request.setAttribute("message", Message.REQUEST_PARAMETERS_ERROR);
			request.getRequestDispatcher("dp/error.jsp").forward(request, response);
		}
		if(UserActionType.LOGIN.equalsIgnoreCase(action))//
		{
			String userName = request.getParameter("username");
			String password = request.getParameter("pwd");
			User user = userService.login(userName, password);
			if(null == user)//登录失败
			{
				request.setAttribute("message", Message.LOGIN_FAIL);
				request.getRequestDispatcher("dp/login.jsp").forward(request, response);
			}else//登录成功
			{
				response.sendRedirect("BlogController?action=blog-list");
			}
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
	}
}

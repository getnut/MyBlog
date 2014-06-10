package com.blog.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.blog.common.Message;
import com.blog.common.UserActionType;
import com.blog.dao.UserDao;
import com.blog.dao.impl.UserDaoImpl;
import com.blog.dbutils.DataSourceFactory;
import com.blog.dbutils.DateUtil;
import com.blog.dbutils.JsonUtil;
import com.blog.dbutils.MD5Util;
import com.blog.dbutils.SystemConfigUtils;
import com.blog.entity.AjaxResponse;
import com.blog.entity.ResponseType;
import com.blog.entity.StatusCode;
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
		response.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		if(null == action || null == userName || null == password)//参数不正确
		{
			request.setAttribute("message", Message.REQUEST_PARAMETERS_ERROR);
			request.getRequestDispatcher("dp/error.jsp").forward(request, response);
			return;
		}
		if(UserActionType.LOGIN.equalsIgnoreCase(action))//使用异步登录
		{
			try
			{
				Thread.sleep(2000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			User user = userService.login(userName, password);
			AjaxResponse aRes = AjaxResponse.getInstance();//响应对象
			aRes.setResponseType(ResponseType.LOGIN);
			if(null == user)//登录失败
			{
				aRes.setStatus(StatusCode.FAIL);
				aRes.addData("message", Message.LOGIN_FAIL);
				
			}else//登录成功
			{
				aRes.setStatus(StatusCode.SUCCESS);
				aRes.addData("url", "/MyBlog/BlogController?action=blog-list");
				String secu = SystemConfigUtils.getSystemConfigValue("token");
				//username|time
				String value = user.getUserName()+"|"+DateUtil.getDateString(new Date());
				String token = MD5Util.getMd5(value+secu);//防止cookie被篡改
				Cookie cookie = new Cookie("userInfo", value+"|"+token);
				cookie.setMaxAge(-1);//会话cookie
				response.addCookie(cookie);
				
			}
			String json = JsonUtil.toJson(aRes);//转化为json字符串
			response.getWriter().write(json);//发给浏览器
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
	}
}


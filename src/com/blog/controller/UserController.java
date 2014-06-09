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
				Thread.sleep(5000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			System.out.println(SystemConfigUtils.getSystemConfigValue("name"));
			User user = userService.login(userName, password);
			AjaxResponse aRes = AjaxResponse.getInstance();//响应对象
			aRes.setResponseType(ResponseType.LOGIN);
			if(null == user)//登录失败
			{
				aRes.setStatus(StatusCode.LOGIN_FAIL);
				aRes.addData("message", Message.LOGIN_FAIL);
				
			}else//登录成功
			{
				aRes.setStatus(StatusCode.LOGIN_SUCCESS);
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
/*ajax的响应对象*/
class AjaxResponse
{
	private int status;//响应的结果状态，比如登录，可能成功，可能失败
	private int responseType;//响应的类型
	private HashMap<String,Object> data = new HashMap<String, Object>();//绑定数据
	
	private AjaxResponse(){}
	public void addData(String key,Object data)
	{
		this.data.put(key, data);
	}
	public void removeData(String key,Object data)
	{
		this.data.remove(data);
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public int getResponseType()
	{
		return responseType;
	}
	public void setResponseType(int responseType)
	{
		this.responseType = responseType;
	}
	public HashMap<String, Object> getData()
	{
		return data;
	}
	public void setData(HashMap<String, Object> data)
	{
		this.data = data;
	}
	public static AjaxResponse getInstance()
	{
		return new AjaxResponse();
	}
}
/*响应状态*/
class StatusCode
{	//登录成功
	public static final int LOGIN_SUCCESS = 1;
	//登录失败
	public static final int LOGIN_FAIL = 2;
	
}
/*响应类型*/
class ResponseType
{
	public static final int LOGIN = 1;
}

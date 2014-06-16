package com.blog.service.impl;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.blog.dao.UserDao;
import com.blog.dbutils.DataSourceUtil;
import com.blog.entity.User;
import com.blog.service.UserService;

public class UserServiceImpl implements UserService
{
	private DataSource dataSource = null;
	private UserDao userDao = null;
	
	@Override
	public User login(String userName, String password)
	{
		User user = null;
		try
		{
			user = this.userDao.findUserByUserNameAndPassword(userName, password);
			
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}finally
		{
			DataSourceUtil.close(dataSource);
		}
		return user;
	}
	public DataSource getDataSource()
	{
		return dataSource;
	}
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	public UserDao getUserDao()
	{
		return userDao;
	}
	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}
	
}

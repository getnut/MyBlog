package com.blog.dao;

import java.sql.SQLException;

import com.blog.entity.User;


public interface UserDao
{
	public User findUserByUserNameAndPassword(String userName,String password) throws SQLException;
}

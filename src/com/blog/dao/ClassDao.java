package com.blog.dao;

import java.sql.SQLException;

import com.blog.entity.Classes;

public interface ClassDao
{
	/*删除类别*/
	public boolean deleteClass(long classId)throws SQLException;
	/*增加类别*/
	public boolean addClass(Classes cls) throws SQLException;
	/*列出所有类别*/
	public boolean getAllClass()throws SQLException;
}

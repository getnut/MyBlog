package com.blog.dao;

import java.sql.SQLException;
import java.util.List;

import com.blog.entity.Classes;

public interface ClassDao {

	//添加列表然后返回id
	public long addClass(Classes cls) throws SQLException;
	
	public boolean deleteClass(long classId) throws SQLException;
	
	public List<Classes> getAllClass() throws SQLException;
	
	public Classes getClass(long classId) throws SQLException;
}

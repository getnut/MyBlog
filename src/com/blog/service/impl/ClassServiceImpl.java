package com.blog.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.blog.dao.ClassDao;
import com.blog.dbutils.DataSourceUtil;
import com.blog.entity.Classes;
import com.blog.service.ClassService;

public class ClassServiceImpl implements ClassService
{

	private ClassDao cd = null;
	private DataSource dataSource = null;
	public ClassServiceImpl(DataSource dataSource,ClassDao cd)
	{
		this.dataSource = dataSource;
		this.cd = cd;
	}
	@Override
	public long addClass(Classes cls)
	{
		long result = -1;
		try
		{
			result = this.cd.addClass(cls);
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}finally
		{
			DataSourceUtil.close(this.dataSource);
		}
		return result;
	}

	@Override
	public List<Classes> getAllClass()
	{
		List<Classes> clses = new ArrayList<Classes>();
		try
		{
			clses = this.cd.getAllClass();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return clses;
	}

	@Override
	public boolean removeClass(long classId)
	{
		boolean result = false;
		try
		{
			result = this.cd.deleteClass(classId);
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
	
}

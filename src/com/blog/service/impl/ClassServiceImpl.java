package com.blog.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import com.blog.dao.ClassDao;
import com.blog.dbutils.DataSourceUtil;
import com.blog.entity.Classes;
import com.blog.service.ClassService;
import com.blog.dao.ClassQuery;
public class ClassServiceImpl implements ClassService
{

	private ClassDao cd = null;
	private ClassQuery classQuery = null;
	private DataSource dataSource = null;

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
	public List<Classes> getAllClasses()
	{
		List<Classes> clses = new ArrayList<Classes>();
		try
		{
			clses =  this.classQuery.getAllClasses();
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
	public ClassDao getCd()
	{
		return cd;
	}
	public void setCd(ClassDao cd)
	{
		this.cd = cd;
	}
	public DataSource getDataSource()
	{
		return dataSource;
	}
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	public ClassQuery getClassQuery()
	{
		return classQuery;
	}

	public void setClassQuery(ClassQuery classQuery)
	{
		this.classQuery = classQuery;
	}
}

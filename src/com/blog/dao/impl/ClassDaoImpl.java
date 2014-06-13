package com.blog.dao.impl;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.blog.dao.ClassDao;
import com.blog.dbutils.JdbcTemplateAdapter;
import com.blog.entity.Classes;

public class ClassDaoImpl implements ClassDao
{

	private DataSource dataSource = null;
	public ClassDaoImpl(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	@Override
	public boolean addClass(Classes cls) throws SQLException
	{
		String sql = "insert into classes(className)values(?)";
		try
		{
			int result =  new JdbcTemplateAdapter(dataSource).doCurdJob(sql, new Object[]{cls.getClassName()});
			if(result > 0)
			{
				return true;
			}
			return false;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public boolean deleteClass(long classId) throws SQLException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getAllClass() throws SQLException
	{
		// TODO Auto-generated method stub
		return false;
	}

}

package com.blog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.blog.dao.ClassDao;
import com.blog.dbutils.JdbcTemplate;
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
	public long addClass(Classes cls) throws SQLException {
		String sql = "insert into classes (className) values(?)";
		return new JdbcTemplateAdapter(this.dataSource).<Long>getGeneratedKey(sql, new Object[]{cls.getClassName()});
	}

	@Override
	public boolean deleteClass(long classId) throws SQLException {
		String sql = "delete from classes where classId = ?";
		int result = new JdbcTemplateAdapter(this.dataSource).doCurdJob(sql,new Object[]{classId});
		if(result > 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public List<Classes> getAllClass() throws SQLException {
		List<Classes> clss = new ArrayList<Classes>();
		
		return null;
	}

}

package com.blog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.blog.dao.PageClassDao;
import com.blog.dbutils.JdbcTemplate;
import com.blog.dbutils.JdbcTemplateAdapter;

public class PageClassDaoImpl implements PageClassDao
{
	private DataSource dataSource = null;
	

	@Override
	public boolean addPageClass(long pageId, long classId) throws SQLException
	{
		String sql = "insert into page_class(pageId,classId) values(?,?)";
		int result = new JdbcTemplateAdapter(this.dataSource).doCurdJob(sql, new Object[]{pageId,classId});
		if(result > 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteByPageId(long pageId)throws SQLException
	{
		String sql = "delete from page_class where pageId = ?";
		int result = new JdbcTemplateAdapter(this.dataSource).doCurdJob(sql, new Object[]{pageId});
		if(result > 0)
		{
			return true;
		}
		return false;
	}
	@Override
	public int totalPageOfClass(long classId) throws SQLException
	{
		String sql = "select count(pageId) from page_class where classId=?";
		return new JdbcTemplate(this.dataSource)
		{
			
			@Override
			public Object doInJob(ResultSet rs) throws SQLException
			{
				int count = 0;
				if(rs.next())
				{
					count = rs.getInt(1);
				}
				return count;
			}
		}.<Integer>doJob(sql, new Object[]{classId});
	}
	public DataSource getDataSource()
	{
		return dataSource;
	}
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
}

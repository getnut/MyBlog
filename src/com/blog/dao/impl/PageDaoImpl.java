package com.blog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.blog.dao.PageDao;
import com.blog.dbutils.DateUtil;
import com.blog.dbutils.JdbcTemplate;
import com.blog.entity.Classes;
import com.blog.entity.Page;

public class PageDaoImpl implements PageDao
{

	private DataSource dataSource = null;
	
	public PageDaoImpl(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	@Override
	public List<Page> getPages(long start, long end) throws SQLException
	{
	
		String sql = "SELECT PageId, PageTitle, PageContent, WriteTime,p.ClassId,c.ClassName FROM pages p inner join classes c on p.ClassId = c.ClassId order by WriteTime desc limit  ?,? ";
		 
		try
		{
			new JdbcTemplate(dataSource)
			{
				
				@Override
				public Object doInJob(ResultSet rs) throws SQLException
				{
					Page page = null;
					Classes clss = null;
					List<Page> pages = new ArrayList<Page>();
					while(rs.next())
					{
						page = new Page();
						page.setPageId(rs.getLong(1));
						page.setPageTitle(rs.getString(2));
						page.setPageContent(rs.getString(3));
						page.setWriteTime(DateUtil.getDateString(rs.getTimestamp(4)));
						clss = new Classes();
						clss.setClassId(rs.getLong(5));
						clss.setClassName(rs.getString(6));
						page.setClss(clss);
						pages.add(page);
					}
					return pages;
				}
			}.<List<Page>>doJob(sql, new Object[]{start,end});
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
		return null;
	}

	@Override
	public List<Page> getPages(long classId, long start, long end) throws SQLException
	{
		String sql = "SELECT PageId, PageTitle, PageContent, WriteTime,p.ClassId,c.ClassName FROM pages p inner join classes c on p.ClassId = c.ClassId  where p.ClassId = ? order by WriteTime desc limit  ?,? ";
		
		try
		{
			new JdbcTemplate(dataSource)
			{
				
				@Override
				public Object doInJob(ResultSet rs) throws SQLException
				{	List<Page> pages = new ArrayList<Page>();
					Page page = null;
					Classes clss = null;
					while(rs.next())
					{
						page = new Page();
						page.setPageId(rs.getLong(1));
						page.setPageTitle(rs.getString(2));
						page.setPageContent(rs.getString(3));
						page.setWriteTime(DateUtil.getDateString(rs.getTimestamp(4)));
						clss = new Classes();
						clss.setClassId(rs.getLong(5));
						clss.setClassName(rs.getString(6));
						page.setClss(clss);
						pages.add(page);
					}
					return pages;
				}
			}.<List<Page>>doJob(sql, new Object[]{classId,start,end});
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
		return null;
	}
	
	//多少个文章
	@Override
	public long totalPages() throws SQLException
	{
		String sql = "select count(PageId) from pages";
		
		try
		{
			return new JdbcTemplate(this.dataSource)
			{
				
				@Override
				public Object doInJob(ResultSet rs) throws SQLException
				{
					long count = 0;
					if(rs.next())
					{
						count  = rs.getLong(1);
					}
					return count;
				}
			}.<Long>doJob(sql, new Object[]{});
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
	}
	@Override
	public long totalPages(long classId)
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}

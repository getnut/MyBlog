package com.blog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.blog.dao.PageDao;
import com.blog.dbutils.DateUtil;
import com.blog.dbutils.JdbcTemplate;
import com.blog.dbutils.JdbcTemplateAdapter;
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
	public List<Page> getPages(int start, int count) throws SQLException
	{
		start = start - 1;
		String sql = "SELECT PageId, PageTitle, summary, WriteTime,p.ClassId,c.ClassName FROM page p inner join classes c on p.ClassId = c.ClassId order by WriteTime desc limit  ?,? ";
		 
		try
		{
			return new JdbcTemplate(dataSource)
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
						page.setSummary(rs.getString(3));
						page.setWriteTime(DateUtil.getDateString(rs.getTimestamp(4)));
						clss = new Classes();
						clss.setClassId(rs.getLong(5));
						clss.setClassName(rs.getString(6));
						page.setClss(clss);
						pages.add(page);
					}
					return pages;
				}
			}.<List<Page>>doJob(sql, new Object[]{start,count});
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public List<Page> getPages(long classId, int start, int count) throws SQLException
	{
		String sql = "SELECT PageId,PageTitle,summary, WriteTime ,p.ClassId,c.ClassName FROM page p inner join classes c on p.ClassId = c.ClassId  where p.ClassId = ? order by WriteTime desc limit ?,? ";
		
		try
		{
			return new JdbcTemplate(dataSource)
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
						page.setSummary(rs.getString(3));
						page.setWriteTime(DateUtil.getDateString(rs.getTimestamp(4)));
						clss = new Classes();
						clss.setClassId(rs.getLong(5));
						clss.setClassName(rs.getString(6));
						page.setClss(clss);
						pages.add(page);
					}
					return pages;
				}
			}.<List<Page>>doJob(sql, new Object[]{classId,start,count});
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
	}

	
	//多少个文章
	@Override
	public int totalPages() throws SQLException
	{
		String sql = "select count(PageId) from page";
		try
		{
			return new JdbcTemplate(this.dataSource)
			{
				
				@Override
				public Object doInJob(ResultSet rs) throws SQLException
				{
					int count = 0;
					if(rs.next())
					{
						count  = rs.getInt(1);
					}
					return count;
				}
			}.<Integer>doJob(sql, new Object[]{});
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
	}
	@Override
	public int totalPages(long classId) throws SQLException
	{
		String sql = "select count(PageId) from page where classId = ?";
		try
		{
			return new JdbcTemplate(this.dataSource)
			{
				
				@Override
				public Object doInJob(ResultSet rs) throws SQLException
				{
					int count = 0;
					if(rs.next())
					{
						count  = rs.getInt(1);
					}
					return count;
				}
			}.<Integer>doJob(sql, new Object[]{classId});
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
	}

	public boolean addPage(Page page) throws SQLException
	{
		String sql = "insert into Page(PageTitle,summary,PageContent,WriteTime,ClassId) values(?,?,?,?,?)";
		Object params[] = new Object[]{page.getPageTitle(),page.getSummary(),page.getPageContent(),page.getWriteTime(),page.getClss().getClassId()};
		try
		{
			int result =  (new JdbcTemplateAdapter(this.dataSource)).doCurdJob(sql, params);
			if(result > 0)
			{
				return true;
			}else
			{
				return false;
			}
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
	}
	@Override
	public Page getPage(long pageId) throws SQLException {
		String sql = "SELECT PageId,PageTitle, pageContent,WriteTime,p.ClassId,c.ClassName FROM page p inner join classes c on p.ClassId = c.ClassId  where p.pageId = ?";
		
		try
		{
			return new JdbcTemplate(dataSource)
			{
				
				@Override
				public Object doInJob(ResultSet rs) throws SQLException
				{	
					Page page = null;
					Classes clss = null;
					if(rs.next())
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
					}
					return page;
				}
			}.<Page>doJob(sql, new Object[]{pageId});
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
	}
	@Override
	public boolean deletePage(long pageId) throws SQLException {
		
		String sql = "delete from page where pageId = ?";
		Object params[] = new Object[]{pageId};
		try
		{
			int result =  (new JdbcTemplateAdapter(this.dataSource)).doCurdJob(sql, params);
			if(result > 0)
			{
				return true;
			}else
			{
				return false;
			}
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
	}
	
}

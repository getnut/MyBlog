package com.blog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.blog.dao.PageQuery;
import com.blog.dbutils.DateUtil;
import com.blog.dbutils.JdbcTemplate;
import com.blog.entity.Classes;
import com.blog.entity.Page;

public class PageQueryImpl implements PageQuery
{

	private DataSource dataSource = null;
	

	
	@Override
	public Page getPage(long pageId) throws SQLException
	{
		String sql = "select  p.PageTitle, p.pageContent, p_c.classId, c.ClassName from page p inner join page_class p_c on p.PageId = p_c.pageId inner join classes c on p_c.classId = c.ClassId where p.pageId = ?";
		return new JdbcTemplate(this.dataSource)
		{
			
			@Override
			public Object doInJob(ResultSet rs) throws SQLException
			{
				Page page = null;
				Classes cls = null;
				while(rs.next())
				{
					if(page == null)
					{
						page = new Page();
						page.setPageTitle(rs.getString(1));
						page.setPageContent(rs.getString(2));
					}
					cls = new Classes();
					cls.setClassId(rs.getLong(3));
					cls.setClassName(rs.getString(4));
					page.getClses().add(cls);
				}
				return page;
			}
		}.doJob(sql, new Object[]{pageId});
	}
	
	@Override
	public List<Page> getPages(int start, int count) throws SQLException
	{
		start = start - 1;
		String sql = "select p.pageId,p.PageTitle,p.summary, p.writeTime from page p order by p.WriteTime desc limit ?,?";
		return new JdbcTemplate(this.dataSource)
		{
			
			@Override
			public Object doInJob(ResultSet rs) throws SQLException
			{
				List<Page> pages= new ArrayList<Page>();
				Page page = null;
				while(rs.next())
				{
					page = new Page();
					page.setPageId(rs.getLong(1));
					page.setPageTitle(rs.getString(2));
					page.setSummary(rs.getString(3));
					page.setWriteTime(DateUtil.getDateString(rs.getTimestamp(4)));
					pages.add(page);
				}
				System.out.println("size="+pages.size());
				return pages;
			}
		}.<List<Page>>doJob(sql, new Object[]{start,count});
	}
	
	@SuppressWarnings("unused")
	private List<Classes> getClassesByPageId(long pageId) throws SQLException
	{
		String sql = "select  c.classId,c.ClassName from page_class p_c inner join classes c on c.classId = p_c.classId where pageId = ?"; 
		return new JdbcTemplate(this.dataSource) {
			
			@Override
			public Object doInJob(ResultSet rs) throws SQLException {
				List<Classes> clses = new ArrayList<Classes>();
				Classes cls = null;
				while(rs.next())
				{
					cls = new Classes();
					cls.setClassId(rs.getLong(1));
					cls.setClassName(rs.getString(2));
					clses.add(cls);
				}
				return clses;
			}
		}.doJob(sql, new Object[]{pageId});
	}
	@Override
	public List<Page> getPagesOfClass(long classId,int start, int count) throws SQLException
	{
		start = start - 1;
		String sql = "select p.pageId,p.PageTitle, p.summary, p_c.classId, c.ClassName from page p inner join page_class p_c on p.PageId = p_c.pageId inner join classes c on p_c.classId = c.ClassId where c.classId = ? limit ?,?";
		return new JdbcTemplate(this.dataSource)
		{
			@Override
			public Object doInJob(ResultSet rs) throws SQLException
			{
				List<Page> pages= new ArrayList<Page>();

				while(rs.next())
				{	
					Page page = null;
					Classes cls = null;
					page = new Page();
					page.setPageId(rs.getLong(1));
					page.setPageTitle(rs.getString(2));
					page.setSummary(rs.getString(3));
					
					if(cls == null)
					{
						cls = new Classes();
						cls.setClassId(rs.getLong(4));
						cls.setClassName(rs.getString(5));
					}
					page.getClses().add(cls);
					pages.add(page);
				}
				return pages;
			}
		}.<List<Page>>doJob(sql, new Object[]{classId,start,count});
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

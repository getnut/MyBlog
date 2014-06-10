package com.blog.dao;

import java.sql.SQLException;
import java.util.List;

import com.blog.entity.Page;

public interface PageDao
{
	public Page getPage(long pageId) throws SQLException;
	public List<Page> getPages(long start,long end) throws SQLException;
	
	public List<Page> getPages(long classId,long start,long end) throws SQLException;
	
	public int totalPages() throws SQLException;
	
	public int totalPages(long classId) throws SQLException;
	
	public boolean addPage(Page page) throws SQLException;
}

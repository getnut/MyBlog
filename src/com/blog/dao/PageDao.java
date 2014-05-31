package com.blog.dao;

import java.sql.SQLException;
import java.util.List;

import com.blog.entity.Page;

public interface PageDao
{
	public List<Page> getPages(long start,long end) throws SQLException;
	
	public List<Page> getPages(long classId,long start,long end) throws SQLException;
	
	public long totalPages() throws SQLException;
	
	public long totalPages(long classId);
}

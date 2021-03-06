package com.blog.dao;

import java.sql.SQLException;
import java.util.List;

import com.blog.entity.Page;

public interface PageDao
{
	public Page getPage(long pageId) throws SQLException;
	
	public List<Page> getPages(int start,int count) throws SQLException;
	
	public int totalPages() throws SQLException;
	
	public long addPage(Page page) throws SQLException;
	
	public boolean deletePage(long pageId) throws SQLException;
}

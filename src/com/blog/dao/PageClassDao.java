package com.blog.dao;

import java.sql.SQLException;

/*对PageClass表的操作*/
public interface PageClassDao
{
	//根据pageId删除
	public boolean deleteByPageId(long pageId) throws SQLException;
	
	//增加
	public boolean addPageClass(long pargeId,long classId) throws SQLException;
	
	//某个分类文章数量
	public int totalPageOfClass(long classId) throws SQLException;
}

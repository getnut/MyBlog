package com.blog.dao;

import java.sql.SQLException;
import java.util.List;

import com.blog.entity.Page;

/*主要进行多表查询*/
public interface PageQuery
{
	//所有文章分页
	public  List<Page> getPages(int start,int count) throws SQLException;
	
	//获得文章
	public  Page getPage(long pageId) throws SQLException;
	
	//某个分类下文章分页
	public List<Page> getPagesOfClass(long classId,int start,int count) throws SQLException;

}

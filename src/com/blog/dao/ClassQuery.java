package com.blog.dao;

import java.sql.SQLException;
import java.util.List;

import com.blog.entity.Classes;

public interface ClassQuery {
	//得到所有的分类以及文章的个数
	//主要从缓存中获得
	public List<Classes> getAllClasses() throws SQLException;
}

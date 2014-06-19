package com.blog.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.blog.dao.ClassQuery;
import com.blog.dbutils.JdbcTemplate;
import com.blog.entity.Classes;

public class ClassQueryImpl implements ClassQuery {

	private DataSource dataSource = null;
	

	//获得分类以及分类中文章的个数，这里面需要使用缓存
	//每次获得数据都要加入的缓存中，这里使用的基于时间的缓存
	@Override
	public List<Classes> getAllClasses() throws SQLException {
		String sql = "select cls.classId,cls.className,count(p_c.pageId) from classes cls left outer join page_class p_c on cls.classId = p_c.classId group by cls.classId";
		return new JdbcTemplate(this.dataSource) {
			
			@Override
			public Object doInJob(ResultSet rs) throws SQLException {
				List<Classes> clses = new ArrayList<Classes>();
				while(rs.next())
				{
					Classes tmp = new Classes();
					tmp.setClassId(rs.getLong(1));
					tmp.setClassName(rs.getString(2));
					tmp.setCount(rs.getLong(3));
					clses.add(tmp);
				}
				return clses;
			}
		}.doJob(sql, new Object[]{});
	}
	
	
	
	
	
	
	public DataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}

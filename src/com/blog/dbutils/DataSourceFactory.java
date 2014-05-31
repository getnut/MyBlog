package com.blog.dbutils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/*数据源工厂*/
public class DataSourceFactory
{
	private static DataSource dataSource = null;
	static
	{
		try
		{
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			dataSource = (DataSource) envCtx.lookup("jdbc/myblog");
			System.out.println("dataSource："+dataSource.getClass());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static DataSource createDataSource()
	{
		return dataSource;
	}
}

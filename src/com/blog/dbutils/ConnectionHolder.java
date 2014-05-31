package com.blog.dbutils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

/**
 * 
 * @author Administrator
 * ConnectionHolder作为线程局部变量中的元素，这样的设计为了让一个connection可以跨越service层和dao层，
 * 有效的进行事物提交和回滚。
 */
public class ConnectionHolder {
	
	/*
	 *使用map，可以使用ConnectionHolder可以服务多个DataSource
	 */
	private Map<DataSource,Connection> connectionMap = new HashMap<DataSource,Connection>();
	
	/*获得连接*/
	public Connection getConnection(DataSource dataSource)throws SQLException
	{
		Connection connection = connectionMap.get(dataSource);
		/*如果不存在该dataSource对应的connection，dataSource的方法重新获得一个新的connection对象，然后加入map中*/
		if(null == connection || connection.isClosed())
		{
			connection = dataSource.getConnection();
			connectionMap.put(dataSource, connection);
		}
		return connection;
	}
	/*移除连接*/
	public void removeConnection(DataSource dataSource)
	{
		 connectionMap.remove(dataSource);
	}
}

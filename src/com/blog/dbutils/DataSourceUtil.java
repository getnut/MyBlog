package com.blog.dbutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

/*释放数据库资源*/
public class DataSourceUtil {
	public static void close(Connection con,PreparedStatement ps,ResultSet rs)
	{
		try
		{
			if (null != rs)
			{
				rs.close();
			}
			if (null != ps)
			{
				ps.close();
			}
			if(null != con)
			{
				con.close();
			}
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	public static void close(PreparedStatement ps,ResultSet rs)
	{
		try
		{
			if (null != rs)
			{
				rs.close();
			}
			if (null != ps)
			{
				ps.close();
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement ps)
	{
		try
		{
			if (null != ps)
			{
				ps.close();
			}
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	 public static void close(DataSource dataSource)
	    {
	        Connection connection = null;
	        try
	        {
	            connection = SingleThreadConnectionHolder.getConnection(dataSource);
	            connection.setAutoCommit(true);
	            connection.setReadOnly(false);
	            connection.close();
	            SingleThreadConnectionHolder.removeConnection(dataSource);
	        } catch (SQLException e)
	        {
	            throw new RuntimeException("Couldn't close connection[" + connection + "].", e);
	        }
	    }
}

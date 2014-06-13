package com.blog.dbutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;


public abstract class JdbcTemplate
{
	private DataSource ds = null;
	
	public JdbcTemplate (DataSource ds)
	{
		this.ds = ds;
	}
	//主要用户查询
	@SuppressWarnings("unchecked")
	public <T> T doJob(String sql,Object[]params) throws SQLException
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			con = SingleThreadConnectionHolder.getConnection(ds);
			ps = con.prepareStatement(sql);
			for (int i = 1;i <= params.length;i++)
			{
				ps.setObject(i, params[i-1]);
			}
			rs = ps.executeQuery();
			return (T)this.doInJob(rs);
			
		}catch(SQLException  ex)
		{
			ex.printStackTrace();
			throw ex;
		}finally
		{
			DataSourceUtil.close(ps, rs);
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> T getGeneratedKey(String sql,Object[]params) throws SQLException
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			con = SingleThreadConnectionHolder.getConnection(ds);
			ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			for (int i = 1;i <= params.length;i++)
			{
				ps.setObject(i, params[i-1]);
			}
			ps.execute();
			rs = ps.getGeneratedKeys();
			Object id = null;
			if(rs != null && rs.next())
			{
				id = rs.getObject(1);
			}
			return (T)id;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			throw ex;
		}finally
		{
			DataSourceUtil.close(ps, rs);
		}
	}
	
	public abstract Object doInJob(ResultSet rs) throws SQLException;
	
	public int doCurdJob(String sql,Object[]params) throws SQLException
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			con = SingleThreadConnectionHolder.getConnection(ds);
			ps = con.prepareStatement(sql);
			for (int i = 1;i <= params.length;i++)
			{
				ps.setObject(i, params[i-1]);
			}
			int count = ps.executeUpdate();
			return count;
			
		}catch(SQLException  ex)
		{
			ex.printStackTrace();
			throw ex;
		}finally
		{
			DataSourceUtil.close(ps, rs);
		}
	}
}

package com.blog.dbutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


public abstract class JdbcTemplate
{
	private DataSource ds = null;
	
	public JdbcTemplate (DataSource ds)
	{
		this.ds = ds;
	}
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
	public abstract Object doInJob(ResultSet rs) throws SQLException;
}

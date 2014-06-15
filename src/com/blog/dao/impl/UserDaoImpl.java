package com.blog.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.blog.dao.UserDao;
import com.blog.dbutils.DateUtil;
import com.blog.dbutils.JdbcTemplate;
import com.blog.entity.User;

public class UserDaoImpl implements UserDao
{
	private DataSource dataSource = null;
	
	/**根据姓名和密码查找用户**/
	public User findUserByUserNameAndPassword(final String userName,final String password) throws SQLException
	{
		String sql = "SELECT UserId,LoginTime FROM user where UserName=? and Password= ?";
			return new JdbcTemplate(this.dataSource)
			{
				@Override
				public Object doInJob(ResultSet rs) throws SQLException
				{
					User user = null;
					if(rs.next())
					{
						user = new User();
						user.setUserId(rs.getLong(1));
						user.setLastLoginTime(DateUtil.getDateString(rs.getTimestamp(2)));
						user.setUserName(userName);
						user.setPassword(password);
					}
					return user;
				}
			}.<User>doJob(sql, new Object[]{userName,password});
	}
	public DataSource getDataSource()
	{
		return dataSource;
	}
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
}

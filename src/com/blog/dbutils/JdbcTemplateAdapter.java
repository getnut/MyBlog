package com.blog.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcTemplateAdapter extends JdbcTemplate{

	public JdbcTemplateAdapter(DataSource ds) {
		super(ds);
	}

	@Override
	public Object doInJob(ResultSet rs) throws SQLException {
		return null;
	}
}

package com.ablaze.util.poolman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class DBHelper {
	public static ResultSet read(String tag,String sql,String[] vals) throws SQLException{
		return sql_query(sql, vals);
	}
	public static int write(String tag,String sql,String[] vals) throws SQLException{
		return sql_update(sql, vals);
	}
	public static PreparedStatement prepare(String tag,String sql)throws SQLException{
		Connection connection = getConnection();
		PreparedStatement stmt= connection.prepareStatement(sql);
		stmt.close();
		connection.close();
		return stmt;
	}
	public static boolean create(String tag,String table,String sql) throws SQLException{
		ResultSet rs = read(tag, "show tables like '"+table+"';", null);
		if(rs.next())return true;
		return write(tag,sql,null)==0;
	}
	
	public static ResultSet sql_query(String query,String[] vals) throws SQLException{
		Connection connection = getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		if(vals!=null&&vals.length>0){
			for(int i=0;i<vals.length;i++){
				stmt.setString(i+1, vals[i]);
			}
		}
		ResultSet rs= stmt.executeQuery();
		return rs;
	}
	public static int sql_update(String query,String[] vals) throws SQLException{
		Connection connection = getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		if(vals!=null&&vals.length>0){
			for(int i=0;i<vals.length;i++){
				stmt.setString(i+1, vals[i]);
			}
		}
		int result = stmt.executeUpdate();
		stmt.close();
		connection.close();
		return result;
	}
	private static Connection getConnection(){
		DataSource source = DbPoolConnection.getDataSource();
		try {
			return source.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

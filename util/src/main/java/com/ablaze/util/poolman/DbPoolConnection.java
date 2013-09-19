package com.ablaze.util.poolman;

import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DbPoolConnection {
	private static DruidDataSource source;
	private static final String FILE_NAME = "db_server.properties";
	
	private DbPoolConnection(){}

	public static DataSource getDataSource() {
		if(source == null){
			Properties properties = new Properties();
			try {
				InputStream in = DbPoolConnection.class.getClassLoader().getResourceAsStream(FILE_NAME);
				properties.load(in);
				source = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return source;
		
	}
	public static DataSource getDataSource(Properties properties){
		if(source == null){
			try {
				source = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return source;
	}

}

package com.ablaze.util.poolman;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBHelperTest {
	public static final Logger logger = LoggerFactory.getLogger(DBHelperTest.class);
	
	@Test
	public void test_path() {

		logger.debug(System.getProperty("user.dir"));

		File file = new File("resource/db_server.properties");
		logger.debug(file.getAbsolutePath());
		

	}

	@Test
	public void test_read() {
		try {
			Properties properties = new Properties();
			File file = new File("D:/workspace/git/ablaze/util/src/test/resources/db_server.properties");
			InputStream in = new FileInputStream(file);
			properties.load(in);
			DbPoolConnection.getDataSource(properties);
			ResultSet rs = DBHelper.read(DBHelperTest.class.getName(),"SELECT * FROM test", null);
			while (rs.next()) {
				int i = 1;
				logger.debug(rs.getInt(i++) + " : " + rs.getString(i++)
						+ " : " + rs.getString(i++));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

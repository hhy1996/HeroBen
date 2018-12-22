package com.goli.heroben.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
	public static final String DBDRIVER = "com.mysql.jdbc.Driver";
	public static final String DBURL = "jdbc:mysql://127.0.0.1:3306/heroben?useUnicode=true&characterEncoding=UTF-8";
	public static final String DBUSER = "tzy";
	public static final String DBPASS = "123108199";

	private Connection conn = null;
	// 在构造方法里面进行了数据库连接

	public DataBaseConnection() throws Exception {
		try {
			Class.forName(DBDRIVER);
			this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		} catch (Exception e) {
			throw e;
		}
	}

	public Connection getConnection() {
		return this.conn;
	}

	public void close() {
		if (this.conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

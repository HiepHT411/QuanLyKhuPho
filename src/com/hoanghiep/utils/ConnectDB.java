package com.hoanghiep.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	static final String userName = "root";
	static final String passWord = "";
	static final String driver = "com.mysql.cj.jdbc.Driver";
	static final String url = "jdbc:mysql://localhost:3306/quanlykhupho?serverTimezone=UTC";
	static Connection conn = null;

	public static Connection getConnection() {
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userName, passWord);
		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
		System.err.println("Kết nối tới CSDL thành công");
		return conn;
	}
	
	public static void shutDownConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.err.println("Ngat Kết nối tới CSDL");
	}
	
	
}

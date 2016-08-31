package kh.com.kshrd.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagement {

	private static Connection con;
	private static String url;
	private static String username;
	private static String password;
	private static String driver;

	public ConnectionManagement() {
		this(url, username, password,driver);
	}

	public ConnectionManagement(String url, String username, String password,String ddriver) {
		try {
			// 1. Loading and Registering driver
			Class.forName(driver);

			// 2. Establishing a Connection
			con = DriverManager.getConnection(url, username, password);
			System.out.println("YOU HAVE BEEN CONNECTED SUCCESSFULLY.");
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	// Get the connection object
	public static Connection getConnection() {
		return con;
	}

	// Close the connection
	public static void closeConnection() {
		if (con != null) {
			try {
				con.close();
				con = null;
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			
			System.out.println("CONNECTION HAS BEEN CLOSED SUCCESSFULLY.");
		}
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		ConnectionManagement.url = url;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		ConnectionManagement.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		ConnectionManagement.password = password;
	}

	public static String getDriver() {
		return driver;
	}

	public static void setDriver(String driver) {
		ConnectionManagement.driver = driver;
	}
	
}

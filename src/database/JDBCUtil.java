package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCUtil {
	public static Connection getConnection() {
		Connection c=null;
		try {
			String url="jdbc:sqlserver://localhost:1433;DatabaseName=QuanLyTourDuLich";
			String username="sa";
			String password="123456";
			
			c=DriverManager.getConnection(url,username,password);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	public static void closeConnection(Connection c) {
		try {
		if (c!=null) 
			c.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void printInfo(Connection c) {
		try {
			if (c!=null) {
				DatabaseMetaData data=c.getMetaData();
				System.out.println(data.getDatabaseProductName());
				System.out.println(data.getDatabaseProductVersion());
				System.out.println("Kết nối thành công");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

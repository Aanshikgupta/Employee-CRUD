package database;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
	
	public static final String db_url="jdbc:mysql://localhost:3306/employee_db";
	public static final String db_username="root";
	public static final String db_password="root";
	
	public static Connection getConnection() {
		try {
			System.out.println("Starting getConnection");
			Class.forName("com.mysql.jdbc.Driver");
			
			connection=DriverManager.getConnection(db_url,db_username,db_password);
			
			if(connection!=null)return connection;
			
			return null;
			
		}catch(Exception e) {
			System.out.println("Exception in DB Connection");
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static Connection connection=getConnection();

	public static void main(String[] args) {
		System.out.println(DBConnection.connection);
	}

}

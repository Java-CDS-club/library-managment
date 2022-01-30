package services;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnecteurBD {
	private static final String URL="jdbc:mysql://localhost:3306/gestion_bibliotheque?serverTimezone=UTC";
	private static final String USERNAME="root";
	private static final String PASSWORD="";
	private static Connection connect = null;
	
	@SuppressWarnings("exports")
	public static Connection getConnection()
	{   
		try {
			if(connect==null) {
				connect=DriverManager.getConnection(URL, USERNAME, PASSWORD);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return connect;		
	}

}

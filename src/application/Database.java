package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	private String databaseURL;
	private Connection c;
	private Statement s;
	
	Database(){
		
		
		try(Connection c = 
				DriverManager.getConnection(databaseURL);
				Statement s = c.createStatement();) {		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Connection getConnection() {
		return this.c;
	}
	
	public Statement getStatement() {
		return this.s;
	}
}

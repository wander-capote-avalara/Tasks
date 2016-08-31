	package task.bd.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTask {

	private Connection connectionTask;
	private String driver = "org.postgresql.Driver";
	private String path = "jdbc:postgresql://localhost:5432/taskdb";
	private String user = "postgres";
	private String password = "root";
	
	public Connection openConnection() throws Exception {
		
		try {	
			Class.forName(driver);	
			System.setProperty("jdbc.Drivers", driver);
			connectionTask = DriverManager.getConnection(path, user, password);
		} catch (Exception e) {
			throw new Exception("Error while connecting with DataBase!", e);
		}
		return connectionTask;
	}
	
	public void closeConnection() throws Exception {
		try {
			connectionTask.close();
		} catch (Exception e) {
			throw new Exception("Error while disconnecting!", e);
		}
	}

}

/*
	Class.forName("org.postgresql.Driver");
	connectionTask = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/taskdb", "postgres", "root");
	
	Class.forName("org.gjt.mm.mysql.Driver");
	connectionTask = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdb", "root", "");
*/

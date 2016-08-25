package task.bd.connection;

import java.sql.Connection;

public class ConnectionTask {

	private Connection connectionTask;

	public Connection openConnection() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			connectionTask = java.sql.DriverManager
					.getConnection("jdbc:mysql://localhost:3306/taskdb", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connectionTask;
	}
	
	public void closeConnection() {
		try {
			connectionTask.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

/*
	Class.forName("org.postgresql.Driver");
	connectionTask = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:3306/mydb", "root", "root");
*/

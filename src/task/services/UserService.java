package task.services;

import java.sql.Connection;

import task.JDBC.JDBCUserDAO;
import task.bd.connection.ConnectionTask;
import task.objects.User;

public class UserService {

	public void add(User user)
	{
		ConnectionTask conec = new ConnectionTask();
		Connection connection = conec.openConnection();
		JDBCUserDAO JDBCuserDAO = new JDBCUserDAO(connection);
		JDBCuserDAO.add(user);
				
		conec.closeConnection();
	}
	
}

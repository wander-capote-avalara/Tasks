package task.services;

import java.sql.Connection;

import task.JDBC.JDBCUserDAO;
import task.bd.connection.ConnectionTask;
import task.helper.Helper;
import task.objects.User;

public class UserService {

	public void add(User user) throws Exception
	{
		user.setPassword(Helper.hasher(user.getPassword()));
		ConnectionTask conec = new ConnectionTask();
		Connection connection = conec.openConnection();
		JDBCUserDAO JDBCuserDAO = new JDBCUserDAO(connection);
		JDBCuserDAO.add(user);
				
		conec.closeConnection();
	}

	public User getUserInfo(int id) throws Exception {
		ConnectionTask conec = new ConnectionTask();
		Connection connection = conec.openConnection();
		JDBCUserDAO JDBCuserDAO = new JDBCUserDAO(connection);
		User user = JDBCuserDAO.getUserInfo(id);
		conec.closeConnection();

		return user;
	}
	
}

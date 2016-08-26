package task.services;

import java.sql.Connection;
import java.util.List;

import task.JDBC.JDBCUserDAO;
import task.JDBC.JDBCwiDAO;
import task.bd.connection.ConnectionTask;
import task.helper.Helper;
import task.objects.User;
import task.objects.Work_Item;

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

	public List<User> getUsers(int id) throws Exception {
		ConnectionTask connection = new ConnectionTask();
		Connection connec = connection.openConnection();
		JDBCUserDAO JDBCuserDAO = new JDBCUserDAO(connec);
		List<User> uList = JDBCuserDAO.getUsers(id);
		connection.closeConnection();

		return uList;
	}
	
}

package task.services;

import java.sql.Connection;
import java.util.List;

import task.JDBC.JDBCwiDAO;
import task.bd.connection.ConnectionTask;
import task.objects.WI_Log;
import task.objects.Work_Item;

public class WIService {

	public List<Work_Item> getWIs(int id, int userId, int status, boolean showAll) throws Exception {
		ConnectionTask connection = new ConnectionTask();
		Connection connec = connection.openConnection();
		JDBCwiDAO jdbcWI = new JDBCwiDAO(connec);
		List<Work_Item> WIList = jdbcWI.getWIs(id, userId, status, showAll);
		connection.closeConnection();

		return WIList;
	}

	public void add(Work_Item wi) throws Exception {
		ConnectionTask connection = new ConnectionTask();
		Connection connec = connection.openConnection();
		JDBCwiDAO jdbcWI = new JDBCwiDAO(connec);
		jdbcWI.add(wi);
				
		connection.closeConnection();	
	}

	public List<WI_Log> getWILogs(int id) throws Exception {
		ConnectionTask connection = new ConnectionTask();
		Connection connec = connection.openConnection();
		JDBCwiDAO jdbcWI = new JDBCwiDAO(connec);
		List<WI_Log> WILogs = jdbcWI.getWILogs(id);
		connection.closeConnection();

		return WILogs;
	}

}

package task.services;

import java.sql.Connection;
import java.util.List;

import task.JDBC.JDBCwiDAO;
import task.bd.connection.ConnectionTask;
import task.objects.Work_Item;

public class WIService {

	public List<Work_Item> getWIs(int id) throws Exception {
		ConnectionTask connection = new ConnectionTask();
		Connection connec = connection.openConnection();
		JDBCwiDAO jdbcWI = new JDBCwiDAO(connec);
		List<Work_Item> WIList = jdbcWI.getWIs(id);
		connection.closeConnection();

		return WIList;
	}

}

package task.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;

import task.objects.User;

public class JDBCUserDAO {

	private Connection connection;

	public JDBCUserDAO(Connection connectionR) {
		this.connection = connectionR;
	}

	public void add(User user) {
		StringBuilder stbd = new StringBuilder();

		stbd.append("INSERT INTO users (");
		stbd.append("email, password");
		stbd.append(") VALUES (?,?)");

		PreparedStatement p;

		try {
			p = this.connection.prepareStatement(stbd.toString());
			p.setString(1, user.getEmail());
			p.setString(2, user.getPassword());
			p.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

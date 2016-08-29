package task.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import task.objects.User;

public class JDBCUserDAO {

	private Connection connection;

	public JDBCUserDAO(Connection connectionR) {
		this.connection = connectionR;
	}

	public void add(User user) throws Exception {
		StringBuilder stbd = new StringBuilder();

		stbd.append("INSERT INTO users (");
		stbd.append("email, password, username");
		stbd.append(") VALUES (?,?,?)");

		PreparedStatement p;

		try {
			p = this.connection.prepareStatement(stbd.toString());
			p.setString(1, user.getEmail());
			p.setString(2, user.getPassword());
			p.setString(3, user.getUsername());
			p.execute();

		} catch (Exception e) {
			throw new Exception("Error while adding user!");
		}
	}
	
	public boolean checkEmail(String email) throws Exception{
		StringBuilder stbd = new StringBuilder();
		stbd.append("SELECT email ");
		stbd.append("FROM users ");
		stbd.append("WHERE email = ?");

		PreparedStatement p;
		ResultSet rs = null;

		try {
			p = this.connection.prepareStatement(stbd.toString());
			p.setString(1, email);
			
			rs = p.executeQuery();

			return rs.next() ? true : false;
		} catch (Exception e) {
			throw new Exception("Error while searching for email!", e);
		}
	}

	public User login(User user) throws Exception {
		StringBuilder stbd = new StringBuilder();
		stbd.append("SELECT id, email ");
		stbd.append(" FROM users ");
		if (!user.equals("null") || !user.equals("")) 
			stbd.append("WHERE email=? AND password=?");

		PreparedStatement p;
		ResultSet rs = null;
		User userExist = null;

		try {
			p = this.connection.prepareStatement(stbd.toString());
			p.setString(1, user.getEmail());
			p.setString(2, user.getPassword());				
			
			rs = p.executeQuery();

			while (rs.next()) {
				userExist = new User();
				userExist.setId(rs.getInt("id"));
				userExist.setEmail(rs.getString("email"));
			}

		} catch (Exception e) {
			throw new Exception("Error while searching for user!", e);
		}
		return userExist;
	}
	
	public User getUserInfo(Integer id) throws Exception {
		StringBuilder stbd = new StringBuilder();
		stbd.append("SELECT id, email, username ");
		stbd.append(" FROM users ");
		if (id != null) 
			stbd.append("WHERE id=?");

		PreparedStatement p;
		ResultSet rs = null;
		User user = null;

		try {
			p = this.connection.prepareStatement(stbd.toString());
			p.setInt(1, id);			
			
			rs = p.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
			}

		} catch (Exception e) {
			throw new Exception("Error while searching for user!", e);
		}
		return user;
	}
	
	public List<User> getUsers(int id) throws Exception {
		StringBuilder stbd = new StringBuilder();
		stbd.append("SELECT id, email, username ");
		stbd.append(" FROM users ");
		if(id != 0)
			stbd.append("WHERE id = ?");

		PreparedStatement p;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();

		try {
			p = this.connection.prepareStatement(stbd.toString());
			if(id != 0)
				p.setInt(1, id);
			
			rs = p.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				userList.add(user);
			}

		} catch (Exception e) {
			throw new Exception("Error while searching for users!", e);
		}
		return userList;
	}

}

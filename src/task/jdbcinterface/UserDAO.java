package task.jdbcinterface;

import java.util.List;

import task.objects.User;

public interface UserDAO {	
	public void add(User user) throws Exception;
	public boolean checkEmail(String email) throws Exception;
	public User getUserInfo(Integer Id) throws Exception;
	public List<User> getUsers(int id) throws Exception;
	public User login(User user) throws Exception;
}

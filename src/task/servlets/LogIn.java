package task.servlets;

import java.sql.Connection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import task.JDBC.JDBCUserDAO;
import task.bd.connection.ConnectionTask;
import task.helper.Helper;
import task.objects.User;

public class LogIn extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LogIn(){
		super();
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = new User();
		try {
			String context = request.getServletContext().getContextPath();
			if(user.getEmail() == "" || user.getPassword() == "")
				throw new Exception("Fill in the fields correctly!");

			user.setEmail(request.getParameter("email"));
			user.setPassword(Helper.hasher(request.getParameter("password")));

			ConnectionTask connection = new ConnectionTask();
			Connection connec = connection.openConnection();
			JDBCUserDAO JDBCuserDAO = new JDBCUserDAO(connec);
			User userExists = JDBCuserDAO.login(user);
			connection.closeConnection();
			
			HttpSession session = request.getSession();
			if (userExists != null) {
				session.setAttribute("id", Integer.toString(userExists.getId()));
				session.setAttribute("email", Helper.hasher(userExists.getEmail()));

				response.sendRedirect(context + "/resources/index.html");
			} else {				
				response.sendRedirect(context + "/index.html?incorrect");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

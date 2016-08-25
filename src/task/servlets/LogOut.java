package task.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogOut() {
		super();
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath() + "/index.html");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

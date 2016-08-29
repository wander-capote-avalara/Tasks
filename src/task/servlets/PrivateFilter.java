package task.servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PrivateFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String context = request.getServletContext().getContextPath()+"/index.html";

		try {			
			HttpSession session = ((HttpServletRequest) request).getSession();

			if (((HttpServletRequest) request).getRequestURI().equals(context) || session.getAttribute("id") != null) {
				chain.doFilter(request, response);
			} else {
				((HttpServletResponse) response).sendRedirect(context+"?invalid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub		
	}
}

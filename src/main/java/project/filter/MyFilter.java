package project.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.controller.LoginController;

@WebFilter("/master/*")
public class MyFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession(false);
		boolean loggedIn = session != null && session.getAttribute("loggedInUser") != null;
		boolean loginRequest = req.getRequestURI().equals("/Project01/master/login");
		//This could be something like modifying the URI or
				// checking to see if they are currently in a session
		if (loggedIn || loginRequest) {
			chain.doFilter(request, response);
		}else {
			resp.sendRedirect("/Project01/master/login");
		}
		
		
		
	}

}

package project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import project.model.User;
import project.service.UserService;
import project.service.UserServiceImplementation;

public class LoginController {
	
//	private static void checkSession(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//		System.out.println("\t\t\t<LoginController checkSession>");
//		HttpSession session = req.getSession(false);
//		if(session == null) {
//			req.getRequestDispatcher("/index.html").forward(req, resp);
//		}
//		System.out.println("\t\t\t</LoginController checkSession>");
//	}
	
	public static void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("\t\t<LoginController login>");
//		checkSession(req,resp); //This will redirect user to the main page it they try to access the url for this method.
		String myPath = "/index.html";

		// Extracting fields to be worked on
		String username = req.getParameter("loginUsername");
		String password = req.getParameter("loginPassword");

		if (!req.getMethod().equals("POST")) {
			myPath = "/index.html";
			req.getRequestDispatcher(myPath).forward(req, resp);
		} else {
			// Checking if using correct info
			UserService serv = new UserServiceImplementation();
			User resultUser = serv.login(username, password);
			if (resultUser != null) { // if user exists, login is possible\
				req.getSession().setAttribute("loggedInUser", resultUser);
				System.out.println("\t\t\tSession User username: "+resultUser.getUsername());
				if (resultUser.getRole() == 1) { // if role is 1, we're dealing with an Employee
					myPath = "/employeepage.html";
				} else if (resultUser.getRole() == 2) { // if role is 2, we're dealing with a Financial Manager
					myPath = "/fmanagerpage.html";
				}
			} else {
				myPath = "/indexloginfail.html";
			}
			req.getRequestDispatcher(myPath).forward(req, resp);
			
		}
		System.out.println("\t\t</LoginController login>");
	}
	
	public static void getCurrentUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("\t\t<LoginController getCurrentUser>");
		User user = (User) req.getSession().getAttribute("loggedInUser");
		
		PrintWriter printer = resp.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		printer.write(mapper.writeValueAsString(user));
		
		System.out.println("\t\t</LoginController getCurrentUser>");
	}
	
	public static void logoff(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("\t\t<LoginController logoff>");
		
		req.getSession().invalidate(); //destroy session for this user.
//		checkSession(req,resp);
		req.getRequestDispatcher("/index.html").forward(req, resp);
		
		System.out.println("\t\t</LoginController logoff>");
	}
	
	
}

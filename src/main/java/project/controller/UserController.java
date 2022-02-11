package project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import project.model.User;
import project.service.UserService;
import project.service.UserServiceImplementation;

public class UserController {
	public static void deleteUserById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("\t\t<UserController getUserById>");
		UserService serv = new UserServiceImplementation();
		int id = Integer.parseInt(req.getParameter("userDeleteId"));
		User myUser = serv.selectUserById(id);
		serv.removeUser(id);
		// Returning a JSON object with the user to the browser
		PrintWriter printer = resp.getWriter();
		printer.write(new ObjectMapper().writeValueAsString(myUser));
		System.out.println("\t\t</UserController getUserById>");
	}
	
	public static void getUserById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("\t\t<UserController getUserById>");
		UserService serv = new UserServiceImplementation();
//		System.out.println("\t\t\tTHE NUMBER ACQUIRED IS: "+req.getParameter("userGetId"));
		User myUser = serv.selectUserById(Integer.parseInt(req.getParameter("userGetId")));

		// Returning a JSON object with the user to the browser
		PrintWriter printer = resp.getWriter();
		printer.write(new ObjectMapper().writeValueAsString(myUser));
		System.out.println("\t\t</UserController getUserById>");
	}
	
	public static void getUser1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("\t\t<UserController getUser1>");
		UserService serv = new UserServiceImplementation();
//		System.out.println("\t\t\tTHE NUMBER ACQUIRED IS: "+req.getParameter("userGetId"));
		User myUser = serv.selectUserById(1);

		// Returning a JSON object with the user to the browser
		PrintWriter printer = resp.getWriter();
		printer.write(new ObjectMapper().writeValueAsString(myUser));
		System.out.println("\t\t</UserController getUser1>");
	}

	public static void getUserByUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("\t\t<UserController getUserByUsername>");
		UserService serv = new UserServiceImplementation();
		User myUser = serv.selectUserByUsername(req.getParameter("userGetUsername"));

		// Returning a JSON object with the user to the browser
		PrintWriter printer = resp.getWriter();
		printer.write(new ObjectMapper().writeValueAsString(myUser));
		System.out.println("\t\t</UserController getUserByUsername>");
	}

	public static void createFromFields(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("\t\t<UserTestController testCreate>");

		String userUsername = req.getParameter("userUsername");
		String userPassword = req.getParameter("userPassword");
		String userFirstName = req.getParameter("userFirstName");
		String userLastName = req.getParameter("userLastName");
		String userEmail = req.getParameter("userEmail");
		int userRole = Integer.parseInt(req.getParameter("userRole"));

		User newUser = new User(userUsername, userPassword, userFirstName, userLastName, userEmail, userRole);
		UserService serv = new UserServiceImplementation();
		serv.insertUser(newUser);

		// Returning a JSON object with the user to the browser
		PrintWriter printer = resp.getWriter();
		printer.write(new ObjectMapper().writeValueAsString(newUser));

		System.out.println("\t\t</UserTestController testCreate>");
	}
}

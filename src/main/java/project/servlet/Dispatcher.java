package project.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.controller.LoginController;
import project.controller.ReimbursementController;
import project.controller.UserController;

public class Dispatcher {
	public static void myVirtualRouter(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		System.out.println("\t<Dispatcher myVirtualRouter>");
		System.out.println("\t\tCurrent URL: " + req.getRequestURL());
		System.out.println("\t\tCurrent URI: " + req.getRequestURI());
		
		switch(req.getRequestURI()) {
		case "/Project01/master/login":
			System.out.println("\t\tEntering login...");
			LoginController.login(req, resp);
			break;
		case "/Project01/master/userInfo":
			System.out.println("\t\tEntering get user Info...");
			LoginController.getCurrentUser(req, resp);
			break;
		case "/Project01/master/userLogoff":
			System.out.println("\t\tEntering logoff...");
			LoginController.logoff(req, resp);
			break;
		case "/Project01/master/reimbursement/changeStatus":
			System.out.println("\t\tEntering changing reimbursement status...");
			ReimbursementController.changeStatus(req, resp);
			break;
		case "/Project01/master/reimbursement/createFromUser":
			System.out.println("\t\tEntering create reimbursement from user id...");
			ReimbursementController.createFromUser(req, resp);
			break;
		case "/Project01/master/reimbursement/getAllReimbursementFromUser":
			System.out.println("\t\tEntering get all reimbursement from user id...");
			ReimbursementController.getAllReimbursementFromUser(req, resp);
			break;
		case "/Project01/master/reimbursement/getAllReimbursement":
			System.out.println("\t\tEntering get all reimbursements...");
			ReimbursementController.getAllReimbursements(req, resp);
			break;
		default:
			System.out.println("\t\tUninplemented shenanigans!!!");
			break;
		}
		System.out.println("\t</Dispatcher myVirtualRouter>");
	}
}

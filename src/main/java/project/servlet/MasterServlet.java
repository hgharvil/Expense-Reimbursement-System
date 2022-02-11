package project.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
public class MasterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("<MasterServlet doGet>");
//		HttpSession session = req.getSession();
//		HttpSession session = req.getSession(false);
//		if(session == null) {
//			
//		}
		//session.setAttribute("currentUsuario", myUsuario);
		Dispatcher.myVirtualRouter(req, resp);

//		System.out.println(req.getRequestDispatcher(getServletInfo()));
//		User loggedUser = (User) req.getSession().getAttribute("loggedInUser");
//		if(loggedUser != null) System.out.println("\tCurrent User username: "+loggedUser.getUsername());
//		else System.out.println("\tNo user logged in...");
		System.out.println("</MasterServlet doGet>");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("<MasterServlet doPost>");
		doGet(req, resp);
		System.out.println("</MasterServlet doPost>");

	}
}

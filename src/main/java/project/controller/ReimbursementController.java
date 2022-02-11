package project.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import project.model.Reimbursement;
import project.model.User;
import project.service.ReimbursementService;
import project.service.ReimbursementServiceImplementation;
import project.service.UserService;
import project.service.UserServiceImplementation;

public class ReimbursementController {
	public static void deleteReimbursementById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("\t\t<ReimbursementController deleteReimbursementById>");

		System.out.println("\t\t</ReimbursementController deleteReimbursementById>");
	}

	public static void getReimbursementById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("\t\t<ReimbursementController getReimbursementById>");
		ReimbursementService serv = new ReimbursementServiceImplementation();
		System.out.println("\t\t\tTHE NUMBER ACQUIRED IS: " + req.getParameter("reimbursementGetId"));
		Reimbursement myReimbursement = serv
				.selectReimbursementId(Integer.parseInt(req.getParameter("reimbursementGetId")));
		PrintWriter printer = resp.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		printer.write(mapper.writeValueAsString(myReimbursement));
		System.out.println("\t\t</ReimbursementController getReimbursementById>");
	}

	public static void getReimbursement1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("\t\t<ReimbursementController getReimbursement1>");
		ReimbursementService serv = new ReimbursementServiceImplementation();
		Reimbursement myReimbursement = serv.selectReimbursementId(1);
		PrintWriter printer = resp.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		printer.write(mapper.writeValueAsString(myReimbursement));
		System.out.println("\t\t</ReimbursementController getReimbursement1>");
	}
	
	public static void changeStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("\t\t<ReimbursementController changeStatus>");
		int id = Integer.parseInt(req.getParameter("reimbursementId"));
		int status = Integer.parseInt(req.getParameter("reimbursementStatus"));
		User user = (User) req.getSession().getAttribute("loggedInUser");
		System.out.println("\t\t\tTHE REIMBURSEMENT ID IS: " + id);
		System.out.println("\t\t\tTHE REIMBURSEMENT STATUS IS: " + status);
		ReimbursementService serv = new ReimbursementServiceImplementation();
		
		Reimbursement myReimbursement = serv.selectReimbursementId(id);
		myReimbursement.setStatusId(status);
		myReimbursement.setResolver(user.getId());
		myReimbursement.setResolved(LocalDateTime.now());
		System.out.println("\t\t\tThe new reimbursement is: " + myReimbursement);
		serv.updateReimbursement(myReimbursement);
		
		
		
//		PrintWriter printer = resp.getWriter();
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.registerModule(new JavaTimeModule());
//		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//		printer.write(mapper.writeValueAsString(myReimbursement));
		
		req.getRequestDispatcher("/fmanagerpage.html").forward(req, resp);
		
		System.out.println("\t\t</ReimbursementController changeStatus>");
	}
	
	public static void getAllReimbursementFromUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("\t\t<ReimbursementController getAllReimbursementFromUser>");
		User user = (User) req.getSession().getAttribute("loggedInUser");
		
		ReimbursementService serv = new ReimbursementServiceImplementation();
		ArrayList<Reimbursement> reimbursementList = serv.selectAllReimbursementFromAuthor(user.getId());
		
		PrintWriter printer = resp.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		printer.write(mapper.writeValueAsString(reimbursementList));
		System.out.println("\t\t\t"+reimbursementList);
		System.out.println("\t\t</ReimbursementController getAllReimbursementFromUser>");
	}
	
	public static void getAllReimbursements(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("\t\t<ReimbursementController getAllReimbursements>");
		
		ReimbursementService serv = new ReimbursementServiceImplementation();
		ArrayList<Reimbursement> reimbursementList = serv.selectAllReimbursement();
		
		PrintWriter printer = resp.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		printer.write(mapper.writeValueAsString(reimbursementList));
		System.out.println("\t\t\t"+reimbursementList);
		System.out.println("\t\t</ReimbursementController getAllReimbursements>");
	}

	public static void createFromFields(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("\t\t<ReimbursementController createFromFields>");
		
		int reimbursementAmount = Integer.parseInt(req.getParameter("reimbursementAmount"));
		LocalDateTime reimbursementSubmitted = LocalDateTime.parse(req.getParameter("reimbursementSubmitted"));
		LocalDateTime reimbursementResolved = LocalDateTime.parse(req.getParameter("reimbursementResolved"));
		String reimbursementDescription = req.getParameter("reimbursementDescription");
		byte[] reimbursementReceipt = req.getParameter("reimbursementReceipt").getBytes();
		int reimbursementAuthor = Integer.parseInt(req.getParameter("reimbursementAuthor"));
		int reimbursementResolver = Integer.parseInt(req.getParameter("reimbursementResolver"));
		int reimbursementStatus = Integer.parseInt(req.getParameter("reimbursementStatus"));
		int reimbursementType = Integer.parseInt(req.getParameter("reimbursementType"));

		Reimbursement newReimbursement = new Reimbursement(reimbursementAmount, reimbursementSubmitted,
				reimbursementResolved, reimbursementDescription, reimbursementReceipt, reimbursementAuthor,
				reimbursementResolver, reimbursementStatus, reimbursementType);
		ReimbursementService serv = new ReimbursementServiceImplementation();
		serv.insertReimbursement(newReimbursement);

		// Returning a JSON object with the user to the browser
		PrintWriter printer = resp.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		printer.write(mapper.writeValueAsString(newReimbursement));

		System.out.println("\t\t</ReimbursementController createFromFields>");
	}

	public static void createFromUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("\t\t<ReimbursementController createFromFields>");
		User user = (User) req.getSession().getAttribute("loggedInUser");
		int reimbursementAmount = 0;
		if(req.getParameter("reimbursementAmount") != "") reimbursementAmount = Integer.parseInt(req.getParameter("reimbursementAmount"));
		LocalDateTime reimbursementSubmitted = LocalDateTime.now();
		LocalDateTime reimbursementResolved = null; // null
		String reimbursementDescription = req.getParameter("reimbursementDescription");
		Part filePart = req.getPart("reimbursementReceipt");
//		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Gets image name
		InputStream fileContent = filePart.getInputStream();
		
		byte[] reimbursementReceipt = fileContent.readAllBytes();
		

		String reimbursementReceiptUrl = req.getParameter("reimbursementReceipt");
		System.out.println("\t\t\tReceived receipt item: "+ reimbursementReceiptUrl);
		
		int reimbursementAuthor = user.getId();
		int reimbursementResolver = 0;
		int reimbursementStatus = 1;
		int reimbursementType = 4;
		if(req.getParameter("reimbursementType") != "") reimbursementType = Integer.parseInt(req.getParameter("reimbursementType"));

		Reimbursement newReimbursement = new Reimbursement(reimbursementAmount, reimbursementSubmitted,
				reimbursementResolved, reimbursementDescription, reimbursementReceipt, reimbursementAuthor,
				reimbursementResolver, reimbursementStatus, reimbursementType);
		ReimbursementService serv = new ReimbursementServiceImplementation();
		serv.insertReimbursementFromUser(newReimbursement);
		UserService userv = new UserServiceImplementation();
		User updatedUser = userv.selectUserById(user.getId());
		req.getSession().setAttribute("loggedInUser", updatedUser);
		
		
		// Returning a JSON object with the user to the browser
//		PrintWriter printer = resp.getWriter();
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.registerModule(new JavaTimeModule());
//		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//		printer.write(mapper.writeValueAsString(newReimbursement));

		req.getRequestDispatcher("/employeepage.html").forward(req, resp);

		System.out.println("\t\t</ReimbursementController createFromFields>");
	}


}

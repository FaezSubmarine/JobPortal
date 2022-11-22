package com.ncs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

/**
 * Servlet implementation class AddSeeker
 */
public class AddSeeker extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String password = req.getParameter("password");
		
		String email = req.getParameter("email");
		
		String phoneNumber = req.getParameter("phoneNumber");
		String address = req.getParameter("address");
		String seekStatus = req.getParameter("SeekStatus");

		
		Model m = new Model();
		m.setFirstName(firstName);
		m.setLastName(lastName);
		m.setPassword(password);
		m.setEmail(email);
		m.setPhoneNumber(phoneNumber);
		m.setAddress(address);
		m.setSeekStatus(seekStatus);
		
		int result = m.registerSeeker();
		
		if(result == 1) {
			HttpSession session = req.getSession(true);
			session.setAttribute("id", m.getId());
			/*
			 * session.setAttribute("firstName", firstName);
			 * session.setAttribute("lastName", lastName); session.setAttribute("password",
			 * password); session.setAttribute("email", email);
			 * session.setAttribute("phoneNumber", phoneNumber);
			 * session.setAttribute("address", address); session.setAttribute("SeekStatus",
			 * seekStatus);
			 */
			
			resp.sendRedirect("GetSeekerHomePage");
			//resp.sendRedirect("GetSeekerHomePage?id="+m.getId());
		}
		else {
			resp.sendRedirect("/JobPortal/RegisterAsSeeker.jsp");
		}
	}
	
	

}

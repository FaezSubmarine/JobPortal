package com.ncs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

public class AddEmployer extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String phoneNumber = req.getParameter("phoneNumber");
		String address = req.getParameter("address");
		String company = req.getParameter("company");

		
		Model m = new Model();
		m.setFirstName(firstName);
		m.setLastName(lastName);
		m.setPassword(password);
		m.setEmail(email);
		m.setPhoneNumber(phoneNumber);
		m.setAddress(address);
		m.setCompany(company);
		
		int result = m.registerEmployer();
		
		if(result == 1) {
			session.setAttribute("id", m.getId());
			session.setAttribute("firstName", firstName);
			session.setAttribute("lastName", lastName);
			session.setAttribute("email", email);
			session.setAttribute("password", password);
			session.setAttribute("phoneNumber", phoneNumber);
			session.setAttribute("address", address);
			session.setAttribute("company", company);
			
			resp.sendRedirect("/JobPortal/EmployerHomePage.jsp");
		}
		else {
			resp.sendRedirect("/JobPortal/index.jsp");
		}
	}

}

package com.ncs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

/**
 * Servlet implementation class EmployerUpdateDetail
 */
public class EmployerUpdateDetail extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String phoneNumber = req.getParameter("phoneNumber");
		String address = req.getParameter("address");
		String company = req.getParameter("company");
		
		Model m = new Model();
		
		System.out.println(email+ " "+session.getAttribute("email"));
		if(!email.equals(session.getAttribute("email"))) {
			System.out.println("conditional");
			m.setOldEmail(session.getAttribute("email").toString());
			m.setEmail(email);
			m.updateEmployerEmail();
			session.setAttribute("email",m.getEmail());
		}
		m.setId(Integer.parseInt(session.getAttribute("id").toString()));
		m.setFirstName(firstName);
		m.setLastName(lastName);
		m.setPhoneNumber(phoneNumber);
		m.setAddress(address);
		m.setCompany(company);
		
		m.updateEmployer();
		
		session.setAttribute("firstName", m.getFirstName());
		session.setAttribute("lastName", m.getLastName());
		session.setAttribute("phoneNumber", m.getPhoneNumber());
		session.setAttribute("address", m.getAddress());
		session.setAttribute("company", m.getCompany());
		
		resp.sendRedirect("/JobPortal/EmployerHomePage.jsp");
	}
}

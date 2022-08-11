package com.ncs.controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;
/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Boolean employerChecked = req.getParameter("UserType").equals("Employer");
		
		Model m = new Model();
		m.setEmail(email);
		m.setPassword(password);
		
		if(employerChecked) {
			int result = m.loginEmployer();
			if(result ==1) {
				HttpSession session = req.getSession(true);
				session.setAttribute("id", m.getId());
				session.setAttribute("firstName", m.getFirstName());
				session.setAttribute("lastName", m.getLastName());
				session.setAttribute("password", m.getPassword());
				session.setAttribute("email", m.getEmail());
				session.setAttribute("phoneNumber", m.getPhoneNumber());
				session.setAttribute("address", m.getAddress());
				session.setAttribute("company", m.getCompany());
				resp.sendRedirect("/JobPortal/EmployerHomePage.jsp");
			}
			else {
				resp.sendRedirect("/JobPortal/index.jsp");
			}
		}
		else {
			int result = m.loginSeeker();
			if(result ==1) {
				HttpSession session = req.getSession(true);
				session.setAttribute("id", m.getId());
				session.setAttribute("firstName", m.getFirstName());
				session.setAttribute("lastName", m.getLastName());
				session.setAttribute("password", m.getPassword());
				session.setAttribute("email", m.getEmail());
				session.setAttribute("phoneNumber", m.getPhoneNumber());
				session.setAttribute("address", m.getAddress());
				session.setAttribute("SeekStatus", m.getSeekStatus());
				resp.sendRedirect("/JobPortal/SeekerHomePage.jsp");
			}
			else {
				resp.sendRedirect("/JobPortal/index.jsp");
			}
		}
	}

}

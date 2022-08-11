package com.ncs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

/**
 * Servlet implementation class AddJobsPosted
 */
public class AddJobsPosted extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String role = req.getParameter("role");
		String description = req.getParameter("description");
		String salary = req.getParameter("salary");
		String relevantExp = req.getParameter("RelevantExp");
		
		Model m = new Model();
		
		HttpSession session = req.getSession(false);
		
		m.setCompany(session.getAttribute("company").toString());
		m.setEmail(session.getAttribute("email").toString());
		m.setRole(role);
		m.setDescription(description);
		m.setSalary(salary);
		m.setRelevantExp(relevantExp);
		
		int result = m.addJobsPosted();
		
		if(result==1) {
			resp.sendRedirect("/JobPortal/EmployerHomePage.jsp");
		}
		else {
			resp.sendRedirect("/JobPortal/EmployerHomePage.jsp");
		}
	}

}

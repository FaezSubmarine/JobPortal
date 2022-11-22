package com.ncs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

public class EmployerJobList extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession(false);
		if(session==null) {
			response.sendRedirect("index.jsp");
		}
		
		
		Model m = new Model();
		
		m.setId(session.getAttribute("id").toString());
		session.setAttribute("JobList", m.getJobListingForEmployer());
		
		response.sendRedirect("JobsAppliedList.jsp");
	}

}

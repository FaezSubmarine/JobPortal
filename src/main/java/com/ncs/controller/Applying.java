package com.ncs.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

/**
 * Servlet implementation class Applying
 */
public class Applying extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Model m = new Model();
		m.setJobID(Integer.parseInt(request.getParameter("JobID")));
		HashMap<String,String> result = m.getJobListingFromID();
		m.setApplicantEmail(session.getAttribute("email").toString());
		m.setPosterEmail(result.get("EmployerEmail"));
		m.setRole(result.get("role"));
		m.setDatePosted(result.get("datePosted"));
		m.addJobsApplied();
		
		response.sendRedirect("SeekerJobList");
	}

}

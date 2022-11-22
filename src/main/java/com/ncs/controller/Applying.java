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
		String getQuery = request.getQueryString();
		int id = Integer.valueOf(getQuery.substring(3));
		
		HttpSession session = request.getSession();
		Model m = new Model();
		m.setJobID(id);
		HashMap<String,String> result = m.getJobListingFromID();
		m.setId(Integer.valueOf(request.getSession().getAttribute("id").toString()));

		System.out.println("did it come here"+ Integer.valueOf(result.get("EmployerID")));

		m.setRole(result.get("role"));
		m.setDatePosted(result.get("datePosted"));
		m.addJobsApplied(Integer.valueOf(result.get("EmployerID")));
		
		response.sendRedirect("SeekerJobList");
	}

}

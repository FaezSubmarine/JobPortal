package com.ncs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

/**
 * Servlet implementation class SeekerJobList
 */
public class SeekerJobList extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Model m = new Model();
		
		HttpSession session= req.getSession();
		m.setId(session.getAttribute("id").toString());
		session.setAttribute("JobList", m.getJobListingForSeeker());
		
		resp.sendRedirect("ApplyToAJob.jsp");
	}
}

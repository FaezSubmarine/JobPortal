package com.ncs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

/**
 * Servlet implementation class SeekerJobAppliedList
 */
public class SeekerJobAppliedList extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session= req.getSession(false);
		if(session==null) {
			resp.sendRedirect("index.jsp");
		}
		
		Model m = new Model();
		
		m.setId(req.getSession().getAttribute("id").toString());
		session.setAttribute("JobList", m.getJobsAppliedListingForSeeker());
		
		resp.sendRedirect("SeekerJobsAppliedList.jsp");
	}

}

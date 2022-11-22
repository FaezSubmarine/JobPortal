package com.ncs.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

/**
 * Servlet implementation class EmployerJobPosting
 */
public class EmployerJobPosting extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession(false)==null) {
			System.out.println("no session");
			response.sendRedirect("/JobPortal/index.jsp");
			return;
		}
		Model m = new Model();
		HttpSession session = request.getSession();
		m.setId(Integer.valueOf(session.getAttribute("id").toString()));
		session.setAttribute("JobList",m.getJobPostingForEmployer());
		response.sendRedirect("EmployerJobPosting.jsp");
	}

}

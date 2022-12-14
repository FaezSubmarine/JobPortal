package com.ncs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ncs.model.Model;

public class UpdateJobStatus extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model m = new Model();
		m.setStatus(request.getParameter("status"));
		m.setJobID(Integer.parseInt(request.getParameter("jobID")));
		m.updateJobStatus();
		
		response.sendRedirect("EmployerJobList");
	}

}

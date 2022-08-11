package com.ncs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

public class DeleteJobPosting extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Model m = new Model();
		HttpSession session = req.getSession();
		
		m.setId(Integer.parseInt(req.getParameter("jobID")));
		m.deleteJobPosting();
		
		resp.sendRedirect("EmployerJobPosting");
	}
}
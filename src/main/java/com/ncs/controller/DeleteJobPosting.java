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
		String getQuery = req.getQueryString();
		int id =-1;
		if(getQuery !=null) {
			id = Integer.valueOf(getQuery.substring(3));
		}
		Model m = new Model();
		
		m.setId(id);
		m.deleteJobPosting();
		
		resp.sendRedirect("EmployerJobPosting");
	}
}

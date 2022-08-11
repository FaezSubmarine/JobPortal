package com.ncs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

public class DeleteEmployer extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Model m = new Model();
		
		HttpSession session = req.getSession();
		
		m.setEmail(session.getAttribute("email").toString());
		m.deleteEmployer();
		req.getSession().invalidate();
		resp.sendRedirect("index.jsp");
	}

}

package com.ncs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

/**
 * Servlet implementation class SeekerChangePassword
 */
public class SeekerChangePassword extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Model m = new Model();
		HttpSession session = req.getSession();
		
		m.setPassword(req.getParameter("password"));
		m.setId(session.getAttribute("id").toString());
		m.updatePasswordForSeeker();
		
		resp.sendRedirect("GetSeekerHomePage");
	}

}

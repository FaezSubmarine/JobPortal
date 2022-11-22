package com.ncs.controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;
/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Boolean isSeeker = req.getParameter("IsSeeker") !=null;
		System.out.println("IsSeeker "+isSeeker.toString());
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		Model m = new Model();
		m.setEmail(email);
		m.setPassword(password);
		System.out.println(m.getEmail()+" "+m.getScrambledPassword());
		
		if(!isSeeker) {
			int result = m.loginEmployer();
			if(result ==1) {
				if(req.getSession()!=null)
					req.getSession().invalidate();
				
				HttpSession session = req.getSession(true);
				session.setAttribute("id", m.getId());
				session.setAttribute("email", m.getEmail());
				session.setAttribute("isSeeker", false);
				resp.sendRedirect("/JobPortal/GetEmployerHomePage");
			}
			else {
				HttpSession session = req.getSession(true);
				session.setAttribute("NoPassword", true);
				resp.sendRedirect("/JobPortal/EmployerLogin.jsp");
			}
		}
		else {
			int result = m.loginSeeker();
			if(result ==1) {
				if(req.getSession()!=null)
					req.getSession().invalidate();
				
				HttpSession session = req.getSession(true);

				session.setAttribute("id", m.getId());
				session.setAttribute("email", m.getEmail());
				session.setAttribute("isSeeker", true);

				resp.sendRedirect("/JobPortal/GetSeekerHomePage");
			}
			else {
				HttpSession session = req.getSession(true);

				session.setAttribute("NoPassword", true);
				resp.sendRedirect("/JobPortal/SeekerLogin.jsp");
			}
		}
	}

}

package com.ncs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

/**
 * Servlet implementation class SeekerUpdateDetail
 */
public class SeekerUpdateDetail extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String phoneNumber = req.getParameter("phoneNumber");
		String address = req.getParameter("address");
		String seekStatus = req.getParameter("SeekStatus");
		
		Model m = new Model();
		
		if(!email.equals(session.getAttribute("email"))) {
			System.out.println("conditional");
			m.setOldEmail(session.getAttribute("email").toString());
			m.setEmail(email);
			m.updateSeekerEmail();
			session.setAttribute("email",m.getEmail());
		}
		m.setId(Integer.parseInt(session.getAttribute("id").toString()));
		m.setFirstName(firstName);
		m.setLastName(lastName);
		m.setPhoneNumber(phoneNumber);
		m.setAddress(address);
		m.setSeekStatus(seekStatus);
		
		m.updateSeeker();
		
		session.setAttribute("firstName", m.getFirstName());
		session.setAttribute("lastName", m.getLastName());
		session.setAttribute("phoneNumber", m.getPhoneNumber());
		session.setAttribute("address", m.getAddress());
		session.setAttribute("SeekStatus", m.getSeekStatus());
		
		resp.sendRedirect("/JobPortal/SeekerHomePage.jsp");
	}

}

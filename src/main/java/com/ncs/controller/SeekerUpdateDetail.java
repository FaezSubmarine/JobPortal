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
		String password = req.getParameter("password");

		Model m = new Model();
		

		m.setId(Integer.parseInt(session.getAttribute("id").toString()));
		m.setPassword(password);

		m.setEmail(email);
		m.setFirstName(firstName);
		m.setLastName(lastName);
		m.setPhoneNumber(phoneNumber);
		m.setAddress(address);
		m.setSeekStatus(seekStatus);
		
		if(m.updateSeeker()==0){
			return;
		}
		
		req.setAttribute("id", m.getId());
		req.setAttribute("firstName", m.getFirstName());
		req.setAttribute("lastName", m.getLastName());
		req.setAttribute("email", m.getEmail());
		req.setAttribute("phoneNumber", m.getPhoneNumber());
		req.setAttribute("address", m.getAddress());
		req.setAttribute("SeekStatus", m.getSeekStatus());
		
		resp.sendRedirect("GetSeekerHomePage");
	}

}

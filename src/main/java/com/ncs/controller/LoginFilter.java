package com.ncs.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ncs.model.Model;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Model m = new Model();
		m.setEmail(request.getParameter("email"));
		m.setPassword(request.getParameter("password"));
		Boolean employerChecked = request.getParameter("UserType").equals("Employer");
		HttpServletRequest session = (HttpServletRequest)request;
		
		session.removeAttribute("NoEmail");
		session.removeAttribute("passwordMismatch");
		if(employerChecked) {
			if(!m.checkExistingEmailForEmployer()) {
				session.getSession().setAttribute("NoEmail", "true");
				HttpServletResponse resp = (HttpServletResponse)response;
				
				resp.sendRedirect("index.jsp");
				return;
			}
			if(!m.checkExistingEmailForEmployerWithPassword()) {
				session.getSession().setAttribute("passwordMismatch", "true");
				HttpServletResponse resp = (HttpServletResponse)response;
				
				resp.sendRedirect("index.jsp");
				return;
			}
		}
		else {
			if(!m.checkExistingEmailForSeeker()) {
				session.getSession().setAttribute("NoEmail", "true");
				HttpServletResponse resp = (HttpServletResponse)response;
				
				resp.sendRedirect("index.jsp");
				return;
			}
			if(!m.checkExistingEmailForSeekerWithPassword()) {
				session.getSession().setAttribute("passwordMismatch", "true");
				HttpServletResponse resp = (HttpServletResponse)response;
				
				resp.sendRedirect("index.jsp");
				return;
			}
		}

		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

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
 * Servlet Filter implementation class AddEmployerFilter
 */
public class AddEmployerFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Model m = new Model();
		m.setEmail(request.getParameter("email"));
		if(m.checkExistingEmailForEmployer()) {
			HttpServletRequest session = (HttpServletRequest)request;
			session.getSession().setAttribute("ExistingEmail", "true");
			HttpServletResponse resp = (HttpServletResponse)response;
			
			resp.sendRedirect("RegisterAsEmployer.jsp");
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

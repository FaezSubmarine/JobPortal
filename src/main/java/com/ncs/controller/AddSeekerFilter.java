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
 * Servlet Filter implementation class AddSeekerFilter
 */
public class AddSeekerFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AddSeekerFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Model m = new Model();
		m.setEmail(request.getParameter("email"));
		if(m.checkExistingEmailForSeeker()) {
			HttpServletRequest session = (HttpServletRequest)request;
			session.getSession().setAttribute("ExistingEmail", "true");
			HttpServletResponse resp = (HttpServletResponse)response;
			
			resp.sendRedirect("RegisterAsSeeker.jsp");
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

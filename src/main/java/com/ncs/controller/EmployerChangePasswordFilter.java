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
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

/**
 * Servlet Filter implementation class EmployerChangePasswordFilter
 */
public class EmployerChangePasswordFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EmployerChangePasswordFilter() {
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
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		m.setId(session.getAttribute("id").toString());
		m.setPassword(req.getParameter("oldPassword"));
		
		if(!m.checkPasswordOfEmployer()) {
			HttpServletResponse resp = (HttpServletResponse)response;
			req.getSession().setAttribute("failedNotif", "Change password failed");
			resp.sendRedirect("GetEmployerHomePage");
			return;
		}
		req.getSession().setAttribute("successNotif", "Change password successful");

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

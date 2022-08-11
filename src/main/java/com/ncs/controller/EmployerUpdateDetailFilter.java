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
 * Servlet Filter implementation class EmployerUpdateDetailFilter
 */
public class EmployerUpdateDetailFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EmployerUpdateDetailFilter() {
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
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		session.removeAttribute("mismatchPassword");
		session.removeAttribute("ExistingEmail");
		
		int id =Integer.parseInt(session.getAttribute("id").toString());
		System.out.println("update "+id);
		m.setId(id);
		m.setEmail(request.getParameter("email"));
		m.setPassword(req.getParameter("password"));
		if(m.loginEmployer()==0) {
			session.setAttribute("mismatchPassword", "true");
			resp.sendRedirect("EmployerUpdateDetail.jsp");
			return;
		}
		
		if(!request.getParameter("email").equals(session.getAttribute("email"))) {
			m.setId(Integer.parseInt(session.getAttribute("id").toString()));
			
			if(m.checkExistingEmailForEmployerWithDiffID()) {
				session.setAttribute("ExistingEmail", "true");
				
				resp.sendRedirect("EmployerUpdateDetail.jsp");
				return;
			}
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

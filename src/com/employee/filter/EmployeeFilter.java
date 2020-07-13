package com.employee.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.po.Admin;

/**
 * Servlet Filter implementation class EmployeeFilter
 */
@WebFilter("/EmployeeFilter")
public class EmployeeFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EmployeeFilter() {
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
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
	HttpServletRequest request=(HttpServletRequest)req;
	HttpServletResponse response=(HttpServletResponse)resp;
	HttpSession session=request.getSession();
	Admin admin=(Admin)session.getAttribute("admin");
	String path =request.getServletPath();
	
	if(admin!=null||path.equals("/AdminServlet")||path.equals("/register.jsp")){
		chain.doFilter(req, resp);
	}else{
		request.getRequestDispatcher("login.jsp").forward(request, response);
		//response.sendRedirect("login.jsp");
	}
	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

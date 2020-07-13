package com.employee.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {

	private String defaultEncode;
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		//HttpServletResponse response = (HttpServletResponse)resp;
		String method = request.getMethod();
		if(method.equals("post")){
			request.setCharacterEncoding(defaultEncode);
			resp.setContentType("text/html;charset="+defaultEncode);
		}else{
			EncodeRequest er = new EncodeRequest(request);
			request = er;
		}
		chain.doFilter(request, resp);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		defaultEncode = fConfig.getInitParameter("encoding");	
		System.out.println("编码过滤器初始化成功"+defaultEncode);
	}

}

package com.employee.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.po.Admin;
import com.employee.service.AdminService;
import com.employee.service.AdminServiceIMPL;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       AdminService adminService=new AdminServiceIMPL();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method=request.getParameter("method");
	  if(method.equals("login")){
		  this.login(request,response);
	  }else if(method.equals("logOut")){
		  this.logOut(request,response);
	  }else if(method.equals("register")){
		  this.register(request,response);
	  }
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		if(username==null||username==""||username.equals("")||password==""||password.equals("")){
			request.setAttribute("msg", "�û����������벻��Ϊ��");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}else{
		
		if(adminService.getByName(username)!=null){
			request.setAttribute("msg", "�û����Ѵ���");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}else{
		
		
		Admin admin =new Admin(username,password);
		System.out.println(admin);
		boolean result =adminService.register(admin);
		String msg;
		if(result){
			msg = "ע��ɹ������Ե�¼";
			request.setAttribute("msg", msg);
			response.sendRedirect("login.jsp");
		}else{
			msg = "ע��ʧ�ܣ�������";
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		}
		}
	}

	private void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession();
		session.removeAttribute("admin");
		response.sendRedirect("login.jsp");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Admin admin =adminService.login(username, password);
			if(admin!=null){
				HttpSession session =request.getSession();
				session.setAttribute("admin", admin);
				
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else{
				request.setAttribute("msg", "�û��������������");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
	}

}

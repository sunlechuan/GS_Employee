package com.employee.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.po.Dept;
import com.employee.po.Employee;
import com.employee.service.DeptServiceIMPL;
import com.employee.service.IDeptService;

@WebServlet("/DeptServlet")
public class DeptServlet extends HttpServlet {
	
	IDeptService deptservice = new DeptServiceIMPL();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter("method");
		switch (method) {
		case "add":
			add(request,response);
			break;
		case "findAll":
			findAll(request,response);
			break;
		case "toUpdate":
			toUpdate(request,response);
			break;
		case "update":
			update(request,response);
			break;
		case "delete":
			delete(request,response);
			break;
		case "queryDeptName":
			queryDeptName(request,response);
			break;
		default:
			break;
		}
	}


	private void queryDeptName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int deptno= Integer.parseInt(request.getParameter("deptno"));
		List<Employee> list= deptservice.queryDeptName(deptno);
		request.setAttribute("list", list);
		request.getRequestDispatcher("queryDeptname.jsp").forward(request, response);
	}


	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int deptno = Integer.parseInt(request.getParameter("deptno")); 
		boolean result =deptservice.delete(deptno);
		String msg;
		if(result){
			msg = "操作成功";
		}else{
			msg = "操作失败";
		}
		request.setAttribute("msg",msg);
		findAll(request, response);
	}


	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int deptno = Integer.parseInt(request.getParameter("deptno")); 
		String deptname = request.getParameter("deptname");
		if(deptname==null||deptname==""||deptname.equals("")){
			request.setAttribute("msg", "部门名称不能为空!请重新输入！");
			request.getRequestDispatcher("updateDept.jsp").forward(request, response);
		}
		else{
		Dept dept = new Dept(deptno, deptname);
		boolean result = deptservice.update(dept);
		String msg;
		if(result){
			msg = "操作成功";
		}else{
			msg = "操作失败";
		}
		request.setAttribute("msg",msg);
		findAll(request, response);}
	}


	private void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		Dept dept = deptservice.getById(deptno);
		if(dept!=null){
			request.setAttribute("dept", dept);
			request.getRequestDispatcher("updateDept.jsp").forward(request, response);
		}
		
	}


	private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Dept> list = deptservice.findAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("deptList.jsp").forward(request, response);;
	}


	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("deptname");
		Dept dept = new Dept(name);
		if(name==null||name==""||name.equals("")){
			request.setAttribute("msg", "部门名称不能为空!请重新输入！");
			request.getRequestDispatcher("addDept.jsp").forward(request, response);
		}
		else{
		boolean result = deptservice.add(dept);
		String msg = null;
		if(result){
			msg = "操作成功";
		}else{
			msg = "操作失败";
		}
		request.setAttribute("msg",msg);
		findAll(request, response);
	}
	}
}

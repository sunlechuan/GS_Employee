package com.employee.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;

import com.employee.po.Dept;
import com.employee.po.Employee;
import com.employee.po.History;
import com.employee.service.DeptServiceIMPL;
import com.employee.service.EmployeeServiceIMPL;
import com.employee.service.IDeptService;
import com.employee.service.IEmployeeService;
import com.employee.util.DateUtil;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//部门的service
    IDeptService deptService = new DeptServiceIMPL();
    //员工的service
	IEmployeeService empService = new EmployeeServiceIMPL();
	//定义一个分页的条数
	int size=3;//每页三条数据
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
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
		String method = request.getParameter("method");
		switch (method) {
		case "getDept":
			getDept(request,response);
			break;
		case "addEmp":
			addEmp(request,response);
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
		case "leaveByEmpno":
			leaveByEmpno(request,response);
			break;
		case "leave":
			leave(request,response);
			break;
		case "delete":
			delete(request,response);
			break;
		default:
			break;
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String empno=request.getParameter("empno");
		boolean result = empService.delete(empno);
		String msg ;
		if(result){
			msg = "操作成功";
		}else{
			msg = "操作失败";
		}
		request.setAttribute("msg", msg);
		
		findAll(request,response);
	}

	private void leave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String empno=request.getParameter("empno");
		String empname=request.getParameter("empname");
		String empsex=request.getParameter("empsex");
		String sentrydate=request.getParameter("entrydate");
		Date entrydate=DateUtil.getNow();
		String empphone=request.getParameter("empphone");
		String empaddr=request.getParameter("empaddr");
		int salary=Integer.parseInt(request.getParameter("salary"));
		int deptno=Integer.parseInt(request.getParameter("deptno"));
		Employee emp=new Employee(empno,deptno,empname,empsex,entrydate,empphone,empaddr,salary,1);
		String dismissionreason=request.getParameter("dismissionreason");
		History history =new History();
		history.setDismissionreason(dismissionreason);
		boolean result = empService.dismission(emp, history);
		String msg = "";
		if(result){
			msg = "操作成功";
		}else{
			msg = "操作失败";
		}
		request.setAttribute("msg", msg);
		findAll(request,response);
	}

	private void leaveByEmpno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String empno=request.getParameter("empno");
		Employee emp=empService.getEmpno(empno);
		int deptno = Integer.parseInt(empno);
		Dept dept =deptService.getByName(deptno);
		request.setAttribute("emp", emp);
		request.setAttribute("dept", dept);
		request.getRequestDispatcher("dismission.jsp").forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String empno=request.getParameter("empno");
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		String empphone=request.getParameter("empphone");
		String empaddr=request.getParameter("empaddr");
		int salary=Integer.parseInt(request.getParameter("salary"));
		Date changedate=DateUtil.getNow();
		String changereason=request.getParameter("changereason");
	
		
		Employee emp=new Employee(empno,deptno,empphone,empaddr,salary);
		History history=new History();
		history.setEmpno(empno);
		history.setDeptno(deptno);
		history.setSalary(salary);
		history.setChangedate(changedate);
		history.setChangereason(changereason);
		boolean result = empService.update(emp, history);
		String msg = "";
		if(result){
			msg = "操作成功";
		}else{
			msg = "操作失败";
		}
		request.setAttribute("msg", msg);
		findAll(request,response);
	}

	private void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String empno=request.getParameter("empno");
		Employee emp=empService.getEmpno(empno);
		List<Dept> deptlist=deptService.findAll();
		request.setAttribute("emp", emp);
		request.setAttribute("deptlist", deptlist);
		request.getRequestDispatcher("updateEmp.jsp").forward(request, response);
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String spage=request.getParameter("page");
		int page;
		if(spage==null||spage==""||spage.equals("")){
			page=1;
		}else{
			page=Integer.parseInt(spage);
		}
		
		List<Employee> list=empService.findAll(page,size);
        int pageCount =empService.pageCount(size);
        request.setAttribute("pageCount", pageCount);
		request.setAttribute("list", list);
		request.getRequestDispatcher("employeeList.jsp").forward(request, response);
	}

	private void addEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		String empname = request.getParameter("empname");
		String empsex = request.getParameter("empsex");
		String empphone = request.getParameter("empphone");
		String sentrydate = request.getParameter("entrydate");
		//转换日期
		Date entrydate = null;
		String empaddr = request.getParameter("empaddr");
		int salary = 0;
		if(empname==null||empname==""||empname.equals("")||empsex.equals("")||empphone==null||empphone==""||empphone.equals("")
		||sentrydate==null||sentrydate==""||sentrydate.equals("")||empaddr==null||empaddr==""||empaddr.equals("")||StringUtils.isBlank(request.getParameter("salary"))	
				){
			request.setAttribute("msg", "部门名称不能为空!请重新输入！");
//			request.getRequestDispatcher("updateEmp.jsp").forward(request, response);
		}else{
		entrydate = DateUtil.toDate(sentrydate);
		salary = Integer.parseInt(request.getParameter("salary"));
		//封装对象
		Employee emp = new Employee(deptno,  empname, empsex, entrydate, empphone, empaddr, salary, 1);
		boolean result = empService.add(emp);
		String msg = "";
		if(result){
			msg = "操作成功";
		}else{
			msg = "操作失败";
		}
		request.setAttribute("msg",msg);
		}
		findAll(request,response);
	}

	private void getDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Dept> list = deptService.findAll();
		request.setAttribute("deptList", list);
		request.getRequestDispatcher("addEmp.jsp").forward(request, response);
	}

}

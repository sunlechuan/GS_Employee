package com.employee.servlet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;

import com.employee.po.History;
import com.employee.service.HistoryService;
import com.employee.service.IHistoryServiceIMPL;
import com.employee.util.ExcelTool;

/**
 * Servlet implementation class HistoryServlet
 */
@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       HistoryService historyService=new IHistoryServiceIMPL();
       int size=3;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryServlet() {
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
		if(method.equals("findAll")){
			this.findAll(request,response);
		}else if(method.equals("exportExcel")){
			this.exportExcel(request,response);
		}else if(method.equals("exportExcelPage")){
			this.exportExcelPage(request,response);
		}
	}

	private void exportExcelPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String key =request.getParameter("search");
		String spage=request.getParameter("page");
	    int page;
		if(spage==null||spage==""||spage.equals("")){
			page=1;
		}else{
			page=Integer.parseInt(spage);
		}
		List<History> list =historyService.findByKey(key, page, size);
		String[] headers={"编号","员工编号","部门编号","工资","变更时间","变更原因","离职时间","离职原因"};
		String[] dataHeaders={"changeno","empno","deptno","salary","changedate","changereason","dismissiondate","dismissionreason"};
		FileOutputStream out = new FileOutputStream(request.getServletContext().getRealPath("/")+"用户变更历史1.xls");
	    
			try {
				new ExcelTool().exportExcel(headers, dataHeaders, list, out);
				 findAll(request,response);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void exportExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getServletContext().getRealPath("/")+"用户变更历史1.xls");
		String key =request.getParameter("key");
		String[] headers={"编号","员工编号","部门编号","工资","变更时间","变更原因","离职时间","离职原因"};
		String[] dataHeaders={"changeno","empno","deptno","salary","changedate","changereason","dismissiondate","dismissionreason"};
		List<History> list=historyService.list(key);
		
		FileOutputStream out = new FileOutputStream(request.getServletContext().getRealPath("/")+"用户变更历史2.xls");

	    try {
			new ExcelTool().exportExcel(headers, dataHeaders, list, out);
		
			   findAll(request,response);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String key =request.getParameter("key");
		if(key==null){
			key="";
		}
		String spage=request.getParameter("page");
		int page;
		if(spage==null||spage==""||spage.equals("")){
			page=1;
		}else{
			page=Integer.parseInt(spage);
		}
		List<History> historyList=historyService.findByKey(key,page,size);
		int pageCount = historyService.pageCount(size);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("search", key);//把key再放进去
		request.setAttribute("historyList", historyList);
		request.getRequestDispatcher("history.jsp").forward(request, response);
	}

}

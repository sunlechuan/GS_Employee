package com.employee.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.employee.dao.DeptDao;
import com.employee.dao.EmployeeDao;
import com.employee.dao.HistoryDao;
import com.employee.po.Dept;
import com.employee.po.Employee;
import com.employee.po.History;
import com.employee.util.EmpnoUtil;
import com.employee.util.JDBCUtils;
import com.sun.javafx.scene.traversal.Hueristic2D;

public class EmployeeServiceIMPL implements IEmployeeService {

	EmployeeDao edao = new EmployeeDao();
	HistoryDao hdao = new HistoryDao();
	
	@Override
	public boolean add(Employee emp) {
		// TODO Auto-generated method stub
		Connection con = JDBCUtils.getConnection();
		JDBCUtils.startTransaction();
		boolean result = true;
		String empno = "";
		try {
			String sql_1 = "select empno from t_emp order by empno desc limit 0,1";
			Object[] params_1 = {};
			Employee employee = edao.get(con, sql_1, Employee.class, params_1);
			if(employee == null){
				empno="000";
			}else{
				empno = employee.getEmpno();
			}
			empno = EmpnoUtil.createEmpno(empno);
			//添加数据
			String sql_2 = "insert into t_emp(empno,deptno,empname,empsex,empphone,entrydate,empaddr,salary,state) values(?,?,?,?,?,?,?,?,?)";
			Object[] params_2 = {empno,emp.getDeptno(),emp.getEmpname(),emp.getEmpsex(),emp.getEmpphone(),emp.getEntrydate(),emp.getEmpaddr(),emp.getSalary(),1};
			edao.update(con,sql_2, params_2);
			History history = new History();
			history.setDeptno(emp.getDeptno());
			history.setSalary(emp.getSalary());
			history.setChangedate(emp.getEntrydate());
			history.setChangereason("入职");
			String sql_3 = "insert into t_history(empno,deptno,salary,changedate,changereason) values(?,?,?,?,?)";
			Object[] params_3 = {empno,history.getDeptno(),history.getSalary(),history.getChangedate(),history.getChangereason()};
			hdao.update(con,sql_3,params_3);
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
				result = false;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Employee> findAll(int page,int size) {
		// TODO Auto-generated method stub
		//计算分页查询的数据从什么位置开始的
		page=(page-1)*size;
		
		//还需要展示部门名称
		String sql="select empno,deptname,empname,empsex,empphone,entrydate,empaddr,salary,leavedate,state from"
				+" t_emp,t_dept where t_emp.deptno=t_dept.deptno and t_emp.state=1  limit ?,?";
		Object[] params={page,size};
		return edao.query(sql, Employee.class, params);
	}

	@Override
	public Employee getEmpno(String empno) {
		// TODO Auto-generated method stub
		String sql="select * from t_emp where empno=?";
		Object[] params={empno};
		
		return edao.get(sql, Employee.class, params);
	}

	@Override
	public boolean update(Employee emp, History history) {
		// TODO Auto-generated method stub
		
		Connection con=JDBCUtils.getConnection();
		boolean result=true;
		
		JDBCUtils.startTransaction();
		try {
		String sql_1="update t_emp set deptno=?,empphone=?,empaddr=?,salary=? where empno=?";
		Object[] params_1={emp.getDeptno(),emp.getEmpphone(),emp.getEmpaddr(),emp.getSalary(),emp.getEmpno()};
		edao.update(con,sql_1, params_1);
		String sql_2="insert into t_history(empno,deptno,salary,changedate,changereason) values(?,?,?,?,?)";
		Object[] params_2={history.getEmpno(),history.getDeptno(),history.getSalary(),history.getChangedate(),history.getChangereason()};
		hdao.update(con,sql_2, params_2);
		
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			result=false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean dismission(Employee emp, History history) {
		// TODO Auto-generated method stub
		Date date=new Date(System.currentTimeMillis());
		history.setDismissiondate(date);
		Connection con=JDBCUtils.getConnection();
		boolean result=true;
		try {
		JDBCUtils.startTransaction();
		String sql_1="update t_emp set leavedate=now() where empno=?";
		Object[] params_1={emp.getEmpno()};
		edao.update(con,sql_1, params_1);
		String sql_2="insert into t_history(empno,deptno,salary,dismissiondate,dismissionreason)"
				+" values(?,?,?,?,?)";
		Object[] params_2={emp.getEmpno(),emp.getDeptno(),emp.getSalary(),history.getDismissiondate(),history.getDismissionreason()};
		hdao.update(con,sql_2, params_2);
		
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				result=false;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(String empno) {
		// TODO Auto-generated method stub
		//先查询员工是否离职  采用逻辑删除，不采用物理删除  好处：可以对数据进行留存
		String sql_1="select leavedate from t_emp where empno=?";
		Object[] params_1={empno};
		boolean result =true;
		if(edao.get(sql_1, Employee.class, params_1).getLeavedate()==null){
			//说明还没离职
			  result=false;
		}else{
			String sql_2="update t_emp set state =0 where empno=?";
			Object[] params_2={empno};
			edao.update(sql_2, params_2);
		}
		return result;
	}

	@Override
	public int pageCount(int size) {
		// TODO Auto-generated method stub
		String sql="select count(*) count from t_emp where state=1";
		Object[] params={};
		int hang=0;
		int pageCount=0;
		Employee emp=edao.get(sql, Employee.class, params);
		hang=emp.getCount();
		if(hang%size==0){
			pageCount=hang/size;
		}else{
			pageCount=hang/size+1;
		}
		return pageCount;
	}


	
}

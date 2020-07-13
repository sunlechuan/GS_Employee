package com.employee.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.employee.dao.DeptDao;
import com.employee.po.Dept;
import com.employee.po.Employee;
import com.employee.util.JDBCUtils;

public class DeptServiceIMPL implements IDeptService{
	DeptDao ddao = new DeptDao();
	@Override
	public boolean add(Dept dept) {
		// TODO Auto-generated method stub
		String sql = "insert into t_dept(deptname) values(?)";
		Object[] params = {dept.getDeptname()};
		return ddao.update(sql, params);		
	}
	@Override
	public List<Dept> findAll() {
		// TODO Auto-generated method stub
		String sql = "select * from t_dept";
		Object[] params = {};
		return ddao.query(sql, Dept.class, params);
	}
	@Override
	public Dept getById(int deptno) {
		// TODO Auto-generated method stub
		String sql = "select * from t_dept where deptno=?";
		Object[] params = {deptno};
		return ddao.get(sql, Dept.class, params);
	}
	@Override
	public boolean update(Dept dept) {
		// TODO Auto-generated method stub
		String sql = "update t_dept set deptname=? where deptno=?";
		Object[] params = {dept.getDeptname(),dept.getDeptno()};
		return ddao.update(sql, params);
	}
	@Override
	public boolean delete(int deptno) {
		// TODO Auto-generated method stub
		Connection con = JDBCUtils.getConnection();
		JDBCUtils.startTransaction();
		boolean result = true;
		try {
			String sql_1 = "select* from t_dept left join t_emp on t_dept.deptno = t_emp.deptno where t_emp.deptno = ?";
			Object[] params_1 = {deptno};
			Dept dept = ddao.get(con,sql_1, Dept.class, params_1);
			if(dept!=null){
				return result = false;
			}else{
				String sql_2 = "delete from t_dept where deptno=?";
				Object[] params_2 = {deptno};
				ddao.update(con, sql_2, params_2);
				con.commit();	
			}
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
	public List<Employee> queryDeptName(int deptno) {
		// TODO Auto-generated method stub
		String sql="select empno,empname,t_dept.deptname,empsex,entrydate,empphone,empaddr,salary,leavedate,state"
				+" from t_emp,t_dept where t_emp.deptno=t_dept.deptno and t_emp.deptno=?";
		Object[] params={deptno};
		return ddao.query(sql, Employee.class, params);
	}

	@Override
	public Dept getByName(int deptno) {
		// TODO Auto-generated method stub
		String sql="select deptno,deptname from t_dept where deptno=(select deptno from t_emp where empno=?)";
		Object[] params={deptno};
		return ddao.get(sql, Dept.class, params);
	}

}

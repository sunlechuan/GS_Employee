package com.employee.service;

import java.util.List;

import com.employee.po.Dept;
import com.employee.po.Employee;

public interface IDeptService {
	public boolean add(Dept dept);
	
	public List<Dept> findAll();
	
	public Dept getById(int deptno);
	
	public boolean update(Dept dept);
	
	public boolean delete(int deptno);
    
	public List<Employee> queryDeptName(int deptno); 

	public Dept getByName(int deptno);
}

package com.employee.service;

import java.util.List;


import com.employee.po.Employee;
import com.employee.po.History;

public interface IEmployeeService {
	
	public boolean add(Employee employee);
	public List<Employee> findAll(int page,int size);
	//¸ù¾Ýempno²éÑ¯
	public Employee getEmpno(String empno);
	public boolean update(Employee emp,History history);
	//lizhi
	public boolean dismission(Employee emp,History history);
	public boolean  delete(String empno);
	public int pageCount(int size);
}

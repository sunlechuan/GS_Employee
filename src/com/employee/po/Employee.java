package com.employee.po;

import java.sql.Date;

public class Employee {

	private String empno;
	private int deptno;
	private String deptname;
	private String empname;
	private String empsex;
	private Date entrydate;
	private String empphone;
	private String empaddr;
	private int salary;
	private Date leavedate;
	private int state;
	private int count;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String empno, int deptno, String deptname, String empname, String empsex, Date entrydate,
			String empphone, String empaddr, int salary, Date leavedate, int state) {
		super();
		this.empno = empno;
		this.deptno = deptno;
		this.deptname = deptname;
		this.empname = empname;
		this.empsex = empsex;
		this.entrydate = entrydate;
		this.empphone = empphone;
		this.empaddr = empaddr;
		this.salary = salary;
		this.leavedate = leavedate;
		this.state = state;
	}
	//新增
	public Employee(int deptno,String empname, String empsex, Date entrydate, String empphone,
			String empaddr, int salary, int state) {
		super();
		this.deptno = deptno;
		this.deptname = deptname;
		this.empname = empname;
		this.empsex = empsex;
		this.entrydate = entrydate;
		this.empphone = empphone;
		this.empaddr = empaddr;
		this.salary = salary;
		this.state = state;
	}
	//修改
	public Employee(String empno, int deptno, String empphone, String empaddr, int salary) {
		super();
		this.empno = empno;
		this.deptno = deptno;
		this.empphone = empphone;
		this.empaddr = empaddr;
		this.salary = salary;
	}
	//离职
	public Employee(String empno, int deptno, String empname, String empsex, Date entrydate, String empphone,
			String empaddr, int salary, int state) {
		super();
		this.empno = empno;
		this.deptno = deptno;
		this.empname = empname;
		this.empsex = empsex;
		this.entrydate = entrydate;
		this.empphone = empphone;
		this.empaddr = empaddr;
		this.salary = salary;
		this.state = state;
	}
	
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getEmpsex() {
		return empsex;
	}
	public void setEmpsex(String empsex) {
		this.empsex = empsex;
	}
	public Date getEntrydate() {
		return entrydate;
	}
	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}
	public String getEmpphone() {
		return empphone;
	}
	public void setEmpphone(String empphone) {
		this.empphone = empphone;
	}
	public String getEmpaddr() {
		return empaddr;
	}
	public void setEmpaddr(String empaddr) {
		this.empaddr = empaddr;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Date getLeavedate() {
		return leavedate;
	}
	public void setLeavedate(Date leavedate) {
		this.leavedate = leavedate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}

package com.employee.po;

import java.sql.Date;

public class History {

	private int changeno;
	private String empno;
	private int deptno;
	private int salary;
	private Date changedate;
	private String changereason;
	private Date dimissiondate;
	private String dimissionreason;
	private String employeename;
	private String deptname;
	private String empname;
	private int count;
	public History() {
		super();
		// TODO Auto-generated constructor stub
	}
	//查询
	public History(int changeno, String empno, int deptno, int salary, Date changedate, String changereason,
			Date dimissiondate, String dimissionreason, String employeename, String deptname, String empname) {
		super();
		this.changeno = changeno;
		this.empno = empno;
		this.deptno = deptno;
		this.salary = salary;
		this.changedate = changedate;
		this.changereason = changereason;
		this.dimissiondate = dimissiondate;
		this.dimissionreason = dimissionreason;
		this.employeename = employeename;
		this.deptname = deptname;
		this.empname = empname;
	}
	//新增
	public History(int changeno, String empno, int deptno, int salary, Date changedate, String changereason,
			Date dimissiondate, String dimissionreason, String empname) {
		super();
		this.changeno = changeno;
		this.empno = empno;
		this.deptno = deptno;
		this.salary = salary;
		this.changedate = changedate;
		this.changereason = changereason;
		this.dimissiondate = dimissiondate;
		this.dimissionreason = dimissionreason;
		this.empname = empname;
	}
	//修改
	public History(String empno, int deptno, int salary, Date changedate, String changereason) {
		super();
		this.empno = empno;
		this.deptno = deptno;
		this.salary = salary;
		this.changedate = changedate;
		this.changereason = changereason;
	}
	
	public int getChangeno() {
		return changeno;
	}
	public void setChangeno(int changeno) {
		this.changeno = changeno;
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
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Date getChangedate() {
		return changedate;
	}
	public void setChangedate(Date changedate) {
		this.changedate = changedate;
	}
	public String getChangereason() {
		return changereason;
	}
	public void setChangereason(String changereason) {
		this.changereason = changereason;
	}
	
	public Date getDismissiondate() {
		return dimissiondate;
	}
	public void setDismissiondate(Date dimissiondate) {
		this.dimissiondate = dimissiondate;
	}
	public String getDismissionreason() {
		return dimissionreason;
	}
	public void setDismissionreason(String dimissionreason) {
		this.dimissionreason = dimissionreason;
	}
	public String getEmployeename() {
		return employeename;
	}
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "History [changeno=" + changeno + ", empno=" + empno + ", deptno=" + deptno + ", salary=" + salary
				+ ", changedate=" + changedate + ", changereason=" + changereason + ", dimissiondate=" + dimissiondate
				+ ", dimissionreason=" + dimissionreason + ", employeename=" + employeename + ", deptname=" + deptname
				+ ", empname=" + empname + ", count=" + count + "]";
	}
	
	
}

package com.employee.po;

public class Dept {
	private int deptno;
	private String deptname;
	
	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dept(String name) {
		super();
		this.deptname = name;
	}

	public Dept(int deptno, String deptname) {
		super();
		this.deptno = deptno;
		this.deptname = deptname;
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

	@Override
	public String toString() {
		return "Dept [deptno=" + deptno + ", deptname=" + deptname + "]";
	}
	
	
	
}

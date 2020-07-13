<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
	.page-header{
		text-align: center;
		width:500px;
	}
	#tab{
	background-color: #AAAAAA;
	}
	td{
		background-color: #ffffff;
		font-size: 14px;
	}
</style>
</head>
<body>
<div class="page-header">
<h3>员工离职</h3>
</div>
<form action="EmployeeServlet?method=leave" method="post">
<table id="tab" border="0" width="800" cellpadding="3" cellspacing="1">
<input type="hidden" name="empno" id="empno" value="${emp.empno }" />
<input type="hidden" name="empname" id="empname" value="${emp.empname }" />
<input type="hidden" name="empsex" id="empsex" value="${emp.empsex }" />
<input type="hidden" name="empphone" id="empphone" value="${emp.empphone }" />
<input type="hidden" name="entrydate" id="entrydate" value="${emp.entrydate }" />
<input type="hidden" name="empaddr" id="empaddr" value="${emp.empaddr }" />
<input type="hidden" name="salary" id="salary" value="${emp.salary }" />
<input type="hidden" name="deptno" id="deptno" value="${dept.deptno }" />
<tr>
<td width="120">部门名称</td>
<td width="120">员工姓名</td>
<td width="120">员工性别</td>
<td width="120">入职日期</td>
<td width="120">联系电话</td>
<td width="120">现住址</td>
<td width="120">薪资</td>

</tr>
<tr>
<td width="380">${dept.deptname }</td>
<td width="380">${emp.empname }</td>
<td width="380">${emp.empsex }</td>
<td width="380">${emp.entrydate }</td>
<td width="380">${emp.empphone }</td>
<td width="380">${emp.empaddr }</td>
<td width="380">${emp.salary }</td>

</tr>
</table>
<textarea rows="3" cols="40" name="dismissionreason" id="dismissionreason"></textarea>

<input type="submit" name="button" id="button" value="确定"/>&nbsp;&nbsp;
<a href="EmployeeServlet?method=findAll">返回</a>
</form>

</body>
</html>
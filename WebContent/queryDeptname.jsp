<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.page-header{
		text-align: center;
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
	<h3>员工列表</h3>
</div>
<div id="contener">
	<table id="tab" border="0" width="900" cellpadding="2" cellspacing="1" align="center">
		<tr style="background-color: #ffffff">
			<th>员工编号</th>
			<th>部门名称</th>
			<th>员工姓名</th>
			<th>员工性别</th>
			<th>入职日期</th>
			<th>联系电话</th>
			<th>员工住址</th>
			<th>薪资</th>
			<th>离职日期</th>
		</tr>
		
		<c:forEach items="${list }" var = "l">
		<tr>
			<td>${l.empno }</td>
			<td>${l.deptname }</td>
			<td>${l.empname }</td>
			<td>${l.empsex }</td>
			<td>${l.entrydate }</td>
			<td>${l.empphone }</td>
			<td>${l.empaddr }</td>
			<td>${l.salary }</td>
			<td>${l.leavedate }</td>
			</tr>	
		</c:forEach>
		<tr>
		<td colspan="9" align="center">
		<a href="DeptServlet?method=findAll"></a></td>
		</tr>
	</table>
</div>
</body>
</html>

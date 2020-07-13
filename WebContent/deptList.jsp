<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%--两个包 jstl，standard --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门列表</title>
<style type="text/css">
.page-header{
	text-align:center;
}
#add{	
	margin:0 auto;
	width:660px;
	text-align:right;
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
	<h3>部门列表</h3>
</div>
<div id="add">
	<a href="addDept.jsp">新增部门</a>
</div>
<div id="contener">
	<table id="tab" cellpadding="1" cellspacing="1" border="0" width="600" align="center">
		<tr style="background-color: #ffffff">
			<th>部门编号</th>
			<th>部门名称</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list }" var="l">
		<tr>
			<td>${l.deptno }</td>
			<td><a href="DeptServlet?method=queryDeptName&deptno=${l.deptno }">${l.deptname }</a></td>
			<td>
			<a href="DeptServlet?method=delete&deptno=${l.deptno }">删除</a>&nbsp;&nbsp;
			<a href="DeptServlet?method=toUpdate&deptno=${l.deptno }">修改</a>
			</td>
		</tr>
		</c:forEach>
		
		
	</table>
	<div id="msg" align="center">${msg }</div>
	
</div>
</body>
</html>
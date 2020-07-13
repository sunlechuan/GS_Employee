<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.page-header{
		width:500px;
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
<div class="page-header" align="center">
	<h3>修改部门</h3>
</div>
<form action="DeptServlet?method=update" method="post">
	<table id="tab" cellpadding="3" cellspacing="1" width="500" border="0">
		<tr>
			<td>部门编号</td>
			<td><input type="text" name="deptno" id="deptno" value="${dept.deptno }" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>部门名称</td>
			<td><input type="text" name="deptname" id="deptname" value="${dept.deptname }"/></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" name="button" id="button" value="确定" >
				<input type="reset" name="button2" id="button2" value="重置">
				<a href="DeptServlet?method=findAll">返回</a>
			</td>
		</tr>
	</table>
	<div id="msg" align="center">${msg }</div>
</form>
</body>
</html>
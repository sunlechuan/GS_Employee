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
		width:500px;
		text-align: center;
	}
	#tab{
		background-color: #AAAAAA;
	}
	#td{
		background-color: #ffffff;
	}
</style>
</head>
<body>
<div class="page-header">
	<h3>新增部门</h3>
</div>
<form action="DeptServlet?method=add" method="post">
	<table id="tab" borer="0" width="500" cellpadding="3" cellspacing="1">
		<tr>
			<td width="120">部门名称</td>
			<td><input type="text" name="deptname" id="deptname" ></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="submit" value="增加" name="button" id="button">
			<input type="reset" value="重置" name="button2" id="button2">
			<a href="DeptServlet?method=findAll">返回</a>
			</td>
		</tr>
		<div id="msg" align="center">${msg }</div>
	</table>
</form>
</body>
</html>
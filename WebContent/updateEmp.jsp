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
	td{
		background-color: #ffffff;
		font-size: 14px;
	}
</style>
</head>
<body>

<div class="page-header">
	<h3>人员变更</h3>
</div>
<form action="EmployeeServlet?method=update" method="post">

	<table id="tab" border="0" cellpadding="3" cellspacing="1" width="500" align="center">
	<input type="hidden" name="empno" id="empno" value="${emp.empno }">
		<tr>
			<td width="120">部门名称</td>
			<td width="380">
				<select name="deptno" id="deptno">
					<c:forEach items="${deptlist }" var='dept'>
						<option value="${dept.deptno }" <c:if test="${dept.deptno eq emp.deptno }">selected="slected"</c:if>>${dept.deptname }</option>
					</c:forEach>
				
				</select>
			</td>
		</tr>
		<tr>
			<td width="120">员工姓名</td>
			<td width="380">
				<input type="text" name="empname" id="empname" value="${emp.empname }" readOnly="readOnly"/>
			</td>
		</tr>
		<tr>
			<td width="120">员工性别</td>
			<td width="380">
				<input type="radio" name="empsex" id="empsex" value="男" checked="checked">男
				<input type="radio" name="empsex" id="empsex" value="女" >女
			</td>
		</tr>
		<tr>
			<td width="120">入职日期</td>
			<td width="380">
				<input type="text" class="Wdate" name="entrydate" id="entrydate" value="${emp.entrydate}" readOnly="readOnly"/>
			</td>
		</tr>
		<tr>
			<td width="120">联系电话</td>
			<td width="380">
				<input type="text"  name="empphone" id="empphone" value="${emp.empphone }">
			</td>
		</tr>
		<tr>
			<td width="120">现住址</td>
			<td width="380">
				<input type="text"  name="empaddr" id="empaddr" value="${emp.empaddr }">
			</td>
		</tr>
		<tr>
			<td width="120">薪资</td>
			<td width="380">
				<input type="text" name="salary" id="salary" value="${emp.salary }">
			</td>
		</tr>
		<tr>
			<td width="120">变更原因</td>
			<td width="380">
				<textarea rows="3" cols="40" name="changereason" id="changereason"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" name="button" id="button" value="确定">&nbsp;&nbsp;
				<input type="reset" name="button2" id="button2" value="取消">&nbsp;&nbsp;
				<a href="EmployeeServlet?method=findAll">返回</a>
			</td>
		</tr>
	</table>
	<div id="msg" align="center">${msg }</div>
</form>
</body>
</html>

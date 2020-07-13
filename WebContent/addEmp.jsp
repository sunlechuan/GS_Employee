<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
	.page-header{
		text-align: center;
	}
	#tab{
		background-color: #AAAAAA;
		font-size: 14px;
	}
	td{
		background-color: #ffffff;
	}
</style>
<title>Insert title here</title>
</head>
<body>

	<div class="page-header">
		<h3>新增员工</h3>
	</div>
	<form action="EmployeeServlet?method=addEmp" method="post">
		<table id="tab" width="500" border="0" cellpadding="2" cellspacing="1">
			<tr>
				<td width="120">部门名称</td>
				<td width="380">
					<select name="deptno" id="deptno">
						<c:forEach items="${deptList }" var="dept">
							<option value="${dept.deptno }">${dept.deptname }</option>	
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width=120>员工姓名</td>
				<td width="380">
					<input type="text" name="empname" id="empname">
				</td>
			</tr>
			<tr>
				<td width=120>员工性别</td>
				<td width="380">
					男<input type="radio" name="empsex" id="empsex" value="男" checked="checked">
					女<input type="radio" name="empsex" id="empsex" value="女">
				</td>
			</tr>
			<tr>
				<td width=120>入职日期</td>
				<td width="380">
					<input type="text" name="entrydate" id="entrydate" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:'true'})" >
				</td>
			</tr>
			<tr>
				<td width=120>联系电话</td>
				<td width="380">
					<input type="text" name="empphone" id="empphone">
				</td>
			</tr>
			<tr>
				<td width=120>住址</td>
				<td width="380">
					<input type="text" name="empaddr" id="empaddr">
				</td>
			</tr>
			<tr>
				<td width=120>薪资</td>
				<td width="380">
					<input type="text" name="salary" id="salary">￥
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="submit" name="button" id="button" value="增加">
				<input type="reset" name="button2" id="button2" value="取消">
				<a href="EmployeeServlet?method=findAll">返回</a>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>
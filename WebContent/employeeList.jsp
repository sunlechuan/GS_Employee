<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%--两个包 jstl，standard --%>
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
		font-size: 14px;
	}
	td{
		background-color: #ffffff;
	}
	#add{
		margin: 0 auto;
		width:850px;
		text-align: center;
	}
	#msg{
		color:#ff00000;
	}
	
</style>
</head>
<body>

	<div class="page-header">
		<h3>员工列表</h3>
	</div>
	<div id="add">
		<a href="<%=path%>/EmployeeServlet?method=getDept">新增员工</a>
	</div>
	<div id="contener">
		<table id="tab" border="0" cellpadding="2" cellspacing="1" width="900"
			align="center">
			<tr style="background-color: #ffffff">
				<th>员工编号</th>
				<th>部门名称</th>
				<th>员工姓名</th>
				<th>员工性别</th>
				<th>员工入职日期</th>
				<th>员工联系电话</th>
				<th>员工住址</th>
				<th>员工薪资</th>
				<th>员工离职日期</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${list }" var="l">
				<tr>
				<td>${l.empno}</td>
				<td>${l.deptname }</td>
				<td>${l.empname }</td>
				<td>${l.empsex }</td>
				<td>${l.entrydate }</td>
				<td>${l.empphone }</td>
				<td>${l.empaddr }</td>
				<td>${l.salary }</td>						
				<td>${l.leavedate }</td>
					<td>
					<c:if test="${l.leavedate==null }">
					<a href="EmployeeServlet?method=toUpdate&empno=${l.empno }">变更</a> 
					<a href="EmployeeServlet?method=leaveByEmpno&empno=${l.empno }">离职</a> 
					</c:if>
					<c:if test="${l.leavedate!=null }">
					<a href="EmployeeServlet?method=delete&empno=${l.empno }">减员    </a>
					</c:if>
					</td>
			</tr>
			</c:forEach>
		
			
			
		</table>
		<a href="EmployeeServlet?method=findAll&page=1">首页</a>
		<c:if test="${param.page>1 }">
		<a href="EmployeeServlet?method=findAll&page=${param.page-1 }">上一页</a>
		</c:if>
		<!--显示页码 -->
		<c:forEach begin="1" end="${pageCount }" varStatus="pageCount">
		<a href="EmployeeServlet?method=findAll&page=${pageCount.index }">${pageCount.index}</a>
		
		</c:forEach>
		<c:if test="${param.page<requestScope.pageCount }">
		<a href="EmployeeServlet?method=findAll&page=${param.page+1 }">下一页</a>
		
		</c:if>
	  <a href="EmployeeServlet?method=findAll&page=${requestScope.pageCount }">尾页</a>
	  <form action="EmployeeServlet?method=findAll" method="post">
	  <select name="page">
	  <c:forEach begin="1" end="${pageCount }" varStatus="pageCount">
	  <option value="${pageCount.index }">${pageCount.index }</option>
	  
	  </c:forEach>
	  </select>
	  <input type="submit" value="跳转"/>
	  </form>
	  
		<div id="msg">${msg }</div>
	</div>

</body>

</html>
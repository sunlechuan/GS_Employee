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
		margin:5px 0;
		text-align: center;
	}
	#tab{
		background-color: #AAAAAA;
	}
	.query{
	margin:5px 0;
		text-align: center;
	}
	td{
		background-color: #ffffff;
		font-size: 14px;
	}
	#export{
	margin: 0 auto;
	width:850px;
    text-align:right;
	}
</style>
</head>
<body>
<div class="page-header">
<h3>员工变更记录</h3>
</div>
<div class="query">
<form action="HistoryServlet?method=findAll" method="post" >
<input type="text" name="key" id="key"/>&nbsp;&nbsp;
<input type="submit" name="button" id="button" value="查询"/>
<div id="export">
<input type="hidden" value="${search }" name=search id="search" />
<a href="HistoryServlet?method=exportExcel">导出全部数据</a>
<a href="HistoryServlet?method=exportExcelPage&search=${search }&page=${param.page}">导出当前页</a>


</div>

<div id="contener">
<table id="tab" cellpadding="3" border="0" width="800" cellspacing="1">
<tr style="background-color:#ffffff">
  <th>员工姓名</th>
  <th>部门名称</th>
  <th>薪资</th>
  <th>变更日期</th>
  <th>变更原因</th>  
  <th>离职日期</th>
  <th>离职原因</th>     
       
</tr>
<c:forEach items="${historyList }"  var= "history" >
<tr>
<td>${history.empname }</td>
<td>${history.deptname }</td>
<td>${history.salary }</td>
<td>${history.changedate }</td>
<td>${history.changereason }</td>
<td>${history.dismissiondate }</td>
<td>${history.dismissionreason }</td>
</tr>
</c:forEach>
</table>
</div>

</form>
</div>
<a href="HistoryServlet?method=findAll&page=1">首页</a>
		<c:if test="${param.page>1 }">
		<a href="HistoryServlet?method=findAll&page=${param.page-1 }">上一页</a>
		</c:if>
		<!--显示页码 -->
		<c:forEach begin="1" end="${pageCount }" varStatus="pageCount">
		<a href="HistoryServlet?method=findAll&page=${pageCount.index }">${pageCount.index}</a>
		
		</c:forEach>
		<c:if test="${param.page<requestScope.pageCount }">
		<a href="HistoryServlet?method=findAll&page=${param.page+1 }">下一页</a>
		
		</c:if>
	  <a href="HistoryServlet?method=findAll&page=${requestScope.pageCount }">尾页</a>
	  <form action="HistoryServlet?method=findAll" method="post">
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
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
#msg{
text-align:center;
color:red;
width:500px;
}
</style>

</head>
<body>
<center>
<font>
<h2>登陆界面</h2>
</font>
<form action="AdminServlet?method=login" method="post">
用户名：<input type="text" name="username" id="username"></br>
密&nbsp;码：   <input type="password" name="password" id="password"></br>
<input type="submit" name="button" id="button" value="登陆">
<a href="register.jsp">注册</a>
</form>

<div id="msg">${msg }</div>
</center>


</body>
</html>
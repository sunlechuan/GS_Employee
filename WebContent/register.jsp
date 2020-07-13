<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
 function usernameBlur(){
	 if($("#username").val()==""){
		 alert("用户名不能为空");
	 }
	 
 }
 function passwordBlur(){
	 if($("#password").val()==""){
		 alert("密码不能为空");
	 }
	 
 

}
</script>
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
<h2>注册界面</h2>
</font>
<form action="AdminServlet?method=register" method="post">
用户名：<input type="text" name="username" id="username" onblur="usernameBlur()"></br>
密&nbsp;码<input type="password" name="password" id="password" onblur="passwordBlur()"></br>
<input type="submit" name="button" id="button" value="注册" >
<a href="login.jsp">返回</a>
</form>
<div id="msg">${msg }</div>
</center>
</body>
</html>
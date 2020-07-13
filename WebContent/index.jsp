<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/b.tabs.css">
<style type="text/css">
	div.menuSiderBar{}
	div.menuSiderBar li.nav-header{
		font-size:14px;
		padding:3px 15px;
	}
	div.menuSideBar .nav-list > li > a,div.menuSider .dropdown-menu li a{
		-webkit-border-radius:0px;
		-moz-border-radius:0px;
		-ms-border-radius:0px;
		border-radius:0px;
	}
	#welcome{

float:right
	}
</style>
</head>
<body>
<div class="content">
	<div class="container">
	<div id="welcome">
	<h4>	欢迎您<b>${admin.username }</b>登陆本系统
		<a href="AdminServlet?method=logOut">[退出]</a></h4>
		</div>
		<h3 class="page-header">公司人事后台管理系统</h3>
		
		<div class="">
			<div class="row-fluid">
				<div class="col-md-2" style="padding-left:0px;">
					<div class="well menuSideBar" style="padding:8px 0px;">
						<ul class="nav nav-list" id="menuSideBar">
							<li class="nav-header">导航菜单</li>
							<li class="nav-divider"></li>
							<li mid="tab1" funurl="<%=path%>/DeptServlet?method=findAll"><a tabindex="-1" href="javascript:void(0)">部门管理</a></li>
							<li	mid="tab2" funurl="<%=path%>/EmployeeServlet?method=findAll&page=1"><a tabindex="-1" href="javascript:void(0)">员工管理</a></li>
							<li	mid="tab3" funurl="<%=path%>/HistoryServlet?method=findAll&key=&page=1"><a tabindex="-1" href="javascript:void(0)">历史记录管理</a></li>
							
						</ul>
					</div>
				</div>
				<div class="col-md-10" id="mainFrameTabs" style="padding:0px">
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active noclose"><a href="#bTabs_navTabsMainPage" data-toggle="tab">首页</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="bTabs_navTabsMainPage">
							<div class="page-header">
								<h2 style="font-size:31.5px;text-align:center; font-weigth:normal;line-heigh:60px">
								欢迎使用<br>
								公司人事后台管理系统
								</h2>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div style="text-align:center; margin:100px; font:normal 14px/24px 'MicroSoft YaHei';">
	<p>360、FireFox、Chrome不支持IE8及以下浏览器	</p>
</div>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/b.tabs.js"></script>
<script type="text/javascript" src="js/demo.js"></script>
</body>
</html>
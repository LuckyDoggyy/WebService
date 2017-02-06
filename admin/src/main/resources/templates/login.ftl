<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" 
"http://www.w3.org/TR/html4/strict.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String error = (String) request.getSession().getAttribute("error");
	String errormsg = (String) request.getSession().getAttribute(
			"errormsg");
	if ("true".equals(error)) {
		//response.sendRedirect("login.jsp");
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="platform/login/images/favicon.ico" />

<title>考勤系统登录</title>
<link href="platform/login/css/operating-license.css" rel="stylesheet"
	type="text/css">



</head>
<body>
	<div class="wrap">
		<div class="header">
			<h1>考勤管理系统</h1>
		</div>
		<div class="main">
			<form id="loginForm" name="userform" method="post"
				action="login!checkLogin.action">
				<div class="hidden-input">
					<input type="hidden" name=""> <input type="hidden" name="">
					<input type="hidden" name="">
				</div>
				<%
					if ("true".equals(error)) {
				%>
				<div style="text-align: center; color: red;"></div>
				<%
					} else {
				%>
				<div style="text-align: center; color: red;">&nbsp;</div>
				<%
					}
				%>
				<div class="user-name">
					<label class="lb">用户名：</label> <input type="text" name="j_username"
						id="j_username" value="" class="ipt-t"
						onFocus="this.className+=' ipt-t-focus'"
						onBlur="this.className='ipt-t'">
				</div>
				<div class="pass-word">
					<label class="lb">密&nbsp;&nbsp;&nbsp;码：</label> <input
						type="password" name="j_password" id="j_password" class="ipt-t"
						value="" onFocus="this.className+=' ipt-t-focus'"
						onBlur="this.className='ipt-t'">
				</div>

				<div class="login-button">
					<input name="" type="submit" value="登录" class="button"
						id="btnSearch" onmouseover="this.className+=' button-focus'"
						onmouseout="this.className='button'"> <input name=""
						type="reset" value="重置" class="button-rewrite" id="btnSearch"
						onmouseover="this.className+=' button-focus'"
						onmouseout="this.className='button-rewrite'">
				</div>
			</form>
		</div>
		<div>
			<br>
			<center><p>用户名为工号,初始密码为工号</p></center>
		</div>
		<div class="footer">
		<p>Copyright &copy; 2015 版权所有: 浙江工业大学</p>
			<a href="Manual.html" target="_Blank">查看软件说明书</a>
		</div>
	</div>
	<script type="text/javascript">
		document.getElementById("j_username").focus();
	</script>
</body>
</html>

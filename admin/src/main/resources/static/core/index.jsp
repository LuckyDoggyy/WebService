<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" 
"http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String userbh = (String) request.getSession().getAttribute(
			"loginuserbh");
	if(userbh==null||userbh.trim()==""){
		//System.out.println("login.jsp");
		response.sendRedirect(basePath+"login.jsp");
	}
%>
<html>
<head>
<base href="<%=basePath%>">
<title>服务管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/extjs4/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/wirelessdoor/wlcore/css/comm.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/extjs4/examples/shared/example.css" />
<script type="text/javascript" src="<%=path%>/extjs4/ext-all.js"></script>
<script type="text/javascript" src="<%=path%>/extjs4/examples/shared/examples.js"></script>
<script type="text/javascript"
	src="<%=path%>/extjs4/examples/ux/statusbar/StatusBar.js"></script>
<script type="text/javascript"
	src="<%=path%>/extjs4/examples/ux/ProgressBarPager.js"></script>
<script type="text/javascript" src="<%=path%>/extjs4/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/wirelessdoor/wlcore/wlapp.js"></script>
</head>

<body>

</body>
</html>

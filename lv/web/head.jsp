<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>欢迎使用人保武侯分公司旅游保险系统</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	

	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/mainst.css">
	<script type="text/javascript" src="<%=basePath%>js/myjs.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/meizzDate.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/prototype.js"></script>
	

</head>
<body>

</body>
</html>
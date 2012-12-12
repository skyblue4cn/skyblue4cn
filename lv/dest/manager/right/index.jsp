<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>权限配置页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="styles.css">

  </head>
  <body>
  	<div style="text-align:center;">
	   	<div style="font:16px bold;">权限配置页面</div>
	   	<p>&nbsp;</p>
	   	<p>这里是配置整个系统权限及权限资源的页面，如果你不是系统开发员，请不要再此配置</p>
	   	<p><a href="rightList.action" target="_blank">配置权限</a></p>
	   	<p>&nbsp;</p>
	   	<p><a href="resourceList.action" target="_blank">配置权限资源</a></p>
   	</div>
   	
  </body>
</html>

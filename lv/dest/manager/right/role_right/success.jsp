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
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
  		<ww:include value="/head.jsp"></ww:include>
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
    	<p align="center"><ww:include value="/message.jsp"></ww:include></p>
  		<p>&nbsp;</p>
  		<p align="center"><input type="button" value="确定" class="inputbutton" onclick="returnParentWindow()"></p>
  </body>
</html>

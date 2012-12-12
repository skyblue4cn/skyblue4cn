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
    
    <title>My JSP 'addRight.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<ww:include value="/head.jsp"></ww:include>
  	<p>&nbsp;</p>
  	<p>添加权限</p>
  	<p>&nbsp;</p>
  	<ww:form action="addRight" method="post">
		<ww:textfield label="权限名称" name="strName"></ww:textfield>  	
  		<ww:textfield label="说明" name="strDesc"></ww:textfield>
  		<ww:hidden name="tbRight.intRightType" value="%{tbRight.intRightType}"></ww:hidden>
  		<ww:submit value="提交"></ww:submit>
  	</ww:form>
  </body>
</html>

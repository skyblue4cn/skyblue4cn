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
  	<p>添加权限资源</p>
  	<p>&nbsp;</p>
  	<ww:form action="addResource" method="post">
		<ww:textfield label="Action" name="strActionName"></ww:textfield>  	
  		<ww:textfield label="说明" name="strModuleName"></ww:textfield>
  		<ww:if test="type==1">
  		<ww:select list="bxRightList" name="intRightId" label="所属权限" listKey="id" listValue="strName" headerKey="-1" headerValue="请选择"></ww:select>
  		</ww:if>
  		<ww:if test="type==2">
  		<ww:select list="lxsRightList" name="intRightId" label="所属权限" listKey="id" listValue="strName" headerKey="-1" headerValue="请选择"></ww:select>
  		</ww:if>
  		<ww:hidden name="type" value="%{type}"></ww:hidden>
  		<ww:submit value="提交"></ww:submit>
  	</ww:form>
  </body>
</html>

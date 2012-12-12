<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.sunnydale.include.RoleTreeBean"%>
<%@ taglib prefix="ww" uri="webwork" %>
<%@ taglib uri="/WEB-INF/Tree.tld" prefix="tree"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>旅行社树形管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="styles.css">
	<link rel="stylesheet" href="tree/dtree.css" type="text/css" />
	<script type="text/javascript" src="tree/dtree.js"></script>
  </head>
  
  <body>
    	<%
    		RoleTreeBean rt = (RoleTreeBean)request.getAttribute("partmentRoleTreeBean") ;
    		List list = rt.getTreeList();
    	%>
    	<tree:show tree="<%=list %>"></tree:show>
  </body>
</html>

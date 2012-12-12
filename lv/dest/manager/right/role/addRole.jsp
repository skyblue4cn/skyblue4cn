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
    
    <title>添加角色</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
   	<ww:include value="/head.jsp"></ww:include>
   	<p><ww:include value="/message.jsp"/></p>
   	<p>&nbsp;</p>
   	<div align="center">添加角色</div>
   	<p>&nbsp;</p>
   	<ww:form action="addRole.action" method="post">
   	<ww:hidden name="tbRole.intTypeId" value="%{tbRole.intTypeId}"></ww:hidden>
   	<table cellpadding="0" cellspacing="0" width="90%" class="table">
   		<ww:textfield label="角色名称" name="tbRole.strRoleName" value="%{tbRole.strRoleName}"></ww:textfield>
   	</table>
   <p>&nbsp;</p>
   	<table cellpadding="0" cellspacing="0" width="90%">
   		<tr>
   			<td align="right"><input type="submit" value="添加" class="inputbutton"/></td>
   		</tr>
   	</table>
   	</ww:form>
   
  </body>
</html>

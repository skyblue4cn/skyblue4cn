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
    
    <title>修改角色信息</title>
    
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
   	<p>&nbsp;</p>
   	<ww:form action="updateRole.action" method="post">
   	<ww:hidden name="tbRole.id" value="%{tbRole.id}"></ww:hidden>
   	<ww:hidden name="tbRole.intTypeId" value="%{tbRole.intTypeId}"></ww:hidden>
   	<table cellpadding="0" cellspacing="0" width="90%" class="table">
   		<ww:textfield label="角色名称" name="tbRole.strRoleName" value="%{tbRole.strRoleName}"></ww:textfield>
   	</table>
   <p>&nbsp;</p>
   	<table cellpadding="0" cellspacing="0" width="90%">
   		<tr>
   			<td><input type="submit" value="确定" class="inputbutton"/></td>
   		</tr>
   	</table>
   	</ww:form>
   
  </body>
</html>

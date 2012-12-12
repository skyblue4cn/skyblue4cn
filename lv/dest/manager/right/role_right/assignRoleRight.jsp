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
    
    <title>给角色分配权限</title>
    
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
    <form action="assignRoleRight.action" method="post">
    <ww:hidden name="intRoleId" value="%{intRoleId}"></ww:hidden>
    <div style="font:16px bold;text-align:center;height:30px;">分配权限</div>
     <table border="0" cellpadding="0" cellspacing="0" width="90%">
    	<tr>
    		<td width="20%">角色名称:</td>
    		<td style="color:blue;" align="left"><ww:property value="tbRole.strRoleName"/></td>
    	</tr>
    </table>
    <table border="0" cellpadding="0" cellspacing="0" width="90%" class="table">
    	<ww:iterator value="RoleAndRightXrefList">
    	<tr>
    		<td width="20%"><ww:checkbox name="rightIds" value="%{isHasRight}" fieldValue="%{intRightId}" theme="simple"/></td>
    		<td><ww:property value="tbRight.strName"/></td>
    	</tr>
    	</ww:iterator>
    </table>
    <p>&nbsp;</p>
    <table border="0" cellpadding="0" cellspacing="0" width="90%">
    	<tr>
    		<td><input type="button" value="提交" class="inputbutton" onclick="if(window.confirm('确定提交吗?'))this.form.submit();"></td>
    		<td><input type="button" value="取消" class="inputbutton" onclick="window.close();"/></td>
    	</tr>
    </table>
    </form>
  </body>
</html>

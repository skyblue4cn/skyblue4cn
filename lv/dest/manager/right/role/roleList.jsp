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
    
    <title>角色及权限管理</title>
    
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
    	<ww:include value="/top.jsp"></ww:include>
    	<p>&nbsp;</p>
    	<p>&nbsp;</p>
    	<div style="width:80%;">
    	<table border="0" cellpadding="0" cellspacing="0" width="100%">
    		<tr>
    			<td style="color:blue;">保险公司角色管理</td>
    			<td align="right" height="30px;" valign="top"><a href="javascript:openAWindow('toAddBxRole.action')"><img src="images/tianjia.gif"/></a></td>
    		</tr>
    		<ww:if test="'tomcat'.equals(#session.tbUser.strUserLogName)">
    		<tr>
    			<td colspan="2"><a href="toAdminRight.action" target="_blank">配置权限</a></td>
    		</tr>
    		</ww:if>
    	</table>
    	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="table">
    		<tr>
    			<th>序号</th>
    			<th>角色名称</th>
    			<th>权限分配</th>
    			<th>修改</th>
    			<th>删除</th>
    		</tr>
    		<ww:iterator value="bxRoleList" status="index">
    		<tr style="text-align:center;">
    			<td><ww:property value="#index.index+1"/></td>
    			<td><ww:property value="strRoleName"/></td>
    			<td><a href="javascript:openAWindow('toAssignRightToRole.action?intRoleId=<ww:property value="id"/>')"><img src="images/biangeng.gif"></a></td>
    			<td><a href="javascript:openAWindow('toUpdateRole.action?id=<ww:property value="id"/>')"><img src="images/xiugai.gif"></a></td>
    			<td><a href="javascript:openAWindow('toDeleteRole.action?id=<ww:property value="id"/>')"><img src="images/shanchu.gif"></a></td>
    		</tr>
    		</ww:iterator>
    	</table>
    	<p>&nbsp;</p>
    	<p>&nbsp;</p>
    	<table border="0" cellpadding="0" cellspacing="0" width="100%">
    		<tr>
    			<td style="color:blue;">旅行社角色管理</td>
    			<td align="right" height="30px;" valign="top"><a href="javascript:openAWindow('toAddLxsRole.action')"><img src="images/tianjia.gif"/></a></td>
    		</tr>
    	</table>
    	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="table">
    		<tr>
    			<th>序号</th>
    			<th>角色名称</th>
    			<th>权限分配</th>
    			<th>修改</th>
    			<th>删除</th>
    		</tr>
    		<ww:iterator value="lxsRoleList" status="index">
    		<tr style="text-align:center;">
    			<td><ww:property value="#index.index+1"/></td>
    			<td><ww:property value="strRoleName"/></td>
    			<td><a href="javascript:openAWindow('toAssignRightToRole.action?intRoleId=<ww:property value="id"/>')"><img src="images/biangeng.gif"></a></td>
    			<td><a href="javascript:openAWindow('toUpdateRole.action?id=<ww:property value="id"/>')"><img src="images/xiugai.gif"></a></td>
    			<td><a href="javascript:openAWindow('toDeleteRole.action?id=<ww:property value="id"/>')"><img src="images/shanchu.gif"></a></td>
    		</tr>
    		</ww:iterator>
    	</table>
    	</div>
  </body>
</html>

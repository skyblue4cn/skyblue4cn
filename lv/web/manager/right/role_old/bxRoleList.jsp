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
    
    <title>旅行社角色管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/mainst.css">
	
	<script type="text/javascript" src="js/myjs.js"></script>
	
  </head>
  
  <body>
   	<ww:include value="/top.jsp"></ww:include>
   <table cellpadding="0" cellspacing="0" width="100%" height="540px">
   	<tr valign="top">
   		<td width="15%" style="background:#ededed;" valign="top" align="center">
   			<br/>
	    	<br/>
	    	<br/>
	    	<br/>
	    	<br/>
	    	<br/>
	    	<input class="inputbutton3" type="button" onclick="getAllPeiKuanLog.action" value="保险角色管理   " /><br/><br/><br>
	    	<input class="inputbutton" type="button" onclick="openAWindow('manager/peikuan/add_peikuan.jsp')" value="旅行社角色管理 " /><br/><br/>
   		</td>
   		<td>
   			<br>
   			<table border="0" cellpadding="0" cellspacing="0" width="90%" align="center">
   				<tr>
   					<td width="90%"><div class="text1">内部角色管理</div></td>
   					<td><input type="button" value="添加" class="inputbutton" onclick="openAWindow('toAddBxRole.action')"/></td>
   				</tr>
   			</table>
   			
   			<br>
   			<table border="0" cellpadding="0" cellspacing="0" width="90%" class="table" align="center">
   					<tr>
   						<th>序号</th>
   						<th>角色名称</th>
   						<th>修改</th>
   						<th>权限设置</th>
   					</tr>
   				<ww:iterator value="bxRoleList">
   					<tr>
   						<td><ww:property value="id"/></td>
   						<td><ww:property value="strRoleName"/></td>
   						<td  align="center"><a href="javascript:openAWindow('toUpdateRole.action?id=<ww:property value="id"/>')" ><img src="images/xiugai.gif" border="0"/></a></td>
   						<td align="center"><a href=""><img src="images/xiugai.gif" border="0"/></a></td>
   					</tr>
   				</ww:iterator>
   			</table>
   		</td>
   	</tr>
   </table>
   
   
  </body>
</html>

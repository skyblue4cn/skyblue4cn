<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>欢迎使用武侯保险-旅游保险系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=basePath%>css/login.css" rel="stylesheet" type="text/css" />
</head>
  
<body onload="document.getElementById('admin_name').focus();">
	<div class="loginform">
		<form id="login" name="adminlogin" action="login.action" method="post">
		  <table width="100%" height="374" border="0" cellpadding="0" cellspacing="5">
		    <tr >
		      <td height="310" colspan="5" align="right">&nbsp;</td>
		      </tr>
		     <tr height="25">
		     	<td>&nbsp;</td>
		      	<td colspan="4" class="errm">
		      		<ww:include value="/message.jsp"></ww:include>
		      	</td>
		      </tr>
		    <tr >
		      <td width="15%" align="right"><label>用户名：</label></td>
		      <td width="10%" align="left"><input name="j_username" type="text" class="inputbox" id="admin_name" size="12" value="<ww:property value="j_username"/>" /></td>
		      <td width="10%" align="right"><label>密码：</label></td>
		      <td width="10%" align="left"><input name="j_password" type="password" class="inputbox" id="admin_pwd" size="14" /></td>
		       <td width="15%" align="right"><label>验证码：</label></td>
		      <td width="10%" align="left"><input name="j_validate" type="text" class="inputbox" id="admin_vdt" size="10" /></td>
		      <td width="15%" align="left"><img src="validation.jsp" border="0"/></td>
		      <td width="15%" align="left"><input type="submit"  value=" 登录 " /></td>
		    </tr>
		  </table>
		</form>
</div>
</body>
</html>
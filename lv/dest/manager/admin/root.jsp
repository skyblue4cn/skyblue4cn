<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
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
	<script type="text/javascript">
		function refreshTree(){
			window.parent.frametree.location.reload();
		}
	</script>
  </head>
  <body style="background:#ededed;">
  		<ww:include value="/head.jsp"></ww:include>
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
  		<fieldset style="width:95%">
  			<legend>刷新树</legend>
  			<p>&nbsp;</p>
	  		<table border="0" cellpadding="0" cellspacing="0" width="98%">
	  			<tr>
	  				<td><input type="button" class="inputbutton" value="刷新树" onclick="refreshTree()"/></td>
	  			</tr>
	  		</table>
	  		<p>&nbsp;</p>
  		</fieldset>
  		
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
  		
  		<fieldset style="width:95%">
  			<legend>添加旅行社</legend>
  			<p>&nbsp;</p>
	  		<table border="0" cellpadding="0" cellspacing="0" width="98%">
	  			<tr>
	  				<td><input type="button" class="inputbutton" value="添加旅行社" onclick="openAWindow('toAddCompany.action')"/></td>
	  			</tr>
	  		</table>
	  		<p>&nbsp;</p>
  		</fieldset>
  </body>
</html>

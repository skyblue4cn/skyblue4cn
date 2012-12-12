<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="webwork" prefix="ww" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>操作成功</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="admin.css">
	<script type="text/javascript">
		function refreshThePage(){
			//先将显示内容的地方刷新一遍
			window.opener.location.reload() ;
			//在关闭弹出窗口
			window.close() ;
		}
	</script>
</head>
  
<body style="background:#ededed;">
		<ww:include value="/head.jsp"></ww:include>
		<br>
		<br>		
		<table border="0" cellpadding="0" cellspacing="0" width="80%" style="text-align:center;">
			<tr>
				<td style="color:green;"><ww:actionmessage /></td>
			</tr>
			<tr height="50px"><td>&nbsp;</td></tr>
			<tr>
				<td><input type="button" value="确定" onclick="refreshThePage()" class="inputbutton"/></td>
			</tr>
		</table>
</body>
</html>
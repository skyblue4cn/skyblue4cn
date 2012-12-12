<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 	String local=request.getServletPath();
   //	out.println(request.getServletPath());
%>
<html>
<head>
	<base href="<%=basePath%>">
	<title>处理保单</title>
	<link rel="stylesheet" type="text/css" href="css/mainst.css"/>
	<script type="text/javascript" src="js/myjs.js"></script>
	<script type="text/javascript" src="js/prototype.js"></script>
	<script type="text/javascript" src="js/meizzDate.js"></script>
	<script type="text/javascript">
		function closeWindow(){
			window.opener.location.reload() ;
			window.close() ;
		}
	</script>
</head>
<body>
<br/>
	<table border="0" align="center" cellpadding="0" cellspading="0">
		<tr>
			<td>
				<ww:actionmessage/>
			</td>
			<td>	
				<input type="button" value="确定" onclick="returnParentWindow()" class="inputbutton">
			</td>
		</tr>
	</table>
</body>
</html>
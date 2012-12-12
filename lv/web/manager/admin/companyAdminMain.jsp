<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>旅行社管理</title>
		<link href="admin.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="tree/dtree.css" type="text/css" />
		<script type="text/javascript" src="tree/dtree.js"></script>
		<script type="text/javascript" src="js/util.js"></script>
		<script type="text/javascript">
		<!--
		
		//-->
		</script>
	</head>
	<body>
	<ww:include value="/top.jsp"></ww:include>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
    	<tr valign="top">
    		<td width="20%" valign="top">
			<div class="cuibox" style="height: 490px">
				<iframe frameborder="0" scrolling="auto" height="490px" width="100%"
					name="frametree" id="frametree" src="getPartmentAdminTree.action">
				</iframe>
			</div>
    		</td>
    		<td align="left" valign="top">
				<iframe frameborder="0" scrolling="auto" height="490px" width="100%"
					name="framepage" src="manager/admin/message.jsp">
				</iframe>
			</td>
		</tr>
	</table>
	</body>
</html>

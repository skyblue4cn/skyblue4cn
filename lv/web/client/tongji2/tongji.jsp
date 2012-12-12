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
    
    <title>数据统计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/mainst.css">

  </head>
  
  <body>
    	<ww:include value="/client_top.jsp"></ww:include>
    	<ww:include value="c_tongJiByYear.action"></ww:include>
    	<!--
    	<p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <table border="0" cellpadding="0" cellspacing="0" width="80%" height="100">
	  	<tr>
	  		<td>按年统计</td>
	  		<td><a href="c_tongJiByYear.action" target="_blank">进入</a></td>
	   </tr>
	   <tr>
	  		<td width="20%">按时间段统计</td>
	  		<td>
		  		<div>
			    	<form action="c_tongJiByTimeSpace.action" method="get" target="_blank">
			    	按时间段查询
			    	起<input type="text" id="stime" name="startTime" class="inputbox" onclick="setday(this)" readonly="readonly" size="15"/>
			    	止<input type="text" id="etime" name="endTime" class="inputbox" onclick="setday(this)" readonly="readonly" size="15"/>
			    	
			    	<input type="submit" value="提交"/>
			    	</form>
	    		</div>
    		</td>
	   </tr>
	  
	  
	  
	  </table>
    	<div>
    		
    	</div>
    	<p>&nbsp;</p>
    	
    	
    	
  --></body>
</html>

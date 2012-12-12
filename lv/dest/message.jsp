<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork"%>
<html>
  <head>
    <title>信息显示页面</title>
	<link rel="stylesheet" type="text/css" href="css/mainst.css">
	<style type="text/css">
		.error{
			color:red;text-align:center;
		}
		.mess{
			color:blue;text-align:center;
		}
	</style>
  </head>
  
  <body>
    	<ww:if test="hasActionErrors()">
    		<div class="error"><ww:actionerror/></div>
    	</ww:if>
    	<ww:if test="hasActionMessages()">
    		<div class="mess"><ww:actionmessage /></div>
    	</ww:if>
    	
  </body>
</html>

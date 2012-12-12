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
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
  	<ww:include value="/head.jsp"></ww:include>
  	 <P>&nbsp;</P>
    <P>&nbsp;</P>
    <p>保险公司角色的权限资源</p>
    <table cellpadding="0" cellspacing="0" width="80%" >
    	<tr>
    		<td align="right"><a href="javascript:openAWindow('toAddResource.action?type=1')">添加</a></td>
    	</tr>
    </table>
    <table cellpadding="0" cellspacing="0" width="80%" border="1" style="text-align:center;">
    	<tr>
    		<td width="10%">序号</td>
    		<td width="15%">Action</td>
    		<td width="15%">所属权限</td>
    		<td width="30%">说明</td>
    		<td width="10%">修改</td>
    		<td width="10%">删除</td>
    	</tr>
    	<ww:iterator value="bxResourceList" status="index">
    	<tr>
    		<td><ww:property value="#index.index+1"/></td>
    		<td><ww:property value="strActionName"/></td>
    		<td><ww:property value="tbRight.strName"/></td>
    		<td><ww:property value="strModuleName"/></td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
    	</tr>
    	</ww:iterator>
    </table>
    <P>&nbsp;</P>
    <P>&nbsp;</P>
    <P>&nbsp;</P>
    <p>旅行社角色的权限资源</p>
      	<table cellpadding="0" cellspacing="0" width="80%" >
    	<tr>
    		<td align="right"><a href="javascript:openAWindow('toAddResource.action?type=2')">添加</a></td>
    	</tr>
      </table>
      <table cellpadding="0" cellspacing="0" width="80%" border="1" style="text-align:center;">
    	<tr>
    		<td width="10%">序号</td>
    		<td width="15%">Action</td>
    		<td width="15%">所属权限</td>
    		<td width="30%">说明</td>
    		<td width="10%">修改</td>
    		<td width="10%">删除</td>
    	</tr>
    	<ww:iterator value="lxsResourceList" status="index">
    	<tr>
    		<td><ww:property value="#index.index+1"/></td>
    		<td><ww:property value="#index.index+1"/></td>
    		<td><ww:property value="strActionName"/></td>
    		<td><ww:property value="tbRight.strName"/></td>
    		<td><ww:property value="strModuleName"/></td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
    	</tr>
    	</ww:iterator>
    </table>
  </body>
</html>

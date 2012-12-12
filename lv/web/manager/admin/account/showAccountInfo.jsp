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
    
    <title>显示旅行社或部门账户信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="mainst.css">

  </head>
  
  <body style="background:#ededed;">
  	<ww:include value="/head.jsp"></ww:include>
  	<ww:include value="/manager/admin/navigation.jsp"></ww:include>
  	<fieldset style="width:100%;">
			<legend>账户信息</legend>
			<table border="0" cellpadding="0" cellspacing="0" width="98%">
				<tr>
					<td align="right" height="40px">
						<input type="button" value="存款" class="inputbutton" onclick="openAWindow('toCunKuanForAccount.action?id=<ww:property value="tbAccount.id"/>','550','500')"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="修改设置" class="inputbutton" onclick="openAWindow('toUpdateAccountProperty.action?id=<ww:property value="tbAccount.id"/>','550','500')"/>
					</td>
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="98%" class="table">
				<tr>
					<td width="30%">当前帐户余额:</td>
					<td><ww:property value="tbAccount.dbeResidual"/>元</td>
				</tr>
				<tr>
					<td>帐户类型:</td>
					<td>
						<ww:radio name="tbAccount.intPayTypeId" list="#{1:'预付',2:'月结'}" value="%{tbAccount.intPayTypeId}"theme="simple" disabled="true"/>
					</td>
				</tr>

				<tr>
					<td>最多允许欠费金额:</td>
					<td><ww:property value="tbAccount.dbeAcceptMaxMoney" />元</td>
				</tr>
				<tr>
					<td>最多允许欠费天数：</td>
					<td><ww:property value="tbAccount.intAcceptDays"/>天</td>
				</tr>
				<tr>
					<td>设置帐户为：</td>
					<td><ww:radio name="tbAccount.intAccountState" list="#{1:'可用',0:'不可用'}" value="%{tbAccount.intAccountState}" theme="simple" disabled="true"/></td>
				</tr>
			</table>
	</fieldset>
  </body>
</html>

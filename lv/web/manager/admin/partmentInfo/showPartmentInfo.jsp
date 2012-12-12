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
    
    <title>显示旅行社或部门信息</title>
    
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
  	<ww:if test="tbPartment.intParentId == 0">
  	<fieldset style="width:100%;">
			<legend><ww:property value="tbPartment.strPartmentName"/>信息</legend>
			<table border="0" cellpadding="0" cellspacing="0" width="98%">
				<tr>
					<td align="right" height="40px">
						<input type="button" value="删除" style="color:red;" class="inputbutton" onclick="openAWindow('toDeletePartment.action?id=<ww:property value="tbPartment.id"/>','550','500')"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="修改" class="inputbutton" onclick="openAWindow('toUpdateCompanyInfo.action?id=<ww:property value="tbPartment.id"/>','550','500')"/>
					</td>
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="98%" class="table">
				<tr>
					<td width="30%" align="left" class="text4" >名称</td>
					<td><ww:property value="tbPartment.strPartmentName"/></td>
				</tr>
				<tr>
					<td class="text4">
						许可证
					</td>
					<td>
						<ww:property value="tbPartment.strLicenceNumber"/>
					</td>
				</tr>
				<tr>
					<td class="text4">责任保险起始时间</td>
					<td>
						<ww:date name="tbPartment.dteResStartDate" format="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td class="text4">责任保险结束时间</td>
					<td>
						<ww:date name="tbPartment.dteResEndDate" format="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td class="text4">地址</td>
					<td><ww:property value="tbPartment.strAddress"/></td>
				</tr>
				<tr>
					<td class="text4">电话</td>
					<td><ww:property value="tbPartment.strPhoneNumber"/></td>
				</tr>
				<tr>
					<td class="text4">传真</td>
					<td><ww:property value="tbPartment.strFax"/></td>
				</tr>
				<tr>
					<td class="text4">联系人</td>
					<td><ww:property value="tbPartment.strConnectPeople"/></td>
				</tr>
				<tr>
					<td class="text4">是否查看部门保单</td>
					<td><ww:radio name="tbPartment.intIsSeePartmentBill" theme="simple" disabled="true" list="#{1:'是',0:'否'}" value="%{tbPartment.intIsSeePartmentBill}" /></td>
				</tr>
				<tr>
					<td class="text4">是否审核保单</td>
					<td><ww:radio name="tbPartment.intIsNeedSureBill" theme="simple" disabled="true" list="#{1:'审核',0:'不审核'}" value="%{tbPartment.intIsNeedSureBill}" /></td>
				</tr>
			</table>
	</fieldset>
  	</ww:if>
  	<ww:else>
  		<fieldset style="width:95%;">
			<legend><ww:property value="tbPartment.strPartmentName"/>信息</legend>
			<table border="0" cellpadding="0" cellspacing="0" width="98%">
				<tr>
					<td align="right" height="40px">
						<input type="button" value="删除" style="color:red;" class="inputbutton" onclick="openAWindow('toDeletePartment.action?id=<ww:property value="tbPartment.id"/>','550','500')"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="修改" class="inputbutton" onclick="openAWindow('toUpdatePartmentInfo.action?id=<ww:property value="tbPartment.id"/>','550','500')"/>
					</td>
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="98%" class="table">
				<tr>
					<td width="30%" class="text4">名称</td>
					<td><ww:property value="tbPartment.strPartmentName" /></td>
				</tr>
				<tr>
					<td class="text4">电话</td>
					<td><ww:property value="tbPartment.strPhoneNumber"/></td>
				</tr>
				<tr>
					<td>传真</td>
					<td><ww:property value="tbPartment.strFax"/></td>
				</tr>
				<tr>
					<td>联系人</td>
					<td><ww:property value="tbPartment.strConnectPeople" /></td>
				</tr>
			</table>
			<br/>
	</fieldset>
  	</ww:else>
  </body>
</html>

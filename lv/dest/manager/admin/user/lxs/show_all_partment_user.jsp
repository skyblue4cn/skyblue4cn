<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>旅行社用户管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body style="background:#ededed;">
    <ww:include value="/head.jsp"></ww:include>
	<ww:include value="/manager/admin/navigation.jsp"></ww:include>
	<fieldset style="width:100%;">
			<legend><ww:property value="tbUser.tbPartment.strPartmentName"/>用户管理</legend>
			<table border="0" cellpadding="0" cellspacing="0" width="98%">
				<tr>
					<td align="right" height="30px" valign="middle">
						<input type="button" value="添加" class="inputbutton" onclick="openAWindow('toAddLxsUser.action?intPartmentId=<ww:property value="nodeid"/>','550','500')"/>
					</td>
				</tr>
			</table>
			<table border=0 width="100%" cellpadding="0" cellspacing="0" class="table" >
				<tr height="25px;">
					<th scope="col" width="10%">序号</th>
					<th scope="col" width="15%">登录名</th>
					<th scope="col" width="35%">姓名</th>
					<th scope="col" width="20%">角色</th>
					<th scope="col" width="10%">状态</th>
					<th scope="col" width="10%">修改</th>
				</tr>
				<ww:iterator value="tbUserList" status="index">
				<tr height="25px;">
					<td align="center"><ww:property value="#index.index+1"/></td>
					<td align="center"><ww:property value="strUserLogName"/></td>
					<td align="center"><ww:property value="strUserName"/></td>
					<td align="center"><ww:property value="tbRole.strRoleName"/></td>
					<td align="center">
						<ww:if test="intUserState==0"><span style="color:red;">不可用</span></ww:if>
						<ww:else>可用</ww:else>
					</td>
					<td align="center">
						<a href="javascript:;" onclick="openAWindow('toUpdateUserByBx.action?id=<ww:property value="id"/>','500','400')">
							<img src="images/xiugai.gif" border="0">
						</a>
					</td>
				</tr>
				</ww:iterator>
			</table>
			<ww:if test="tbUserList.size()==0">
			<div class="text8">没有用户！ 
			</div>
		</ww:if>
	</fieldset>
  </body>
</html>

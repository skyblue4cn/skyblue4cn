<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>	
<ww:include value="/head.jsp"></ww:include>
<div  id="lvdbtable" style="width:100%;height:400;text-align: center;">
	<div class="bdtop" width="100%" align="center">添加内部用户</div>
	<ww:actionmessage/>
	<form action="addBxUser.action" method="post">
	<table border="0" cellpadding="0" cellspacing="0" width="80%">
		<tr>
			<td>用户名</td>
			<td><input type="text" name="tbUser.strUserLogName"></td>
		</tr>
		<tr>
			<td>真实姓名</td>
			<td><input type="text" name="tbUser.strUserName"/></td>
		</tr>
		<tr>
			<td>用户角色</td>
			<td>
				<ww:select list="tbRoleList" headerKey="-1" headerValue="请选择用户角色" listKey="id" listValue="strRoleName" name="tbUser.intRoleId" theme="simple"></ww:select>
			</td>
		</tr>	
	</table>
	<br/>
	<table width="100%" border="0">
		<tr>
			<td width="50%" align="center"><input type="button" class="inputbutton" value="添加" onclick="if(window.confirm('确认添加吗？'))this.form.submit();"/></td>
			<td align="center"><input type="button" value="取消" class="inputbutton" onclick="window.close();"/></td>
		</tr>
	</table>
	</form>
	
</div>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>	
<ww:include value="/head.jsp"></ww:include>
<div  id="lvdbtable" style="width:100%;height:400;text-align: center;">
	<div class="bdtop" width="100%" align="center">更新内部用户信息</div>
	<font color="red"><ww:actionmessage/></font>
	<form action="updateBxUser.action" method="post">
	<ww:hidden name="tbUser.id" value="%{tbUser.id}"/>
	<ww:hidden name="tbUser.intPartmentId" value="%{tbUser.intPartmentId}"/>
	<br>
	<table border="0" cellpadding="0" cellspacing="0" width="80%" class="table">
		<tr>
			<td>用户名:</td>
			<td><ww:textfield name="tbUser.strUserLogName" theme="simple"/></td>
		</tr>
		<tr>
			<td>修改密码：</td>
			<td>
				<ww:radio name="isUpdatePassword" theme="simple" list="#{0:'不修改密码',1:'修改密码'}" value="0"/>
			</td>
		</tr>
		<tr>
			<td>请输入新密码:</td>
			<td><input type="password" name="tbUser.strUserPassword" value=""></td>
		</tr>
		<tr>
			<td>请再次输入密码:</td>
			<td><input type="password" name="tbUser.strReUserPassword" value=""></td>
		</tr>
		<tr>
			<td>姓名:</td>
			<td><ww:textfield name="tbUser.strUserName" theme="simple"/></td>
		</tr>
		<tr>
			<td>角色:</td>
			<td>
				<ww:select list="tbRoleList" listKey="id" listValue="strRoleName" name="tbUser.intRoleId" value="%{tbUser.intRoleId}" theme="simple"></ww:select>
			</td>
		</tr>
		<tr>
			<td>用户状态:</td>
			<td><ww:radio name="tbUser.intUserState" list="#{1:'可用',0:'不可用'}" value="%{tbUser.intUserState}" theme="simple"/></td>
		</tr>	
	</table>
	<br/>
	<table width="80%">
		<tr>
			<td width="50%" align="left"><input type="button" class="inputbutton" value="修改" onclick="if(window.confirm('确认修改吗？'))this.form.submit();"/></td>
			<td align="left"><input type="button" value="取消" class="inputbutton" onclick="window.close();"/></td>
		</tr>
	</table>
	</form>
	
</div>
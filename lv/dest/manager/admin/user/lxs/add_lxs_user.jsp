<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>	
<ww:include value="/head.jsp"></ww:include>
<div  id="lvdbtable" style="width:100%;height:400;text-align: center;">
	<div class="bdtop" align="center">给<ww:property value="tbUser.tbPartment.strPartmentName"/>添加用户</div>
	<div class="errm" align="center"><ww:actionerror/></div>
	<form action="addLxsUser.action" method="post">
	<ww:hidden name="tbUser.strUserPassword" value="111111"/>
	<input type="hidden" name="tbUser.intPartmentId" value="<ww:property value="tbUser.intPartmentId"/>"/>
	<br/>
	<table border="0" cellpadding="0" cellspacing="0" width="80%" style="line-height: 25px;">
		<tr>
			<td>用户名</td>
			<td><input type="text" name="tbUser.strUserLogName" value="<ww:property value="tbUser.strUserLogName"/>"></td>
		</tr>
		<tr>
			<td>真实姓名</td>
			<td><input type="text" name="tbUser.strUserName" value="<ww:property value="tbUser.strUserName"/>"/></td>
		</tr>
		<tr>
			<td>用户角色</td>
			<td>
				<ww:select list="tbRoleList" headerKey="0" headerValue="请选择用户角色" listKey="id" listValue="strRoleName" name="tbUser.intRoleId" theme="simple"></ww:select>
			</td>
		</tr>	
		<tr>
			<td colspan="2" style="color:green;text-align: left; font-weight: bold;line-height: 40px;">
				说明：用户初始密码为六个1
			</td>
		</tr>
	</table>
	<br/>
	

		<br/>
	<table width="80%">
		<tr>
			<td width="50%" align="center"><input type="button" class="inputbutton" value="添加" onclick="if(window.confirm('确认添加吗？'))this.form.submit();"/></td>
			<td align="center"><input type="button" value="取消" class="inputbutton" onclick="window.close();"/></td>
		</tr>
	</table>
	</form>
	
</div>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<div  id="lvdbtable" style="width:100%;text-align: center;height: 500px;">
	<div class="bdtop" width="100%" align="center"><ww:property value="tbPartment.strPartmentName"/>部门信息</div>
	<table border="0" cellpadding="0" cellspacing="0" width="80%" style="line-height:25px ;" class="table">
		<tr>
			<td  width="40%">旅行社：</td>
			<td><ww:property value="tbPartment.company.strPartmentName"/></td>
		</tr>
		<tr>
			<td>名称</td>
			<td><ww:property value="tbPartment.strPartmentName" /></td>
		</tr>
		<tr>
			<td>电话：</td>
			<td><ww:property value="tbPartment.strPhoneNumber"/></td>
		</tr>
		<tr>
			<td>传真：</td>
			<td><ww:property value="tbPartment.strFax"/></td>
		</tr>
		<tr>
			<td>联系人：</td>
			<td><ww:property value="tbPartment.strConnectPeople" /></td>
		</tr>
	</table>
</div>


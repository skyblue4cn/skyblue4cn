<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<div  id="lvdbtable" style="width:100%;text-align: center;height: 400px;">
	<div class="bdtop" width="100%" align="center">旅行社信息</div>
		<table border="0" cellpadding="0" cellspacing="0" width="90%" style="line-height:25px;" class="table">
			<tr>
				<td width="40%">名称：</td>
				<td><ww:property value="tbPartment.strPartmentName"/></td>
			</tr>
			<tr>
				<td>许可证：</td>
				<td><ww:property value="tbPartment.strLicenceNumber" /></td>
			</tr>
			<tr>
				<td>责任保险起始时间：</td>
				<td><ww:date name="tbPartment.dteResStartDate" format="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<td>责任保险结束时间：</td>
				<td><ww:date name="tbPartment.dteResEndDate" format="yyyy-MM-dd"/> </td>
			</tr>
			<tr>
				<td>地址：</td>
				<td><ww:property value="tbPartment.strAddress"/></td>
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
			<tr>
				<td>是否审核保单：</td>
				<td><ww:radio name="tbPartment.intIsNeedSureBill" list="#{1:'审核',0:'不审核'}" value="%{tbPartment.intIsNeedSureBill}" disabled="true" theme="simple"/></td>
			</tr>
		</table>
		<br/>
		
</div>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<ww:if test="tbBill != null">
	<div class="text2">根据保单号查询到的保单详情如下</div>
	<br/>
	<table border="1" cellpadding="0" cellspacing="0" width="95%" class="table">
		<tr>
			<th scope="col">保单号</th>
			<th scope="col">旅行社</th>
			<th scope="col">部门</th>
			<th scope="col">团队号</th>
			<th scope="col">起始时间</th>
			<th scope="col">终止时间</th>
			<th scope="col">路线</th>
			<th scope="col">保单详情</th>
		</tr>
		<tr>
			<td><ww:property value="tbBill.strBillNumber"/></td>
			<td><ww:property value="tbBill.tbPartment.company.strPartmentName"/></td>
			<td><ww:property value="tbBill.tbPartment.strPartmentName"/></td>
			<td><ww:property value="tbBill.strTeamNumber"/></td>
			<td><ww:date name="tbBill.dteStartTime" format="yyyy-MM-dd"/></td>
			<td><ww:date name="tbBill.dteEndTime" format="yyyy-MM-dd"/></td>
			<td><ww:property value="tbBill.strTravelRold"/></td>
			<td>
				<a href="getBillInfoToPrint.action?id=<ww:property value="tbBill.id"/>" target="blank">查看</a>
			</td>
		</tr>
	</table>
	<br/>
	<br/>
	<div class="text2">
		填写赔款详细情况
	</div>
	<div id="div3"></div>
	<br/>
	<form action="" method="post" name="addform">
		<ww:hidden name="tbPeiKuan.intBillId" value="%{tbBill.id}"/>
		<table border="0" cellpadding="0" cellspacing="0" width="95%" class="table">
			<tr>
				<td width="30%">赔款金额：</td>
				<td><ww:textfield name="tbPeiKuan.dbePeiKuanFee" theme="simple"/></td>
			</tr>
			<tr>	
				<td>备注：</td>
				<td><ww:textfield name="tbPeiKuan.strDesc" theme="simple"/></td>
			</tr>
			<tr>	
				<td colspan="2"><input type="button" value="提交" class="inputbutton" onclick="ajaxForm('addPeiKuanLog.action','addform','div3')"/></td>
			</tr>
			
		</table>
	</form>
</ww:if>
<ww:else>
	<div class="text2">没有查询到该保单，请查看保单号输入是否正确！</div>
</ww:else>	




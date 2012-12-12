<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<br/>
<div>	
<div >
	<span class="text1">
		<ww:property value="tbMonthPayInfo.tbPartment.company.strPartmentName"/>--
		<ww:property value="tbMonthPayInfo.tbPartment.strPartmentName"/>
	</span>
	<br/>
	<br/>
	<span class="text9">应付清单列表(<ww:property value="tbMonthPayInfo.strYear"/>年<ww:property value="tbMonthPayInfo.strMonth"/>月)</span>
</div>
<br/>
	<table border="1" cellpadding="0"  cellspacing="0" width="90%" class="table">
					<tr height=25 class="text2">
						<td width="12%">保单号</td>
						<td width="10%">申请时间</td>
						<td width="10%">种类</td>
						<td width="6%">内宾</td>
						<td width="6%">外宾</td>
						<td width="10%">路线</td>
						<td width="10%">起始时间</td>
						<td width="10%">结束时间</td>
						<td width="10%">费用</td>
					</tr>
					<ww:iterator value="tbMonthPayInfo.yingFuListForMonthPay"  status="index">
					<tr style="text-align:center;">
						<td>
							<a href="getBillInfoToPrint.action?tbBill.id=<ww:property value="tbBill.id"/>" target="_blank"><ww:property value="tbBill.strBillNumber"/></a>
						<td><ww:date name="tbBill.dteApplyDate" format="yy-MM-dd"/></td>
						<td>
							<ww:if test="tbBill.intKindId==1">责任险</ww:if>
							<ww:else>意外险</ww:else>
						</td>
						<td><ww:property value="tbBill.intChinaTravelerNumber"/></td>
						<td><ww:property value="tbBill.intOtherTravelerNumber"/></td>
						<td><ww:property value="tbBill.strTravelRold"/></td>
						<td><ww:date name="tbBill.dteStartTime" format="yyyy-MM-dd"/></td>
						<td><ww:date name="tbBill.dteEndTime" format="yyyy-MM-dd"/></td>
						<td><ww:property value="tbBill.dbeAllFee"/></td>
					</tr>
					</ww:iterator>
					<ww:iterator value="tbMonthPayInfo.qianFeeListForMonthPay"  status="index1">
					<tr>
						<td><ww:property value="tbBill.id" /></td>
						<td><ww:date name="tbBill.dteApplyDate" format="yy-MM-dd"/></td>
						<td>
							<ww:if test="tbBill.intKindId==1">责任险</ww:if>
							<ww:else>意外险</ww:else>
						</td>
						<td><ww:property value="tbBill.intChinaTravelerNumber"/></td>
						<td><ww:property value="tbBill.intOtherTravelerNumber"/></td>
						<td><ww:property value="tbBill.dbeAllFee"/></td>
					</tr>
					</ww:iterator>
				</table>
		<br/>
		<table border="0" cellpadding="0"  cellspacing="0" width="90%" class="table" style="text-align: left;">
			<tr>
				<td width="20%" style="font-weight: bold;">保单数目：</td>
				<td width="30%"><ww:property value="tbMonthPayInfo.yingFuListForMonthPay.size()"/></td>
				<td width="20%" style="font-weight: bold;">应交费用：</td>
				<td width="30%"><ww:property value="tbMonthPayInfo.dbeNeedToPay"/>元</td>
				
			</tr>
		</table>

	<br/>
	<br/>
	<table border="0" cellpadding="0"  cellspacing="0" width="90%">
		<tr>
			<td align="left">
				<ww:if test="tbMonthPayInfo.intIsPay==0">
					<input type="button" value="收费" class="inputbutton" onclick="location.href='toPayMonthFee.action?tbMonthPayInfo.id=<ww:property value="id"/>'">
				</ww:if>
				<ww:else>
					<input type="button" value="已收费" class="inputbutton" onclick="alert('已收费!')">
				</ww:else>
			</td>
			<td align="left"><input type="button" value="关闭" class="inputbutton" onclick="window.close()" /></td>
		</tr>
	</table>
</div>

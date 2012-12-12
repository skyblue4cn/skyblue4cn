<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<br/>
<div>	
<div class="text1">
	<ww:property value="tbMonthPayInfo.strYear"/>年<ww:property value="tbMonthPayInfo.strMonth"/>月应付清单列表
	<ww:if test="tbMonthPayInfo.intIsPay==1">
		(已支付)
	</ww:if>
	<ww:else>
		(未支付)
	</ww:else>
</div>
<br/>
		<table border="1" cellpadding="0"  cellspacing="0" width="90%" class="table">
		<tr height=25 >
			<td width="20%">保单号</td>
			<td width="8%">申请人</td>
			<td width="10%">申请时间</td>
			<td width="8%">种类</td>
			<td width="12%">路线</td>
			<td width="10%">起始时间</td>
			<td width="10%">终止时间</td>
			<td width="6%">内宾</td>
			<td width="6%">外宾</td>
			<td width="10%">费用</td>
		</tr>
		<ww:iterator value="tbMonthPayInfo.yingFuListForMonthPay" status="index">
		<tr>
		</tr>
		<tr>
			<td>
				<a href="c_getBillInfoToPrint.action?limitId=<ww:property value="tbBill.id"/>&limitKey=<ww:property value="@cn.insurance.action.SupportAction@getEencodeIdKey('chakan',tbBill.id)"/>&limitType=chakan" target="_blank"><ww:property value="tbBill.strBillNumber" /></a>
			</td>
			<td><ww:property value="tbBill.strSignatoryName"/></td>
			
			<td><ww:date name="tbBill.dteApplyDate" format="yy-MM-dd"/></td>
			<td>
				<ww:if test="tbBill.intKindId==1">责任险</ww:if>
				<ww:else>意外险</ww:else>
			</td>
			<td><ww:property value="tbBill.strTravelRold"/></td>
			<td><ww:date name="tbBill.dteStartTime" format="yyyy-MM-dd"/></td>
			<td><ww:date name="tbBill.dteEndTime" format="yyyy-MM-dd"/></td>
			<td><ww:property value="tbBill.intChinaTravelerNumber"/></td>
			<td><ww:property value="tbBill.intOtherTravelerNumber"/></td>
			
			<td><ww:property value="tbBill.dbeAllFee"/></td>
		
		</tr>
		</ww:iterator>
		<ww:iterator value="tbMonthPayInfo.qianFeiListForMonthPay" status="index">
				<tr>
					<td>
						<ww:if test="intPayMonthFeeId == tbMonthPayInfo.id">
							<ww:checkbox name="ids" fieldValue="%{tbBill.id}" value="1" theme="simple"/>
						</ww:if>
						<ww:else>
							<ww:checkbox name="ids" fieldValue="%{tbBill.id}" value="0" theme="simple"/>
						</ww:else>
					</td>
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
	<table border="0" cellpadding="0"  cellspacing="0" width="90%" style="text-align: center;">
		<tr>
			<td><input type="button" value="关闭本页" onclick="window.close()"/></td>
		</tr>
	</table>
</div>

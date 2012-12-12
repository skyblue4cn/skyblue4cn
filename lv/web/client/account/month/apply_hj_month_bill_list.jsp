<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<div style="height:400px;overflow: auto;">	
<br/>
<div class="text1">清单结算</div>
<br/>
<div class="text2">本月的应付清单</div>
<form action="applyHjMonthByUser.action.action" method="post">
	<ww:hidden name="tbMonthPayInfo.id" value="%{tbMonthPayInfo.id}"></ww:hidden>
	<table border="1" cellpadding="0"  cellspacing="0" width="90%" class="table">
		<tr height=25 >
			<td width="14%">选择</td>
			<td width="14%">保单号</td>
			<td width="16%">申请时间</td>
			<td width="14%">种类</td>
			<td width="14%">内宾</td>
			<td width="14%">外宾</td>
			<td width="14%">费用</td>
		</tr>
		<ww:iterator value="tbMonthPayInfo.yingFuListForMonthPay" status="index">
		<tr>
			<td width="14%">
				<ww:checkbox name="ids" fieldValue="%{tbBill.id}" value="1" theme="simple"/>
			</td>
			<td width="14%"><ww:property value="tbBill.id" /></td>
			<td width="16%"><ww:date name="tbBill.dteApplyDate" format="yy-MM-dd"/></td>
			<td width="14%">
				<ww:if test="tbBill.intKindId==1">责任险</ww:if>
				<ww:else>意外险</ww:else>
			</td>
			<td width="14%"><ww:property value="tbBill.intChinaTravelerNumber"/></td>
			<td width="14%"><ww:property value="tbBill.intOtherTravelerNumber"/></td>
			<td width="14%"><ww:property value="tbBill.dbeAllFee"/></td>
		</tr>
		</ww:iterator>
		<ww:if test="tbMonthPayInfo.yingFuListForMonthPay == null || tbMonthPayInfo.yingFuListForMonthPay.size()==0">
				<tr>
					<td colspan="7" align="center">没有应付清单</td>
				</tr>
		</ww:if>
	</table>
	<br/>
	<div class="text2">本月的缓付清单</div>
	<table border="1" cellpadding="0"  cellspacing="0" width="90%" class="table">
		<ww:iterator value="tbMonthPayInfo.huanFuListForMonthPay" status="index">
			<tr>
				<td width="14%">
					<ww:checkbox name="ids" fieldValue="%{tbBill.id}" value="0" theme="simple"/>
				</td>
				<td width="14%"><ww:property value="tbBill.id" /></td>
				<td width="16%"><ww:date name="tbBill.dteApplyDate" format="yy-MM-dd"/></td>
				<td width="14%">
					<ww:if test="tbBill.intKindId==1">责任险</ww:if>
					<ww:else>意外险</ww:else>
				</td>
				<td width="14%"><ww:property value="tbBill.intChinaTravelerNumber"/></td>
				<td width="14%"><ww:property value="tbBill.intOtherTravelerNumber"/></td>
				<td width="14%"><ww:property value="tbBill.dbeAllFee"/></td>
			</tr>
		</ww:iterator>
		<ww:if test="tbMonthPayInfo.huanFuListForMonthPay==null || tbMonthPayInfo.huanFuListForMonthPay.size()==0">
			<tr>
				<td colspan="7" align="center">没有缓付清单</td>
			</tr>
		</ww:if>
	</table>
	<br/>
		<div class="text2">以前的欠费清单</div>
	<table border="1" cellpadding="0"  cellspacing="0" width="90%" class="table">
			<ww:iterator value="tbMonthPayInfo.qianFeiListForMonthPay" status="index">
				<tr>
					<td width="14%">
						<ww:if test="intPayMonthFeeId == tbMonthPayInfo.id">
							<ww:checkbox name="ids" fieldValue="%{tbBill.id}" value="1" theme="simple"/>
						</ww:if>
						<ww:else>
							<ww:checkbox name="ids" fieldValue="%{tbBill.id}" value="0" theme="simple"/>
						</ww:else>
					</td>
					<td width="14%"><ww:property value="tbBill.id" /></td>
					<td width="16%"><ww:date name="tbBill.dteApplyDate" format="yy-MM-dd"/></td>
					<td width="14%">
						<ww:if test="tbBill.intKindId==1">责任险</ww:if>
						<ww:else>意外险</ww:else>
					</td>
					<td width="14%"><ww:property value="tbBill.intChinaTravelerNumber"/></td>
					<td width="14%"><ww:property value="tbBill.intOtherTravelerNumber"/></td>
					<td width="14%"><ww:property value="tbBill.dbeAllFee"/></td>
				</tr>
			</ww:iterator>
			<ww:if test="tbMonthPayInfo.qianFeiListForMonthPay==null || tbMonthPayInfo.qianFeiListForMonthPay.size()==0">
				<tr>
					<td colspan="7" align="center">没有欠费签单</td>
				</tr>
			</ww:if>
		</table>
		<br/>	
	<div>
		<table border="0" cellpadding="0"  cellspacing="0" width="90%" style="text-align: left;color:red;">
			<tr>
				<td>
					<li>如需修改，请勾选要交费的保单，然后点击提交！</li>
				</td>
			</tr>
		</table>
		<br/>
		<table border="0" cellpadding="0"  cellspacing="0" width="90%" style="text-align: left;">
			<tr>
				<td width="50%"><input type="button" value="提交" class="inputbutton" onclick="if(window.confirm('确认提交吗?'))this.form.submit()" /></td>
				<td><input type="button" value="取消" onclick="window.close()" class="inputbutton"/></td>
			</tr>
		</table>
	</div>
	</form>
	<br/>
	<br/>
	<br/>
</div>


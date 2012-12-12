<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<script type="text/javascript">
	function shFei(){
		var opt1 = document.getElementById('pt1') ;
		var opt2 = document.getElementById('pt2') ;
		var osh = document.getElementById('sh') ;
		if(opt1.checked==false && opt2.checked==true){
			opt2.checked=false ;
		}
	}
</script>

			<br/>
	<div >
		<span class="text1">
			<ww:property value="tbMonthPayInfo.tbPartment.company.strPartmentName"/>--
			<ww:property value="tbMonthPayInfo.tbPartment.strPartmentName"/>
		</span>
		<br/>
		<br/>
		<span class="text9">付费清单(<ww:property value="tbMonthPayInfo.strYear"/>年<ww:property value="tbMonthPayInfo.strMonth"/>月)</span>
	</div>
			<br/>
			<table border="0" cellpadding="0"  cellspacing="0" width="90%" class="table" style="text-align: left;">
				<tr>
					<td width="20%">旅行社名称:</td>
					<td width="30%"><ww:property value="tbMonthPayInfo.tbPartment.company.strPartmentName"/></td>

					<td width="20%" >部门名称:</td>
					<td width="30%"><ww:property value="tbMonthPayInfo.tbPartment.strPartmentName"/></td>
				</tr>
				<tr>
					<td >开始时间:</td>
					<td><ww:date name="tbMonthPayInfo.dteStartTime" format="yyyy-MM-dd"/></td>
					<td >结束时间:</td>
					<td><ww:date name="tbMonthPayInfo.dteEndTime" format="yyyy-MM-dd"/></td>
				</tr>
			</table>

			<br/>
		<div id="billQd"  width="90%">	
			<br/>
			<div class="text1">应付清单</div>
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
				</table>
				<br/>
		</div>
		<br/>
			<table border="0" cellpadding="0"  cellspacing="0" width="90%" class="table" style="text-align: left;">
				<tr>
					<td width="20%">保单数目</td>
					<td width="30%">
						<ww:property value="tbMonthPayInfo.yingFuListForMonthPay.size()"/>
					</td>
					<td width="20%">应交费用：</td>
					<td width="30%"><ww:property value="tbMonthPayInfo.dbeNeedToPay"/>元</td>
				</tr>
			</table>
		<br/>
		<ww:include value="/message.jsp"></ww:include>
	<ww:form action="shouMonthFee.action" method="post" >
		<div>
			<table border="1" cellpadding="0"  cellspacing="0" width="90%" class="table">
					<tr>
						<td style="font-weight:bold;">交费方式:</td>
						<td><ww:radio name="tbMonthPayInfo.payType" value="%{tbMonthPayInfo.payType}" list="#{1:'已从旅行社收费',2:'从账户存款中扣除'}" theme="simple" /></td>
					</tr>
					<tr>
						<td style="font-weight:bold;">经办人:</td>
						<td><ww:textfield name="tbMonthPayInfo.tbMonthPayOutLog.strUserName" value="%{#session['tbUser'].strUserName}" readonly="true" theme="simple" size="30"/></td>
					</tr>
					<tr>
						<td width="20%" style="font-weight:bold;">交费时间:</td>
						<td><input type="text" name="tbMonthPayInfo.tbMonthPayOutLog.dtePayDate" value="<ww:date name="new java.util.Date()" format="yyyy-MM-dd"/>" onclick="setday(this)" size="30"/></td>
					</tr>
					<tr>
						<td style="font-weight:bold;">备注:</td>
						<td><ww:textarea name="tbMonthPayInfo.tbMonthPayOutLog.strDesc" theme="simple" cssStyle="width:220px;"/></td>
					</tr>
					<tr height="30">
						<td style="font-weight:bold;">重要说明:</td>
						<td style="color:blue;">请确认信息后点击提交！</td>
					</tr>
				</table>
				<ww:hidden name="tbMonthPayInfo.id" value="%{tbMonthPayInfo.id}"/>
				<ww:hidden name="tbMonthPayInfo.tbMonthPayOutLog.intMonthPayId" value="%{tbMonthPayInfo.id}"/>
				<ww:hidden name="tbMonthPayInfo.tbMonthPayOutLog.intUserId" value="%{#session['tbUser'].id}"/>
				<br/>
				<br/>
				<table border="0" width="90%">
					<tr>
						<td align="left">
							<ww:if test="tbMonthPayInfo.intIsPay==0">
								<input type="button" class="inputbutton" value="收费" onclick="if(window.confirm('确认提交吗？'))this.form.submit();"/>
							</ww:if>
							<ww:else>
								<input type="button" class="inputbutton" value="已收费" onclick="alert('已收费！')"/>
							</ww:else>
						</td>
						<td align="left"><input type="button" class="inputbutton" value="取消" onclick="window.close();" /></td>
				</table>
		</div>
	</ww:form>
<br/>
<br/>
<br/>
<br/>

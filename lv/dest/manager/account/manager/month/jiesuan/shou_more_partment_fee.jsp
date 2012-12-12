<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>

<ww:form action="shouMonthFeeByIdList.action" method="post" name="form1">
	<br/>
		<div >
		<span class="text1">
			<ww:property value="tbMonthPayInfo.tbPartment.company.strPartmentName"/>--
			部门月费交费
		</span>
		<br/>
	
		<br/>
		<span class="text9">付费清单(<ww:property value="tbMonthPayInfo.strYear"/>年<ww:property value="tbMonthPayInfo.strMonth"/>月)</span>
	</div>
			<br/>
			<table border="0" cellpadding="0"  cellspacing="0" width="90%" class="table">
				<tr>
					<td width="20%">旅行社名称</td>
					<td colspan="3"><ww:property value="tbMonthPayInfo.tbPartment.company.strPartmentName"/></td>
				</tr>
				<tr>
					<td >开始时间:</td>
					<td><ww:date name="tbMonthPayInfo.dteStartTime" format="yyyy-MM-dd"/></td>
					<td >结束时间</td>
					<td><ww:date name="tbMonthPayInfo.dteEndTime" format="yyyy-MM-dd"/></td>
				</tr>
			</table>
			<br/>	
		<ww:iterator value="monthPayInfoList" status="status">
		<ww:hidden name="ids" value="%{id}"/>
	
			<table border="0" cellpadding="0"  cellspacing="0" width="90%" class="table">
				<tr>
					<td width="20%">部门名称:</td>
					<td colspan="3"><ww:property value="tbMonthPayInfo.tbPartment.strPartmentName"/></td>
				</tr>
				<tr>
					<td width="20%">保单数目:</td>
					<td width="30%"><ww:property value="yingFuListForMonthPay.size()"/></td>
					<td width="20%">应交费用:</td>
					<td width="30%"><ww:property value="dbeNeedToPay"/></td>
				</tr>
				<tr>
					<td colspan="2"><a href="javascript:showDiv('billQd<ww:property value="#status.index"/>')">查看清单</a></td>
					<td colspan="2"><a href="javascript:hiddenDiv('billQd<ww:property value="#status.index"/>')">隐藏清单</a></td>
				</tr>
				<tr style="display:none;" id="billQd<ww:property value="#status.index"/>">
					<td colspan="4" align="center">
						<br/>
						<div class="text1"><ww:property value="tbPartment.strPartmentName"/>应付清单</div>
						<br/>
						<table border="1" cellpadding="0"  cellspacing="0" width="100%" class="table">
							<tr height=25 class="text2">
								<td width="6%">序号</td>
								<td width="12%">保单号</td>
								<td width="10%">申请时间</td>
								<td width="10%">种类</td>
								<td width="6%">内宾</td>
								<td width="6%">外宾</td>
								<td width="10%">路线</td>
								<td width="10%">团队号</td>
								<td width="10%">起始时间</td>
								<td width="10%">结束时间</td>
								<td width="10%">费用</td>
							</tr>
							<ww:iterator value="yingFuListForMonthPay"  status="index">
							<tr>
								<td><ww:property value="#index.index+1"/></td>
								<td>
									<ww:property value="tbBill.strBillNumber.substring(0,16)" /><br/><ww:property value="tbBill.strBillNumber.substring(16)" />
								</td>
								<td><ww:date name="tbBill.dteApplyDate" format="yy-MM-dd"/></td>
								<td>
									<ww:if test="tbBill.intKindId==1">责任险</ww:if>
									<ww:else>意外险</ww:else>
								</td>
								<td><ww:property value="tbBill.intChinaTravelerNumber"/></td>
								<td><ww:property value="tbBill.intOtherTravelerNumber"/></td>
								<td><ww:property value="tbBill.strTravelRold"/></td>
								<td><ww:property value="tbBill.strTeamNumber"/></td>
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
									<ww:property value="tbBill.strKindName"/>
								</td>
								<td><ww:property value="tbBill.intChinaTravelerNumber"/></td>
								<td><ww:property value="tbBill.intOtherTravelerNumber"/></td>
								<td><ww:property value="tbBill.dbeAllFee"/></td>
							</tr>
							</ww:iterator>
						</table>
					</td>
				</tr>
			</table>
			<br/>

		</ww:iterator>
			<br/>
		
		<div>
			<table border="1" cellpadding="0"  cellspacing="0" width="90%" class="table">
				<tr>
					<td width="20%">总共应交费用：</td>
					<td width="30%"><ww:property value="allFee"/>元</td>
				</tr>
					<tr>
						<td>交费时间</td>
						<td><input type="text" name="tbMonthPayInfo.tbMonthPayOutLog.dtePayDate" value="<ww:date name="new java.util.Date()" format="yyyy-MM-dd"/>" onclick="setday(this)"/></td>
					</tr>
					<tr>
						<td>备注:</td>
						<td><ww:textfield name="tbMonthPayInfo.tbMonthPayOutLog.strDesc" theme="simple"/></td>
					</tr>
					<tr height="30">
						<td>说明:</td>
						<td style="color:red;">请确认收费后点击提交！</td>
					</tr>
				</table>
				
				<ww:hidden name="tbMonthPayInfo.tbMonthPayOutLog.intUserId" value="%{#session['tbUser'].id}"/>
				<ww:hidden name="tbMonthPayInfo.tbMonthPayOutLog.strUserName" value="%{#session['tbUser'].strUserName}"/>
				<br/>
				<table border="0" width="90%">
					<tr>
						<td align="left"><input type="button" class="inputbutton" value="提交" onclick="if(window.confirm('确认提交吗？'))this.form.submit();"/></td>
						<td align="left"><input type="button" class="inputbutton" value="取消" onclick="window.close();" /></td>
				</table>
		</div>
	</ww:form>


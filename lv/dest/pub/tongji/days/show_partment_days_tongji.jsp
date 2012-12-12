<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<br/>
<br/>
<div class="text1"><ww:property value="partmentMonthStat.tbPartment.strPartmentName"/>统计报表</div>
<div style="line-height:40px;text-align:right;font-size:14px;">
	统计时间：<ww:property value="partmentMonthStat.year"/>年<ww:property value="partmentMonthStat.month"/>月
	&nbsp;&nbsp;&nbsp;&nbsp;
</div>
<table border="1" cellpadding="0" cellspacing="0" width="95%" class="table" style="text-align:center;line-height:30px;" >
	<tr class="text8">
		<td rowspan="3">时间</td>
		<td colspan="3">保单情况</td>
		<td colspan="4">付费情况</td>
		<td colspan="2">赔款情况</td>
	</tr>
	<tr class="text8">
		<td rowspan="2">投保申请</td>
		<td rowspan="2" >核得通过</td>
		<td rowspan="2">未通过</td>
		<td colspan="2">已付费</td>
		<td colspan="2">未付费</td>
		<td rowspan="2">赔款份数</td>
		<td rowspan="2">赔款金额</td>
	</tr>
	<tr class="text8">
		<td>份数</td>
		<td>金额</td>
		<td>份数</td>
		<td>金额</td>
	</tr>
	<ww:iterator value="partmentMonthStat.pdsList" >
	<tr>
		<td><ww:property value="day"/>日</td>
		<td><ww:property value="allBillCount"/></td>
		<td><ww:property value="hasSureBillList.size()"/></td>
		<td><ww:property value="returnBillList.size()"/></td>
		<td><ww:property value="hasPayBillCount"/></td>
		<td><ww:property value="hasPayBillFee"/></td>
		<td><ww:property value="notPayBillCount"/></td>
		<td><ww:property value="notPayBillFee"/></td>
		<td><ww:property value="peiKuaiBillCount"/></td>
		<td><ww:property value="peiKuanNumber"/></td>
	</tr>
	</ww:iterator>
	<tr>
		<td>总计</td>
		<td><ww:property value="partmentMonthStat.allBillCount"/></td>
		<td><ww:property value="partmentMonthStat.hasSureBillList.size()"/></td>
		<td><ww:property value="partmentMonthStat.returnBillList.size()"/></td>
		<td><ww:property value="partmentMonthStat.hasPayBillCount"/></td>
		<td><ww:property value="partmentMonthStat.hasPayBillFee"/></td>
		<td><ww:property value="partmentMonthStat.notPayBillCount"/></td>
		<td><ww:property value="partmentMonthStat.notPayBillFee"/></td>
		<td><ww:property value="partmentMonthStat.peiKuaiBillCount"/></td>
		<td><ww:property value="partmentMonthStat.peiKuanNumber"/></td>
	</tr>
</table>
<br/>
<table border="0" cellpadding="0" cellspacing="0" width="30%" align="center">
	<tr>
		<td><input type="button" value=" 打 印 "/></td>
		<td><input type="button" value=" 下 载 "/></td>
		<td><input type="button" value=" 关 闭 "/></td>
	</tr>
</table>

<br>
<br/>
<br/>
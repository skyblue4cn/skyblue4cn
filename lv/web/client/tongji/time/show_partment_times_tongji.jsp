<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<br/>
<br/>
<div class="text1"><ww:property value="partmentTimeStat.tbPartment.strPartmentName"/>统计报表</div>
<div style="line-height:40px;text-align:right;font-size:14px;">
	统计时间：<ww:property value="partmentTimeStat.startTime"/>至<ww:property value="partmentTimeStat.endTime"/>
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
	<tr>
		<td><ww:property value="partmentTimeStat.startTime"/>/<ww:property value="partmentTimeStat.endTime"/></td>
		<td><ww:property value="partmentTimeStat.allBillCount"/></td>
		<td><ww:property value="partmentTimeStat.hasSureBillList.size()"/></td>
		<td><ww:property value="partmentTimeStat.returnBillList.size()"/></td>
		<td><ww:property value="partmentTimeStat.hasPayBillCount"/></td>
		<td><ww:property value="partmentTimeStat.hasPayBillFee"/></td>
		<td><ww:property value="partmentTimeStat.notPayBillCount"/></td>
		<td><ww:property value="partmentTimeStat.notPayBillFee"/></td>
		<td><ww:property value="partmentTimeStat.peiKuaiBillCount"/></td>
		<td><ww:property value="partmentTimeStat.peiKuanNumber"/></td>
	</tr>
	<tr>
		<td>总计</td>
		<td><ww:property value="partmentTimeStat.allBillCount"/></td>
		<td><ww:property value="partmentTimeStat.hasSureBillList.size()"/></td>
		<td><ww:property value="partmentTimeStat.returnBillList.size()"/></td>
		<td><ww:property value="partmentTimeStat.hasPayBillCount"/></td>
		<td><ww:property value="partmentTimeStat.hasPayBillFee"/></td>
		<td><ww:property value="partmentTimeStat.notPayBillCount"/></td>
		<td><ww:property value="partmentTimeStat.notPayBillFee"/></td>
		<td><ww:property value="partmentTimeStat.peiKuaiBillCount"/></td>
		<td><ww:property value="partmentTimeStat.peiKuanNumber"/></td>
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
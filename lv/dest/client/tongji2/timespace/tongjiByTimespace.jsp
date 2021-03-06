<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>


<br>
<div style="text-align:center;font:16px;font-weight:bold;"><ww:property value="#session.tbUser.tbPartment.strPartmentName"/>数据统计</div>
<br>
<table border="0" cellpadding="0" cellspacing="0" width="95%">
	<tr>
		<td align="right">统计时间: <ww:date name="new java.util.Date()" format="yyyy年MM月dd日"/></td>
	</tr>
</table>

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
		<td>
			<ww:property value="partmentTimeStat.startTime"/>至<ww:property value="partmentTimeStat.endTime"/>
		</td>
		<td><ww:property value="partmentTimeStat.allBillCount"/></td>
		<td><ww:property value="partmentTimeStat.hasSureBillCount"/></td>
		<td><ww:property value="partmentTimeStat.returnBillCount"/></td>
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
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<br>
<div style="text-align:center;font:16px;font-weight:bold;"><ww:property value="ads.tbPartment.strPartmentName"/>&nbsp;<ww:property value="ads.year"/>年<ww:property value="ads.month"/>月数据统计</div>
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
	<ww:iterator value="ads.partmentDayStatList" >
	<tr>
		<td>
			<ww:property value="year"/>年<ww:property value="month"/>月<ww:property value="day"/>日
		</td>
		<td><ww:property value="allBillCount"/></td>
		<td><ww:property value="hasSureBillCount"/></td>
		<td><ww:property value="returnBillCount"/></td>
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
		<td><ww:property value="ads.partmentMonthStat.allBillCount"/></td>
		<td><ww:property value="ads.partmentMonthStat.hasSureBillCount"/></td>
		<td><ww:property value="ads.partmentMonthStat.returnBillCount"/></td>
		<td><ww:property value="ads.partmentMonthStat.hasPayBillCount"/></td>
		<td><ww:property value="ads.partmentMonthStat.hasPayBillFee"/></td>
		<td><ww:property value="ads.partmentMonthStat.notPayBillCount"/></td>
		<td><ww:property value="ads.partmentMonthStat.notPayBillFee"/></td>
		<td><ww:property value="ads.partmentMonthStat.peiKuaiBillCount"/></td>
		<td><ww:property value="ads.partmentMonthStat.peiKuanNumber"/></td>
	</tr>
</table>
<br/>
<!--<table border="0" cellpadding="0" cellspacing="0" width="30%" align="center">
	<tr>
		<td><input type="button" value=" 打 印 "/></td>
		<td><input type="button" value=" 下 载 "/></td>
		<td><input type="button" value=" 关 闭 "/></td>
	</tr>
</table>

--><br>
<br/>
<br/>
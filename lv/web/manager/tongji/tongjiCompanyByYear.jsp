<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="cn.insurance.model.tongji2.PartmentYearStat"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<br>
<div style="text-align:center;font:16px;font-weight:bold;"><ww:property value="company.strPartmentName"/>&nbsp;<ww:property value="year"/>年数据统计</div>
<a href="file:///c://按年导出的该旅行社的数据.xls">下载Excel版本(右击>>目标另存为)</a>
<br/>
<table border="0" cellpadding="0" cellspacing="0" width="95%">
	<tr>
		<td align="right">统计时间: <ww:date name="new java.util.Date()" format="yyyy年MM月dd日"/></td>
	</tr>
</table>
<table border="1" cellpadding="0" cellspacing="0" width="95%" class="table" style="text-align:center;line-height:30px;" >
	<tr class="text8">
		<td rowspan="3" width="20%">旅行社</td>
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
	<ww:iterator value="partmentYearStatList" >
	<tr>
		<td>
			<a href="adminTongJiByMonth.action?partmentId=<ww:property value="tbPartment.id"/>&year=<ww:property value="year"/>">
				<ww:property value="tbPartment.strPartmentName"/>
			</a>
		</td>
		<td>
			<ww:property value="year"/>年
		</td>
		<td><ww:property value="allBillCount"/>
		</td>
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
	<!--START 修改于2010-6-7 -->
	<%PartmentYearStat ps = (PartmentYearStat)request.getAttribute("ps");%>
	<tr>
		<td>总计</td>
		<td><ww:property value="year"/>年</td>
		<td><%=ps.getAllBillCount()%></td>
		<td><%=ps.getHasSureBillCount()%></td>
		<td><%=ps.getReturnBillCount()%></td>
		<td><%=ps.getHasPayBillCount()%></td>
		<td><%=ps.getHasPayBillFee()%></td>
		<td><%=ps.getNotPayBillCount()%></td>
		<td><%=ps.getNotPayBillFee()%></td>
		<td><%=ps.getPeiKuaiBillCount()%></td>
		<td><%=ps.getPeiKuanNumber()%></td>
	</tr>
	<!-- END -->
</table>
<br/>


<br>
<br/>
<br/>
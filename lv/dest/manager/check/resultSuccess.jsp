<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="ww" uri="webwork"%>
	<table border="0" cellpadding="0" cellspacing="0" width="80%" class="table">
		<tr>
			<td width="10%">验证结果:</td>
			<td><ww:property value="checkResult" /></td>
			<td width="10%"><a href="getBillInfoToPrint.action?id=<ww:property value="tbBill.id"/>" target="blank">查看保单</a></td>
		</tr>
	</table>


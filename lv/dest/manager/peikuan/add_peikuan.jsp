<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<ww:include value="/head.jsp"/>

	    <div class="tabcont" id="div1">
			<div align="center">
				<div class="text1" style="width:90%;">添加赔款记录</div>
					<br/>
				<div style="color:red;">
					<ww:actionerror />
				</div>
				<form action="addPeiKuanLog.action" method="post" >
					<table border="0" cellpadding="0" cellspacing="0" width="100%" class="table">
						<tr>
							<td>保单号:</td>
							<td><ww:textfield name="tbBill.strBillNumber" theme="simple" size="50"/></td>
						</tr>
						<tr>
							<td width="20%">赔款金额：</td>
							<td><ww:textfield name="tbPeiKuan.dbePeiKuanFee" theme="simple" size="50"/></td>
						</tr>
						<tr>	
							<td>备注：</td>
							<td><ww:textarea name="tbPeiKuan.strDesc" theme="simple" cols="41" rows="3" cssStyle="overflow:hidden;"/></td>
						</tr>
					</table>
					<br>
					<table border="0" cellpadding="0" cellspacing="0" width="60%">
					<tr>	
						<td><input type="submit" value="提交" class="inputbutton" /></td>
						<td><input type="reset" value="重置" class="inputbutton" /></td>
					</tr>
					</table>
				</form>
				<br/>
			</div>
	  	</div>

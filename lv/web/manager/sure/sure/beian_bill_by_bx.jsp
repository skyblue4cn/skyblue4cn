<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>

<div  style="text-align:center;">
<br/>
	<form action="beiAnBillByBx.action" method="post">
		<ww:hidden name="tbBill.id" value="%{tbBill.id}"/>
		<ww:hidden name="tbBill.tbBillSureInfo.intSureUserId" value="%{#session['tbUser'].id}"/>
		<ww:hidden name="tbBill.intBillStateId" value="%{tbBill.intBillStateId}"></ww:hidden>
		<ww:hidden name="tbBill.tbBillSureInfo.strSureUserName" value="%{#session['tbUser'].strUserName}"  />
		<input type="hidden" name="tbBill.tbBillSureInfo.dteSureTime"  value="<ww:date name="new java.util.Date()" format="yyyy-MM-dd"/>" />
		<div class="text1">请选择备案原因:</div>
		<br/>
		<table border="0" width="80%" class="table">
			<tr>
				<td colspan="2">
					<input type="radio" name="tbBill.intBeiAnReason" value="1">散拼团人员名单不确定
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="radio" name="tbBill.intBeiAnReason" value="2">部分游客名单不确定
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="radio" name="tbBill.intBeiAnReason" value="3">无总社负责人签章
				</td>
			</tr>
		</table>
		<br/>
		<table border="0" width="80%">
			<tr>
				<td><input type="button" value="提交" onclick="if(window.confirm('确认提交吗？')) this.form.submit();" class="inputbutton"/></td>
				<td><input type="button" value="取消" onclick="window.close();" class="inputbutton"/></td>
			</tr>
		</table>
	</form>	

</div>

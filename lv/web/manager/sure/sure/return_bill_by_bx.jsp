<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<div  id="lvdbtable" style="width:100%;text-align: center;height: 500px;">
<br/>
	<form action="returnBillByBx.action" method="post">
		<ww:hidden name="tbBill.id" value="%{tbBill.id}"/>
		<ww:hidden name="tbBill.tbBillSureInfo.intSureUserId" value="%{#session['tbUser'].id}"/>
		<ww:hidden name="tbBill.intBillStateId" value="%{tbBill.intBillStateId}"></ww:hidden>
		<ww:hidden name="tbBill.tbBillSureInfo.strSureUserName" value="%{#session['tbUser'].strUserName}"  />
		<input type="hidden" name="tbBill.tbBillSureInfo.dteSureTime"  value="<ww:date name="new java.util.Date()" format="yyyy-MM-dd"/>" />
		<table border="0" width="80%">
			<tr>
				<td class="text1" colspan="2" align="left">请填写退单原因:<font color="red">*</font></td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea rows="4" cols="50" name="tbBill.strReturnReason"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					&nbsp;
					<br/>
				<br/>
				</td>
			</tr>
			<tr>
				<td><input type="button" value="提交" onclick="if(window.confirm('确认提交吗？')) this.form.submit();" class="inputbutton"/></td>
				<td><input type="button" value="取消" onclick="window.close();" class="inputbutton"/></td>
			</tr>
		</table>
	</form>	

</div>

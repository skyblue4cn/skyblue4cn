<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>

<ww:include value="/head.jsp"></ww:include>
<div  id="lvdbtable" style="width:100%;text-align: center;height:400;">
	<div class="bdtop" width="100%" align="center">更新游客信息</div>
	<div style="color:green;font-size: 14px;font-weight: bold;"><ww:actionmessage/></div>
	<ww:form action="updateTraveler.action" method="post">
			<table border="0" cellpadding="0" cellspacing="0" width="80%">
				<tr><td>姓名:</td><td><ww:textfield name="tbTravelerInfo.strTravelerName" theme="simple"/></td></tr>
				<tr><td>国籍:</td><td><ww:textfield name="tbTravelerInfo.strCountry" theme="simple"/></td></tr>
				<tr><td>身份证号或护照号:</td><td><ww:textfield name="tbTravelerInfo.strIndentyNumber" theme="simple"/></td></tr>	
			</table>
			<ww:hidden name="tbTravelerInfo.intBillId" value="%{tbTravelerInfo.intBillId}"/>
			<ww:hidden name="tbTravelerInfo.id" value="%{tbTravelerInfo.id}"/>
			<br/>
			<table border="0" width="80%">
			<tr>
				<td align="center"><input type="submit" value="确认提交" align="center"/></td>
				<td align="center"><input type="button" onclick="window.close();" value="取消"></td>
			</tr>	
			</table>
			</ww:form>
</div>

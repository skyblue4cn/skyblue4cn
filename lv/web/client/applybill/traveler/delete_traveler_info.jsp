<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>

<ww:include value="/head.jsp"></ww:include>
<div  id="lvdbtable" style="width:100%;text-align: center;height:400;">
	<div class="bdtop" width="100%" align="center">删除旅客信息</div>
	<br/>
	<div class="text1">旅客信息</div>
	<br>
	<ww:form action="deleteTraveler.action" method="post">
			<table border="0" cellpadding="0" cellspacing="0" width="80%" class="table">
				<tr><td>姓名:</td><td><ww:property value="tbTravelerInfo.strTravelerName"/></td></tr>
				<tr><td>国籍:</td><td><ww:property value="tbTravelerInfo.strCountry"/></td></tr>
				<tr><td>身份证号或护照号:</td><td><ww:property value="tbTravelerInfo.strIndentyNumber"/></td></tr>	
			</table>
			<ww:hidden name="tbTravelerInfo.id" value="%{tbTravelerInfo.id}"/>
			<br/>
			<table border="0" width="80%">
			<tr>
				<td align="center"><input type="button" class="inputbutton" value="删除" align="center" onclick="if(window.confirm('确认删除吗?'))this.form.submit();"/></td>
				<td align="center"><input type="button" class="inputbutton" onclick="window.close();" value="取消"></td>
			</tr>	
			</table>
			</ww:form>
</div>

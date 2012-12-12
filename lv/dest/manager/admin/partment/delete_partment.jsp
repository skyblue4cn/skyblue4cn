<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<div  id="lvdbtable" style="width:100%;text-align: center;height: 500px;">
	<div class="bdtop" width="100%" align="center">删除部门信息</div>
	<ww:actionmessage/>
	<ww:form action="deletePartmentById.action" method="post" >
			<table border="0" cellpadding="0" cellspacing="0" width="80%" style="line-height:30px;">
				<tr>
					<td width="30%">名称</td>
					<td><ww:property value="tbPartment.strPartmentName" /></td>
				</tr>
				<tr>
					<td>说明</td>
					<td style="color:red;">删除旅行社或部门并不是真正的删除数据，只是将数据隐藏起来，删除后将不再显示在管理界面！</td>
				</tr>
				<ww:hidden name="tbPartment.id" value="%{tbPartment.id}"/>
			</table>
			<br/>
			<br/>
			<table width="80%">
				<tr><td width="50%" align="center"><input type="button" class="inputbutton" value="删除" onclick="if(window.confirm('确认删除吗？'))this.form.submit();"/></td>
				<td align="center"><input type="button" value="取消" class="inputbutton" onclick="window.close();"/></td></tr>
			</table>
		</ww:form>	
		<br/>
		<br/>
		<br/>
</div>
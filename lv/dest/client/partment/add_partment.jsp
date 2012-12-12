<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<div  id="lvdbtable" style="width:100%;text-align: center;height: 500px;">
	<div class="bdtop" width="100%" align="center">添加部门</div>
	<ww:include value="/message.jsp"></ww:include>
	<ww:form action="addPartment.action" method="post" >
		<br/>
			<table border="0" cellpadding="0" cellspacing="0" width="80%" class="table">
				<tr>
					<td width="30%">名称</td>
					<td><ww:textfield name="tbPartment.strPartmentName" theme="simple" value="" /></td>
				</tr>
				<ww:textfield name="tbPartment.strPhoneNumber" label="电话" value=""/>
				<ww:textfield name="tbPartment.strFax" label="传真"  value=""/>
				<ww:textfield name="tbPartment.strConnectPeople" label="联系人" value=""/>
				<!--<ww:file name="partmentImg" label="部门盖章图片"/> -->
				<ww:hidden name="tbPartment.intParentId" value="%{tbPartment.intParentId}"></ww:hidden> 
				<ww:hidden name="tbPartment.isShowHistoryBill" value="0"/>
			</table>
			<br/>
			<table width="80%">
				<tr><td width="50%" align="center"><input type="button" class="inputbutton" value="添加" onclick="if(window.confirm('确认添加吗？'))this.form.submit();"/></td>
				<td align="center"><input type="button" value="取消" class="inputbutton" onclick="window.close()"/></td></tr>
			</table>
		</ww:form>	
		<br/>
		<br/>
		<br/>
</div>


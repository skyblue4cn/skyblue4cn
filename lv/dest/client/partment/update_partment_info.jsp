<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<div  id="lvdbtable" style="width:100%;text-align: center;height: 500px;">
	<div class="bdtop" width="100%" align="center">更新部门信息</div>
	<ww:actionmessage/>
	<ww:form action="updatePartmentInfo.action" method="post">
			<table border="0" cellpadding="0" cellspacing="0" width="80%" class="table">
				<tr>
					<td width="30%">名称</td>
					<td><ww:textfield name="tbPartment.strPartmentName" theme="simple" /></td>
				</tr>
				<ww:textfield name="tbPartment.strPhoneNumber" label="电话" />
				<ww:textfield name="tbPartment.strFax" label="传真" />
				<ww:textfield name="tbPartment.strConnectPeople" label="联系人" />
				<!--<ww:file name="partmentImg" label="部门盖章图片"/> -->
				<ww:hidden name="tbPartment.intParentId" value="%{tbPartment.intParentId}"></ww:hidden> 
				<ww:hidden name="tbPartment.id" value="%{tbPartment.id}"/>
				<ww:hidden name="tbPartment.isShowHistoryBill" value="%{tbPartment.isShowHistoryBill}"/>
			</table>
			<br/>
			<table width="80%">
				<tr><td width="50%" align="center"><input type="button" class="inputbutton" value="提交" onclick="if(window.confirm('确认提交吗？'))this.form.submit();"/></td>
				<td align="center"><input type="button" value="取消" class="inputbutton" onclick="window.close();"/></td></tr>
			</table>
		</ww:form>	
		<br/>
		<br/>
		<br/>
</div>
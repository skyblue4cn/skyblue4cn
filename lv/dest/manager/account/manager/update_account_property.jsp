<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<div  id="lvdbtable" style="width:100%;text-align: center;height:450px;">
	<div class="bdtop" width="100%" align="center">修改帐户设置</div>
	<ww:actionmessage/>
	<ww:form action="updateAccountProperty.action" method="post" >
			<table border="0" cellpadding="0" cellspacing="0" width="80%" style="line-height:25px;" class="table">
				<tr>
					<td>当前帐户余额:</td>
					<td><ww:property value="tbAccount.dbeResidual"/>元</td>
				</tr>
				<tr>
					<td width="40%">帐户类型:</td>
					<td>
						<%--
						<ww:if test="tbPartment.intParentId==0">
							<ww:radio name="tbAccount.intPayTypeId" list="#{1:'预付',2:'月结',3:'年费'}" value="%{tbPartment.tbAccount.intPayTypeId}" theme="simple"/>
						</ww:if>
						--%>
							<ww:radio name="tbAccount.intPayTypeId" list="#{1:'预付',2:'月结'}" value="%{tbAccount.intPayTypeId}"theme="simple"/>
				
					</td>
				</tr>

				<tr>
					<td>最多允许欠费金额:</td>
					<td><ww:textfield name="tbAccount.dbeAcceptMaxMoney" value="%{tbAccount.dbeAcceptMaxMoney}" theme="simple"/>元</td>
				</tr>
				<tr>
					<td>最多允许欠费天数：</td>
					<td><ww:textfield name="tbAccount.intAcceptDays" value="%{tbAccount.intAcceptDays}" theme="simple"/>天</td>
				</tr>
				<tr>
					<td>设置帐户为：</td>
					<td><ww:radio name="tbAccount.intAccountState" list="#{1:'可用',0:'不可用'}" value="%{tbAccount.intAccountState}" theme="simple"/></td>
				</tr>
			</table>
			<ww:hidden name="tbAccount.id" value="%{tbAccount.id}"/>
			<br/>
			<table border="0" width="80%">
				<tr>
					<td align="center"><input type="button" value="提交" onclick="if(window.confirm('确认修改帐户设置吗？'))this.form.submit();"/></td>
					<td align="center"><input type="button" value="取消" onclick="window.close();" /></td>
			</table>
	</ww:form>
</div>
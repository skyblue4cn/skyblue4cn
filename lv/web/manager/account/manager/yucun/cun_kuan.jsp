<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<script type="text/javascript">
<!--
	function shoufei(){
		var formObj = document.getElementById('shf') ;
		if(formObj.num_1.value == formObj.num_2.value){
			if(window.confirm('确认提交吗？')){
			formObj.submit();
			}
		}else{
			alert('两次输入存款数不一样。') ;
			return;
		}
	}
//-->
</script>
<div  id="lvdbtable" style="width:100%;text-align: center;height:400px;">
	<div class="bdtop" width="100%" align="center">修改帐户设置</div>
	
	<ww:form action="shouFeeForYuCun.action" method="post" id="shf">
		<table border="0" cellpadding="0" cellspacing="0" width="80%" style="line-height:30px;">
			<ww:if test="tbAccount.intPayTypeId!=1">
			<tr>
				<td style="color:green;font-weight: bold;text-align: left;" colspan="3">
					该帐户目前不是预存类型，是否修改？ &nbsp;&nbsp;
					<a href="toUpdateAccountProperty.action?id=<ww:property value="tbAccount.id"/>">修改</a>
				</td>
			</tr>
			</ww:if>
			<ww:if test="tbAccount.intAccountState == 0">
				<tr>
					<td style="color:green;font-weight: bold;text-align: left;" colspan="3">
						该帐户目前被设置为不可用，是否激活？&nbsp;&nbsp;
						<a href="toUpdateAccountProperty.action?id=<ww:property value="tbAccount.id"/>">激活</a>
					</td>
				</tr>
			</ww:if>
			<tr>
				<td>当前帐户余额:</td>
				<td><ww:property value="tbAccount.dbeResidual"/>元</td>
			</tr>
			<tr>
				<td>存款金额：</td>
				<td><ww:textfield name="tbPayOutInfo.dbePayoutNumber" value="0" theme="simple" id="num_1"/>元</td>
			</tr>
			<tr>
				<td>再次输入存款金额:</td>
				<td><ww:textfield name="dbeReNumber" value="0" theme="simple" id="num_2"/>元</td>
			</tr>
			<tr>
				<td>备注：</td>
				<td><ww:textarea name="tbPayOutInfo.strDesc" cols="17" rows="4" theme="simple" /></td>
			</tr>
		</table>
		<ww:hidden name="tbPayOutInfo.intAccountId" value="%{tbAccount.id}"/>
		<ww:hidden name="tbPayOutInfo.intFromUserId" value="%{#session['tbUser'].id}" />
		<ww:hidden name="tbPayOutInfo.strSaveUserName" value="%{#session['tbUser'].strUserName}"/>
		<br/>
		<table border="0" width="80%">
			<tr>
				<td align="center"><input type="button" value="提交" onclick="shoufei()"/></td>
				<td align="center"><input type="button" value="取消" onclick="window.close();" /></td>
		</table>
	</ww:form>
</div>
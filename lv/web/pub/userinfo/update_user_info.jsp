<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>	
<ww:include value="/head.jsp"></ww:include>
<script type="text/javascript">
<!--
	function checkForm(){
		var formObj = document.all.form1 ;
		if(trim(formObj.userid.value) == ''){
			alert('请填写用户名!') ;
			return ;
		}
		var password = formObj.password.value ;
		var repassword = formObj.repassword.value ;
		if(trim(password)== ''){
			alert('请填写密码！')
			return ;
		}
		if(password!= repassword){
			alert('两次输入密码不一致，请重新输入!')
			return ;
		}
		if(trim(password).length <8 || trim(password).length >16){
			alert('密码必须设定为8-16位,请重新填写密码！') ;
			return ;
		}
		
		if(trim(formObj.realname.value) == ''){
			alert('请填写真实姓名！') ;
			return ;
		}
		if(window.confirm('确认提交吗？')){
			formObj.submit() ;
		}
		
	}
//-->
</script>
<div  id="lvdbtable" style="width:100%;height:400;text-align: center;">
	<div class="bdtop" width="100%" align="center">修改个人信息</div>
	<span class="errm"><ww:actionerror/></span>
	<form action="updateUserInfoByUser.action" method="post" name="form1">
	<ww:hidden name="tbUser.id" value="%{tbUser.id}"/>
	<ww:hidden name="tbUser.intRoleId" value="%{tbUser.intRoleId}"/>
	<ww:hidden name="tbUser.intUserState" value="%{tbUser.intUserState}"/>
	<table border="0" cellpadding="0" cellspacing="0" width="80%" class="table">
		<ww:if test="tbUser.tbPartment != null">
			<tr>
				<td>所属旅行社：</td>
				<td><ww:property value="tbUser.tbPartment.company.strPartmentName"/></td>
			</tr>
			<tr>
				<td>部门名称</td>
				<td><ww:property value="tbUser.tbPartment.strPartmentName"/></td>
			</tr>
		</ww:if>
		<tr>
			<td>用户名:（登录系统的ID）</td>
			<td><ww:textfield id="userid" name="tbUser.strUserLogName" theme="simple"/></td>
		</tr>
		<tr>
			<td>密码(8-16位):</td>
			<td><ww:password id="password" name="tbUser.strUserPassword" value="%{tbUser.strUserPassword}"  theme="simple" size="22"/></td>
		</tr>
		
		<tr>
			<td>请再次输入密码:</td>
			<td><ww:password id="repassword" name="tbUser.strReUserPassword" value="%{tbUser.strUserPassword}"  theme="simple" size="22"/></td>
		</tr>
		<tr>
			<td>真实姓名:（真实姓名不能更改）</td>
			<td><ww:textfield id="realname" name="tbUser.strUserName" theme="simple" readonly="true"/></td>
		</tr>
	</table>
	<br/>
	<table width="80%" border="0">
		<tr>
			<td width="50%" align="left"><input type="button" class="inputbutton" value="提交" onclick="checkForm()"/></td>
			<td align="left"><input type="button" value="取消" class="inputbutton" onclick="window.close();"/></td>
		</tr>
	</table>
	</form>
	
</div>
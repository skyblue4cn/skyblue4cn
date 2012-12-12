<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<script type="text/javascript">
<!--
	function checkAddForm(){
		var obj = document.forms['form1'] ;
		if(trim(obj.name.value)==''){
			alert('请填写姓名！')
			return ;
		}
		if(trim(obj.country.value)==''){
			alert('请填写国家!')
			return ;
		}
		if(trim(obj.indenty.value)==''){
			alert('请填写身份证号或护照号!')
			return ;
		}
		obj.submit() ;
	}
//-->
</script>




<div  id="lvdbtable" style="width:100%;text-align: center;height:400;">
	<div class="bdtop" width="100%" align="center">添加游客</div>
	<div style="color:green;font-size: 14px;font-weight: bold;"><ww:actionmessage/></div>
	<ww:form action="addTbTraveler.action" method="post" name="form1">
			<table border="0" cellpadding="0" cellspacing="0" width="60%">
				<tr><td>姓名:</td><td><input type="text" id="name" name="tbTravelerInfo.strTravelerName"/></td></tr>
				<tr><td>国籍:</td><td><input type="text" id="country" name="tbTravelerInfo.strCountry"/></td></tr>
				<tr><td>身份证号或护照号:</td><td><input type="text" id="indenty" name="tbTravelerInfo.strIndentyNumber"/></td></tr>	
			</table>
			<ww:hidden name="tbTravelerInfo.intBillId" value="%{tbTravelerInfo.intBillId}"/>
			<br/>
			<table border="0" width="60%">
			<tr>
				<td align="center"><input type="button" class="inputbutton" value="提交" onclick="checkAddForm()" align="center"/></td>
				<td align="center"><input type="button" class="inputbutton" onclick="window.close();" value="取消"></td>
			</tr>	
			</table>
			</ww:form>
</div>

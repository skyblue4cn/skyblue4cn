<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="ww" uri="webwork"%>
<ww:include value="/top.jsp"></ww:include>
<script type="text/javascript">
<!--
	function submitCheckForm(){
		if(!checkForm()){
			return ;
		}
		document.getElementById('result').innerHTML="<font >正在提交，请稍候...</font>" ;
		ajaxForm('checkBillMd5Code.action','form1','result') ;
	}
	
	/**检查表单的参数是否填写完整*/
	function  checkForm(){
		var obj = document.all.form1 ;	
		if(trim(obj.billNumber.value) == ''){
			alert('请填写保单号！') ;
			return false ;
		}
		if(trim(obj.nbNumber.value) == '' || !checkNumber(trim(obj.nbNumber.value))){
			alert('请正确填写内宾人数！');
			return false ;
		}
		if(trim(obj.wbNumber.value) == '' || !checkNumber(trim(obj.wbNumber.value))){
			alert('请正确填写外宾人数!');
			return false ;
		}
		if(trim(obj.sureTime.value) == ''){
			alert('请填写保单确认时间！') ;
			return false ;
		}
		if(trim(obj.md5CheckCode.value) == ''){
			alert('请填写保单确认码！') ;
			return false ;
		}
		return true ;
	}
//-->
</script>

<br>

<div class="text1" style="width:80%;">保单确认码验证</div>
<br>
<form action="checkBillMd5Code.action" method="post" id="form1">
	<table border="0" cellpadding="0" cellspacing="0" class="table" width="80%">
	<ww:textfield id="billNumber" name="tbBill.strBillNumber" value="" label="保单号" title="请填写保单的保单号" size="40"></ww:textfield>
	<ww:textfield id="nbNumber" name="tbBill.intChinaTravelerNumber" value="" label="内宾人数" title="请填写保单内宾人数" size="40"></ww:textfield>	
	<ww:textfield id="wbNumber" name="tbBill.intOtherTravelerNumber" value="" label="外宾人数" title="请填写保单外宾人数" size="40"></ww:textfield>	
	<ww:textfield id="sureTime" name="tbBill.dteSureTime" value="" label="确认时间" title="请填写保单确认时间" size="40" onclick="setday(this)" readonly="true"></ww:textfield>
	<ww:textfield id="md5CheckCode" name="tbBill.md5CheckCode" value="" label="确认码" title="请填写确认码" size="40"></ww:textfield>
	</table>
	<br>
	<table border="0" cellpadding="0" cellspacing="0"width="80%">
		<tr>
			<td width="50%" align="left"><input type="button" value="提交" onclick="submitCheckForm()" class="inputbutton"/></td>
			<td align="left"><input type="button" value="重置" onclick="document.all.form1.reset()"  class="inputbutton"/></td>
		</tr>
	</table>
	<br>
	<br>
	<div id="result"></div>
</form>


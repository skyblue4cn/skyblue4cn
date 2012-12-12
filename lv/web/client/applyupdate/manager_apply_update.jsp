<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib  prefix="ww" uri="webwork"%>
<ww:include value="/client_top.jsp"></ww:include>
<script type="text/javascript">
<!--
	function applyupdate(){
		var obj = document.getElementById('form1') ;
		if(obj.strBillNumber.value == ""){
			alert('请填写保单号！');
			return;
		}
		obj.submit();
	}
//-->
</script>
<table border="0" width="100%" align="center" cellpadding="0" cellspacing="0">
	<tr valign="top">
		<td valign="top" width="15%" align="center" style="background:#ededed;" height="540px">
			<div class="tabcont">
	    	<br/>
	    	<br/>
	    	<br/>
	    	<input id="button1" class="inputbutton3" type="button" onclick="location.href='c_toManagerApplyUpdate.action'" value="填写新申请" /><br/><br/>
	    	<input id="button1" class="inputbutton" type="button" onclick="location.href='c_getNotReplyApply.action'" value="待审核申请" /><br/><br/>
	    	<input id="button2" class="inputbutton" type="button" onclick="location.href='c_getHasReplyApply.action'" value="已审核申请" /><br/><br/>
					
	    </div>
		</td>
		<td>
		 	<br>
		    <br>
		    <br>
		    <div class="tabcont" style="border-bottom: 2px solid #ccc;">
		    	<form action="c_toAddApplyUpdate.action" method="post" name="form1" target="_blank" id=form1>
			        <table width="100%"  border="0" cellpadding="0" cellspacing="0">
				        <tr>
				            <td style="font-weight: bold;">填写新的批改申请：</td>
				          	<td>
				          		请填写保单号:<ww:textfield name="strBillNumber" theme="simple" size="35"></ww:textfield>
				            </td>
				          <td><input type="button" class="inputbutton" id="button" value="填写" onclick="applyupdate()"/></td>
				        <td width="30%">&nbsp;</td>
				        
				        </tr>
			        </table>
	        </form>
	      <br/>
	      </div>
	      <br>
	      <br>
	      <div align="left" style="text-indent:50px">
	      	<div style="color:blue;font:16px;font-weight:bold; ">说明:</div>
	      <ul>
	      	<li>1.如果保单已确认，保单就不能更改，如果情况特殊，需要更改保单，可以在此填写批改申请。</li>
	        <li>2.批改申请提交后，由保险公司审核，保险公司根据情况决定是否同意此申请。</li>
	        <li>3.申请成功后，保单只能由保险公司更改。</li>
	      </ul>
	      </div>
			
		</td>
	</tr>
</table>
<br>



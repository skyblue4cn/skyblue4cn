<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="webwork" prefix="ww" %>

	<ww:include value="/client_top.jsp"/>
	<script type="text/javascript">
<!--	
	function applyNewBill(){
		var fobj = document.forms['form1'] ;
		if(fobj.billkind.value=='-1'){
			alert('请选择保单种类！') ;
			return ;
		}
		fobj.submit() ;
	}
	
	
//-->
</script>
	
	
	
<div>	
	<table width="100%" border="0"  cellspacing="0" cellpadding="0">
  <tr>
    <td width="15%" valign="top" align="center" style="background:#ededed;"  height="540px">
	    <div class="tabcont">
	    	<br/>
	    	<br/>
	    	<br/>
	    	<input id="button1" class="inputbutton3" type="button" onclick="location.href='c_toApplyNewBill.action" value="填写新保单" /><br/><br/>
	    	<input id="button1" class="inputbutton" type="button" onclick="location.href='c_showReferBillList.action'" value="待审核保单" /><br/><br/>
	    	<input id="button2" class="inputbutton" type="button" onclick="location.href='c_showBeiAnBillList.action'" value="已备案保单" /><br/><br/>
			
			<input id="button3" class="inputbutton" type="button" onclick="location.href='c_showEffectedBillList.action'" value="已确认保单" /><br/><br/>
			<input id="button4" class="inputbutton" type="button" onclick="location.href='c_showReturnBillList.action'" value="已退回保单" />
					
	    </div>
    </td>
   <td width="85%" valign="top">
    <br>
    <br>
    <br>
    <div class="tabcont" style="border-bottom: 2px solid #ccc;">
    	<form action="c_toAddBill.action" method="post" name="form1" target="_blank">
			<ww:hidden name="tbBill.intApplyUserId" value="%{#session['tbUser'].id}"/>
			<ww:hidden name="tbBill.intPartmentId" value="%{#session['tbUser'].tbPartment.id}"/>
			<ww:hidden name="tbBill.strCompanyName" value="%{#session['tbUser'].tbPartment.company.strPartmentName}" />
			<ww:hidden name="tbBill.strPartmentName" value="%{#session['tbUser'].tbPartment.strPartmentName}"/>
	        <ww:hidden name="tbBill.strSignatoryName" value="%{#session['tbUser'].strUserName}"/>
	        <table width="100%"  border="0" cellpadding="0" cellspacing="0">
		        <tr>
		            <td style="font-weight: bold;">填写新保单：</td>
		          	<td>
		          		<select name="tbBill.intKindId" id="billkind">
							<option value="-1" selected="selected">请选择保单种类</option>			          			
<%--				            <option value="1" >责任险</option>--%>
							<option value="2">意外险</option>
		           		 </select>
		            </td>
		          <td><input type="radio" name="tbBill.intTravelProperty" id="radio" value="0" checked="checked"/>
		            国内旅游</td>
		          <td><input type="radio" name="tbBill.intTravelProperty" id="radio2" value="1" />
		           出入境旅游</td>
		          <td><input type="button" class="inputbutton" id="button" value="填写" onclick="applyNewBill()"/></td>
		        <td width="10%">&nbsp;</td>
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
      	<li>1. 请注意正确选择保单种类</li>
       <li>2. 申请之后请注意查看对保单的处理，如果是备案或退回，请注意及时更新保单或重新提交。</li>
       <li>3. 如果申请确认后还需要更改，可以点击批改申请菜单向保险公司申请对保单做修改。</li>
      </ul>
      </div>
      </td>
  </tr>
</table>

</div>

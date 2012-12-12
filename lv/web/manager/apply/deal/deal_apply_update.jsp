<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib  prefix="ww" uri="webwork"%>
<ww:include value="/head.jsp"></ww:include>
<script type="text/javascript">
	function writeApply(){
		if(!window.confirm('填写回执单之前，请先对保单做处理！')){
			return  ;
		}
		var obj = document.getElementById('reply')
		if(obj.style.display=='none'){
			obj.style.display='block'
		}else{
			obj.style.display='none'
		}
	}


</script>
<br/>
<div class="text1">中 国 人 民 保 险 公 司 成 都 市 武 侯 区 支 公 司</div>
<br/>
<div class="text8">旅游意外（责任）险保险单批改申请书</div>
<br/>
	<table border="1" cellpadding="0" cellspacing="0" width="70%" class="table text9" >
		<tr>
			<td colspan="2" class="text9">
			中国人民保险公司成都市武侯区支公司:
			</td>
		</tr>
		<tr>
			<td colspan="2" valign="top">
				&nbsp;&nbsp;&nbsp;&nbsp;本保险人现向你公司申请批改
					<a href="getBillInfoToPrint.action?id=<ww:property value="tbApply.intBillId"/>" target="_blank"><ww:property value="tbApply.strBillNumber"/></a>
				保险单的下列内容,请核实并给予办理批改手续。
			</td>
		</tr>
		<tr>
			<td align="center" style="height:30px;" colspan="2">申请批改内容</td>
		</tr>
		<tr style="height:150px;">
			<td valign="top" colspan="2" class="applytext" >
				<ww:property value="tbApply.strApplyContent"/>
			</td>
		</tr>
		<tr>
			<td valign="bottom" width="50%">
				申请人:<ww:property value="tbApply.strApplyUserName"/>
			</td>
			<td>
				申请时间:<ww:date name="tbApply.dteApplyTime"  format="yyyy-MM-dd"/>
			</td>
		</tr>
	
	</table>
	<br/>
	<br/>
	<table border="0" cellpadding="0" cellspacing="0" width="70%">
		<tr>
			<td>
				<form action="toDealBillByBx.action" target="_blank">
					<ww:hidden name="tbBill.id" value="%{tbApply.intBillId}"/>
					<input type="submit" class="inputbutton" value="处理该保单"/>
				</form>
			</td>
			<td><input type="button" class="inputbutton" value="填写处理意见" onclick="writeApply()"/></td>
			<td><input type="button" class="inputbutton" value="取消" onclick="window.close()"/></td>
		</tr>
	</table>
	<br/>
	
	<div id="reply" style="display:none;">
	<form action="replyApplyByBx.action" method="post">
	<ww:hidden name="tbApply.id" value="%{tbApply.id}"/>
	<ww:hidden name="tbApply.intReplyUserId" value="%{#session.tbUser.id}"/>
	<table  border="0" cellpadding="0" cellspacing="0" width="70%">
		<tr >
			<td class="text8" colspan="2">核保人意见：</td>
		</tr>
		<tr>	
			<td colspan="2">
				<ww:textarea cols="86" rows="10" name="tbApply.strReplyContent" cssClass="inputarea"  value="请处理保单后，在这里填写回复!" theme="simple" onclick="if(this.value=='请处理保单后，在这里填写回复!')this.value=''"></ww:textarea>
			</td>
		</tr>
		<tr>	
			<td colspan="2">
				&nbsp;
			</td>
		</tr>
		<tr >
			<td>核保人：<ww:textfield name="tbApply.strReplyUserName" value="%{#session.tbUser.strUserName}" theme="simple" readonly="true"/></td>
			<td>日期：<input type="text" name="tbApply.dteReplyTime" value="<ww:date name="new java.util.Date()" format="yyyy-MM-dd"/>" readonly="true"/></td>
		</tr>
	</table>
	<br/>
	<table border="0" cellpadding="0" cellspacing="0" width="70%">
		<tr>
			<td><input type="button" class="inputbutton" value="提交回复" onclick="if(window.confirm('确认提交吗？'))this.form.submit()"/></td>
		</tr>
	</table>
	</form>
	</div>
	<br/>
	<br/>
	<br/>
	<br/>
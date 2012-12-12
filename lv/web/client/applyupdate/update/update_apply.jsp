<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib  prefix="ww" uri="webwork"%>
<ww:include value="/head.jsp"></ww:include>
<script type="text/javascript">
<!--
	function setValue(id){
		var obj = document.getElementById(id)
		if(id=='billNumber'){
			
			if(obj.value=='请填写保单号'){
				obj.value="" ;
				return ;
			}
		}
		if(id=='applyContent'){
			if(obj.value=='请在这里填写要申请批改的内容!'){
				obj.value="" ;
				return ;
			}
		}
	}
	
	function hiddenMDiv(){
		document.getElementById('div1').style.display='none'
	}
//-->
</script>



<br/>
<div class="text1">中 国 人 民 保 险 公 司 成 都 市 武 侯 区 支 公 司</div>
<br/>
<div class="text8">旅游意外（责任）险保险单批改申请书</div>
<br/>
<form action="c_updateApplyUpdate.action" method="post">
	<ww:hidden name="tbApply.id" value="%{tbApply.id}"/>
	<ww:hidden name="tbApply.intFromUserId" value="%{#session.tbUser.id}"/>
	<ww:hidden name="tbApply.intPartmentId" value="%{#session.tbUser.intPartmentId}"/>
	<ww:hidden name="tbApply.intBillId" value="%{tbApply.intBillId}"/>
	<ww:hidden name="tbApply.intIsReply" value="%{tbApply.intIsReply}"/>
	<ww:if test="actionMessages.size>0">
		<div id="div1"><font color="red"><li><ww:property value="actionMessages.get(0)"/></li></font></div>
		<script type="text/javascript">
			setTimeout(hiddenMDiv, 3000)
		</script>
	</ww:if>
	<table border="0" cellpadding="0" cellspacing="0" width="70%" height="300px" class="text9">
		<tr>
			<td colspan="2" >
			中国人民保险公司成都市武侯区支公司:
			</td>
		</tr>
		<tr>
			<td colspan="2">
				&nbsp;&nbsp;&nbsp;&nbsp;本保险人现向你公司申请批改
					<ww:textfield name="tbApply.strBillNumber"  value="%{tbApply.strBillNumber}" id="billNumber" cssClass="inputboxb" theme="simple" size="40" readonly="true"/>
				保险单的下列内容，请核实并给予办理批改手续。
			</td>
		</tr>
		<tr height="100px;">
			<td colspan="2">
				<br/>
				<ww:textarea name="tbApply.strApplyContent" cols="100" rows="10" theme="simple" 
					id="applyContent" 
				cssClass="inputarea"  value="%{tbApply.strApplyContent}">
				
				</ww:textarea>
			</td>
		</tr>
		<tr>
			<td>修改人:<ww:textfield theme="simple" name="tbApply.strApplyUserName" value="%{#session.tbUser.strUserName}" readonly="true"/></td>
			<td>时间:<input type="text" name="tbApply.dteApplyTime" value="<ww:date name="new java.util.Date()" format="yyyy-MM-dd"/>" readonly="true"/></td>
		</tr>
	</table>
	<p>&nbsp;</p>
	<table border="0" cellpadding="0" cellspacing="0" width="70%">
	
		<tr>
			<td>
				<input type="submit" class="inputbutton" value="提交"/>
			</td>
			<td>
				<input type="button" class="inputbutton" value="取消" onclick="window.close()"/>
			</td>
		</tr>
	</table>
</form>
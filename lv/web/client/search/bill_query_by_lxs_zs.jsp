<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork"%>

<ww:include value="/client_top.jsp"/>

<script type="text/javascript">
	
	function search(){
		var oj = document.forms['form1'] ;
		var postStr = Form.serialize(oj) ;
		var url = "queryBillByComditionForUser.action" ;
		var myajax=new Ajax.Updater('show',url,{method:'post',parameters:postStr});
	}
	
</script>


<table border="0" width="95%" cellpadding="0" cellspacing="0" style="text-align: center;">
	<tr>
		<td width="20%" >
			<br/>
			<br/>
			<form action="queryBillByComditionForUser.action" method="post" name="form1">
			<table border="0" width="30%" cellpadding="0" cellspacing="0" style="text-align: left;">
				<tr>
					<td style="font-weight: bold;">保单状态:</td>
				</tr>
				<tr>
					<td>
						<ww:checkbox name="state1" fieldValue="1" theme="simple" value="1" />待审查
						<ww:checkbox name="state2" fieldValue="2" theme="simple" value="1"/>已审核
						<br/>
						<ww:checkbox name="state3" fieldValue="3" theme="simple" value="1"/>已退回
					</td>
				</tr>
				<tr>
					<td style="font-weight: bold;">
					<br/>
					提交时间:</td>
				</tr>
				<tr>
					<td>
						自<br/>
						<ww:textfield name="startTime" theme="simple" onclick="setday(this)"/>
					</td>
				<tr>
					<td>
						至<br/>
						<ww:textfield name="endTime" theme="simple" onclick="setday(this)"/>
					</td>
				</tr>
				<tr>
					<td style="font-weight: bold;"><br/>保单号码:</td>
				</tr>
				<tr>
					<td><ww:textfield name="tbBill.id" value="" theme="simple"/></td>
				</tr>
				<tr>
					<td style="font-weight: bold;"><br/>签发人:</td>
				</tr>
				<tr>
					<td><ww:select name="tbBill.intApplyUserId" list="userList" headerKey="-1" headerValue="全部" listKey="id" listValue="strUserName" theme="simple" cssStyle="width:140px;"/></td>
				</tr>
				<tr>
					<td align="right">
					<br/>
					<input type="button" class="inputbutton" onclick="search()" value="查询" title="点击查询 "/></td>
				</tr>
			</table>
				<ww:hidden name="tbBill.intPartmentId" value="%{#session['tbUser'].tbPartment.id}"/>
			</form>
		</td>
		<td valign="top">
			<br/>
			<div style="text-align: center;font-weight: bold;border-bottom: 2px solid #ccc;">保单查询<br/><br/></div>
			<br/>
			<div id="show">
				<ww:include value="queryBillByComditionForUser.action?tbBill.intPartmentId=%{#session['tbUser'].tbPartment.id}"></ww:include>
			</div>
		</td>
	</tr>
</table>


	



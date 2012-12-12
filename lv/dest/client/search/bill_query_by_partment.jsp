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
	function searchPage(page){
		var oj = document.forms['form1'] ;
		var postStr = Form.serialize(oj) + '&pageBean.curPage='+page;
		var url = "queryBillByComditionForUser.action" ;
		var myajax=new Ajax.Updater('show',url,{method:'post',parameters:postStr});
	}
</script>


<table border="0" width="100%" cellpadding="0" cellspacing="0" style="text-align: center;" >
	<tr valign="top">
		<td width="15%" valign="top" style="background:#ededed;" height="540px">
			<br/>
			<br/>
			<form action="c_queryBillByComditionForUser.action" method="post" name="form1" target="showframe">
			<table border="0" width="30%" cellpadding="0" cellspacing="0" style="text-align: left;">
				<tr>
					<td style="font-weight: bold;">保单状态:</td>
				</tr>
				<tr>
					<td>
						<ww:radio name="state" list="#{1:'已备案<br>',2:'已确认<br>',3:'已退回'}" theme="simple" value="%{state}" />
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
						<ww:textfield name="startTime" theme="simple" onclick="setday(this)" size="15"/>
					</td>
				<tr>
					<td>
						至<br/>
						<ww:textfield name="endTime" theme="simple" onclick="setday(this)"  size="15" />
					</td>
				</tr>
				<tr>
					<td style="font-weight: bold;"><br/>保单号码:</td>
				</tr>
				<tr>
					<td><ww:textfield name="tbBill.strBillNumber" value="" theme="simple" size="15"/></td>
				</tr>
				<!-- 新添加功能 根据保单所包含游客信息查询 -->
				<tr>
					<td style="font-weight: bold;"><br/>保单所包括旅客姓名:</td>
				</tr>
				<tr>
					<td><ww:textfield name='strTravelerName' theme="simple" size="15"></ww:textfield></td>
				</tr>
				<tr>
					<td style="font-weight: bold;"><br/>保单所包括游客证件号:</td>
				</tr>
				<tr>
					<td><ww:textfield name='strIndentyNumber' theme="simple" size="15"></ww:textfield></td>
				</tr>
				
				<!-- end -->
				<tr>
					<td style="font-weight: bold;"><br/>签发人:</td>
				</tr>
				<tr>
					<td><ww:select name="tbBill.intApplyUserId" list="userList" headerKey="-1" headerValue="全部" listKey="id" listValue="strUserName" theme="simple" cssStyle="width:120px;"/></td>
				</tr>
				<tr>
					<td align="justify">
					<br/>
					<input type="button" class="inputbutton" onclick="this.form.reset()" value="重置" title="点击重置"/>
					&nbsp;&nbsp;
					<input type="button" class="inputbutton" onclick="this.form.submit()" value="查询" title="点击查询 "/></td>
				</tr>
			</table>
				<ww:hidden name="tbBill.intPartmentId" value="%{#session['tbUser'].tbPartment.id}"/>
			</form>
		</td>
		<td valign="top">
			<br/>
			<div style="text-align: center;font-weight: bold;border-bottom: 2px solid #ccc;font-size: 16px;">
			保单查询<br/><br/></div>
			<br/>
			<div id="show">
				<iframe align="top" frameborder="0" scrolling="auto" height="460" width="100%" name="showframe"></iframe>
			</div>
		</td>
	</tr>
</table>


	



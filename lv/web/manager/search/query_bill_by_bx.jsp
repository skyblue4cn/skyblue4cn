<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork"%>
<ww:include value="/top.jsp"/>
<script type="text/javascript">
	
	function search(){
		var oj = document.forms['form1'] ;
		var postStr = Form.serialize(oj) ;
		var url = "queryBillByComditionForBx.action" ;
		var myajax=new Ajax.Updater('show',url,{method:'post',parameters:postStr});
	}
	
	
	function searchPartmentList(){
		var keyword = document.forms['form1'].company.value ;
		var postStr = 'tbPartment.id=' + keyword  ;
		var url="getAllPartmentToSelect.action" ;
		var myajax=new Ajax.Updater('partment',url,{method:'post',parameters:postStr});
	}
	
</script>
<!--START 修改于2010-6-8 -->
<!-- 
<style>
#rightBanner{
	padding:0px;
	margin:0px;
}
#rightBanner td{
	height:20px;
}
</style> -->
<!-- END -->
<table border="0" width="100%" cellpadding="0" cellspacing="0" style="text-align: center;" id=rightBanner>
	<tr valign="top">
		<td width="15%" valign="top" style="background:#ededed;" height="540px" align="center">
			<br/>
			<br/>
			<form action="queryBillByComditionForBx.action" method="post" name="form1" target="searchframe">
			<table border="0" width="80%" height="400" cellpadding="0" cellspacing="0" style="text-align: left;">
				<tr>
					<td class="text4">保单状态:<br>
						<ww:radio name="state" list="#{1:'已备案<br>',2:'已确认<br>',3:'已退回'}" value="${state}" theme="simple"></ww:radio>
					</td>
				</tr>
				<tr>
					<td class="text4">	旅行社：<br>
						<ww:select id="company" list="companyList" headerValue="所有旅行社" 
							headerKey="-1" listKey="id" listValue="strPartmentName" 
							name="companyId" theme="simple"
						 	cssStyle="width:150px;"
						 	onchange="searchPartmentList()"
						 	></ww:select>
					</td>
				</tr>
				<tr>
					<td class="text4">部门:<br>
					<div id="partment">
						<ww:select name="partmentId" list="#{-1:'所有部门'}" theme="simple"  cssStyle="width:150px;"/>
					</div>
					</td>
				</tr>
				<tr>
					<td style="font-weight: bold;">开始时间:<br>
						<ww:textfield name="startTime" theme="simple" onclick="setday(this)"/>
					</td>
				</tr>
				<tr>
					<td class="text4">
						结束时间:<br>
						<ww:textfield name="endTime" theme="simple" onclick="setday(this)"/>
					</td>
				</tr>
				<tr>
					<td class="text4">保单序号:<br>
					<ww:textfield name="tbBill.strBillNumber"  theme="simple"/></td>
				</tr>
				<!-- 修改于2010-6-8 -->
				<!-- 增加功能，保单号 ，游客姓名，身份证号 （查询时需要输入至少其中一个） -->
				<!-- 
				<tr>
					<td class=text4>快速查询条件<br>
						包含以上条件<ww:checkbox name="cstate" theme="simple"/>
					</td>
				</tr>-->
				<tr>
					<td class=text4>保单所包括旅客姓名:<br/>
					<ww:textfield name='strTravelerName' theme="simple"/></td>
				</tr>
				<tr>
					<td class=text4>保单所包括游客证件号:<br/>
					<ww:textfield name='strIndentyNumber' theme="simple"/></td>
				</tr> 
				<!-- END -->
				<tr>
					<td align="justify" >
					<br>
					<input type="button" class="inputbutton" onclick="this.form.reset()" value="重置" title="点击重置 "/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" class="inputbutton" onclick="this.form.submit()" value="查询" title="点击查询 "/></td>
				</tr>
			</table>
			</form>
		</td>
		<td valign="top">
			<br/>
			<div class="bdtext">
			保单查询<br><br></div>
			<br>
			<iframe name="searchframe" frameborder="0" width="100%" align="top" scrolling="auto" height="470px"></iframe>
		</td>
	</tr>
</table>


	



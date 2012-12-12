<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<ww:include value="/top.jsp"></ww:include>
<html>
<body class="welcm">
<table border="0" cellpadding="0" cellspacing="0" width="95%" height="450">
	<tr valign="top">
		<td width="10%" style="text-align:center">
			<div class="setbar">
				<br/>
				<br/>
				<br/>
				<br>
				<br>
				<br>
		      	<input id="button1" class="inputbutton3" type="button" onclick="location.href='bxDbMessage.action'" value="待 办 事 务" /><br/><br/>
				<input id="button2" class="inputbutton" type="button" onclick="location.href='bxDyMessage.action'" value="待 阅 事 务" /><br/><br/>
     		 </div>
		</td>
		<td width="90%">
			<div id="contbar">
				<div class="pind">
						  <img src="images/dzwelcm_03.gif" width="153" height="66" />
						<div class="tsinfo">
						<ul>
							<li>有 <span class="page"><ww:property value="newBillNumber"/></span> 个新申请的保单，请及时确认！<ww:a href="getBillNeedBxSure.action">处理</ww:a></li>	        
							<li>有 <span class="page"><ww:property value="newUpdateApply"/></span> 个新的批改申请，请及时处理！  <ww:a href="getAllNotReplyOfApplyForBx.action">处理</ww:a></li>
						</ul>
				</div>
			</div>		
		</td>
	</tr>
</table>



<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<ww:include value="/top.jsp"/>
<body class="welcm">
	<table border="0" cellpadding="0" cellspacing="0" width="98%" height="450">
		<tr valign="top">
			<td width="10%" style="text-align:center">
				 <div class="tabcont">
			    	<br/>
			    	<br/>
			    	<br/>
			    	<Br/>
			    	<br>
			    	<br/>
			    	<input id="button1" class="inputbutton3" type="button" onclick="location.href='getBillNeedBxSure.action'" value="待审核保单" /><br/><br/>
					<input id="button2" class="inputbutton" type="button" onclick="location.href='getBillForBxBeiAn.action'" value="已备案保单" /><br/><br/>
					<input id="button3" class="inputbutton" type="button" onclick="location.href='getBillForBxHasSure.action'" value="已确认保单" /><br/><br/>
					<input id="button4" class="inputbutton" type="button" onclick="location.href='getBillForBxHasReturn.action'" value="已退回保单" />
				
	   		 	</div>
			</td>
			<td width="90%">
				<div id="contbar">
				      <ww:include value="getBillNeedBxSure.action"></ww:include>
				 </div>
			    	
			</td>
		</tr>
	</table>

</body>

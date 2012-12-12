<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>

<ww:include value="/head.jsp"/> 
<body onload="window.opener.location.reload();">
<div  id="lvdbtable" style="width:100%;text-align: center;height: 500px;">
	<div class="bdtop" width="100%" align="center">添加旅客明细</div>
	<div class="errm"><ww:actionmessage/></div>
	<br/>
	<table border="0" width="80%">
		<tr>
			<td align="left"><input type="button" onclick="location.href='toAddTraveler.action?intBillId=<ww:property value="tbTravelerInfo.intBillId"/>'" value="继续添加" class="inputbutton" /></td>
			<td align="left"><input type="button" onclick="returnParentWindow()" value="关闭本页" class="inputbutton"/></td>
		</tr>
	</table>
	<br/>
	<br/>
	<br/>
</div>
</body>
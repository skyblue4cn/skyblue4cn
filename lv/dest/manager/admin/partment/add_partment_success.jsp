<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>

<ww:include value="/head.jsp"/> 
<body onload="window.opener.location.reload();">
<div  id="lvdbtable" style="width:100%;text-align: center;height: 500px;">
	<div class="bdtop" width="100%" align="center">添加旅行社</div>
	<br/>
	<ww:actionmessage/>
	&nbsp;&nbsp;
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<table border="0" width="60%">
		<tr>
			<td align="center"><input type="button" onclick="location.href='toAddPartment.action?tbPartment.id=<ww:property value="tbPartment.intParentId"/>'" value="继续添加" class="inputbutton" /></td>
			<td align="center"><input type="button" onclick="returnParentWindow()" value="关闭本页" class="inputbutton"/></td>
		</tr>
	</table>
	<br/>
	<br/>
	<br/>
</div>
</body>
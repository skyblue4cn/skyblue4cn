<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<body id="lvdbtable">
<div  style="width:100%;" align="center">
	<br/>
	<div align="center">
		<table border=0>
			<tr>
				<td class="text1"><ww:actionerror theme="simple"/></td>
				<td class="text1"><ww:actionmessage theme="simple"/></td>
				<td><input type="button" value="确定" class="inputbutton" onclick="returnParentWindow()"/></td>
			</tr>
		</table>
	</div>
</div>
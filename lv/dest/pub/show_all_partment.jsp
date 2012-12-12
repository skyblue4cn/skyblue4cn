<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork"%>
<ww:if test="partmentList.size()!=0">
	<ww:select 
		id="partmentSelect"
		name="partmentId"
		list="partmentList"
		headerValue="所有部门"
		headerKey="-1"
		listValue="strPartmentName"
		listKey="id"
		theme="simple"
		cssStyle="width:150px;height:20px;overflow: auto;"
	/>
</ww:if>
<ww:else>
	<select id="partmentSelect" name="partmentId" style="width:150px;">
		<option value="-1" selected="selected">所有部门</option>
	</select>
</ww:else>

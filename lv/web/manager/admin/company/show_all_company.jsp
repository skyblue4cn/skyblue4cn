<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<ww:include value="/top.jsp"/>
<br/>
	<div class="text1"></div>
<br/>
<table width="80%"  border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="80%" class="text1">所有旅行社列表</td>
		<td><input type="button" onclick="openAWindow('toAddCompany.action',500,400)" class="inputbutton" value="添加旅行社">		</td>
	</tr>
</table>
<br/>
<table width="80%"  border="0" cellpadding="0" cellspacing="0" class="table" style="line-height:30px;">
	<tr>
		<ww:iterator value="partmentList" status="index">
			
			<ww:if test="!(#index.index ==0 || #index.index%3 !=0)">
				</tr><tr>
			</ww:if>
			<td><a href="getAllPartmentByCompanyId.action?tbPartment.id=<ww:property value="id"/>" target="_blank"><ww:property value="strPartmentName"/></a></td>
		</ww:iterator>
	</tr>
</table>


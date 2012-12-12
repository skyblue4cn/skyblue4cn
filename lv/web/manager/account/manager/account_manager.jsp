<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<ww:include value="/top.jsp"/>
<br/>
<Br/>
	<div class="text1">旅行社帐户管理</div>
<br/>
<br/>
<table width="80%"  border="0" cellpadding="0" cellspacing="0" class="table" style="line-height: 40px;">
  <tr>
  	<ww:iterator value="partmentList" status="index">
  	<ww:if test="!(#index.index==0 || #index.index%3!=0)">
		</tr>
  		<tr>
  	</ww:if>
  	<td>	
		<a href="getPartmentsToAccountManager.action?tbPartment.id=<ww:property value="id"/>" target="_blank">
			<ww:property value="strPartmentName"/>
		</a>
	</td>
  	</ww:iterator>
  </tr>
 
</table>

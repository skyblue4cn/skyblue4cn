<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<ww:include value="/client_top.jsp"/>
<table width="95%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top">
    	<br/>
	    <div class="tabcont" id="billContent">
	      	<ww:include value="getMessageByPartmentId.action?partmentId=%{#session['tbUser'].tbPartment.id}"></ww:include>
	  	</div>
	  
  </td>
  </tr>
</table>

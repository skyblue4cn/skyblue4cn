<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<ww:include value="/top.jsp"/>

<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top" align="center" width="20%">
	    <div class="tabcont">
	    	<br/>
	    	<br/>
	    	<input class="inputbutton" type="button" onclick="ajaxF('toAddPeiKuanLog.action','','div1')" value="添加记录" /><br/><br/>
			<input class="inputbutton" type="button" onclick="ajaxF('getAllPeiKuanLog.action','','div1')" value="查看记录" /><br/><br/>
	    </div>
    </td>
    <td valign="top">
    	<br/>
	    <div class="tabcont" id="div1">
	      	<ww:include value="toAddPeiKuanLog.action"/>
	  	</div>
	  
  </td>
  </tr>
</table>
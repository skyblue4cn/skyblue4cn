<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<div class="text1" style="width:80%;">添加赔款记录</div>
<br/>
<div style="border-bottom:2px solid #ccc;">
	<form action="#" method="post" name="searchform">
	<table border="0" cellpadding="0" cellspacing="0" width="95%">
	  <tr>
	    <td width="20%" class="text2">请输入保单号:</td>
	    <td width="40%"><ww:textfield name="tbBill.strBillNumber" theme="simple" value="" size="40"/></td>
	    <td width="30%"><input type="button" value="查询" class="inputbutton" onclick="ajaxForm('toSearchBillByNumber.action','searchform','div2')"/></td>
	    <td>&nbsp;</td>
	  </tr>
	 </table>
	 </form>
	<br/>
	
</div>
<br/>
<div id="div2">
	&nbsp;
<div>






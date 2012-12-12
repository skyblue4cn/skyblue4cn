<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<script type="text/javascript">
<!--
	function loadlvke(){
		var obj = window.opener.document.getElementById('lvke1')
		obj.innerHTML = document.getElementById('hhh').innerHTML ;
		window.close();
	}
//-->
</script>


<div id="hhh" style="display:none;">	
	 <table id="travelerTable" width="100%" border="0" cellspacing="0" cellpadding="0" class="table">
	     <tr>
               <td width="10%" align="center">序号</td>
               <td width="15%" align="center">姓名</td>
               <td width="15%" align="center">性别</td>
               <td width="15%" align="center">出生年月</td>
                <td width="15%" align="center">国籍</td>
               <td width="20%" align="center">证件号</td>
               <td width="10%" align="center">删除</td>
	      </tr>
		 <ww:iterator value="tbTravelerInfoList" status="index">
		 	<tr id="tr<ww:property value="#index.index+1"/>">	
		 		<td><ww:property value="#index.index+1"/></td>
		 		<td><input type="text" name="travelerName" class="inputbox2" value='<ww:property value="strTravelerName"/>'/></td>
		 		<td><input type="text" name="travelerSex" class="inputbox2" value='<ww:property value="strSex"/>'/></td>
		 		<td><input type="text" name="travelerBirthday" class="inputbox2" value='<ww:property value="strBirthday"/>'/></td>
		 		<td><input type="text" name="travelerCountry" class="inputbox2" value='<ww:property value="strCountry"/>'/></td>
		 		<td><input type="text" name="travelerIndentity" class="inputbox3" value='<ww:property value="strIndentyNumber"/>'/></td>
		 		<td><a href="javascript:deleteTr('<ww:property value="#index.index+1"/>')">删除</a></td>
		 	</tr>
		 </ww:iterator>          
	 </table>
</div>
<br/>
<div class="text2">
	<ww:actionmessage/>请点击确定按钮！
	<input type="button" value="确定" onclick="loadlvke()"/>
</div>





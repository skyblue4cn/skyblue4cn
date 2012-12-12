<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
	<script type="text/javascript">
<!--
	function tongji(){
		var obj = document.forms['form1'] ;
		/*如果没有选择按时间段来查询*/
		var action='';
		if(obj.queryType.checked==false){
			if(obj.month.value==-1){
				action ='getPartmentYearStatByYear.action' ;
			}else{
				action="getPartmentMonthStatByMonth.action" ;
			}
		}else{
			action="getPartmentTimeStatByTime.action";
		}
		ajaxForm(action,'form1','tjDiv')
	}
//-->
</script>



<br/>

	<form action="#" method="post" name="form1">
		<table border="0" cellpadding="0" cellspacing="0"  width="95%">
			<tr>
				<td>
					<ww:select name="partmentId" list="partmentList" headerKey="%{#session.tbUser.intPartmentId}" headerValue="总社" listKey="id" listValue="strPartmentName" theme="simple"/>
				</td>
				<td class="text9">选择时间：</td>
				<td><ww:select id="year" name="partmentMonthStat.year" list="#{2008:'2008年',2009:'2009年',2010:'2010年'}" theme="simple" value="2008" cssStyle="width:120px;" /></td>
				<td><ww:select id="month" name="partmentMonthStat.month" list="#{-1:'所有月份',1:'1月',2:'2月',3:'3月',4:'4月',5:'5月',6:'6月',7:'7月',8:'8月',9:'9月',10:'10月',11:'11月',12:'12月'}" value="0" theme="simple" cssStyle="width:120px;"/></td>
				<td>&nbsp;</td>
				<td width="12%"><ww:checkbox name="queryType" value="0"  fieldValue="1" theme="simple"/>按时间段查询</td>
				<td width="15%">
					起<input type="text" id="stime" name="partmentTimeStat.startTime" class="inputbox" onclick="setday(this)" readonly="readonly" size="15"/>
				</td>
				<td width="15%"> 
					止<input type="text" id="etime" name="partmentTimeStat.endTime" class="inputbox" onclick="setday(this)" readonly="readonly" size="15"/>
				</td>
				<td ><input type="button" value="确定" class="inputbutton" onclick="tongji()"/></td>
			</tr>
		</table>
	</form>

<br>
<br/>
<div class="text1"><ww:property value="#session.tbUser.tbPartment.strPartmentName"/>--旅游保险统计报表</div>
<div id="tjDiv">
	<ww:include value="getDefaultPartmentYearStat.action"></ww:include>
</div>
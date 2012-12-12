<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>

<ww:include value="/head.jsp"></ww:include>
<script type="text/javascript">
<!--
	function sall(){
		var objs = document.getElementsByName('ids') ;
		var checkType = document.getElementById('alls').checked ;
		for(var i =0 ;i<objs.length ; i++){
			objs[i].checked = checkType ;
		}
	}
	
//-->
</script>

 <form action="toShouMonthFeeMorePartment.action" method="post" target="blank" id="form2">		

<a href="file:///c://导出数据.xls">下载Excel版本(右击>>目标另存为)</a>
<br/>
<table width="95%" border="0" cellspacing="0" cellpadding="0" class="table">
	<tr>
         <th scope="col" >编号</th>
         <th scope="col" >名称</th>
         <th scope="col" >应付</th>
         <!--
         <th scope="col" >欠费</th>
         -->
         <th scope="col" >状态</th>
         <th scope="col" >清单</th>
         <th scope="col">交费</th>
     </tr>
      <ww:iterator value="monthPayInfoList" status="index">
     	<ww:if test="intIsPay==0 || isShowAll ==1">
				<tr <ww:if test="#index.index%2!=0">   bgcolor="#ededed" </ww:if>>
				<td align="center">
					<!-- <input type="checkbox" name="ids" value="<ww:property value="id"/>" /> -->
					<ww:property value="#index.index+1"/>	
				</td>
				<td><ww:property value="tbPartment.strPartmentName"/></td>
				<td><ww:property value="dbeNeedToPay"/></td>
				<!--
				<td><ww:property value="dbeHfFee"/></td>
				-->
				<td>
					<ww:if test="intIsPay==-1">
						无费用
					</ww:if>
					<ww:elseif test="intIsPay==0"><span style="color:red;">未交费</span></ww:elseif>
					<ww:else>
						已交费
					</ww:else>
				</td>
				<td>
					<ww:if test="intIsPay==-1">
						无清单
					</ww:if>
					<ww:else>
						<a href="getBillListByMonthIdForBx.action?tbMonthPayInfo.id=<ww:property value="id"/>" target="_blank">
							查看	
						</a>
					</ww:else>
				</td>
				<td  align="center">
					<ww:if test="intIsPay==-1">
						<ww:a href="javascript:alert('无月费记录，不需要收费！')">
							无费用
						</ww:a>
					</ww:if>
					<ww:elseif test="intIsPay==1">
						<ww:a href="javascript:alert('已收费！')">
							已收费
						</ww:a>
					</ww:elseif>
					<ww:else>
						<a href="toPayMonthFee.action?tbMonthPayInfo.id=<ww:property value="id"/>" target="_blank">收费</a>
					</ww:else>
				</td>
			</tr>
		</ww:if>
	</ww:iterator>
</table>
<br/>
<!-- 
<table width="95%" border="0" cellspacing="0" cellpadding="0" >
	<tr>
		<td width="10%"><input type="checkbox" id="alls" onclick="sall()"/>全选</td>
		<td align="left"><input type="button" class="inputbutton" value="收费" onclick="openAWindow('toShouMorePartmentMonthFee.action')"/></td>
	</tr>
</table>
 -->
<br/>
</form>
<br/>
<br/>
<br/>





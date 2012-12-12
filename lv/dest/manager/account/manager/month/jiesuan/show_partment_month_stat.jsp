<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>

<table width="95%" border="0" cellspacing="0" cellpadding="0" class="table">
	<tr  style="line-height:25px;">
        <th scope="col" width="14%">编号</th>
         <th scope="col" width="16%">名称</th>
         <th scope="col" width="14%">应付</th>
         <th scope="col" width="14%">欠费</th>
         <th scope="col" width="14%">状态</th>
         <th scope="col" width="14%">清单</th>
         <th scope="col" width="14%" title="查看详情">交费</th>
     </tr>
     <tr style="color:green;">
     	<td>1</td>
     	<td><ww:property value="tbMonthPayInfo.tbPartment.strPartmentName"/></td>
     	<td><ww:property value="tbMonthPayInfo.dbeNeedToPay"/></td>
     	<td><ww:property value="tbMonthPayInfo.dbeHfFee"/></td>
     	<td>
    		<ww:if test="intIsPay==0"><span style="color:red;">未交费</span></ww:if>
			<ww:else>已交费</ww:else>
		</td>
		<td>
			<ww:a href="javascript:openAWindow('getBillListByMonthIdForUser.action?tbMonthPayInfo.id=%{tbMonthPayInfo.id}')">
				查看
			</ww:a>
		</td>
     	<td align="center">
     		<ww:if test="intIsPay==1">
				<input type="button" value="已收费" class="inputbutton" onclick="alert('已收费！')"/>
			</ww:if>
			<ww:else>
				<input type="button" value="收费" class="inputbutton" onclick="openAWindow('toPayMonthFee.action?tbMonthPayInfo.id=<ww:property value="id"/>')"/>
			</ww:else>
     	</td>
     </tr>

</table>

<br/>
<br/>
<br/>





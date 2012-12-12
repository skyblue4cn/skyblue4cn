<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork"%>

<ww:include value="/head.jsp"/>
		
<table width="98%" border="0" cellspacing="0" cellpadding="0" class="table">
        <tr>
          <th scope="col" width="6%">序号</th>
           <th scope="col" width="8%">种类</th>
          <th scope="col" width="25%">保单号</th>
          <th scope="col" width="16%">路线</th>
          <th scope="col" width="6%">内宾</th>
          <th scope="col" width="6%">外宾</th>
          <th scope="col" width="9%">起保日期</th>
          <th scope="col" width="9%">终止日期</th>
        
          <th scope="col" width="9%">保费</th>
          
          <th scope="col" width="7%">查看</th>
        </tr>
    <ww:iterator value="pageBean.dataList" status="index">
		<tr <ww:if test="#index.index%2!=0">   bgcolor="#ededed" 	</ww:if>>
			<td><ww:property value="pageBean.curFromIndex + #index.index"/></td>
			<td><ww:property value="strKindName"/></td>
			<td><ww:property value="strBillNumber"/></td>
			<td><ww:property value="strTravelRold"/></td>
			<td><ww:property value="intChinaTravelerNumber"/></td>
			<td><ww:property value="intOtherTravelerNumber"/></td>
			<td><ww:date name="dteStartTime" format="yy-MM-dd"/></td>
			<td><ww:date name="dteEndTime" format="yy-MM-dd"/></td>
			
			<td><ww:property value="dbeAllFee"/>元</td>
			
			<td align="center">
				<a href="c_getBillInfoToPrint.action?limitId=<ww:property value="id"/>&limitKey=<ww:property value="@cn.insurance.action.SupportAction@getEencodeIdKey('search',id)"/>&limitType=search" target="_blank">
					<img src="images/chakan.gif" border="0">
				</a>
			</td>
		</tr>
	</ww:iterator>

</table>
	<br>
	<ww:if test="pageBean.dataList.size()==0">
		<div class="text2">没有找到任何记录！</div>
	</ww:if>
	<ww:else>
		<table width="98%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>&nbsp;</td>
					<td width="10%" align="center">
						当前第<ww:property value="pageBean.curPage"/>/<ww:property value="pageBean.maxPage"/>页
					</td>
					<td width="5%" align="right">
						<ww:if test="pageBean.curPage<=1">首页</ww:if>
						<ww:else>
						<a href="
						<ww:url>
						<ww:param name="state" value="state[0]"/>
						<ww:param name="startTime" value="startTime"/>
						<ww:param name="endTime" value="endTime"/>
						<ww:param name="tbBill.intApplyUserId" value="tbBill.intApplyUserId"/>
						<ww:param name="tbBill.intPartmentId" value="tbBill.intPartmentId"/>
						<ww:param name="tbBill.strBillNumber" value="tbBill.strBillNumber"/>
						<ww:param name="pageBean.curPage" value="1"></ww:param>
						</ww:url>">首页</a>
						</ww:else>
					</td>
					<td width="5%" align="right">
						<ww:if test="pageBean.curPage<=1">上页</ww:if>
						<ww:else>
						<a href="<ww:url>
						<ww:param name="state" value="state[0]"/>
						<ww:param name="startTime" value="startTime"/>
						<ww:param name="endTime" value="endTime"/>
						<ww:param name="tbBill.intApplyUserId" value="tbBill.intApplyUserId"/>
						<ww:param name="tbBill.intPartmentId" value="tbBill.intPartmentId"/>
						<ww:param name="tbBill.strBillNumber" value="tbBill.strBillNumber"/>
						<ww:param name="pageBean.curPage" value="%{pageBean.curPage-1}"></ww:param>
						</ww:url>">上页</a>
						</ww:else>
					</td>
					<td width="5%" align="right">
						<ww:if test="pageBean.curPage>=pageBean.maxPage">下页</ww:if>
						<ww:else>
						<a href="<ww:url>
						<ww:param name="state" value="state[0]"/>
						<ww:param name="startTime" value="startTime"/>
						<ww:param name="endTime" value="endTime"/>
						<ww:param name="tbBill.intApplyUserId" value="tbBill.intApplyUserId"/>
						<ww:param name="tbBill.intPartmentId" value="tbBill.intPartmentId"/>
						<ww:param name="tbBill.strBillNumber" value="tbBill.strBillNumber"/>
						<ww:param name="pageBean.curPage" value="%{pageBean.curPage+1}"></ww:param>
						</ww:url>">下页</a>
						</ww:else>
					</td>
					<td width="5%" align="right">
						<ww:if test="pageBean.curPage>=pageBean.maxPage">末页</ww:if>
						<ww:else>
						<a href="<ww:url>
						<ww:param name="state" value="state[0]"/>
						<ww:param name="startTime" value="startTime"/>
						<ww:param name="endTime" value="endTime"/>
						<ww:param name="tbBill.intApplyUserId" value="tbBill.intApplyUserId"/>
						<ww:param name="tbBill.intPartmentId" value="tbBill.intPartmentId"/>
						<ww:param name="tbBill.strBillNumber" value="tbBill.strBillNumber"/>
						<ww:param name="pageBean.curPage" value="%{pageBean.maxPage}"></ww:param>
						</ww:url>">末页</a>
						</ww:else>
					</td>
				</tr>
			</table>	
		
	</ww:else>






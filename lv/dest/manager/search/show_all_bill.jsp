<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork"%>
<ww:include value="/head.jsp"></ww:include>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table">
  <tr>
         <th scope="col" width="10%" >种类</th>
         <th scope="col" width="16%">旅行社</th>
         <th scope="col" width="20%">保单号</th>
         <th scope="col" width="7%">内宾</th>
         <th scope="col" width="7%">外宾</th>
         <th scope="col" width="10%">起保日期</th>
         <th scope="col" width="10%">终止日期</th>
         <th scope="col" width="10%">保费</th>
          <th scope="col" width="5%">修改</th>
         <th scope="col" width="5%">查看</th>
        </tr>
         <ww:iterator value="pageBean.dataList" status="index">
			<tr <ww:if test="#index.index%2==0"> bgcolor="#ededed"</ww:if>>
			<td align="center"><ww:property value="strKindName"/></td>
			<td>
				<ww:property value="tbPartment.company.strPartmentName"/><br>
			</td>
			<td>
				<ww:property value="strBillNumber"/>
			</td>
			<td align="center"><ww:property value="intChinaTravelerNumber"/></td>
			<td align="center"><ww:property value="intOtherTravelerNumber"/></td>
			<td align="center"><ww:date name="dteStartTime" format="yy-MM-dd"/></td>
			<td align="center"><ww:date name="dteEndTime" format="yy-MM-dd"/></td>
			<td align="center"><ww:property value="dbeAllFee"/></td>
			<td align="center">
				<a href="toUpdateBillByBx.action?tbBill.id=<ww:property value="id"/>" target="_blank">
					<img src="images/xiugai.gif" border="0">
				</a>
			</td>
			<td align="center">
				<a href="getBillInfoToPrint.action?tbBill.id=<ww:property value="id"/>" target="_blank">
					<img src="images/chakan.gif" border="0">
				</a>
			</td>
	</tr>
	</ww:iterator>
</table>
<br>
	<ww:if test="pageBean.dataList == null || pageBean.dataList.size()==0">
		<div align="center">没有搜索到符合条件的保单</div>
	</ww:if>
	<ww:else>
	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>&nbsp;</td>
								<td width="10%" align="center">
									当前第<ww:property value="pageBean.curPage"/>/<ww:property value="pageBean.maxPage"/>页
								</td>
								<td width="5%" align="right">
									<ww:if test="pageBean.curPage<=1">首页</ww:if>
									<ww:else>
									<a href="<ww:url>
									<ww:param name="state" value="state[0]"/>
									<ww:param name="companyId" value="companyId"/>
									<ww:param name="partmentId" value="partmentId"/>
									<ww:param name="startTime" value="startTime"/>
									<ww:param name="endTime" value="endTime"/>
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
									<ww:param name="companyId" value="companyId"/>
									<ww:param name="partmentId" value="partmentId"/>
									<ww:param name="startTime" value="startTime"/>
									<ww:param name="endTime" value="endTime"/>
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
									<ww:param name="companyId" value="companyId"/>
									<ww:param name="partmentId" value="partmentId"/>
									<ww:param name="startTime" value="startTime"/>
									<ww:param name="endTime" value="endTime"/>
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
									<ww:param name="companyId" value="companyId"/>
									<ww:param name="partmentId" value="partmentId"/>
									<ww:param name="startTime" value="startTime"/>
									<ww:param name="endTime" value="endTime"/>
									<ww:param name="tbBill.strBillNumber" value="tbBill.strBillNumber"/>
									<ww:param name="pageBean.curPage" value="%{pageBean.maxPage}"></ww:param>
									</ww:url>">末页</a><ww:property value="endTime"/>
									</ww:else>
								</td>
							</tr>
					</table>	
	
	</ww:else>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<br>
<div class="text1" style="line-height:30px;"><ww:property value="tbPartment.company.strPartmentName"/>--<ww:property value="tbPartment.strPartmentName"/><br>月结帐户支付记录</div>
<br/>
<table width="95%" border="0" cellspacing="0" cellpadding="0" class="table">
        <tr>
          <th scope="col">时间</th>
       	  <th scope="col">应付</th>
          <th scope="col">欠费</th>
          <th scope="col">状态</th>
          <th scope="col">支付时间</th>
          <th scope="col" >保单清单</th>
          <th scope="col">支付</th>
        </tr>
     <ww:iterator value="pageBean.dataList" status="index">
			<tr <ww:if test="#index.index%2!=0">    bgcolor="#ededed" 	</ww:if>>
		<td>
			<ww:date name="dteStartTime" format="yy-MM-dd"/>/
			<ww:if test="dteEndTime.before(new java.util.Date())">
				<ww:date name="dteEndTime" format="yy-MM-dd"/>
			</ww:if>
			<ww:else>
				至今
			</ww:else>
		</td>
		<td><ww:property value="dbeNeedToPay"/></td>
		<td><ww:property value="dbeHfFee"/></td>
		
		<td>
			<ww:if test="intIsPay==0"><span style="color:red;">未交费</span></ww:if>
			<ww:else>
				已交费
			</ww:else>
		</td>
		<td>
			<ww:date name="tbMonthPayOutLog.dtePayDate" format="yyyy-MM-dd"/>
		</td>
		<td>
			<a href="getBillListByMonthIdForBx.action?tbMonthPayInfo.id=<ww:property value="id"/>" target="blank">
				查看
			</a>
		</td>
		<td>
			<ww:if test="intIsPay ==1">
				<ww:a href="javascript:alert('已支付!')">
					已支付
				</ww:a>
			</ww:if>
			<ww:else>
				<a href="toPayMonthFee.action?tbMonthPayInfo.id=<ww:property value="id"/>" target="_blank">
					支付
				</a>
			</ww:else>
		</td>
	</tr>
	</ww:iterator>
</table>
<br/>
<ww:if test="pageBean.dataList.size()!=0">
	  <table width="95%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>&nbsp;</td>
				<td width="10%" align="center">
					当前第<ww:property value="pageBean.curPage"/>/<ww:property value="pageBean.maxPage"/>页
				</td>
				<td width="5%" align="right">
					<ww:if test="pageBean.curPage<=1">首页</ww:if>
					<ww:else>
					<a href="<ww:url><ww:param name="pageBean.curPage" value="1"></ww:param></ww:url>">首页</a>
					</ww:else>
				</td>
				<td width="5%" align="right">
					<ww:if test="pageBean.curPage<=1">上页</ww:if>
					<ww:else>
					<a href="<ww:url><ww:param name="pageBean.curPage" value="%{pageBean.curPage-1}"></ww:param></ww:url>">上页</a>
					</ww:else>
				</td>
				<td width="5%" align="right">
					<ww:if test="pageBean.curPage>=pageBean.maxPage">下页</ww:if>
					<ww:else>
					<a href="<ww:url><ww:param name="pageBean.curPage" value="%{pageBean.curPage+1}"></ww:param></ww:url>">下页</a>
					</ww:else>
				</td>
				<td width="5%" align="right">
					<ww:if test="pageBean.curPage>=pageBean.maxPage">末页</ww:if>
					<ww:else>
					<a href="<ww:url><ww:param name="pageBean.curPage" value="%{pageBean.maxPage}"></ww:param></ww:url>">末页</a>
					</ww:else>
				</td>
			</tr>
	</table>
</ww:if>
<ww:else>	
	<div style="font-weight: bold;text-align: center;">没有记录！</div>
</ww:else>
	
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<div style="text-align: center;font-weight: bold;">预存帐户退费记录</div>
<br/>
<ww:if test="pageBean.dataList.size()!=0">
<table width="95%" border="0" cellspacing="0" cellpadding="0" class="table">
        <tr>
          <th scope="col">序号</th>
          <th scope="col">时间</th>
          <th scope="col">保单序号</th>
          <th scope="col">退费金额(元)</th>
          <th scope="col">帐户余额（元）</th>
        </tr>
     <ww:iterator value="pageBean.dataList" status="index">
		<tr  <ww:if test="#index.index%2!=0">   bgcolor="#ededed" </ww:if>>
			<td><ww:property value="#index.index+1"/></td>
			<td><ww:date name="dtePayoutTime" format="yyyy-MM-dd"/></td>
			<td><ww:property value="intBillId"/></td>
			<td><ww:property value="dbePayoutNumber"/></td>
			<td><ww:property value="dbeAftResidual"/></td>
		</tr>
	</ww:iterator>
</table>

	<table width="95%" border="0" cellspacing="0" cellpadding="0">
		<tr><td align="right">
		<ww:a href="javascript:ajaxF('getYuCunCancelLogForUser.action','tbAccount.id=%{tbAccount.id}','div1')" title="首页">&lt;</ww:a>
		<ww:if test="pageBean.curPage-2>0">
			<ww:a href="javascript:ajaxF('getYuCunCancelLogForUser.action','tbAccount.id=%{tbAccount.id}&pageBean.curPage=%{pageBean.curPage-2}','div1')" title="第%{pageBean.curPage-2}页"><ww:property value="pageBean.curPage-2"/></ww:a>
		</ww:if>
		<ww:if test="pageBean.curPage-1>0">
			<ww:a href="javascript:ajaxF('getYuCunCancelLogForUser.action','tbAccount.id=%{tbAccount.id}&pageBean.curPage=%{pageBean.curPage-1}','div1')"  title="第%{pageBean.curPage-1}页"><ww:property value="pageBean.curPage-1"/></ww:a>
		</ww:if>
			<ww:a href="javascript:ajaxF('getYuCunCancelLogForUser.action','tbAccount.id=%{tbAccount.id}&pageBean.curPage=%{pageBean.curPage}','div1')"  title="第%{pageBean.curPage}页"><ww:property value="pageBean.curPage"/></ww:a>
		<ww:if test="pageBean.curPage+1<=pageBean.maxPage">
			<ww:a href="javascript:ajaxF('getYuCunCancelLogForUser.action','tbAccount.id=%{tbAccount.id}&pageBean.curPage=%{pageBean.curPage+1}','div1')"  title="第%{pageBean.curPage+1}页"><ww:property value="pageBean.curPage+1"/></ww:a>
		</ww:if>
		<ww:if test="pageBean.curPage+2<=pageBean.maxPage">
			<ww:a href="javascript:ajaxF('getYuCunCancelLogForUser.action','tbAccount.id=%{tbAccount.id}&pageBean.curPage=%{pageBean.curPage+2}','div1')"  title="第%{pageBean.curPage+2}页"><ww:property value="pageBean.curPage+2"/></ww:a>
		</ww:if>
		<ww:a href="javascript:ajaxF('getYuCunCancelLogForUser.action','tbAccount.id=%{tbAccount.id}&pageBean.curPage=%{pageBean.maxPage}','div1')" title="末页">&gt;</ww:a>
		
	</td></tr>
	</table>
</ww:if>
<ww:else>	
	<div>没有记录！</div>
</ww:else>
	
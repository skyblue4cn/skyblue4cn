<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<ww:include value="/head.jsp"></ww:include>
<br>
<div class="text1" style="line-height:30px;"><ww:property value="tbPartment.company.strPartmentName"/>--<ww:property value="tbPartment.strPartmentName"/><br>预存帐户存入记录</div>
<br>
<table width="95%" border="0" cellspacing="0" cellpadding="0" class="table">
        <tr>
          <th scope="col" width="10%">序号</th>
          <th scope="col" width="15%">时间</th>
          <th scope="col" width="15%">存入金额(元)</th>
          <th scope="col" width="15%">帐户余额（元）</th>
          <th scope="col" width="15%">经手人</th>
          <th scope="col" width="30%">备注</th>
        </tr>
     <ww:iterator value="pageBean.dataList" status="index">
			<tr  <ww:if test="#index.index%2!=0">   bgcolor="#ededed" </ww:if>>
			<td><ww:property value="#index.index+1"/></td>
			<td><ww:date name="dtePayoutTime" format="yyyy-MM-dd"/></td>
			<td><ww:property value="dbePayoutNumber"/></td>
			<td><ww:property value="dbeAftResidual"/></td>
			<td><ww:property value="strSaveUserName"/></td>
			<td><ww:property value="strDesc"/></td>
		</tr>
	</ww:iterator>
</table>
<br>
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
	<div class="text2">没有记录！</div>
</ww:else>
	
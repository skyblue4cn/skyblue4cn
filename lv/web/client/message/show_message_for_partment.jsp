<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<ww:include value="/client_top.jsp"></ww:include>
<br>
<div class="text1">最新消息</div>
<br>
<!-- 修改于2010-6-18 2表示为月结算 -->
<ww:if test="#attr.tbUser.tbPartment.tbAccount.intPayTypeId == 2">
	<table width="80%" border="0" cellspacing="0" cellpadding="0" class="table">
	        <ww:iterator value="#msg" status="m" id="s">
	         <tr>
	        	<td align="left" style="color:red;"><ww:property value="#s"/>!</td>
	        </tr>
	        </ww:iterator>
	</table>
</ww:if>
<ww:if test="#attr.tbUser.tbPartment.tbAccount.intPayTypeId == 1">
	<table width="80%" border="0" cellspacing="0" cellpadding="0" class="table">
	        <ww:iterator value="#msg" status="m" id="s">
	         <tr>
	        	<td align="left" style="color:red;"><ww:property value="#s"/>!</td>
	        </tr>
	        </ww:iterator>
	</table>
</ww:if>
<table width="80%" border="0" cellspacing="0" cellpadding="0" class="table">
        <tr>
          <th scope="col" width="10%">序号</th>
          <th scope="col" width="80%" style="text-align: center;">内容</th>
             <th scope="col" width="10%">时间</th>
        </tr>
            <tr>
        	<td>&nbsp;</td>
        	<td align="left" style="color:blue;">欢迎使用武侯保险网上投保系统!</td>
        	<td>&nbsp;</td>
        </tr>
    <ww:iterator value="pageBean.dataList" status="index">
		<tr>
			<td><ww:property value="pageBean.curFromIndex + #index.index"/>.</td>
			<td align="left"><ww:property value="strContent"/></td>
			<td><ww:date name="dteTime" format="yy-MM-dd"/></td>
		</tr>
	</ww:iterator>
	<tr>
			<td><b>查询.</b></td>
			<td align="left"><b>输入保单号查看详细保单详细信息</b></td>
			<td>
			
			<!-- 根据表单号，查看表单详细信息 -->
			<form action="c_getBillInfoToPrint_getBillInfoByNumToPrint.action" method="post">
			
			<input name="listNumber" type="text"/><input type="submit" value="查询"/>
			</form>
			
			</td>
		</tr>
	<ww:if test="pageBean.dataList.size()==0">
		<tr><td align="center" colspan="4">没有信息！</td></tr>
	</ww:if>
</table>
<br/>
<b>被退回的保单.</b>
<table width="98%" border="0" cellspacing="0" cellpadding="0" class="table">
			        <tr>
			          <th scope="col" width="10%">种类</th>
			          <th scope="col" width="27%">保单号</th>
			          <th scope="col" width="15%">路线</th>
			          <th scope="col" width="6%">内宾</th>
			          <th scope="col" width="6%">外宾</th>
			          <th scope="col" width="30%">退回原因</th>
			          <th scope="col" width="6%">查看</th>
			        </tr>
			    <ww:iterator value="pageBeanReturn.dataList" status="index">
					<tr <ww:if test="#index.index%2!=0">    bgcolor="#ededed" 	</ww:if>>
					<td><ww:property value="strKindName"/></td>
					<td>
						<ww:property value="strBillNumber"/>
					</td>
					<td>
						<ww:if test="strTravelRold.length()>8">
							<ww:property value="strTravelRold.substring(0,8)"/>...
						</ww:if>
						<ww:else>
							<ww:property value="strTravelRold"/>
						</ww:else>
					</td>
					<td><ww:property value="intChinaTravelerNumber"/></td>
					<td><ww:property value="intOtherTravelerNumber"/></td>
					<td align="left">
						<ww:if test="strReturnReason!=null && strReturnReason.length()>18">
							<ww:property value="strReturnReason.substring(0,18)"/>...
						</ww:if>
						<ww:else>
							<ww:property value="strReturnReason"/>
						</ww:else>
					</td>
					<td align="center">
						<a href="c_getBillInfoToPrint.action?limitId=<ww:property value="id"/>&limitKey=<ww:property value="@cn.insurance.action.SupportAction@getEencodeIdKey('chakan',id)"/>&limitType=chakan" target="_blank">
							<img src="images/chakan.gif" border="0">
						</a>
					</td>
				</tr>
				</ww:iterator>
			</table><br />	
		
	<ww:if test="pageBeanReturn.dataList.size()!=0">
		<table width="98%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>&nbsp;</td>
				<td width="10%" align="center">
					当前第<ww:property value="pageBeanReturn.curPage"/>/<ww:property value="pageBeanReturn.maxPage"/>页
				</td>
				<td width="5%" align="right">
					<ww:if test="pageBeanReturn.curPage<=1">首页</ww:if>
					<ww:else>
					<a href="<ww:url><ww:param name="pageBeanReturn.curPage" value="1"></ww:param></ww:url>">首页</a>
					</ww:else>
				</td>
				<td width="5%" align="right">
					<ww:if test="pageBeanReturn.curPage<=1">上页</ww:if>
					<ww:else>
					<a href="<ww:url><ww:param name="pageBeanReturn.curPage" value="%{pageBeanReturn.curPage-1}"></ww:param></ww:url>">上页</a>
					</ww:else>
				</td>
				<td width="5%" align="right">
					<ww:if test="pageBeanReturn.curPage>=pageBeanReturn.maxPage">下页</ww:if>
					<ww:else>
					<a href="<ww:url><ww:param name="pageBeanReturn.curPage" value="%{pageBeanReturn.curPage+1}"></ww:param></ww:url>">下页</a>
					</ww:else>
				</td>
				<td width="5%" align="right">
					<ww:if test="pageBeanReturn.curPage>=pageBeanReturn.maxPage">末页</ww:if>
					<ww:else>
					<a href="<ww:url><ww:param name="pageBeanReturn.curPage" value="%{pageBeanReturn.maxPage}"></ww:param></ww:url>">末页</a>
					</ww:else>
				</td>
			</tr>
		</table>
			
		</ww:if>
		<ww:else>	
			<div>没有记录！</div>
		</ww:else>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>

<ww:include value="/head.jsp"></ww:include>
<br/>
<table border=0 width="80%" cellpadding="0" cellspacing="0">
	<tr>
		<td width="80%" class="text1"><ww:property value="tbPartment.strPartmentName"/>管理</td>
		<td><input type="button" value="添加部门" onclick="openAWindow('toAddPartment.action?id=<ww:property value="tbPartment.id"/>','400','400')" class="inputbutton"/></td>
	</tr>
</table>
<br/>
<table border=1 width="90%" cellpadding="0" cellspacing="0" class="table">
		<tr>
			<th scope="col" >名称</th>
			<th scope="col" >用户管理</th>
			<th scope="col" >详细信息</th>
			<th scope="col">帐户类型</th>
			<th scope="col">帐户余额</th>
			<th scope="col">帐户状态</th>
			<th scope="col">帐户设置</th>
			<th scope="col">存款</th>
			<th scope="col">存入记录</th>
			<th scope="col">支出记录</th>
			<th scope="col">月结记录</th>
			<th scope="col" >删除</th>
		</tr>
		<tr bgcolor="#ededed">
			<td><ww:property value="tbPartment.strPartmentName"/>(总社)</td>
			<td align="center">
				<a href="javascript:openAWindow('getAllUserByPartmentId.action?intPartmentId=<ww:property value="tbPartment.id"/>','400','400')">
					<img src="images/chakan.gif" border="0"/>
				</a>
			</td>
			<!--<td align="center">
				<a href="javascript:openAWindow('toAddLxsUser.action?intPartmentId=<ww:property value="tbPartment.id"/>','400','400')">
					<img src="images/tianjia.gif" border="0"/>
				</a>
			</td>
			-->
				
			<td align="center"><a href="javascript:openAWindow('toUpdateCompanyInfo.action?id=<ww:property value="tbPartment.id"/>','500','400')">
					<img src="images/biangeng.gif" border="0"/>
				</a>
			</td>
			<td align="center">
				<ww:if test="tbPartment.tbAccount.intPayTypeId==1">预存</ww:if>
				<ww:if test="tbPartment.tbAccount.intPayTypeId==2">月结</ww:if>
			</td>
			<td align="center">
				<ww:property value="tbPartment.tbAccount.dbeResidual"/>
			</td>
			<td align="center">
				<ww:if test="tbPartment.tbAccount.intAccountState==0">不可用</ww:if>
				<ww:if test="tbPartment.tbAccount.intAccountState==1">可用</ww:if>
			</td>
			<td align="center">
				<ww:a href="javascript:openAWindow('toUpdateAccountProperty.action?tbPartment.id=%{tbPartment.id}')">
					<img src="images/xiugai.gif" border="0">
				</ww:a>
			</td>
			<td align="center">
				<ww:a href="javascript:openAWindow('toCunKuanForAccount.action?id=%{tbPartment.tbAccount.id}')">
					<img src="images/biangeng.gif" border="0">
				</ww:a>
			</td>
			<td align="center">
				<a href="getYuCunCunLogForBx.action?tbPayOutInfo.intAccountId=<ww:property value="tbPartment.tbAccount.id"/>" target="_blank">
					<img src="images/chakan.gif" border="0">
				</a>
			</td>
			<td align="center">
				<a href="getYuCunPayLogForBx.action?tbPayOutInfo.intAccountId=<ww:property value="tbPartment.tbAccount.id"/>" target="_blank">
					<img src="images/chakan.gif" border="0">
				</a>
			</td>
			<td align="center">
				<a href="getMonthPayLogForBx.action?tbMonthPayInfo.intAccountId=<ww:property value="tbPartment.tbAccount.id"/>" target="_blank">
					<img src="images/chakan.gif" border="0">
				</a>
			</td>
			<td align="center">
				<a href="javascript:openAWindow('toDeletePartment.action?id=<ww:property value="tbPartment.id"/>','400','400')" style="color:red;">
					<img src="images/shanchu.gif" border="0"/>
				</a>
			</td>
			

		</tr>
	<ww:iterator value="pageBean.dataList" status="index">
			<tr  <ww:if test="#index.index%2!=0"> bgcolor="#ededed"</ww:if> >
			<td><ww:property value="strPartmentName"/></td>
			<td align="center">
				<a href="javascript:openAWindow('getAllUserByPartmentId.action?intPartmentId=<ww:property value="id"/>','400','400')">
					<img src="images/chakan.gif" border="0"/>
				</a>
			</td><!--
			<td align="center">
				<a href="javascript:openAWindow('toAddLxsUser.action?intPartmentId=<ww:property value="id"/>','400','400')">
					<img src="images/tianjia.gif" border="0"/>
				</a>
			</td>
			-->
			<td align="center"><a href="javascript:openAWindow('toUpdatePartmentInfo.action?id=<ww:property value="id"/>','400','400')">
				<img src="images/biangeng.gif" border="0"/>
				</a>
			</td>	
			<td align="center">
				<ww:if test="tbAccount.intPayTypeId==1">预存</ww:if>
				<ww:if test="tbAccount.intPayTypeId==2">月结</ww:if>
			</td>	
			<td align="center">
				<ww:property value="tbAccount.dbeResidual"/>
			</td>
			<td align="center">
				<ww:if test="tbAccount.intAccountState==0">不可用</ww:if>
				<ww:if test="tbAccount.intAccountState==1">可用</ww:if>
			</td>
			<td align="center">
				<ww:a href="javascript:openAWindow('toUpdateAccountProperty.action?tbPartment.id=%{id}')">
					<img src="images/xiugai.gif" border="0">
				</ww:a>
			</td>
			<td align="center">
				<ww:a href="javascript:openAWindow('toCunKuanForAccount.action?id=%{tbAccount.id}')">
					<img src="images/biangeng.gif" border="0">
				</ww:a>
			</td>
			<td align="center">
				<a href="getYuCunCunLogForBx.action?tbPayOutInfo.intAccountId=<ww:property value="tbAccount.id"/>" target="_blank">
					<img src="images/chakan.gif" border="0">
				</a>
			</td>
			<td align="center">
				<a href="getYuCunPayLogForBx.action?tbPayOutInfo.intAccountId=<ww:property value="tbAccount.id"/>" target="_blank">
					<img src="images/chakan.gif" border="0">
				</a>
			</td>
			<td align="center">
				<a href="getMonthPayLogForBx.action?tbMonthPayInfo.intAccountId=<ww:property value="tbAccount.id"/>" target="_blank">
					<img src="images/chakan.gif" border="0">
				</a>
			</td>
			<td align="center">
				<a href="javascript:openAWindow('toDeletePartment.action?id=<ww:property value="id"/>','400','400')" style="color:red;">
					<img src="images/shanchu.gif" border="0"/>
				</a>
			</td>
	

		</tr>
	</ww:iterator>
</table>
<br/>
	<table width="80%" border="0" cellspacing="0" cellpadding="0">
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

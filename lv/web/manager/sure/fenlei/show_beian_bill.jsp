<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<ww:include value="/top.jsp"/>
<body class="welcm">
	<table border="0" cellpadding="0" cellspacing="0" width="98%" height="450">
		<tr valign="top">
			<td width="10%" style="text-align:center">
				 <div class="tabcont">
			    	<br/>
			    	<br/>
			    	<br/>
			    	<Br/>
			    	<br>
			    	<br/>
			    	<input id="button1" class="inputbutton" type="button" onclick="location.href='getBillNeedBxSure.action'" value="待审核保单" /><br/><br/>
					<input id="button2" class="inputbutton3" type="button" onclick="location.href='getBillForBxBeiAn.action'" value="已备案保单" /><br/><br/>
					<input id="button3" class="inputbutton" type="button" onclick="location.href='getBillForBxHasSure.action'" value="已确认保单" /><br/><br/>
					<input id="button4" class="inputbutton" type="button" onclick="location.href='getBillForBxHasReturn.action'" value="已退回保单" />
				
	   		 	</div>
			</td>
			<td width="90%">
				<div id="contbar">
				      <div class="pind">
		    		<img src="images/yba.gif" width="140" height="55"  />
			        <div class="tsinfo">
			        <br/>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table">
					        <tr>
					         <th scope="col" width="10%">种类</th>
					         <th scope="col" width="20%">旅行社</th>
					         <th scope="col" width="20%">保单号</th>
					         <th scope="col" width="7%">内宾</th>
					         <th scope="col" width="7%">外宾</th>
					         <th scope="col" width="10%">起保日期</th>
					         <th scope="col" width="10%">终止日期</th>
					         <th scope="col" width="10%">原因</th>
					         <th scope="col" width="6%">处理</th>
					        </tr>
					         <ww:iterator value="pageBean.dataList" status="index">
								<tr <ww:if test="#index.index%2!=0"> bgcolor="#ededed"</ww:if>>
								<td align="center"><ww:property value="strKindName"/></td>
								
								<td>
									<ww:if test="tbPartment.company.strPartmentName.length()>15">
										<ww:property value="tbPartment.company.strPartmentName.substring(0,15)"/>
									</ww:if>
									<ww:else>
										<ww:property value="tbPartment.company.strPartmentName"/>
									</ww:else>
									
								</td>
								<td>
									<ww:property value="strBillNumber"/>
								</td>
								<td align="center"><ww:property value="intChinaTravelerNumber"/></td>
								<td align="center"><ww:property value="intOtherTravelerNumber"/></td>
								<td align="center"><ww:date name="dteStartTime" format="yy-MM-dd"/></td>
								<td align="center"><ww:date name="dteEndTime" format="yy-MM-dd"/></td>
								<td align="center"><ww:property value="intBeiAnReason"/></td>
								<td align="center">
									<a href="toSureBillByBX.action?limitId=<ww:property value="id"/>&limitKey=<ww:property value="@cn.insurance.action.SupportAction@getEencodeIdKey('sure',id)"/>&limitType=sure" target="_blank">
										<img src="images/queren.gif" border="0">
									</a>
								</td>
						</tr>
						</ww:iterator>
					</table>
					<br/>
					<ww:if test="pageBean.dataList.size()==0">
						<div style="text-align: center;font-weight: bold;color: black;">没有记录！</div>
					</ww:if>
					<br/>
					<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
						<tr>
							<td><li>备案原因说明：</li></td>
						</tr>
						<tr>
							<td>原因1： 散拼团人员名单不确定。</td>
						</tr>
						<tr>		
							<td>原因2： 部分游客名单不确定。</td>
						</tr>
						<tr>	
							<td>原因3： 保单没有总社负责人签章。</td>
						</tr>
					</table>
					</div>	
				 </div>
				 <br>
			      <table width="98%" border="0" cellspacing="0" cellpadding="0">
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
			</td>
		</tr>
	</table>

</body>


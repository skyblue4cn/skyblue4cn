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
			    	<input id="button1" class="inputbutton3" type="button" onclick="location.href='getAllNotReplyOfApplyForBx.action'" value="未处理申请" /><br/><br/>
					<input id="button2" class="inputbutton" type="button" onclick="location.href='getAllHasReplyOfApplyForBx.action'" value="已处理申请" /><br/><br/>
				
	   		 	</div>
			</td>
			<td>
				<div id="contbar">
				     
				<div class="pind">
						    		<img src="images/wclsq.gif" width="140" height="55"  />
							        <div class="tsinfo">
							        <br/>
				
					<table border="0" cellpadding="0" cellspacing="0" width="100%" class="table">
							<tr>
								<th scope="col" width="7%">序号</th>
								<th scope="col" width="25%">保单号</th>
								<th scope="col" width="19%">旅行社</th>
								<th scope="col" width="19%">部门</th>
								<th scope="col" width="10%">申请时间</th>
								<th scope="col" width="10%">状态</th>
								<th scope="col" width="10%">处理</th>
							</tr>
							<ww:iterator value="pageBean.dataList" status="index">
								<tr>
									<td align="center"><ww:property value="pageBean.curFromIndex + #index.index"/></td>
									<td>
										<a href="getBillInfoToPrint.action?id=<ww:property value="intBillId"/>" target="_blank">
											<ww:property value="strBillNumber"/>
										</a>
									</td>
									<td><ww:property value="tbPartment.company.strPartmentName"/></td>
									<td>
										<ww:if test="tbPartment.intParentId!=0">
											<ww:property value="tbPartment.strPartmentName"/>
										</ww:if>
										<ww:else>
											总社
										</ww:else>
									</td>
									<td><ww:date name="dteApplyTime" format="yy-MM-dd"/></td>
									<td align="center">
										<ww:if test="intIsReply==1">
											已回复
										</ww:if>
										<ww:else>
											未回复
										</ww:else>
									</td>
									<td align="center">
										<a href="toDealWithTheApplyBybx.action?tbApply.id=<ww:property value="id"/>" target="_blank">
											<img src="images/queren.gif" border="0"/>
										</a>
									</td>
							</ww:iterator>
					</table>
					<br/>
					<ww:if test="pageBean.dataList == null || pageBean.dataList.size()==0">
						<div class="text2">没有新的申请!</div>
					</ww:if>
					</div>
				 </div>
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

	
	
	
	
	
	
	
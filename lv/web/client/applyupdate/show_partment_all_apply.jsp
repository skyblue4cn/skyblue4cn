<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib  prefix="ww" uri="webwork"%>
<ww:include value="/head.jsp"></ww:include>
	<table border="0" cellpadding="0" cellspacing="0" width="90%" class="table">
			<tr>
				<th scope="col">序号</th>
				<th scope="col">保单号</th>
				<th scope="col">申请人</th>
				<th scope="col">时间</th>
				<th scope="col">内容</th>
				<th scope="col">状态</th>
				<th scope="col">经手人</th>
				<th scope="col">审核意见</th>
				<th scope="col">详情</th>
			</tr>
			<ww:iterator value="pageBean.dataList" status="index">
				<tr>
					<td><ww:property value="pageBean.curFromIndex + #index.index"/></td>
					<td><a href="getBillInfoToPrint.action?id=<ww:property value="intBillId"/>" target="blank"><ww:property value="strBillNumber"/></a></td>
					<td><ww:property value="strApplyUserName"/></td>
					<td><ww:date name="dteApplyTime" format="yyyy-MM-dd"/></td>
					<td>
						<ww:if test="strApplyContent!=null && strApplyContent.length()>10">
							<a href="javascript:alert('<ww:property value="strApplyContent"/>')">
								<ww:property value="strApplyContent.substring(0,10)"/>
							</a>
						</ww:if>	
						<ww:else>
							<a href="javascript:alert('<ww:property value="strApplyContent"/>')">
								<ww:property value="strApplyContent"/>
							</a>
						</ww:else>
					</td>
					<td>
						<ww:if test="intIsReply==1">
							<span style="color:green;">已回复</span>
						</ww:if>
						<ww:else>
							<span style="color:red;">未回复</span>
						</ww:else>
					</td>
					<td><ww:property value="strReplyUserName"/></td>
					<td>
						<ww:if test="strReplyContent!=null && strReplyContent.length()>10">
							<a href="javascript:alert('<ww:property value="strReplyContent"/>')">
								<ww:property value="strReplyContent.substring(0,10)"/>
							</a>
						</ww:if>	
						<ww:else>
							<a href="javascript:alert('<ww:property value="strReplyContent"/>')">
								<ww:property value="strReplyContent"/>
							</a>
						</ww:else>
					</td>
						
					
					
					<td>
						<form action="getApplyUpdateInfo.action" method="post" target="blank">
							<ww:hidden name="tbApply.id" value="%{id}"/>
							<input type="submit" value="查看" class="inputbutton">
						</form>
					</td>
			</ww:iterator>
	</table>
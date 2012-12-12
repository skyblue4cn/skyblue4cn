<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib  prefix="ww" uri="webwork"%>
<ww:include value="/client_top.jsp"></ww:include>
<table border="0" width="100%" align="center" cellpadding="0" cellspacing="0">
	<tr valign="top">
		<td valign="top" width="15%" align="center" style="background:#ededed;" height="540px">
			<div class="tabcont">
	    	<br/>
	    	<br/>
	    	<br/>
	    	<input id="button1" class="inputbutton" type="button" onclick="location.href='c_toManagerApplyUpdate.action'" value="填写新申请" /><br/><br/>
	    	<input id="button1" class="inputbutton" type="button" onclick="location.href='c_getNotReplyApply.action'" value="待审核申请" /><br/><br/>
	    	<input id="button2" class="inputbutton3" type="button" onclick="location.href='c_getHasReplyApply.action'" value="已审核申请" /><br/><br/>
					
	    </div>
		</td>
		<td>
			<br>
			<table border="0" cellpadding="0" cellspacing="0" width="90%">
				<tr>
					<td align="center" class="text1">已回复申请</td>
				</tr>
			</table>
			<br>
			<table border="0" cellpadding="0" cellspacing="0" width="98%" class="table">
					<tr>
						<th scope="col" width="10%">序号</th>
						<th scope="col" width="20%">保单号</th>
						<th scope="col" width="10%">申请人</th>
						<th scope="col" width="10%">时间</th>
						<th scope="col" width="20%">内容</th>
						<th scope="col" width="20%">审核意见</th>
						<th scope="col" width="10%">详情</th>
					</tr>
					<ww:iterator value="pageBean.dataList" status="index">
						<tr <ww:if test="#index.index%2!=0">    bgcolor="#ededed" 	</ww:if>>
							<td><ww:property value="pageBean.curFromIndex + #index.index"/></td>
							<td><a href="c_getBillInfoToPrint.action?limitId=<ww:property value="intBillId"/>&limitKey=<ww:property value="@cn.insurance.action.SupportAction@getEencodeIdKey('ckbill',intBillId)"/>&limitType=ckbill" target="_blank"><ww:property value="strBillNumber"/></a></td>
							<td><ww:property value="strApplyUserName"/></td>
							<td><ww:date name="dteApplyTime" format="yyyy-MM-dd"/></td>
							<td align="left">
								<ww:if test="strApplyContent!=null && strApplyContent.length()>20">
									<ww:property value="strApplyContent.substring(0,20)"/>...
								</ww:if>	
								<ww:else>
										<ww:property value="strApplyContent"/>
								</ww:else>
							</td>
							<td align="left">
								<ww:if test="strReplyContent!=null && strReplyContent.length()>20">
										<ww:property value="strReplyContent.substring(0,20)"/>...
								</ww:if>	
								<ww:else>
										<ww:property value="strReplyContent"/>
								</ww:else>
							</td>
							<td>
								<a href="c_getApplyUpdateinfo.action?limitId=<ww:property value="id"/>&limitKey=<ww:property value="@cn.insurance.action.SupportAction@getEencodeIdKey('ckapply',id)"/>&limitType=ckapply" target="_blank">
									<img src="images/chakan.gif" border="0">
								</a>
							</td>
					</ww:iterator>
			</table>
				<br/>
		<ww:if test="pageBean.dataList.size()!=0">
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
			
		</ww:if>
		<ww:else>	
			<div>没有记录！</div>
		</ww:else>
		</td>
	</tr>
</table>
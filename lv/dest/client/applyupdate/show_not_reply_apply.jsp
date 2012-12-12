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
	    	<input id="button1" class="inputbutton3" type="button" onclick="location.href='c_getNotReplyApply.action'" value="待审核申请" /><br/><br/>
	    	<input id="button2" class="inputbutton" type="button" onclick="location.href='c_getHasReplyApply.action'" value="已审核申请" /><br/><br/>
					
	    </div>
		</td>
		<td>
			<br>
			<table border="0" cellpadding="0" cellspacing="0" width="90%">
				<tr>
					<td align="center" class="text1">待审核申请</td>
				</tr>
			</table>
			<br>
			<table border="0" cellpadding="0" cellspacing="0" width="98%" class="table">
				<tr>
					<th scope="col" width="8%">序号</th>
					<th scope="col" width="25%">保单号</th>
					<th scope="col" width="10%">申请人</th>
					<th scope="col" width="10%">时间</th>
					<th scope="col" width="27%">内容</th>
					<th scope="col" width="10%">状态</th>
					<th scope="col" width="10%">修改</th>
				</tr>
				<ww:iterator value="pageBean.dataList" status="index">
					<tr <ww:if test="#index.index%2!=0">    bgcolor="#ededed" 	</ww:if>>
						<td><ww:property value="pageBean.curFromIndex + #index.index"/></td>
						<td><a href="c_getBillInfoToPrint.action?limitId=<ww:property value="intBillId"/>&&limitKey=<ww:property value="@cn.insurance.action.SupportAction@getEencodeIdKey('ckbill',intBillId)"/>&limitType=ckbill" target="_blank"><ww:property value="strBillNumber"/></a></td>
						<td><ww:property value="strApplyUserName"/></td>
						<td><ww:date name="dteApplyTime" format="yyyy-MM-dd"/></td>
						<td align="left">
							<ww:if test="strApplyContent!=null && strApplyContent.length()>25">
									<ww:property value="strApplyContent.substring(0,25)"/>...
							</ww:if>	
							<ww:else>
									<ww:property value="strApplyContent"/>
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
						
						
						<td>
							<a href="c_toUpdateApplyUpdate.action?limitId=<ww:property value="id"/>&limitKey=<ww:property value="@cn.insurance.action.SupportAction@getEencodeIdKey('applyupdate',id)"/>&limitType=applyupdate" target="_blank">
								<img src="images/xiugai.gif" border="0" />
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
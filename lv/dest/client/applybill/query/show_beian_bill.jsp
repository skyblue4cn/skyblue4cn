<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="webwork" prefix="ww" %>

	<ww:include value="/client_top.jsp"/>
	<script type="text/javascript">
<!--	
	function applyNewBill(){
		var fobj = document.forms['form1'] ;
		if(fobj.billkind.value=='-1'){
			alert('请选择保单种类！') ;
			return ;
		}
		fobj.submit() ;
	}
	
	
//-->
</script>
	
	
	
<div>	
	<table width="100%" border="0"  cellspacing="0" cellpadding="0" >
  <tr valign="top">
      <td width="15%" valign="top" align="center" style="background:#ededed;" height="540px">
	    <div class="tabcont">
	    	<br/>
	    	<br/>
	    	<br/>
	    	<input id="button1" class="inputbutton" type="button" onclick="location.href='c_toApplyNewBill.action'" value="填写新保单" /><br/><br/>
	    	<input id="button1" class="inputbutton" type="button" onclick="location.href='c_showReferBillList.action'" value="待审核保单" /><br/><br/>
	    	<input id="button2" class="inputbutton3" type="button" onclick="location.href='c_showBeiAnBillList.action'" value="已备案保单" /><br/><br/>
			
			<input id="button3" class="inputbutton" type="button" onclick="location.href='c_showEffectedBillList.action'" value="已确认保单" /><br/><br/>
			<input id="button4" class="inputbutton" type="button" onclick="location.href='c_showReturnBillList.action'" value="已退回保单" />
					
	    </div>
    </td>
    <td valign="top">
    <div class="tabcont" id="billContent">
    	<br>
      	<div style="text-align: center;font-weight: bold; font-size: 16px;">已备案保单</div>
		<br/>
			<table width="98%" border="0" cellspacing="0" cellpadding="0" class="table">
			        <tr>
		           		<th scope="col" width="10%">种类</th>
			            <th scope="col" width="27%">保单号</th>
			            <th scope="col" width="15%">路线</th>
			            <th scope="col" width="6%">内宾</th>
			            <th scope="col" width="6%">外宾</th>
			            <th scope="col" width="10%">起保日期</th>
			            <th scope="col" width="10%">终止日期</th>
			          	<th scope="col" width="10%">备案原因</th>
			          	<th scope="col" width="6%">修改</th>
			        </tr>
			        <ww:iterator value="pageBean.dataList" status="index">
						<tr  <ww:if test="#index.index%2!=0"> bgcolor="#ededed" </ww:if>>
						<td><ww:property value="strKindName"/></td>
						<td>
							<ww:property value="strBillNumber"/>
						</td>
						<td align="left">
							<ww:if test="strTravelRold.length()>8">
								<ww:property value="strTravelRold.substring(0,8)"/>...
							</ww:if>
							<ww:else>
								<ww:property value="strTravelRold"/>
							</ww:else>
						</td>
						<td><ww:property value="intChinaTravelerNumber"/></td>
						<td><ww:property value="intOtherTravelerNumber"/></td>
						<td><ww:date name="dteStartTime" format="yy-MM-dd"/></td>
						<td><ww:date name="dteEndTime" format="yy-MM-dd"/></td>
						<td><ww:property value="intBeiAnReason"/></td>
						<td align="center">
							<a href="c_toUpdateBeiAnBillByUser.action?limitId=<ww:property value="id"/>&limitKey=<ww:property value="@cn.insurance.action.SupportAction@getEencodeIdKey('beian',id)"/>&limitType=beian" target="_blank">
								<img src="images/xiugai.gif" border="0">
							</a>
						</td>
					</tr>
				</ww:iterator>
			</table>
			<br/>
		
		<ww:if test="pageBean.dataList.size()==0">
			<div style="text-align: center;font-weight: bold;color: black;">没有记录！</div>
		</ww:if>
		<ww:else>
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
		</ww:else>
		</div>
		<br/>
		<table width="98%" border="0" cellspacing="0" cellpadding="0" style="text-align:left;line-height:25px;">
			<tr>
				<td><li style="color:red;text-align:left;">重要说明：备案原因对应下面的三种情况，请根据原因及时修改保单，否则过期则保单无效，保险公司不承担保险责任！</li></td>
			</tr>
			<tr>
				<td>原因1： 散拼团人员名单不确定。请最迟于旅游起始当日15：00以前回传确定，否则该保险单无效，保险公司不承担保险责任。</td>
			</tr>
			<tr>		
				<td>原因2： 部分游客名单不确定。请最迟于旅游起始当日15：00以前回传确定否则该保险单无效，保险公司不承担保险责任。</td>
			</tr>
			<tr>	
				<td>原因3： 总社设定保单需负责人签章，但该保单未得到总社签章，请负责人及时签章，否则保险公司不承担保险责任。</td>
			</tr>
		</table>
  
  </td>
  </tr>
</table>

</div>


<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/client_top.jsp"></ww:include>
<div>	
	<table width="100%" border="0"  cellspacing="0" cellpadding="0">
  <tr valign="top">
    <td width="15%" valign="top" align="center" height="540px" style="background:#ededed;" >
	    <div class="tabcont">
	    	
	    	<br/>
	    	<br/>
	    	<br/>
	    	<input id="button1" class="inputbutton" type="button" onclick="location.href='c_toShowPartmentBill.action'" value="各部门保单" /><br/><br/>
	    	<input id="button2" class="inputbutton3" type="button" onclick="location.href='c_toShowPartmentMonthFee.action'" value="各部门月费" /><br/><br/>
		</div>	     
			     
    </td>
    <td valign="top">
    <br/>
    <div class="tabcont" id="div1">
    	<table width="98%" border="0" cellspacing="0" cellpadding="0" >
    		<tr height="50px;" valign="middle">
    			<td width="100%"><div class="text1">月费清单</div></td>
    		</tr>
    		<tr>
    			<td align="right">
    				<form action="c_toShowPartmentMonthFee.action" method="get">
    					<span style="color:blue;">部门:</span>
    					<ww:select name="partmentId" list="partmentList" headerKey="0" headerValue="所有部门" listKey="id" listValue="strPartmentName" theme="simple" onchange="this.form.submit();" cssStyle="width:200px;"/>
    					<span style="color:blue;">时间:</span>
    					<ww:select name="year" list="#{2009:'2009年',2010:'2010年',2011:'2011年',2012:'2012年',2013:'2013年',2014:'2014年',2015:'2015年'}" theme="simple" onchange="this.form.submit();" cssStyle="width:100px;"/>
    					&nbsp;&nbsp;
    					<ww:select name="month" list="#{1:'1月',2:'2月',3:'3月',4:'4月',5:'5月',6:'6月',7:'7月',8:'8月',9:'9月',10:'10月',11:'11月',12:'12月'}" theme="simple" onchange="this.form.submit();" cssStyle="width:100px;"/>
    				</form>
    			</td>
    		</tr>
    	</table>
    	<br>
		<table width="98%" border="0" cellspacing="0" cellpadding="0" class="table">
		        <tr>
		          <th scope="col">序号</th>
		          <th scope="col">部门</th>
		          <th scope="col">时间</th>
		          <th scope="col">应付</th>
		          <th scope="col">状态</th>
		          <th scope="col">支付日期</th>
		          <th scope="col" >应付清单</th>
		        </tr>
		        <!-- 不隐藏记录的 -->
		      <ww:if test="#session.tbUser.tbPartment.isShowHistoryBill == 0">  
			     <ww:iterator value="monthPayInfoList" status="index">
					<tr <ww:if test="#index.index%2!=0">   bgcolor="#ededed" </ww:if>>
					<td><ww:property value="#index.index+1"/></td>
					<td align="left"><ww:property value="tbPartment.strPartmentName"/></td>
					<td>
						<ww:date name="dteStartTime" format="yy-MM-dd"/>/
						<ww:if test="dteEndTime.before(new java.util.Date())">
							<ww:date name="dteEndTime" format="yy-MM-dd"/>
						</ww:if>
						<ww:else>
							至今
						</ww:else>
					</td>
					<td><ww:property value="dbeNeedToPay"/></td>
					<td>
						<ww:if test="intIsPay==0"><span style="color:red;">未交费</span></ww:if>
						<ww:else>
							已交费
						</ww:else>
					</td>
					<td>
						<ww:date name="tbMonthPayOutLog.dtePayDate" format="yyyy-MM-dd"/>
					</td>
					<td>
						<a href="c_getBillListByMonthIdForUser.action?limitId=<ww:property value="id"/>&limitKey=<ww:property value="@cn.insurance.action.SupportAction@getEencodeIdKey('mlist',id)"/>&limitType=mlist" target="_blank">
							<img src="images/chakan.gif" border="0"/>
						</a>
					</td>
				</tr>
				</ww:iterator>
			</ww:if>
			<!-- 要隐藏记录的就将已交费的隐藏 -->
			<ww:else>
				<ww:iterator value="pageBean.dataList" status="index">
					<ww:if test="intIsPay==0">
					<tr <ww:if test="#index.index%2!=0">   bgcolor="#ededed" 	</ww:if>>
						<td><ww:property value="pageBean.curFromIndex + #index.index"/></td>
						<td>
							<ww:date name="dteStartTime" format="yy-MM-dd"/>/
							<ww:if test="dteEndTime.before(new java.util.Date())">
								<ww:date name="dteEndTime" format="yy-MM-dd"/>
							</ww:if>
							<ww:else>
								至今
							</ww:else>
						</td>
						<td><ww:property value="dbeNeedToPay"/></td>
						<td>
							<ww:property value="dbeHfFee"/>
						</td>
						<td>
							<span style="color:red;">未交费</span>					
						</td>
						<td>
							<ww:date name="tbMonthPayOutLog.dtePayDate" format="yyyy-MM-dd"/>
						</td>
						<td>
							<a href="getBillListByMonthIdForUser.action?tbMonthPayInfo.id=<ww:property value="id"/>" target="_blank">
								<img src="images/chakan.gif" border="0"/>
							</a>
						</td>
					</tr>
				</ww:if>
				</ww:iterator>
			</ww:else>
		</table>
		
		<ww:if test="monthPayInfoList==null || monthPayInfoList.size == 0">
			<div class="text2">没有记录！</div>
		</ww:if>
  		
  	
  	
  	</div>
  
  </td>
  </tr>
</table>

</div>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>


<ww:include value="/client_top.jsp"></ww:include>
<div>	
	<table width="100%" border="0"  cellspacing="0" cellpadding="0">
  <tr valign="top">
    <td width="15%" valign="top" align="center" height="540px" style="background:#ededed;" >
     	<br>
       <br>
       <br>
       <br>
	    <div class="tabcont">
	    	
	    	<input class="inputbutton" type="button" 
	    		onclick="location.href='c_toJieSuanByUser.action'" value="帐户信息" /><br/><br/>
		    
		    <input class="inputbutton3" type="button" 
		    	onclick="location.href='c_getMonthPayLogForUser.action'" 
		    	value="月结记录" /><br/><br/>
    		
    		<input type="button" class="inputbutton" value="存入记录"
				onclick="location.href='c_getYuCunCunLogForUser.action'"
			/><br/><br/>
			
			<input type="button" class="inputbutton" value="支出记录"
				onclick="location.href='c_getYuCunPayLogForUser.action'"
			/>
			
			<br/><br/>
			<!-- 
			<input type="button" class="inputbutton" value="退费记录"
				onclick="ajaxF('getYuCunReturnLogForUser.action','tbAccount.id=<ww:property value="tbAccount.id"/>','div1')"
			/><br/><br/>
			 -->
		</div>	     
			     
    </td>
    <td valign="top">
    <br/>
    <div class="tabcont" id="div1">
    	<br>
		<div class="text1">月结帐户支付记录</div>
		<br/>
		<table width="98%" border="0" cellspacing="0" cellpadding="0" class="table">
		        <tr>
		          <th scope="col">序号</th>
		          <th scope="col">时间</th>
		          
		          <th scope="col">应付</th>
		         
		          <th scope="col">状态</th>
		          <th scope="col">支付日期</th>
		          <th scope="col" >应付清单</th>
		        </tr>
		        <!-- 不隐藏记录的 -->
		      <ww:if test="#session.tbUser.tbPartment.isShowHistoryBill == 0">  
			     <ww:iterator value="pageBean.dataList" status="index">
					<tr <ww:if test="#index.index%2!=0">   bgcolor="#ededed" </ww:if>>
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
		
		<ww:if test="pageBean.dataList.size()!=0">
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
		</ww:if>
		<ww:else>	
			<div class="text2">没有记录！</div>
		</ww:else>
		<br/>
  		
  	
  	
  	</div>
  
  </td>
  </tr>
</table>

</div>

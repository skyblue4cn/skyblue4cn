<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/client_top.jsp"></ww:include>

<div>	
	<table width="100%" border="0"  cellspacing="0" cellpadding="0">
  <tr valign="top">
    <td width="15%" valign="top" align="center" height="540px" style="background:#ededed;">
     	<br>
       <br>
       <br>
       <br>
	    <div class="tabcont">
	    	
	    	<input class="inputbutton" type="button" 
	    		onclick="location.href='c_toJieSuanByUser.action'" value="帐户信息" /><br/><br/>
		    
		    <input class="inputbutton" type="button" 
		    	onclick="location.href='c_getMonthPayLogForUser.action'" 
		    	value="月结记录" /><br/><br/>
    		
    		<input type="button" class="inputbutton" value="存入记录"
				onclick="location.href='c_getYuCunCunLogForUser.action'"
			/><br/><br/>
			
			<input type="button" class="inputbutton3" value="支出记录"
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
   <td width="85%" valign="top"  style="border-bottom: 2px thin solid #ccc" >
    
    <div class="tabcont">
		<br>      
		<div class="text1">预存帐户支付记录</div>
		<br/>
		<table width="98%" border="0" cellspacing="0" cellpadding="0" class="table">
		        <tr>
		          <th scope="col">序号</th>
		          <th scope="col">时间</th>
		          <th scope="col">支付金额(元)</th>
		          <th scope="col">帐户余额（元）</th>
		           <th scope="col">备注</th>
		        </tr>
		     <ww:iterator value="pageBean.dataList" status="index">
				<tr <ww:if test="#index.index%2!=0">   bgcolor="#ededed" </ww:if>>
					<td><ww:property value="#index.index+1"/></td>
					<td><ww:date name="dtePayoutTime" format="yyyy-MM-dd"/></td>
					<td><ww:property value="dbePayoutNumber"/></td>
					<td><ww:property value="dbeAftResidual"/></td>
					<td><ww:property value="strDesc"/></td>
				</tr>
			</ww:iterator>
		</table>
		<br>
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
			
      </div>
      </td>  
  </tr>
</table>

</div>

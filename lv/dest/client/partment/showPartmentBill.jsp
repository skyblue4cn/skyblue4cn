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
	<table width="100%" border="0"  cellspacing="0" cellpadding="0">
  <tr valign="top">
      <td valign="top" width="15%" align="center" style="background:#ededed;" height="540px">
	    <div class="tabcont">
	    	<br/>
	    	<br/>
	    	<br/>
	    	<input id="button1" class="inputbutton3" type="button" onclick="location.href='c_toShowPartmentBill.action'" value="各部门保单" /><br/><br/>
	    	<input id="button2" class="inputbutton" type="button" onclick="location.href='c_toShowPartmentMonthFee.action'" value="各部门月费" /><br/><br/>
			
					
	    </div>
    </td>
    <td valign="top">
    <div class="tabcont" id="billContent">
    	<table width="98%" border="0" cellspacing="0" cellpadding="0" >
    		<tr height="50px;">
    			<td width="100%"><div class="text1">已确认保单</div></td>
    		</tr>
    		<tr>
    			<td align="right">
    				<form action="c_toShowPartmentBill.action" method="get">
    				<span style="color:blue;">部门:</span>
    				<ww:select name="partmentId" list="partmentList" headerKey="0" headerValue="所有部门" listKey="id" listValue="strPartmentName" theme="simple" onchange="this.form.submit();" cssStyle="width:200px;"/>
    				</form>
    			</td>
    		</tr>
    	</table>
      	
		<br/>
		<table width="98%" border="0" cellspacing="0" cellpadding="0" class="table">
		        <tr>
		         <th scope="col" width="15%">部门</th>
		         <th scope="col" width="10%">种类</th>
		          <th scope="col" width="27%">保单号</th>
		          <th scope="col" width="6%">内宾</th>
		          <th scope="col" width="6%">外宾</th>
		          <th scope="col" width="10%">起保日期</th>
		          <th scope="col" width="10%">终止日期</th>
		        
		          <th scope="col" width="10%">保费</th>
		         
		           <th scope="col" width="6%">查看</th>
		        </tr>
		    <ww:iterator value="pageBean.dataList" status="index">
					<tr <ww:if test="#index.index%2!=0">    bgcolor="#ededed" 	</ww:if>>
					<td align="left">
						<ww:property value="tbPartment.strPartmentName"/>
					</td>
					<td><ww:property value="strKindName"/></td>
					<td>
						<ww:property value="strBillNumber"/>
					</td>
				
					<td><ww:property value="intChinaTravelerNumber"/></td>
					<td><ww:property value="intOtherTravelerNumber"/></td>
					<td><ww:date name="dteStartTime" format="yy-MM-dd"/></td>
					<td><ww:date name="dteEndTime" format="yy-MM-dd"/></td>
					
					<td><ww:property value="dbeAllFee"/></td>
					
					<td align="center">
						<a href="c_getBillInfoToPrint.action?limitId=<ww:property value="id"/>&limitKey=<ww:property value="@cn.insurance.action.SupportAction@getEencodeIdKey('chakan',id)"/>&limitType=chakan" target="_blank">
							<img src="images/chakan.gif" border="0">
						</a>
					</td>
			</tr>
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
		</div>
  </td>
  </tr>
</table>

</div>


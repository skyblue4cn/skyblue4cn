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
	    	
	    	<input class="inputbutton3" type="button" 
	    		onclick="location.href='c_toJieSuanByUser.action'" value="帐户信息" /><br/><br/>
		    
		    <input class="inputbutton" type="button" 
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
   <td width="85%" valign="top"  style="border-bottom: 2px thin solid #ccc">
    <br>
    <div class="tabcont" >
	       <table width="100%" border="0" cellpadding="0" cellspacing="0" style="size: 14px;font-weight: bold;" align="center">
	       		<tr height="50px">
	       			<td colspan="2" class="text1" style="border-bottom:1px solid #000;" valign="middle">帐户信息</td>
	       		</tr>
		    
	        </table>
	        <br>
	         <table width="80%" border="0" cellpadding="0" cellspacing="0" class="table" >
        	
	           <tr height="30px">
					<td  width="20%" align="left">当前帐户余额:</td>
					<td align="left"><ww:property value="tbAccount.dbeResidual"/>元</td>
					<td width="50%" align="left">帐户余额指在系统帐户剩余的金额,如果为负，则表示欠费</td>
				</tr>
				
				<tr>
					<td align="left">帐户类型:</td>
					<td align="left">
						<ww:radio name="tbAccount.intPayTypeId" list="#{2:'月结',1:'预付'}" value="%{tbAccount.intPayTypeId}" theme="simple" disabled="true"/>
					</td>
					<td width="50%" align="left">帐户类型是指保单的结算方式</td>
				</tr>
				<tr>
					<td align="left">投保许可：</td>
					<td align="left"><ww:radio name="tbAccount.intAccountState" list="#{1:'可用',0:'不可用'}" value="%{tbAccount.intAccountState}" theme="simple" disabled="true"/></td>
					<td width="50%" align="left">帐户可用表示允许投保，不可用表示不允许投保</td>
				</tr>
				<tr>
					<td align="left">最多允许欠费金额:</td>
					<td align="left"><ww:property value="tbAccount.dbeAcceptMaxMoney"/>元</td>
					<td width="50%" align="left">保险公司允许欠费的最大金额，超过此金额则不允许投保</td>
				</tr>
				<tr>
					<td align="left">最多允许欠费天数：</td>
					<td align="left"><ww:property value="tbAccount.intAcceptDays"/>天</td>
					<td width="50%" align="left">保险公司允许欠费的最大天数，超过此时间则不允许投保,请及时缴费</td>
				</tr>
				
		     </table>
      </div>
      </td>  
  </tr>
</table>

</div>

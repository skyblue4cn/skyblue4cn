<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 	String local=request.getServletPath();
   //	out.println(request.getServletPath());
%>
<html>
<head>
	<base href="<%=basePath%>">
	<title>处理保单</title>
	<link rel="stylesheet" type="text/css" href="css/mainst.css"/>
	<script type="text/javascript" src="js/myjs.js"></script>
	<script type="text/javascript" src="js/prototype.js"></script>
	<script type="text/javascript" src="js/meizzDate.js"></script>
	<script type="text/javascript">
		function sendSureResult(action){
			var formObj = document.forms['form1'] ;
			formObj.action = action ;
			formObj.submit() ;
		}
	</script>
</head>
<body>
<div id="lvdbtable" style="width:100%;margin:auto;">
  <table width="95%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td colspan="2"><div class="tabcont2"><span class="lvtitle"><ww:property value="tbBill.strBillName"/></span></div>
        <div class="tabcont2">
          <table width="90%" border="0" cellspacing="0" cellpadding="0" class="table">
            <tr>
              <td width="10%">填写时间</td>
              <td width="40%"><ww:date name="tbBill.dteApplyDate" format="yyyy-MM-dd"/></td>
              <td width="10%">签发人</td>
              <td width="40%"><ww:property value="tbBill.strSignatoryName" /></td>
            </tr>
              <tr>
              <td width="10%">保单种类</td>
              <td width="40%">
              	<ww:if test="tbBill.intKindId == 1">
              		责任险
              	</ww:if>
              	<ww:if test="tbBill.intKindId == 2">
              		意外险
              	</ww:if>
              </td>
              <td width="10%">旅游类型</td>
              <td width="40%">
              	<ww:if test="tbBill.intTravelProperty == 0">
              		国内旅游
              	</ww:if>
              	<ww:if test="tbBill.intTravelProperty == 1">
              		出入境旅游
              	</ww:if>
              </td>
            </tr>
          </table>
        </div>
        <div class="tabcont2">
          <table width="90%" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="21%">投保人（旅行社）名称</td>
              <td colspan="3"><ww:property value="tbBill.strCompanyName"/></td>
              <td width="10%">保单号</td>
              <td width="28%"><ww:property value="tbBill.strBillNumber"/></td>
            </tr>
            <tr>
              <td>部门名称（盖章）</td>
              <td width="17%"><ww:property value="tbBill.strPartmentName"/></td>
              <td width="9%">导游</td>
              <td width="15%"><ww:property value="tbBill.strDragoman" /></td>
              <td>团队号</td>
              <td><ww:property value="tbBill.strTeamNumber"/></td>
            </tr>
            <tr>
              <td>电话</td>
              <td><ww:property value="tbBill.strPhoneNumber"/></td>
              <td>传真</td>
              <td><ww:property value="tbBill.strFax"/></td>
              <td>旅游线路</td>
              <td><ww:property value="tbBill.strTravelRold"/></td>
            </tr>
            <tr>
              <td>组团形式</td>
              <td colspan="3">
              	<ww:radio name="tbBill.intTeamType" list="#{0:'组团社',1:'地接社'}" theme="simple" value="%{tbBill.intTeamType}" disabled="true"/>
              </td>
              <td colspan="2" align="left">旅游起止时间</td>
            </tr>
            <tr>
              <td>旅游形式</td>
              <td colspan="3">
              	<ww:radio name="tbBill.intTravelType" list="#{0:'常规',1:'自驾车',2:'自由人'}" value="%{tbBill.intTravelType}" theme="simple" disabled="true"/>
              </td>
              <td colspan="2">自<ww:date name="tbBill.dteStartTime" format="yyyy-MM-dd"/></td>
            </tr>
            <tr>
              <td>高风险旅游项目</td>
              <td colspan="3">
              	<ww:radio name="tbBill.intIsHasHighDanger" list="#{0:'无',1:'有'}" value="%{tbBill.intIsHasHighDanger}" theme="simple" disabled="true"/>
              </td>
              <td colspan="2">至<ww:date name="tbBill.dteEndTime" format="yyyy-MM-dd"/></td>
            </tr>
          </table>
        </div>
        <div class="tabcont2">
          <table width="90%" border="0" cellpadding="0" cellspacing="0" class="table">
            <tr>
              <td width="15%">承保保险公司</td>
              <td colspan="4">人民保险成都市武侯支公司</td>
            </tr>
            <tr>
              <td>旅游者人数</td>
              <td width="15%">内宾（人）</td>
              <td width="25%"><ww:property value="tbBill.intChinaTravelerNumber"/></td>
              <td width="15%">外宾（人）</td>
              <td width="30%"><ww:property value="tbBill.intOtherTravelerNumber" /></td>
            </tr>
            <ww:if test="tbBill.intKindId==1">
            <tr>
              <td rowspan="2">保险金额</td>
              <td>内宾（万元）</td>
              <td><ww:property value="@cn.insurance.commonwords.BaoFeiRule@getNBBaoFeiDesc(tbBill.intKindId,tbBill.intTravelProperty)"/></td>
              <td>外宾（万元）</td>
              <td><ww:property value="@cn.insurance.commonwords.BaoFeiRule@getWBBaoFeiDesc(tbBill.intKindId,tbBill.intTravelProperty)"/></td>
            </tr>
            <tr>
              <td colspan="4">医疗费用限额4万元，丧葬费1万元。</td>
            </tr>
             </ww:if>
            <ww:else>
            	 <ww:if test="tbBill.intTravelProperty==0">
		           		<tr>
		            		<td rowspan="3">保险金额</td>
		            		<td>内宾(万元/人)</td>
		            		<td colspan="3"><ww:radio name="tbBill.intNBFeeType" list="#{0:'10/意外死亡伤残+2/意外医疗+0.5/丧葬',1:'20/意外死亡伤残+2/意外医疗+0.5/丧葬'}"   value="%{tbBill.intNBFeeType}" disabled="true" theme="my" onchange="getBillPrice()"/></td>
		            	</tr>
		            	<tr>
		            		<td>外宾(万元/人)</td>
		            		<td colspan="3"><ww:radio name="tbBill.intWBFeeType" list="#{0:'30/意外死亡伤残+3/意外医疗+2/丧葬',1:'40/意外死亡伤残+3/意外医疗+2/丧葬'}" value="%{tbBill.intWBFeeType}" disabled="true" theme="my" onchange="getBillPrice()"/></td>
		            	</tr>
	           	</ww:if>
	           	<ww:else>
	           		<tr>
		            		<td rowspan="3">保险金额</td>
		            		<td>内宾(万元/人)</td>
		            		<td colspan="3"><ww:radio name="tbBill.intNBFeeType" list="#{2:'30/意外死亡伤残+3/意外医疗+2/丧葬',3:'40/意外死亡伤残+3/意外医疗+2/丧葬'}" value="%{tbBill.intNBFeeType}" disabled="true" theme="my" onchange="getBillPrice()"/></td>
		            	</tr>
		            	<tr>
		            		<td>外宾(万元/人)</td>
		            		<td colspan="3"><ww:radio name="tbBill.intWBFeeType" list="#{0:'30/意外死亡伤残+3/意外医疗+2/丧葬',1:'40/意外死亡伤残+3/意外医疗+2/丧葬'}" value="%{tbBill.intWBFeeType}" disabled="true" theme="my" onchange="getBillPrice()"/></td>
		            	</tr>
	           	</ww:else>
            	<tr>	
            		<td>未成年人(万元/人)</td>
            		<td colspan="3"><ww:property value="@cn.insurance.commonwords.BaoFeiRule@getWCNBaoFeiDesc(tbBill.intKindId,tbBill.intTravelProperty)"/></td>
            	</tr>
            </ww:else>
            </table>
           </div>
           <div class="tabcont2" id="feeDiv">
            <table width="90%" border="0" cellpadding="0" cellspacing="0" class="table">
           
            <tr>
              <td width="10%">保险费</td>
              <td width="15%">内宾（元/人）</td>
              <td width="25%"><ww:property value="tbBill.dbeChinaFee"/></td>
              <td width="15%">外宾（元/人）</td>
              <td width="25%"><ww:property value="tbBill.dbeOtherFee"/></td>
            </tr>
           
            <tr>
              <td>保费合计(元)</td>
              <td colspan="4"><ww:property value="tbBill.dbeAllFee"/></td>
            </tr>
          </table>
        </div>
  		<br/>
       	<div class="tabcont2">
          <div class="bxuserbox">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" >
              <tr>
                <td align="left">
                	&nbsp;<ww:if test="tbBill.strFileUrl != null">
                		<ww:a href="downloadTravelerFile.action?filePath=%{tbBill.strFileUrl}" cssClass="errm">附&nbsp; 件</ww:a>
                	</ww:if>
                </td>
               </tr>
             </table>
            <span class="lvtitle2">参保旅客明细</span>
           
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table">
              <tr>
                <td>序号</td>
                <td>姓名</td>
                <td>性别</td>
                <td>出生年月</td>
                <td>国籍</td>
                <td>证件号</td>
              </tr>
              <ww:iterator value="tbBill.tbTravelerInfoList" status="index">
              <tr>
                <td><ww:property value="#index.index+1"/></td>
                <td><ww:property value="strTravelerName"/></td>
                <td><ww:property value="strSex"/></td>
                <td><ww:property value="strBirthday"/></td>
                <td><ww:property value="strCountry"/></td>
                <td><ww:property value="strIndentyNumber"/></td>
              </tr>
              </ww:iterator>
            </table>
          </div>
        </div>
        <br/>
         <form action="#" method="post" name="form1">
      <div class="tabcont2">  
     
       <table  border="0" cellspacing="0" cellpadding="0" class="table" width="90%">
	          	<tr>
	          		<td width="20%">审核人:</td>
	          		<td width="30%"><ww:textfield name="tbBill.tbBillSureInfo.strSureUserName" value="%{#session['tbUser'].strUserName}" cssClass="inputbox" title="审核人" readonly="true" theme="simple"/></td>
	          		<td width="20%">审核时间:</td>
	          		<td width="30%"><input type="text" name="tbBill.tbBillSureInfo.dteSureTime"  value="<ww:date name="new java.util.Date()" format="yyyy-MM-dd"/>" class="inputbox" title="确认时间" readonly="readonly"/></td>
	          	</tr>
	          	<ww:if test="tbBill.intIsSureByZs==0">
	            	<tr>
	            		<td colspan="4" align="left"><font color="red">注意：该保单还没有通过总社负责人审查!</font></td>
	            	</tr>  		
	            </ww:if>
        </table>
        </div>
        <br>
        <div class="tabcont2" style="padding:16px 0;">
         	<ww:hidden name="tbBill.id" value="%{tbBill.id}"/>
		    <ww:hidden name="tbBill.tbBillSureInfo.intSureUserId" value="%{#session['tbUser'].id}"/>
		    <ww:hidden name="tbBill.intBillStateId" value="%{tbBill.intBillStateId}"></ww:hidden>
	          <table width="100%" border="0" cellspacing="0" cellpadding="0">
	            <tr>
		              <td>
		              		<input type="button" class="inputbutton" id="button4" onclick="if(window.confirm('您确认该保单通过审查吗？'))sendSureResult('sureBillByBxSuccess.action');" value="确认" />
		              </td>
	              <td>
		           	<input type="button" class="inputbutton" id="button4" onclick="openAWindow('toBeiAnBillbyBx.action?id=<ww:property value="tbBill.id"/>')" value="备案" />
	              </td>
	              <td>
	              		<input type="button" class="inputbutton" id="button3" onclick="openAWindow('toReturnBillByBx.action?id=<ww:property value="tbBill.id"/>')" value="退回"/>
	              </td>
	              <td><input type="button" class="inputbutton" id="button"  onclick="if(window.confirm('窗口将关闭'))window.close();" value="取消" /></td>
	            </tr>
	          </table>
        
        </div>
          </form>
      </td>
    </tr>
  </table>
</div>
</body>
</html>
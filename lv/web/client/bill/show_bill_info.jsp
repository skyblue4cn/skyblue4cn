<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<html>

<ww:include value="/head.jsp"></ww:include>
<body>
<div id="lvdbtable" style="width:100%;margin:auto;">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
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
        <table width="90%" border="0" cellpadding="0" cellspacing="0"  class="table yinz" <ww:if test="tbBill.intBillStateId==4">style="background-image:url(<ww:property value="tbBill.tbPartment.strImgUrl"/>);"    </ww:if>>
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
              	<ww:radio name="tbBill.intTeamType" list="#{0:'组团社',1:'地接社'}" theme="my" value="%{tbBill.intTeamType}" disabled="true"/>
              </td>
              <td colspan="2" align="left">旅游起止时间</td>
            </tr>
            <tr>
              <td>旅游形式</td>
              <td colspan="3">
              	<ww:radio name="tbBill.intTravelType" list="#{0:'常规',1:'自驾车',2:'自由人'}" value="%{tbBill.intTravelType}" theme="my" disabled="true"/>
              </td>
              <td colspan="2">自<ww:date name="tbBill.dteStartTime" format="yyyy-MM-dd"/></td>
            </tr>
            <tr>
              <td>高风险旅游项目</td>
              <td colspan="3">
              	<ww:radio name="tbBill.intIsHasHighDanger" list="#{0:'无',1:'有'}" value="%{tbBill.intIsHasHighDanger}" theme="my" disabled="true"/>
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
		            		<td colspan="3"><ww:radio name="tbBill.intNBFeeType" list="#{0:'10/意外死亡伤残+2/意外医疗+0.5/丧葬',1:'20/意外死亡伤残+2/意外医疗+0.5/丧葬'}"  value="%{tbBill.intNBFeeType}" theme="my" disabled="true" onchange="getBillPrice()"/></td>
		            	</tr>
		            	<tr>
		            		<td>外宾(万元/人)</td>
		            		<td colspan="3"><ww:radio name="tbBill.intWBFeeType" list="#{0:'30/意外死亡伤残+3/意外医疗+2/丧葬',1:'40/意外死亡伤残+3/意外医疗+2/丧葬'}" value="%{tbBill.intWBFeeType}" theme="my" disabled="true" onchange="getBillPrice()"/></td>
		            	</tr>
	           	</ww:if>
	           	<ww:else>
	           		<tr>
		            		<td rowspan="3">保险金额</td>
		            		<td>内宾(万元/人)</td>
		            		<td colspan="3"><ww:radio name="tbBill.intNBFeeType" list="#{2:'30/意外死亡伤残+3/意外医疗+2/丧葬',3:'40/意外死亡伤残+3/意外医疗+2/丧葬'}" value="%{tbBill.intNBFeeType}" theme="my" disabled="true" onchange="getBillPrice()"/></td>
		            	</tr>
		            	<tr>
		            		<td>外宾(万元/人)</td>
		            		<td colspan="3"><ww:radio name="tbBill.intWBFeeType" list="#{0:'30/意外死亡伤残+3/意外医疗+2/丧葬',1:'40/意外死亡伤残+3/意外医疗+2/丧葬'}" value="%{tbBill.intWBFeeType}" theme="my" disabled="true" onchange="getBillPrice()"/></td>
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
           <ww:if test="tbBill.intKindId==1">
            <tr>
              <td width="10%">保险费</td>
              <td width="15%">内宾（元/人）</td>
              <td width="25%">0.2（元/人）</td>
              <td width="15%">外宾（元/人）</td>
              <td width="25%">1（元/人）</td>
            </tr>
           </ww:if>
           <ww:else>
           	<tr>
              <td width="10%">保险费</td>
              <td width="15%">内宾（元/人）</td>
              <td width="25%">10（元/人）</td>
              <td width="15%">外宾（元/人）</td>
              <td width="25%">15（元/人）</td>
            </tr>
           </ww:else>
          </table>
        </div>
  		<br/>
    
          <div class="tabcont2">
            <span class="lvtitle2">参保旅客明细</span>
            <table width="90%" border="1" cellspacing="0" cellpadding="0" class="table">
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
         
            <tr height="30">
            	<td colspan="6" align="center"> 
		            <ww:if test="tbBill.strFileUrl!=null" >
		            	<ww:a href="downloadTravelerFile.action?filePath=%{tbBill.strFileUrl}"><span class="text8">旅客明细(附件文档)</span></ww:a>
		            </ww:if>
           		 </td>
            </tr>
            </table>
          </div>
    
          <br/>
      <div class="tabcont2">
      
      	<table width="90%" border="0" cellpadding="0" cellspacing="0" class="table yinzbx" 	<ww:if test="tbBill.intBillStateId == 4"> style="background-image:url(images/bx/bx.jpg);"</ww:if>>
           	<tr valign="center">
           		<td>确认人</td>
           		<td><ww:property value="tbBill.strSureUserName"/></td>
           		<td>确认时间：</td>
           		<td><ww:date name="tbBill.dteSureTime" format="yyyy-MM-dd"/></td>
           	</tr>
           	<tr>
           		
           		<td colspan="4">
           			保险公司盖章:
           			<br/>
           			<br/>
           			<p style="color:red;">
           				<ww:if test="tbBill.intBillStateId ==1">
           					<!-- 未提交保单 -->
           					保单还未提交！
           				</ww:if>
           				<ww:if test="tbBill.intBillStateId==2 || tbBill.intBillStateId==3 ">
           					<!-- 未确认保单 -->
           					保单还没有被确认！
           				</ww:if>
           				<ww:if test="tbBill.intBillStateId==5">
           					<!-- 旅行社总社退回 -->
           					保单未通过总社确认，被退回！<br>
           					退回原因:<br>
           					<ww:property value="tbBill.strReturnReason"/>
           				</ww:if>
           				<ww:if test="tbBill.intBillStateId==6">
           					<!-- 保单未通过保险公司确认，被退回 -->
           					保单未通过保险公司确认，被退回！<br>
           					退回原因:<br>
           					<ww:property value="tbBill.strReturnReason"/>
           				</ww:if>
           				<ww:if test="tbBill.intBillStateId==7">
           				
           				</ww:if>
           				<ww:if test="tbBill.intBillStateId==7 || tbBill.intBillStateId==8">
           					<!-- 备案保单 -->
           					保单被备案，还未确认！
           				</ww:if>
           			</p>
           			<br/>
           			<ww:if test="tbBill.intBillStateId == 4"> 
           			<br/>
           			<br/>
           			<br/>
           			<br/>
           			<br/>
           			<br/>
           			</ww:if>
           			<p style="text-indent:300px;">
	           			<ww:if test="tbBill.intBillStateId == 4">
	           			确认码：<ww:property value="tbBill.md5CheckCode"/>
	           			</ww:if>
	           			&nbsp;
           			</p>
           			<br/>
           		</td>
      
           	</tr>
          </table>
        </div>
      </td>
    </tr>
  
  </table>
  <br>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  	<tr>
  		<td width="40%">&nbsp;</td>
  		<td width="10%" align="center"><input type="button" value="打印保单" onclick="window.print()"/></td>
  		<td width="10%" align="center"><input type="button" value="关闭本页" onclick="window.close()"/></td>
  		<td width="40%">&nbsp;</td>
          
   </table>
  <br/>
  <br/>
  <br>
  <br>
</div>
</body>
</html>
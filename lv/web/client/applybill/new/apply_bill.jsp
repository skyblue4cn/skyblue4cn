<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<%@page import="cn.insurance.model.TbUser"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<ww:include value="/head.jsp"></ww:include>

<script type="text/javascript" src="<%=basePath%>js/apply_bill.js"></script>
<div id="lvdbtable" style="width:100%;margin:auto;">
<form method="post" name="form1" enctype="multipart/form-data">
	<input type="hidden" name="limitId" value="<ww:property value="tbBill.id"/>"/>
	<input type="hidden" name="limitKey" value="<ww:property value="limitKey"/>">
	<input type="hidden" name="limitType" value="<ww:property value="limitType"/>"/>
	<input type="hidden" name="tbBill.intKindId" value="<ww:property value="tbBill.intKindId"/>"/>
	<input type="hidden" name="tbBill.intApplyUserId" value="<ww:property value="tbBill.intApplyUserId"/>"/>
	<input type="hidden" name="tbBill.intPartmentId" value="<ww:property value="tbBill.intPartmentId"/>"/>
	<input type="hidden" name="tbBill.intTravelProperty" value="<ww:property value="tbBill.intTravelProperty"/>"/>
	<ww:hidden name="tbBill.strBillName" value="%{tbBill.strBillName}"></ww:hidden>
  <table width="95%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td colspan="2"><div class="tabcont2"><span class="lvtitle"><ww:property value="tbBill.strBillName"/></span></div>
      	<ww:include value="/message.jsp"></ww:include>
        <div class="tabcont2">
          <table width="90%" border="0" cellspacing="0" cellpadding="0" class="table">
            <tr>
              <td width="10%">填写时间</td>
              <td width="40%"><input type="text" name="tbBill.dteApplyDate" value="<ww:date name="tbBill.dteApplyDate" format="yyyy-MM-dd"/>" readonly="true" class="inputbox1"/></td>
              <td width="10%">签发人</td>
              <td width="40%"><ww:textfield name="tbBill.strSignatoryName" theme="simple" readonly="true" cssClass="inputbox1"/></td>
            </tr>
			<tr>
              <td width="10%">保单种类</td>
              <td width="40%" style="color:#0c4e7f;">
              	<ww:if test="tbBill.intKindId == 1">
              		责任险
              	</ww:if>
              	<ww:if test="tbBill.intKindId == 2">
              		意外险
              	</ww:if>
              </td>
              <td width="10%">旅游类型</td>
              <td width="40%" style="color:#0c4e7f;">
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
              <td colspan="3"><ww:textfield name="tbBill.strCompanyName" theme="simple" readonly="true" cssClass="inputbox1"/></td>
              <td width="10%">保单号</td>
              <td width="28%"><ww:textfield name="tbBill.strBillNumber" theme="simple" readonly="true" cssClass="inputbox1" size="40"/></td>
            </tr>
            <tr>
              <td>部门名称（盖章）</td>
              <td width="17%"><ww:textfield name="tbBill.strPartmentName" theme="simple" readonly="true" cssClass="inputbox1"/></td>
              <td width="9%">导游</td>
              <td width="15%"><ww:textfield name="tbBill.strDragoman" theme="simple" cssClass="inputbox"/></td>
              <td>团队号<font color="red">*</font></td>
              <td><ww:textfield id="team" name="tbBill.strTeamNumber" theme="simple" cssClass="inputbox"/></td>
            </tr>
            <tr>
              <td>电话<font color="red">*</font></td>
              <td><ww:textfield id="phone" name="tbBill.strPhoneNumber" theme="simple" cssClass="inputbox"/></td>
              <td>传真</td>
              <td><ww:textfield name="tbBill.strFax" theme="simple" cssClass="inputbox"/></td>
              <td>旅游线路<font color="red">*</font></td>
              <td><ww:textfield id="rold" name="tbBill.strTravelRold" theme="simple" cssClass="inputbox"/></td>
            </tr>
            <tr>
              <td>组团形式</td>
              <td colspan="3">
              	<ww:radio name="tbBill.intTeamType" list="#{0:'组团社',1:'地接社'}" theme="my" value="0" />
              </td>
              <td colspan="2" align="left">旅游起止时间<font color="red">*</font></td>
            </tr>
            <tr>
              <td>旅游形式</td>
              <td colspan="3">
              	<ww:radio name="tbBill.intTravelType" list="#{0:'常规',1:'自驾车',2:'自由人'}" value="0" theme="my" onchange="getBillPrice()"/>
              </td>
              <td colspan="2">自<input type="text" id="startTime" name="tbBill.dteStartTime" value="<ww:date name="tbBill.dteStartTime" format="yyyy-MM-dd"/>"  class="inputbox" onclick="setday(this)" readonly="true" onblur="getBillPrice()"/></td>
            </tr>
            <tr>
              <td>高风险旅游项目</td>
              <td colspan="3">
              	<ww:radio name="tbBill.intIsHasHighDanger" list="#{0:'无',1:'有'}" value="0" theme="my" onchange="getBillPrice()"/>
              </td>
              <td colspan="2">至<input type="text" id="endTime" name="tbBill.dteEndTime" value="<ww:date name="tbBill.dteEndTime" format="yyyy-MM-dd"/>" class="inputbox"  onclick="setday(this)" readonly="true" onblur="getBillPrice()"/></td>
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
              <td width="25%"><ww:textfield theme="simple" id="neibin" name="tbBill.intChinaTravelerNumber" cssClass="inputbox" onblur="getBillPrice()"/></td>
              <td width="15%">外宾（人）</td>
              <td width="30%"><ww:textfield theme="simple" id="waibin" name="tbBill.intOtherTravelerNumber" cssClass="inputbox"  onblur="getBillPrice()"/></td>
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
		            		<td colspan="3"><ww:radio name="tbBill.intNBFeeType" list="#{0:'10/意外死亡伤残+2/意外医疗+0.5/丧葬',1:'20/意外死亡伤残+2/意外医疗+0.5/丧葬'}"  value="0" theme="my" onchange="getBillPrice()"/></td>
		            	</tr>
		            	<tr>
		            		<td>外宾(万元/人)</td>
		            		<td colspan="3"><ww:radio name="tbBill.intWBFeeType" list="#{0:'30/意外死亡伤残+3/意外医疗+2/丧葬',1:'40/意外死亡伤残+3/意外医疗+2/丧葬'}" value="0" theme="my" onchange="getBillPrice()"/></td>
		            	</tr>
	           	</ww:if>
	           	<ww:else>
	           		<tr>
		            		<td rowspan="3">保险金额</td>
		            		<td>内宾(万元/人)</td>
		            		<td colspan="3"><ww:radio name="tbBill.intNBFeeType" list="#{2:'30/意外死亡伤残+3/意外医疗+2/丧葬',3:'40/意外死亡伤残+3/意外医疗+2/丧葬'}" value="2" theme="my" onchange="getBillPrice()"/></td>
		            	</tr>
		            	<tr>
		            		<td>外宾(万元/人)</td>
		            		<td colspan="3"><ww:radio name="tbBill.intWBFeeType" list="#{0:'30/意外死亡伤残+3/意外医疗+2/丧葬',1:'40/意外死亡伤残+3/意外医疗+2/丧葬'}" value="0" theme="my" onchange="getBillPrice()"/></td>
		            	</tr>
	           	</ww:else>
            	<tr>	
            		<td>未成年人(万元/人)</td>
            		<td colspan="3"><ww:property value="@cn.insurance.commonwords.BaoFeiRule@getWCNBaoFeiDesc(tbBill.intKindId,tbBill.intTravelProperty)"/></td>
            	</tr>
            </ww:else>
            </table>
           </div>
           <div class="tabcont2" id="feeDiv" onclick="getBillPrice()" >
           
            <table width="90%" border="0" cellpadding="0" cellspacing="0" class="table" title="点击鼠标左键刷新价格">
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
        <div class="tabcont2">
	       <table border="0" cellpadding="0" cellspacing="0" width="90%">
	       	<tr>
	       		<td width="15%"> 
			       <input type="radio" id="travelerRadio1" name="tbBill.intIsAddTraveler" value="0" />
					<span>不添加旅客明细</span>
	         	</td>
	       		<td width="85%" valign="bottom">
	       			<span style="color:red;" id="messageDiv" >
	       				重要说明：请务必在旅游起始时间当天15:00点前添加所有旅客明细，否则保单无效,保险公司不承担保险责任！
	       			&nbsp; </span>
	        	</td>
	       	</tr>
	       	<tr>
	       		<td>
			      <input type="radio" id="travelerRadio2" name="tbBill.intIsAddTraveler" value="1"  checked="checked"/>
		       		添加旅客明细
	       		</td>
	       		<td>
	       			&nbsp;
	       		</td>
	       	</tr>	

	       	</table>
       	</div>
       	<div class="tabcont2">
	       	<div class="bxuserbox2">
	          	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
		            <tr>
		            	<td width="10%">
		            		<span class="text6">上传附件:</span>
		            	</td>
		            	<td width="30%">
		           	 		<input type="file" name="travelerFile" id="travelerFile"/>
		           	 	</td>
		           	 	<td width="30%" align="center">
		           	 		<a href="javascript:openAWindow('client/applybill/new/upload_traveler_xsl.jsp')">导入明细</a>
		           	 	</td>
		            	<td align="center">
		            		<a href="javascript:addTr()"  class="text6">添加旅客</a>
		            	</td>
		           	 </tr>
		         </table>
		       
		         <br/>
	            <span class="lvtitle2">参保旅客明细</span>
	            <div id="lvke1">
	            <table id="travelerTable" width="100%" border="0" cellspacing="0" cellpadding="0" class="table">
	              <tr>
	                <td width="10%" align="center">序号</td>
	                <td width="15%" align="center">姓名</td>
	                <td width="15%" align="center">性别</td>
	                <td width="15%" align="center">出生年月</td>
	                 <td width="15%" align="center">国籍</td>
	                <td width="20%" align="center">证件号</td>
	                <td width="10%" align="center">删除</td>
	              </tr>
	           
	            </table>
	          </div>
	        </div>
        </div>
        <div class="tabcont2" style="padding:16px 0;">
          <table width="90%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td ><input type="button" class="inputbutton" id="button4" onclick="checkForm('<ww:date name="new java.util.Date()" format="yyyy-MM-dd"/>')" value="提交保单" /></td>
              <td align="center"><input type="button" class="inputbutton" id="button" value="删除保单" 
              	onclick="location.href='c_deleteBillByUser.action?limitId=<ww:property value="tbBill.id"/>&limitKey=<ww:property value="@cn.insurance.action.SupportAction@getEencodeIdKey('delete',id)"/>&limitType=delete'" />
             </td>
            </tr>
          </table>
        </div>
      </td>
    </tr>
  </table>
 </form>
 <br/>
 <br/>
</div>
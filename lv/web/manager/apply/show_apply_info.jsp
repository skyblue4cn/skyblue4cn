<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib  prefix="ww" uri="webwork"%>
<ww:include value="/head.jsp"></ww:include>
<br/>
<div class="text1">中 国 人 民 保 险 公 司 成 都 市 武 侯 区 支 公 司</div>
<br/>
<div class="text8">旅游意外（责任）险保险单批改申请书</div>
<br/>
	<table border="1" cellpadding="0" cellspacing="0" width="70%" class="table text9" >
		<tr>
			<td colspan="2" class="text9">
			中国人民保险公司成都市武侯区支公司:
			</td>
		</tr>
		<tr>
			<td colspan="2" valign="top">
				&nbsp;&nbsp;&nbsp;&nbsp;本保险人现向你公司申请批改
					<a href="getBillInfoToPrint.action?id=<ww:property value="tbApply.intBillId"/>" target="_blank"><ww:property value="tbApply.strBillNumber"/></a>
				保险单的下列内容,请核实并给予办理批改手续。
			</td>
		</tr>
			<tr>
				<td colspan="2" align="center">申请批改内容</td>
			</tr>
		<tr style="height:150px;">
			<td valign="top" colspan="2" class="applytext" >
				<ww:property value="tbApply.strApplyContent"/>
			</td>
		</tr>
		<tr>
			<td>
				申请人:<ww:property value="tbApply.strApplyUserName"/>
			</td>
			<td>
				申请时间:<ww:date name="tbApply.dteApplyTime"  format="yyyy-MM-dd"/>
			</td>
		</tr>
		</table>
		<br/>
		<br/>
		<ww:if test="tbApply.intIsReply ==1">
		<table border="1" cellpadding="0" cellspacing="0" width="70%" class="table text9" >
			<tr>
					<td colspan="2" align="center">核保人意见：</td>
			</tr>
			<tr style="height:150px;">
					<td valign="top" colspan="2" class="applytext" >
						<ww:property value="tbApply.strReplyContent"/>
					</td>
			</tr>
			<tr>
				<td>审核人：<ww:property value="tbApply.strReplyUserName"/></td>
				<td>审核时间：<ww:date name="tbApply.dteReplyTime" format="yyyy-MM-dd"/></td>
			</tr>
		</table>
		
		</ww:if>
			<ww:else>
		<div class="text2">还没有回复！</div>
		
	</ww:else>
	
	<br/>

	<br/>
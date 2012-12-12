<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib  prefix="ww" uri="webwork"%>
<ww:include value="/head.jsp"></ww:include>
	<table border="1" cellpadding="0" cellspacing="0" width="70%" class="table text9" style="display:none;">
		<tr>
			<td><a href="">处理该保单</a></td>
		</tr>
		<tr>
			<td>核保人意见：</td>
		</tr>
		<tr>	
			<td>
				<ww:textarea cols="85" rows="10" name="tbApply.strReplyContent" cssClass="inputarea"  value="请处理保单后，在这里填写回复!" theme="simple"></ww:textarea>
			</td>
		</tr>
	</table>
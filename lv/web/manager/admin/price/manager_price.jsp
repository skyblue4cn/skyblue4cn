<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<ww:include value="/top.jsp"></ww:include>
<br>
<div style="text-align: center;font-size: 16px;font-weight: bold;">
	价格管理
</div>
<script type="text/javascript">
<!--
	function openBigWindow(url){
		window.open(url,'updateprice','height=500,width=600,top=150,left=300,dependent,fullscreen=no,menubar=no,toolbar=no,directories=no,location=no,status=no,scrollbars=no,resizable=no')
	}
//-->
</script>
<br/>
<table border="0" cellpadding="0" cellspacing="0" width="80%" class="table" style="line-height: 30px;" align="center">
	<tr>
		<th scope="col">名称</th>
		<!--<th scope="col">自驾车</th>-->
		<th scope="col">意外险</th>	
	</tr>
	<tr>
		<td style="color:blue;font:14px bold ;font-weight: bold; ">通用价格管理</td>
		<!--<td align="center"><a href="javascript:openAWindow('toUpdateNormalAdjustFee.action')">
				<img src="images/biangeng.gif">
			</a></td>-->
		<td align="center">
			<!--<a href="">
				<img src="images/biangeng.gif">
			</a>-->
			<input type="button" value="变更10W" style="border: 1px solid #ccc;" onClick="javascript:openBigWindow('toUpdateNormalAccidentFee.action')"/>&nbsp;&nbsp;
			<input type="button" value="变更20W" style="border: 1px solid #ccc;" onClick="javascript:openBigWindow('toUpdateNormalAccidentFee.action')"/>&nbsp;&nbsp;
			<input type="button" value="变更30W" style="border: 1px solid #ccc;" onClick="javascript:openBigWindow('toUpdateNormalAccidentFee.action')"/>
		</td>
	</tr>
	<ww:iterator value="partmentList" status="index">
	<tr <ww:if test="#index.index%2==0"> bgcolor="#ededed"</ww:if>>
		<td  >
			<ww:property value="strPartmentName"/>
		</td>
		<!--<td width="30%" align="center">
			<a href="javascript:openAWindow('toUpdateCompanyAdjustPrice.action?tbPartment.id=<ww:property value="id"/>')">
				<img src="images/biangeng.gif">
			</a>
		</td>-->
		<td width="30%" align="center">
			<!--<a href="">
				<img src="images/biangeng.gif">
			</a>-->
			<input type="button" value="变更10W" style="border: 1px solid #ccc;" onClick="javascript:openBigWindow('toUpdateCompanyAccidentalPrice.action?tbPartment.id=<ww:property value="id"/>')"/>&nbsp;&nbsp;
			<input type="button" value="变更20W" style="border: 1px solid #ccc;" onClick="javascript:openBigWindow('toUpdateCompanyAccidentalPrice.action?tbPartment.id=<ww:property value="id"/>')"/>&nbsp;&nbsp;
			<input type="button" value="变更30W" style="border: 1px solid #ccc;" onClick="javascript:openBigWindow('toUpdateCompanyAccidentalPrice.action?tbPartment.id=<ww:property value="id"/>')"/>
		</td>
	</tr>
	</ww:iterator>

</table>

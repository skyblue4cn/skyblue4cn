<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<ww:include value="/top.jsp"/>

<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr valign="top">
    <td valign="top" align="center" width="15%" style="background:#ededed;" height="540px">
	    	<br/>
	    	<br/>
	    	<br/>
	    	<br/>
	    	<br/>
	    	<br/>
	    	<input class="inputbutton3" type="button" onclick="getAllPeiKuanLog.action" value="查看记录" /><br/><br/><br>
	    	<input class="inputbutton" type="button" onclick="openAWindow('manager/peikuan/add_peikuan.jsp')" value="添加记录" /><br/><br/>
    </td>
    <td valign="top">
    	<br/>
	    <div class="tabcont" id="div1">
			<div align="center">
				<div class="text1">
					赔款清单列表
				</div>
				<br/>
				<div>
					<table border="0" cellpadding="0" cellspacing="0" class="table" width="95%">
						<tr>
							<th scope="col" width="20%">保单号</th>
							<th scope="col" width="20%">旅行社</th>
							<th scope="col" width="20%">部门</th>
							<th scope="col" width="10%">赔款金额</th>
							<th scope="col" width="30%">备注</th>
						</tr>
						<ww:iterator value="pageBean.dataList" status="index">
							<tr <ww:if test="#index.index%2!=0">bgcolor="#ededed"</ww:if>>
								<td>
									<a href="getBillInfoToPrint.action?id=<ww:property value="id"/>" target="blank">
										<ww:property value="tbBill.strBillNumber"/>
									</a>
								</td>			
								<td><ww:property value="tbBill.tbPartment.company.strPartmentName"/></td>
								<td><ww:property value="tbBill.tbPartment.strPartmentName"/></td>
								<td><ww:property value="dbePeiKuanFee"/></td>
								<td><ww:property value="strDesc"/></td>
							</tr>
						</ww:iterator>
					</table>
				</div>
			</div>
	  	</div>
  </td>
  </tr>
</table>
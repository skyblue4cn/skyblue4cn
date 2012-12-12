<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<div  id="lvdbtable" style="width:100%;text-align: center;">
	<div class="bdtop" width="100%" align="center">修改旅行社信息</div>
	<ww:form action="updateCompanyInfo.action" method="post" enctype="multipart/form-data">
			<table border="0" cellpadding="0" cellspacing="0" width="80%" class="table">
				<tr>
					<td width="30%">名称*</td>
					<td><ww:textfield name="tbPartment.strPartmentName" theme="simple" /></td>
				</tr>
				<ww:textfield name="tbPartment.strLicenceNumber"	label="许可证"/>	
				<tr><td>责任保险起始时间</td><td><input type="text" name="tbPartment.dteResStartDate" value="<ww:date name="tbPartment.dteResStartDate" format="yyyy-MM-dd"/>" onclick="setday(this)" readonly="false" required="true"/></td></tr>
				<tr><td>责任保险结束时间</td><td><input type="text" name="tbPartment.dteResEndDate" value="<ww:date name="tbPartment.dteResEndDate" format="yyyy-MM-dd"/>" onclick="setday(this)"  title="手动填写请填写正确的格式"  required="true"/></td></tr>
				<ww:textfield name="tbPartment.strAddress" label="地址"/>
				<ww:textfield name="tbPartment.strPhoneNumber" label="电话"/>
				<ww:textfield name="tbPartment.strFax" label="传真" />
				<ww:textfield name="tbPartment.strConnectPeople" label="联系人" />
				<ww:radio name="tbPartment.intIsSeePartmentBill" label="是否查看部门保单" list="#{1:'是',0:'否'}" value="%{tbPartment.intIsSeePartmentBill}" />
				<ww:radio name="tbPartment.intIsNeedSureBill" label="是否审核保单" list="#{1:'审核',0:'不审核'}" value="%{tbPartment.intIsNeedSureBill}" title="若选审核，部门申请保单必须经过总社审核才能提交"/>
					<!-- <ww:file name="partmentImg" label="旅行社盖章图片"/> -->
				<ww:hidden name="tbPartment.intParentId" value="0"></ww:hidden> 
				<ww:hidden name="tbPartment.id" value="%{id}"/>
				<ww:hidden name="tbPartment.isShowHistoryBill" value="%{tbPartment.isShowHistoryBill}"/>
			</table>
			<br/>
			<table width="80%">
				<tr><td width="50%" align="center"><input type="button" class="inputbutton" value="提交" onclick="if(window.confirm('确认提交吗？'))this.form.submit();"/></td>
				<td align="center"><input type="button" value="取消" class="inputbutton" onclick="window.close();"/></td></tr>
			</table>
		</ww:form>	
		<br/>
		<br/>
		<br/>
</div>

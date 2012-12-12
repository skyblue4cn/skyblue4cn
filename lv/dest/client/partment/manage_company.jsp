<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>

	<script type="text/javascript">
<!--
	function addCompany(){
		var obj = document.getElementById('addform') ;
		if(obj.pname.value==""){
			alert('请填写旅行社名称！') ;
			return ;
		}
		if(obj.stime.value==""){
			alert("请填写责任险起始时间！") ;
			return ;
		}
		if(obj.etime.value==""){
			alert("请填写责任险结束时间！") ;
			return 	;
		}
		if(window.confirm('确认添加吗？'))obj.submit();
	}
//-->
</script>

<ww:include value="/head.jsp"></ww:include>
<div  id="lvdbtable" style="width:100%;text-align: center;">
	<div class="bdtop" width="100%" align="center">添加旅行社</div>
	<ww:actionmessage/>
	<ww:form action="manageCompany.action" method="post" id="addform">
			<table border="0" cellpadding="0" cellspacing="0" width="80%" class="table">
				<tr>
					<td width="30%">名称*</td>
					<td><ww:textfield name="tbPartment.strPartmentName" theme="simple" id="pname"/></td>
				</tr>
				<ww:textfield name="tbPartment.strLicenceNumber"	label="许可证"/>	
				<ww:textfield name="tbPartment.dteResStartDate" label="责任保险起始时间" onclick="setday(this)" readonly="true" required="true" id="stime"/>
				<ww:textfield name="tbPartment.dteResEndDate" label="责任保险结束时间" onclick="setday(this)"  title="手动填写请填写正确的格式" required="true" id="etime"/>
				<ww:textfield name="tbPartment.strAddress" label="地址"/>
				<ww:textfield name="tbPartment.strPhoneNumber" label="电话"/>
				<ww:textfield name="tbPartment.strFax" label="传真" />
				<ww:textfield name="tbPartment.strConnectPeople" label="联系人" />
				<ww:radio name="tbPartment.intIsSeePartmentBill" label="是否查看部门保单" list="#{1:'是',0:'否'}" value="0" />
				<ww:radio name="tbPartment.intIsNeedSureBill" label="是否审核保单" list="#{1:'审核',0:'不审核'}" value="0" title="若选审核，部门申请保单必须经过总社审核才能提交"/>
				<!-- <ww:file name="partmentImg" label="旅行社盖章图片"/> -->
				<ww:hidden name="tbPartment.intParentId" value="0"></ww:hidden> 
				<ww:hidden name="tbPartment.isShowHistoryBill" value="0"/>
			</table>
			<br/>
			<table width="80%">
				<tr><td width="50%" align="center"><input type="button" class="inputbutton" value="添加" onclick="addCompany()"/></td>
				<td align="center"><input type="button" value="取消" class="inputbutton" onclick="window.close()"/></td></tr>
			</table>
		</ww:form>	
		<br/>
		<br/>
		<br/>
</div>

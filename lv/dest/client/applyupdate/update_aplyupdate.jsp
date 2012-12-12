<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'update_aplyupdate.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>
						<br/>
						<div class="text1">中 国 人 民 保 险 公 司 成 都 市 武 侯 区 支 公 司</div>
						<br/>
						<div class="text8">旅游意外（责任）险保险单批改申请书</div>
						<br/>
						<form action="c_addUpdateApply.action" method="post">
							<ww:hidden name="tbApply.intFromUserId" value="%{#session.tbUser.id}"/>
							<ww:hidden name="tbApply.intPartmentId" value="%{#session.tbUser.intPartmentId}"/>
							<ww:if test="actionMessages.size>0">
								<div id="div1"><font color="red"><li><ww:property value="actionMessages.get(0)"/></li></font></div>
								<script type="text/javascript">
									setTimeout(hiddenMDiv, 3000)
								</script>
							</ww:if>
							<table border="0" cellpadding="0" cellspacing="0" width="70%" height="300px" class="text9">
								<tr>
									<td colspan="2" >
									中国人民保险公司成都市武侯区支公司:
									</td>
								</tr>
								<tr height="50px">
									<td colspan="2">
										&nbsp;&nbsp;&nbsp;&nbsp;本保险人现向你公司申请批改
											<ww:textfield name="tbApply.strBillNumber"  value="请填写保单号" id="billNumber" onclick="setValue('billNumber')" cssClass="inputboxb" theme="simple" size="40"/>
										保险单的下列内容，请核实并给予办理批改手续。
									</td>
								</tr>
								<tr height="100px;">
									<td colspan="2">
										<ww:textarea name="tbApply.strApplyContent" cols="100" rows="10" theme="simple" 
											id="applyContent" 
										cssClass="inputarea" onclick="setValue('applyContent')" value="请在这里填写要申请批改的内容!">
										
										</ww:textarea>
									</td>
								</tr>
								<tr>
									<td>申请人:<ww:textfield theme="simple" name="tbApply.strApplyUserName" value="%{#session.tbUser.strUserName}" readonly="true"/></td>
									<td>时间:<input type="text" name="tbApply.dteApplyTime" value="<ww:date name="new java.util.Date()" format="yyyy-MM-dd"/>" readonly="true"/></td>
								</tr>
							</table>
							<br/>
							<br>
							<table border="0" cellpadding="0" cellspacing="0" width="80%">
							
								<tr>
									<td align="left">
										<input type="submit" class="inputbutton" value="提交"/>
									</td>
									<td align="left">
										<input type="reset" class="inputbutton" value="取消"/>
									</td>
								</tr>
							</table>
							<br>
							<br>
						</form>
					</td>
				</tr>
			</table>
  </body>
</html>

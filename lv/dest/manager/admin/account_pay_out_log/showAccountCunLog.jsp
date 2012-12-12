<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>账户存款记录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="background:#ededed;">
   <ww:include value="/head.jsp"></ww:include>
	<ww:include value="/manager/admin/navigation.jsp"></ww:include>
	<fieldset style="width:100%;">
			<legend>账户存款记录</legend>
			<p>&nbsp;</p>
			<table width="98%" border="0" cellspacing="0" cellpadding="0" class="table">
		        <tr>
		          <th scope="col" width="10%">序号</th>
		          <th scope="col" width="15%">时间</th>
		          <th scope="col" width="15%">存入金额(元)</th>
		          <th scope="col" width="15%">帐户余额（元）</th>
		          <th scope="col" width="15%">经手人</th>
		          <th scope="col" width="30%">备注</th>
		        </tr>
		     <ww:iterator value="pageBean.dataList" status="index">
					<tr  <ww:if test="#index.index%2!=0">   bgcolor="#ededed" </ww:if>>
					<td align="center"><ww:property value="#index.index+1"/></td>
					<td align="center"><ww:date name="dtePayoutTime" format="yyyy-MM-dd"/></td>
					<td align="center"><ww:property value="dbePayoutNumber"/></td>
					<td align="center"><ww:property value="dbeAftResidual"/></td>
					<td align="center"><ww:property value="strSaveUserName"/></td>
					<td align="center"><ww:property value="strDesc"/></td>
				</tr>
			</ww:iterator>
		</table>
		<br>
		<ww:if test="pageBean.dataList.size()!=0">
			  <table width="98%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>&nbsp;</td>
						<td width="10%" align="center">
							当前第<ww:property value="pageBean.curPage"/>/<ww:property value="pageBean.maxPage"/>页
						</td>
						<td width="5%" align="right">
							<ww:if test="pageBean.curPage<=1">首页</ww:if>
							<ww:else>
							<a href="<ww:url><ww:param name="pageBean.curPage" value="1"></ww:param></ww:url>">首页</a>
							</ww:else>
						</td>
						<td width="5%" align="right">
							<ww:if test="pageBean.curPage<=1">上页</ww:if>
							<ww:else>
							<a href="<ww:url><ww:param name="pageBean.curPage" value="%{pageBean.curPage-1}"></ww:param></ww:url>">上页</a>
							</ww:else>
						</td>
						<td width="5%" align="right">
							<ww:if test="pageBean.curPage>=pageBean.maxPage">下页</ww:if>
							<ww:else>
							<a href="<ww:url><ww:param name="pageBean.curPage" value="%{pageBean.curPage+1}"></ww:param></ww:url>">下页</a>
							</ww:else>
						</td>
						<td width="5%" align="right">
							<ww:if test="pageBean.curPage>=pageBean.maxPage">末页</ww:if>
							<ww:else>
							<a href="<ww:url><ww:param name="pageBean.curPage" value="%{pageBean.maxPage}"></ww:param></ww:url>">末页</a>
							</ww:else>
						</td>
					</tr>
			</table>
		</ww:if>
		<ww:else>	
			<div class="text2">没有记录！</div>
		</ww:else>
	</fieldset>
  </body>
</html>

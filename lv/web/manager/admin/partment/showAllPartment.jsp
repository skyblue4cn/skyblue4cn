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
    
    <title>旅行社部门管理</title>
    
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
			<legend>旅行社部门管理记录</legend>
			<table border="0" cellpadding="0" cellspacing="0" width="98%">
				<tr>
					<td align="right" height="30px" valign="middle">
						<input type="button" value="添加" class="inputbutton" onclick="openAWindow('toAddPartment.action?intParentId=<ww:property value="nodeid"/>','550','500')"/>
					</td>
				</tr>
			</table>
			<table border=0 width="98%" cellpadding="0" cellspacing="0" class="table" >
				<tr height="25px;">
					<th scope="col" width="10%">序号</th>
					<th scope="col" width="40%">部门名称</th>
					<th scope="col" width="10%">联系人</th>
					<th scope="col" width="10%">电话</th>
					<th scope="col" width="10%">传真</th>
					<th scope="col" width="10%">修改</th>
					<th scope="col" width="10%">删除</th>
				</tr>
				<ww:iterator value="pageBean.dataList" status="index">
				<tr height="25px;">
					<td align="center"><ww:property value="#index.index+1"/></td>
					<td align="center"><ww:property value="strPartmentName"/></td>
					<td align="center"><ww:property value="strConnectPeople"/></td>
					<td align="center"><ww:property value="strPhoneNumber"/></td>
					<td align="center"><ww:property value="strFax"/></td>
					<td align="center">
						<a href="javascript:;" onclick="openAWindow('toUpdatePartmentInfo.action?id=<ww:property value="id"/>','550','500')">
							<img src="images/xiugai.gif" border="0">
						</a>
					</td>
					<td align="center">
						<a href="javascript:;" onclick="openAWindow('toDeletePartment.action?id=<ww:property value="id"/>','500','400')">
							<img src="images/shanchu.gif" border="0">
						</a>
					</td>
				</tr>
				</ww:iterator>
				
			</table>
			<p>&nbsp;</p>
			
			<ww:if test="pageBean.dataList.size()>0">
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
			<div class="text8">没有部门！</div>
		</ww:else>
	</fieldset>
  </body>
</html>

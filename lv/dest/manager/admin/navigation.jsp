<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.insurance.utils.ParseLabelXml"%>
<%@ taglib prefix="ww" uri="webwork"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String local=request.getServletPath();
//out.println(request.getServletPath());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>业务关键词的管理项目导航</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	.twomenu{background:url(images/icr_admin_22.gif) repeat-x; height:31px; width:100%;}
	.twomenu ul{list-style:none; padding:3px 0 0 3px;}
	.twomenu ul li{float:left;margin-right:1px; font-size:12px;}
	.twomenu ul li a{float:left; overflow:hidden; display:block; background:url(images/icr_admin_25.gif) no-repeat left -32px; padding:0 0 0 6px; height:28px; line-height:28px; cursor:pointer; color:#4b4e54; text-decoration:none;}
	.twomenu ul li a:hover{background-position:left 0;color:#2b2f37;}
	.twomenu ul li a span{float:left; display:block; background:url(images/icr_admin_25.gif) no-repeat left -32px; padding:0 6px 0 0;height:28px; line-height:28px!important;line-height:32px;}
	.twomenu ul li a:hover span{background-position:right 0;}
	.twomenu ul li a.on{background-position:left 0;color:#000;}
	.twomenu ul li a.on span{background-position:right 0;}
	</style>
  </head>
  
  <body>
  	<ww:include value="/head.jsp"></ww:include>	
  	<div class="twomenu">
		<ul>
			<li>
				<a href="partmentInfoAdmin.action?nodeid=<ww:property value="nodeid"/>" class="<%=ParseLabelXml.getStyleName("service_company_manager_info",local)%>">
					<span>信息管理</span>
				</a>
			</li>
			<li>
				<a href="lxsUserAdmin.action?nodeid=<ww:property value="nodeid"/>" class="<%=ParseLabelXml.getStyleName("service_company_manager_user",local)%>"><span>用户管理</span></a>
			</li>
			<li>
				<a href="lxsAccountAdmin.action?nodeid=<ww:property value="nodeid"/>" class="<%=ParseLabelXml.getStyleName("service_company_manager_account",local)%>"><span>账户管理</span></a>
			</li>
			<li>
				<a href="getYuCunCunLogForBx.action?nodeid=<ww:property value="nodeid"/>" class="<%=ParseLabelXml.getStyleName("service_company_manager_account_cun_log",local)%>"><span>账户存入记录</span></a>
			</li>
			<li>
				<a href="getYuCunPayLogForBx.action?nodeid=<ww:property value="nodeid"/>" class="<%=ParseLabelXml.getStyleName("service_company_manager_account_pay_log",local)%>"><span>账户支出记录</span></a>
			</li>
			<li>
				<a href="getMonthPayLogForBx.action?nodeid=<ww:property value="nodeid"/>" class="<%=ParseLabelXml.getStyleName("service_company_manager_month_fee",local)%>"><span>月费结算</span></a>
			</li>
			<ww:if test="@cn.insurance.utils.TreeUtil@isCompany(nodeid)">
			<li>
				<a href="companyPartmentAdmin.action?nodeid=<ww:property value="nodeid"/>" class="<%=ParseLabelXml.getStyleName("service_company_manager_partment_manager",local)%>"><span>部门管理</span></a>
			</li>
			</ww:if>
		</ul>
	</div>
	<p>&nbsp;</p>
  </body>
</html>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib  prefix="ww" uri="webwork"%>
<%@page import="cn.insurance.utils.ParseLabelXml" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  String local=request.getServletPath();
            // out.println("servletPath" +request.getServletPath());
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<base href="<%=basePath%>"></base>

	<title>人保武侯分公司旅游保险系统</title>
	
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    

	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/mainst.css"/>
	
	<script type="text/javascript" src="js/myjs.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/meizzDate.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/prototype.js"></script>

 
    
</head>
  
<body>
	<div id="warp">
	<div id="header">
    	<div class="info">欢迎您，<ww:property value="%{#session.tbUser.tbPartment.strPartmentName}"/> &nbsp;<ww:property value="%{#session['tbUser'].strUserName}"/> | 现在是 <ww:date name="new java.util.Date()" format="yy-MM-dd hh:mm"/><br />
        	 <a href="javascript:window.external.AddFavorite('http://www.vip-china.cn/insurance','人保武侯旅游险网上投保系统')">收藏本站</a> |
        	 <ww:a href="javascript:openAWindow('toUpdateUserInfoByUser.action')">修改个人信息</ww:a> |
        	 <a href="logout.action">切换用户</a> | 
        	 <a href="logout.action">退出</a>
        </div>
        <img class="logo" src="images/danzheng_05.gif" alt="旅行社保险系统" />
        
         <!--  -->
        
        <div class="menus">
        	<ul>
            	 <li><a href="c_userMessage.action" class="<%=ParseLabelXml.getStyleName("client_index",local)%>" ><span>首页</span></a></li>
                <li><a  href="c_toApplyNewBill.action" title="申请新的保单" class="<%=ParseLabelXml.getStyleName("client_apply",local)%>"><span>申请</span></a></li>
               <!-- 
					<li><a href="toManagerBillByZs.action"><span>核单</span></a></li>
				 -->				
				<li><a href="c_toJieSuanByUser.action" title="结算" class="<%=ParseLabelXml.getStyleName("client_jiesuan",local)%>"><span>结算</span></a></li>
				<ww:if test="#session.tbUser.tbPartment.intParentId==0 && #session.tbUser.tbPartment.intIsSeePartmentBill==1 ">
				<li><a  href="toAdminCompany.action" class="<%=ParseLabelXml.getStyleName("service_company_manager",local)%>" title="对旅行社及部门的用户、帐户等进行管理"><span>旅行社管理</span></a></li>
				<li><a  href="c_toShowPartmentBill.action" title="部门的保单" class="<%=ParseLabelXml.getStyleName("client_partment",local)%>"><span>部门</span></a></li>
				</ww:if>
				<ww:if test="#session['tbUser'].tbPartment.intParentId !=0">
				 	<!-- 部门查询 -->
					<li ><a href="c_toQueryBillByLxsPartment.action" class="<%=ParseLabelXml.getStyleName("client_p_query",local)%>"><span>查询</span></a></li>
				</ww:if>
				<ww:else>
					<!-- 总社查询 -->
					<li ><a href="c_toQueryBillByLxsPartment.action" class="<%=ParseLabelXml.getStyleName("client_p_query",local)%>"><span>查询</span></a></li>
				</ww:else>
				<li class="on"><a href="c_toManagerApplyUpdate.action" class="<%=ParseLabelXml.getStyleName("client_applyupdate",local)%>"><span>批改申请</span></a></li>
				<!--<li><a href="toPartmentMonthStat.action" target="blank" ><span>统计</span></a></li>
				
					-->
				<li><a href="c_toPartmentTongJi.action" class="<%=ParseLabelXml.getStyleName("client_tongji",local)%>" ><span>统计</span></a></li>
				<li class="re"><a  href="<%=basePath %>client/help.jsp" title="帮助文档" class="<%=ParseLabelXml.getStyleName("client_p_help",local)%>"><span>使用帮助</span></a></li>
               
          		<li class="re"><a href="<%=basePath %>client/baoan.jsp" title="报案电话等信息" class="<%=ParseLabelXml.getStyleName("client_p_phone",local)%>"><span>报案电话</span></a></li>
				
            </ul>
        </div>
    </div>
  </div>
	
</body>
</html>
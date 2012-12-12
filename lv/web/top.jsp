<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="cn.insurance.model.TbUser"%>
<%@page import="cn.insurance.commonwords.SessionKey"%>
<%@page import="cn.insurance.servlet.YueJieServlet"%>
<%@ taglib  prefix="ww" uri="webwork"%>
<%@page import="cn.insurance.utils.ParseLabelXml" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 	String local=request.getServletPath();
	//out.println(request.getServletPath());
	TbUser user = (TbUser)session.getAttribute(SessionKey.USER_SESSION_KEY) ;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>人保武侯分公司旅游保险系统</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/mainst.css">
	<script type="text/javascript" src="<%=basePath%>js/myjs.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/prototype.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/meizzDate.js"></script>
	<!-- 修改于2010-6-23 -->
	<script type="text/javascript">
	var xmlHttp;
	function createXMLHttp() {
		if (window.ActiveXObject) {
			try {xmlHttp = new ActiveXObject("MSXML2.XMLHTTP");} catch(e){}
			try {xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");} catch(e){xmlHttp = null;}
		} else {
			if (window.XMLHttpRequest) xmlHttp = new XMLHttpRequest();
			else xmlHttp = null;
		}
	}
	function send(state) {
		createXMLHttp();
		if (xmlHttp === null) {
			alert("ajax创建失败！");
			return;
		}
		var str;
		if(state == 0){str = "您确定要关闭自动收取么？";}else{str = "您确定要开启自动收取么？";}
		if(window.confirm(str)){}else{return}
		url = '<%=basePath%>' + 'yueJie.action?c=' + state;
		xmlHttp.open("post", url, true);
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					var y = document.getElementById("yueJie");
					if(state == 0){
						y.innerHTML = "开启自动收费";
						y.href = "javascript:send('1');";
						window.alert('自动收费关闭成功');
					}
					if(state == 1){
						y.innerHTML = "关闭自动收费";
						y.href = "javascript:send('0');";
						window.alert('自动收费开启成功，每月一号将自动收取预交款的旅行社费用');
					}
				}
			} else {
			}
		};
		xmlHttp.send(null);
	}
	
	</script>
</head>
  
<body>
	<div id="warp">
	<div id="header">
    	<div class="info">欢迎您，<ww:property value="%{#session['tbUser'].strUserName}"/> | 现在是 <ww:date name="new java.util.Date()" format="yy-MM-dd hh:mm"/><br />
        	 <!-- 修改于2010-6-23 -->
<%--        	 <%if(YueJieServlet.isRunning){%>--%>
<%--        		 <a href="javascript:send('0');" id="yueJie">关闭自动收费</a>--%>
<%--        	 <%}else{%>--%>
<%--        	  	<a href="javascript:send('1');" id="yueJie">开启自动收费</a>--%>
<%--        	 <%}%>--%>
        	| <a href="javascript:window.external.AddFavorite('http://www.vip-china.cn/insurance','人保武侯旅游险网上投保系统')">收藏本站</a> |
        	 <ww:a href="javascript:openAWindow('toUpdateUserInfoByUser.action')">修改个人信息</ww:a> |
        	 <a href="logout.action">切换用户</a> | 
        	 <a href="logout.action">退出</a>  
        </div>
        <img class="logo" src="images/danzheng_05.gif" alt="旅行社保险系统" />
        
        <div class="menus">
        	<ul>
            	 <li><a  href="bxDbMessage.action" class="<%=ParseLabelXml.getStyleName("service_index",local)%>" title="统计信息，待办事务及待阅事务"><span>首页</span></a></li>
            	 <li><a  href="getBillNeedBxSure.action" class="<%=ParseLabelXml.getStyleName("service_hedan",local)%>" title="审核保单"><span>核单</span></a></li>
            	 <li><a href="getAllNotReplyOfApplyForBx.action" class="<%=ParseLabelXml.getStyleName("service_apply_update",local)%>" title="批改申请"><span>批改申请</span></a></li>
            	 <!--
            	 	<li><a href="toManagerAccount.action" class="<%=ParseLabelXml.getStyleName("service_account_manager",local)%>"><span>帐户管理</span></a> </li>
                 -->
                 <li><a href="toMonthPayStatForBx.action" class="<%=ParseLabelXml.getStyleName("service_yuefei_stat",local)%>"><span>月费统计</span></a></li>
                
                <li><a  href="toAdminCompany.action" class="<%=ParseLabelXml.getStyleName("service_company_manager",local)%>" title="对旅行社及部门的用户、帐户等进行管理"><span>旅行社管理</span></a></li>
				<% if(user.getUserResourceMap().containsKey("toManagerPrice")){ %>
				<li><a  href="toManagerPrice.action" class="<%=ParseLabelXml.getStyleName("service_price_manager",local)%>" title="查询或更新价格"><span>价格管理</span></a></li>
				<%} %>
				<% if(user.getUserResourceMap().containsKey("toManagerBxUser")){ %>
				<li><a  href="toManagerBxUser.action" class="<%=ParseLabelXml.getStyleName("service_neibu_manager",local)%>" title="保险公司内部用户管理"><span>内部管理</span></a></li>
              	<%} %>
              	<% if(user.getUserResourceMap().containsKey("roleList")){ %>
              	<li><a  href="roleList.action" class="<%=ParseLabelXml.getStyleName("service_right_manager",local)%>" title="权限管理"><span>权限管理</span></a></li>
                <%} %>
                
                <li class="re"><a href="toAdminTongJi.action"><span>统计</span></a></li>
                <li class="re"><a href="toQueryBillByBx.action" class="<%=ParseLabelXml.getStyleName("service_search",local)%>"><span>查询</span></a></li>
           		 <li class="re"><a href="toManagerPeiKuan.action" class="<%=ParseLabelXml.getStyleName("service_peikuan_manager",local)%>"><span>赔款记录</span></a></li>
           		 <li class="re"><a  href="<%=basePath%>manager/check/index.jsp" class="<%=ParseLabelXml.getStyleName("service_bill_check",local)%>" title="对保单进行验证"><span>保单验证</span></a></li>
            </ul>
        </div>
    </div>
  </div>

	
</body>
</html>
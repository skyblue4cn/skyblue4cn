<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<div class="bdtop" width="100%" align="center">修改信息成功</div>
<li>修改信息成功！请<span style="color:red;">重新登录</span>！</li>
<br/>
<br/>
<input type="button" value="确定" onclick="window.opener.location.href='login.jsp';window.close()"/>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<p>&nbsp;</p>
<ww:include value="/message.jsp"></ww:include>
<p>&nbsp;</p>
<input type="button" value="删除" onclick="location.href='deleteRole.action?id=<ww:property value="tbRole.id"/>'" class="inputbutton" style="color:red;"/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="取消" onclick="returnParentWindow();" class="inputbutton"/>
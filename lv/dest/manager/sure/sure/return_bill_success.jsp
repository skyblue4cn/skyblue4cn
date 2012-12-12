<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<div  id="lvdbtable" style="width:100%;text-align: center;height: 500px;">
	<br/>
	<ww:actionmessage/>
	<input type="button" value="确定" onclick="window.opener.opener.location.reload() ;window.opener.close();window.close();"/>
</div>

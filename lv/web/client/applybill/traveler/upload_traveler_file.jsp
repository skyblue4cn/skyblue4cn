<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<br/>
<div>上传附件</div>
<br/>
<form action="uploadTravelerFile.action" method="post" enctype="multipart/form-data">
	<ww:hidden name="tbTravelerInfo.intBillId" value="%{tbTravelerInfo.intBillId}"/>
	<input type="file" name="travelerFile" value="浏览" />
	<input type="submit" value="上传" class="inputbutton"/>
</form>
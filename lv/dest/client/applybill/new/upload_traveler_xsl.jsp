<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<br/>
	<div class="text2">导入旅客明细</div>
<br>
<ww:if test="actionErrors[0]!= null">
	<span class="errm"><ww:property value="actionErrors[0]"/></span>
</ww:if>
<form action="c_loadTravelerByXsl.action" method="post" enctype="multipart/form-data">
	<table border="0" cellpadding="0" cellspacing="0" class="table" width="90%" style="line-height:30px;">
		<tr>
			<td>说明：</td>
			<td>请按规定的模板格式填写数据，否则导入的数据将不正确！ <a href="downloadSysFile.action?filePath=lvyou_muban.xls" >模板下载！</a>
			</td>
		</tr>
		<tr>
			<td>导入:(只能导入excel文件)</td>
			<td>
				<ww:file name="travelerFile" theme="simple"/>
				<input type="button" value="确定" onclick="this.form.submit()" class="inputbutton">
			</td>
		</tr>
	</table>
</form>


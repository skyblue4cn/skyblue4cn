<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<div  id="lvdbtable" style="width:100%;text-align: center;height:400;">
	<div class="bdtop" width="100%" align="center">添加游客</div>
	<div style="color:green;font-size: 14px;font-weight: bold;"><ww:actionmessage/></div>
	<br/>
	<ww:form action="#" method="post">
			<table border="0" cellpadding="0" cellspacing="0" width="60%">
				<tr>
					<td style="color:green;font-weight: bold;text-align:left;">导入游客信息</td>
				</tr>
				<tr>

					<td><input type="file" name="file"  value="浏览"/></td>
				</tr>
			</table>
			<br/>
			<br/>
			<table border="0" width="60%">
			<tr>
				<td align="center"><input type="button" class="inputbutton" value="提交" align="center"/></td>
				<td align="center"><input type="button" class="inputbutton" onclick="window.close();" value="取消"></td>
			</tr>	
			</table>
			</ww:form>
</div>
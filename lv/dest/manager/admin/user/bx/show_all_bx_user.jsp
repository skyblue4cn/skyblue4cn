<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<ww:include value="/top.jsp"/>

	   <br/>
		<table border="0" width="95%" cellpadding="0" cellspacing="0">
			<tr>
				<td width="80%"><div width="95%" class="text1">内部所有用户列表</div></td>
				<td><input type="button" class="inputbutton" value="添加用户" onclick="openAWindow('toAddBxUser.action')"/></td>
			</tr>
		</table>
			
		<br/>
		<table border=1 width="95%" cellpadding="0" cellspacing="0" class="table">
				<tr>
					<th scope="col">序号</th>
					<th scope="col">用户ID</th>
					<th scope="col">用户登录名</th>
					<th scope="col">用户真实姓名</th>
					<th scope="col">角色</th>
					<th scope="col">修改</th>
				</tr>
				
				<ww:iterator value="tbUserList" status="index">
					<tr <ww:if test="#index.index%2==0">bgcolor="#ededed"</ww:if>>
					<td><ww:property value="#index.index+1"/></td>
					<td><ww:property value="id"/></td>
					<td><ww:property value="strUserLogName"/></td>
					<td><ww:property value="strUserName"/></td>
					<td><ww:property value="tbRole.strRoleName"/></td>
					<td align="center"><input type="button" value="修改" class="inputbutton" onclick="openAWindow('toUpdateBxUser.action?id=<ww:property value="id"/>')"/></td>
				</tr>
				</ww:iterator>
		</table>
	


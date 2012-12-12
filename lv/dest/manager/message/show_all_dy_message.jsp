<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork" %>
<ww:include value="/top.jsp"></ww:include>
<html>
<body class="welcm">
<table border="0" cellpadding="0" cellspacing="0" width="95%" height="450">
	<tr valign="top">
		<td width="10%" style="text-align:center">
			<div class="setbar">
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
		      	<input id="button2" class="inputbutton" type="button" onclick="location.href='bxDbMessage.action'" value="待 办 事 务" /><br/><br/>
				<input id="button1" class="inputbutton3" type="button" onclick="location.href='bxDyMessage.action" value="待 阅 事 务" /><br/><br/>
     		 </div>
		</td>
		<td width="90%">
			<div id="contbar">
					<div class="pind">
		    		<img src="images/dzwelcm_03_2.gif" width="140" height="55" />
			        <div class="tsinfo">
							        
						<ul>
							 <ww:iterator value="pageBean.dataList" status="index">
								<li><ww:property value="#index.index+1"/>.
									<ww:date name="dteTime" format="MM月dd日hh时mm分"/>
									<ww:property value="strContent"/>
								</li>				
							</ww:iterator>
						</ul>			        
				
				   
					<ww:if test="pageBean.dataList.size()==0">
						<ul><li>没有信息！</li></ul>
					</ww:if>
				</div>
			</div>
			<br>
			<table width="98%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>&nbsp;</td>
								<td width="10%" align="center">
									当前第<ww:property value="pageBean.curPage"/>/<ww:property value="pageBean.maxPage"/>页
								</td>
								<td width="5%" align="right">
									<ww:if test="pageBean.curPage<=1">首页</ww:if>
									<ww:else>
									<a href="<ww:url><ww:param name="pageBean.curPage" value="1"></ww:param></ww:url>">首页</a>
									</ww:else>
								</td>
								<td width="5%" align="right">
									<ww:if test="pageBean.curPage<=1">上页</ww:if>
									<ww:else>
									<a href="<ww:url><ww:param name="pageBean.curPage" value="%{pageBean.curPage-1}"></ww:param></ww:url>">上页</a>
									</ww:else>
								</td>
								<td width="5%" align="right">
									<ww:if test="pageBean.curPage>=pageBean.maxPage">下页</ww:if>
									<ww:else>
									<a href="<ww:url><ww:param name="pageBean.curPage" value="%{pageBean.curPage+1}"></ww:param></ww:url>">下页</a>
									</ww:else>
								</td>
								<td width="5%" align="right">
									<ww:if test="pageBean.curPage>=pageBean.maxPage">末页</ww:if>
									<ww:else>
									<a href="<ww:url><ww:param name="pageBean.curPage" value="%{pageBean.maxPage}"></ww:param></ww:url>">末页</a>
									</ww:else>
								</td>
							</tr>
					</table>			
		</td>
	</tr>
</table>

</body>
</html>

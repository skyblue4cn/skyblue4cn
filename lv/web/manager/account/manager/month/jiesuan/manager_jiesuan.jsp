<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="webwork"%>

<ww:include value="/top.jsp"/>

<script type="text/javascript">
	
	function search(){
		var oj = document.forms['form1'] ;
		if(oj.companyId.value== -1){
			alert('请选择旅行社！') ;
			return ;
		}
		var url = '' ;
		if(oj.partmentId.value==-1){
			url = "getPartmentMonthStatByCompanyId.action" ;
		}else{
			url = "getPartmentMonthStatByPartmentId.action" ;
		}
		oj.submit();
	}
	
	function searchPartmentList(){
		var keyword = document.forms['form1'].company.value ;
		var postStr = 'tbPartment.id=' + keyword  ;
		var url="getAllPartmentToSelect.action" ;
		var myajax=new Ajax.Updater('partment',url,{method:'post',parameters:postStr});
	}
	
	function allselect(){
		var obj = document.forms['form2'];
		var checkobj = obj.getElementsByTagName('input') ;
		for(var i=0;i<checkobj.length;i++){
			if(checkobj[i].type=='checkbox'){
				checkobj[i].checked=true	
			}
		}
	}

	/*检查有没有选择，如果一个都没有选择，则不提交*/
	function checkselect(){
		var obj = document.forms['form2'];
		var checkobj = obj.getElementsByTagName('input') ;
		var bl = false ;
		for(var i=0;i<checkobj.length;i++){
			if(checkobj[i].type=='checkbox'){
				if(checkobj[i].checked==true){
					bl = true ;
					break ;
				}
			}
		}
		if(bl){
			obj.submit() ;
		}else{
			alert('请选择要交费的部门！')
		}	
	}
</script>
	<table border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr valign="top">
			<td valign="top" width="20%" bgcolor="#ededed" height="540px" align="center">
				<br><br><br>
				<form action="getPartmentMonthStatByCompanyId.action" method="get" name="form1" target="stateframe">
				<table border="0" align="center" width="80%" height="60px" cellpadding="0" cellspacing="0">
					<tr height=30px>
						<td class="text4">旅行社:</td>
					</tr>
					<tr height=30px>
						<td>
							<ww:select
								id="company" 
								name="companyId" list="partmentList" headerKey="-1" headerValue="请选择旅行社"
								listKey="id" listValue="strPartmentName"
								theme="simple" cssStyle="width:150px;"
								onchange="searchPartmentList()"/>
						</td>
					</tr>
					<tr height=30px>
						<td class="text4">部门：</td>
					</tr>
					<tr height=30px>
						<td>
							<span id="partment">
								<select name="partmentId" id="partmentSelect" style="width:150px;" >
									<option value="-1">所有部门</option>
								</select>
							</span>
						</td>
					</tr>
					<tr height=30px>
						<td class="text4">年份:</td>
					</tr>
					<tr height=30px>
						<td>
							<ww:select name="tbMonthPayInfo.strYear" list="#{theYear-2:theYear-2+'年',theYear-1:theYear-1+'年',theYear:theYear+'年',theYear+1:theYear+1 +'年',theYear+2:theYear+2+'年'}" value="%{theYear}" cssStyle="width:150px;" theme="simple"></ww:select>
						</td>
					</tr>
					<tr height=30px>
						<td class="text4">月份:</td>
					</tr>
					<tr height=30px>	
						<td>
							<ww:select name="tbMonthPayInfo.strMonth" list="#{1:'1月',2:'2月',3:'3月',4:'4月',5:'5月',6:'6月',7:'7月',8:'8月',9:'9月',10:'10月',11:'11月',12:'12月'}" value="%{theMonth}" cssStyle="width:150px;" theme="simple"></ww:select>
						</td>
					</tr>
						<tr height=30px>
						<td class="text4">刷选：</td>
					</tr>
					<tr height=30px>
						<td>
							<ww:radio name="isShowAll" list="#{-1:'只显示欠费部门<br>',1:'显示所有部门'}" value="-1" theme="simple"></ww:radio>
						</td>
					</tr>	
					<tr height=30px valign="bottom">
						<td align="left">
							<input type="button" class="inputbutton" onclick="search()" value="查询" title="点击查询 "/>
						</td>
					</tr>
				</table>
				</form>
			</td>
			<td valign="top">
				<br/>
				<div class="text1">月费统计</div>
				<br/>
				<div id="show" >
					<iframe frameborder="0" width="100%" name="stateframe" align="top" height="500px" scrolling="auto" ></iframe>
				</div>
			</td>
		</tr>
	</table>
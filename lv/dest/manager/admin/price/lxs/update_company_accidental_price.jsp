<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<div id="lvdbtable" style="width:100%;height:600px;text-align: center;">
<br/>
<div class="text1">
	意外险保险费(10万)
</div>
<br/>
	<fieldset>
	<legend>
		<strong>10万</strong>
	</legend>
	<form action="updateNormalAccidentalPrice.action" method="post" name="form1">
		<ww:hidden name="tbAccidentalFee.id" value="%{tbAccidentalFee.id}"/>
		<table width="95%" border="0" cellspacing="0" cellpadding="0" class="table">
			<tr height="30px">
				<td colspan="4" style="text-align: left;font-weight: bold;" >
					国内旅游
				</td>
			</tr>
			<tr>
				<td>1天</td>
				<td><input type="text" name="tbAccidentalFee.dbeOneDayFee" value="<ww:property value="tbAccidentalFee.dbeOneDayFee"/>" size="4"/>元</td>
				<td>2天</td>
				<td><input type="text" name="tbAccidentalFee.dbeTwoDayFee" value="<ww:property value="tbAccidentalFee.dbeTwoDayFee" />" size="4"/>元</td>
			</tr>
			<tr>
				<td>3天</td>
				<td><input type="text" name="tbAccidentalFee.dbeThreeDayFee" value="<ww:property value="tbAccidentalFee.dbeThreeDayFee"/>" size="4"/>元</td>
				<td>4天</td>
				<td><input type="text"  name="tbAccidentalFee.dbeFourDayFee" value="<ww:property value="tbAccidentalFee.dbeFourDayFee"/>" size="4"/>元</td>
			</tr>
			<tr>
				<td>5天</td>
				<td><input type="text" name="tbAccidentalFee.dbeThreeDayFee" value="<ww:property value="tbAccidentalFee.dbeThreeDayFee"/>" size="4"/>元</td>
				<td>6天</td>
				<td><input type="text"  name="tbAccidentalFee.dbeFourDayFee" value="<ww:property value="tbAccidentalFee.dbeFourDayFee"/>" size="4"/>元</td>
			</tr>
			<tr>
				<td>7天</td>
				<td><input type="text" name="tbAccidentalFee.dbeThreeDayFee" value="<ww:property value="tbAccidentalFee.dbeThreeDayFee"/>" size="4"/>元</td>
				<td>8天</td>
				<td><input type="text"  name="tbAccidentalFee.dbeFourDayFee" value="<ww:property value="tbAccidentalFee.dbeFourDayFee"/>" size="4"/>元</td>
			</tr>
			<tr>
				<td>9天</td>
				<td><input type="text" name="tbAccidentalFee.dbeThreeDayFee" value="<ww:property value="tbAccidentalFee.dbeThreeDayFee"/>" size="4"/>元</td>
				<td>10天</td>
				<td><input type="text"  name="tbAccidentalFee.dbeFourDayFee" value="<ww:property value="tbAccidentalFee.dbeFourDayFee"/>" size="4"/>元</td>
			</tr>
			<tr>
				<td>11天</td>
				<td><input type="text" name="tbAccidentalFee.dbeThreeDayFee" value="<ww:property value="tbAccidentalFee.dbeThreeDayFee"/>" size="4"/>元</td>
				<td>12天</td>
				<td><input type="text"  name="tbAccidentalFee.dbeFourDayFee" value="<ww:property value="tbAccidentalFee.dbeFourDayFee"/>" size="4"/>元</td>
			</tr>
			<tr>
				<td>12天以上</td>
				<td colspan="3">每超出一天加收<input type="text"  name="tbAccidentalFee.dbeAboveTwelveDayFee" value="<ww:property value="tbAccidentalFee.dbeAboveTwelveDayFee"/>" size="4"/>元</td>
			</tr>
			<tr height="30px">
				<td colspan="4" style="text-align: left;font-weight: bold;" >出入境旅游</td>
			</tr>
			<tr>
				<td>30天以下</td>
				<td><input type="text" name="tbAccidentalFee.dbeThirtyDayFee" value="<ww:property value="tbAccidentalFee.dbeThirtyDayFee"/>" size="4"/>元</td>
				<td>30天以上</td>
				<td><input type="text"  name="tbAccidentalFee.dbeAboveThirtyDayFee" 
					value="<ww:property value="tbAccidentalFee.dbeAboveThirtyDayFee"/>"size="4"/>元</td>
			</tr>
			<tr height="30px">
				<td colspan="4" style="text-align: left;font-weight: bold;" >其他特别旅游项目</td>
			</tr>
			<tr>
				<td>自由人</td>
				<td colspan="3">在国内和出入境旅游保险费基础上加收<input type="text" size="1" name="tbAccidentalFee.dbeFreeManFeeRate" value="<ww:property value="tbAccidentalFee.dbeFreeManFeeRate"/>" size="4"/>%</td>
			</tr>
			<tr>
				<td>特殊旅游项目</td>
				<td colspan="3">在国内和出入境旅游保险费基础上加收<input type="text" size="1" name="tbAccidentalFee.dbeSpecialItemFeeRate" value="<ww:property value="tbAccidentalFee.dbeSpecialItemFeeRate"/>" size="4"/>%</td>
			</tr>	
		</table>
		<br/>
		<table border="0" width="95%">
			<tr>
				<td>
					<input type="button"  class="inputbutton" value="提交" onclick="if(window.confirm('确认提交吗?'))this.form.submit()"/>
				</td>
				<td>
					<input type="button" class="inputbutton" value="取消" onclick="window.close()"/>
				</td>
			</tr>
		</table>
	</form>
</fieldset>
<fieldset>
	<legend>
		<strong>自驾车10万</strong>
	</legend>
	<form action="updateNormalAccidentalPrice.action" method="post" name="form1">
		<ww:hidden name="tbAccidentalFee.id" value="%{tbAccidentalFee.id}"/>
		<table width="95%" border="0" cellspacing="0" cellpadding="0" class="table">
			<tr height="30px">
				<td colspan="4" style="text-align: left;font-weight: bold;" >
					国内旅游
				</td>
			</tr>
			<tr>
				<td>1天</td>
				<td><input type="text" name="tbAccidentalFee.dbeOneDayFee" value="<ww:property value="tbAccidentalFee.dbeOneDayFee"/>" size="4"/>元</td>
				<td>2天</td>
				<td><input type="text" name="tbAccidentalFee.dbeTwoDayFee" value="<ww:property value="tbAccidentalFee.dbeTwoDayFee" />" size="4"/>元</td>
			</tr>
			<tr>
				<td>3天</td>
				<td><input type="text" name="tbAccidentalFee.dbeThreeDayFee" value="<ww:property value="tbAccidentalFee.dbeThreeDayFee"/>" size="4"/>元</td>
				<td>4天</td>
				<td><input type="text"  name="tbAccidentalFee.dbeFourDayFee" value="<ww:property value="tbAccidentalFee.dbeFourDayFee"/>" size="4"/>元</td>
			</tr>
			<tr>
				<td>5天</td>
				<td><input type="text" name="tbAccidentalFee.dbeThreeDayFee" value="<ww:property value="tbAccidentalFee.dbeThreeDayFee"/>" size="4"/>元</td>
				<td>6天</td>
				<td><input type="text"  name="tbAccidentalFee.dbeFourDayFee" value="<ww:property value="tbAccidentalFee.dbeFourDayFee"/>" size="4"/>元</td>
			</tr>
			<tr>
				<td>7天</td>
				<td><input type="text" name="tbAccidentalFee.dbeThreeDayFee" value="<ww:property value="tbAccidentalFee.dbeThreeDayFee"/>" size="4"/>元</td>
				<td>8天</td>
				<td><input type="text"  name="tbAccidentalFee.dbeFourDayFee" value="<ww:property value="tbAccidentalFee.dbeFourDayFee"/>" size="4"/>元</td>
			</tr>
			<tr>
				<td>9天</td>
				<td><input type="text" name="tbAccidentalFee.dbeThreeDayFee" value="<ww:property value="tbAccidentalFee.dbeThreeDayFee"/>" size="4"/>元</td>
				<td>10天</td>
				<td><input type="text"  name="tbAccidentalFee.dbeFourDayFee" value="<ww:property value="tbAccidentalFee.dbeFourDayFee"/>" size="4"/>元</td>
			</tr>
			<tr>
				<td>11天</td>
				<td><input type="text" name="tbAccidentalFee.dbeThreeDayFee" value="<ww:property value="tbAccidentalFee.dbeThreeDayFee"/>" size="4"/>元</td>
				<td>12天</td>
				<td><input type="text"  name="tbAccidentalFee.dbeFourDayFee" value="<ww:property value="tbAccidentalFee.dbeFourDayFee"/>" size="4"/>元</td>
			</tr>
			<tr>
				<td>12天以上</td>
				<td colspan="3">每超出一天加收<input type="text"  name="tbAccidentalFee.dbeAboveTwelveDayFee" value="<ww:property value="tbAccidentalFee.dbeAboveTwelveDayFee"/>" size="4"/>元</td>
			</tr>
			<tr height="30px">
				<td colspan="4" style="text-align: left;font-weight: bold;" >出入境旅游</td>
			</tr>
			<tr>
				<td>30天以下</td>
				<td><input type="text" name="tbAccidentalFee.dbeThirtyDayFee" value="<ww:property value="tbAccidentalFee.dbeThirtyDayFee"/>" size="4"/>元</td>
				<td>30天以上</td>
				<td><input type="text"  name="tbAccidentalFee.dbeAboveThirtyDayFee" 
					value="<ww:property value="tbAccidentalFee.dbeAboveThirtyDayFee"/>"size="4"/>元</td>
			</tr>
			<tr height="30px">
				<td colspan="4" style="text-align: left;font-weight: bold;" >其他特别旅游项目</td>
			</tr>
			<tr>
				<td>自由人</td>
				<td colspan="3">在国内和出入境旅游保险费基础上加收<input type="text" size="1" name="tbAccidentalFee.dbeFreeManFeeRate" value="<ww:property value="tbAccidentalFee.dbeFreeManFeeRate"/>" size="4"/>%</td>
			</tr>
			<tr>
				<td>特殊旅游项目</td>
				<td colspan="3">在国内和出入境旅游保险费基础上加收<input type="text" size="1" name="tbAccidentalFee.dbeSpecialItemFeeRate" value="<ww:property value="tbAccidentalFee.dbeSpecialItemFeeRate"/>" size="4"/>%</td>
			</tr>	
		</table>
		<br/>
		<table border="0" width="95%">
			<tr>
				<td>
					<input type="button"  class="inputbutton" value="提交" onclick="if(window.confirm('确认提交吗?'))this.form.submit()"/>
				</td>
				<td>
					<input type="button" class="inputbutton" value="取消" onclick="window.close()"/>
				</td>
			</tr>
		</table>
	</form>
</fieldset>
</div>
<script>	document.getElementById("lvdbtable").style.overflow = "auto";	</script>
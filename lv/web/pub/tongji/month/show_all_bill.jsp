<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<ww:include value="/head.jsp"></ww:include>
<ww:iterator value="partmentMonthStat">
	<ww:property value="strBillNumber"/>
</ww:iterator>
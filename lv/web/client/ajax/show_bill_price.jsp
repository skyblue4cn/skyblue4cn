<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="ww" uri="webwork"%>

<table width="90%" border="0" cellpadding="0" cellspacing="0" class="table" title="点击鼠标左键刷新价格">
            <tr>
              <td width="10%">保险费</td>
              <td width="15%">内宾（元/人）</td>
              <td width="25%"><ww:property value="tbBill.dbeChinaFee"/></td>
              <td width="15%">外宾（元/人）</td>
              <td width="25%"><ww:property value="tbBill.dbeOtherFee"/></td>
            </tr>
            <tr>
              <td>保费合计(元)</td>
              <td colspan="4"><ww:property value="tbBill.dbeAllFee"/></td>
            </tr>
</table>

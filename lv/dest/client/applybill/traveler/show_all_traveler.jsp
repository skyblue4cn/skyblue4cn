<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="webwork" prefix="ww" %>
<a href="#">导入明细</a>
            <a href="javascript:openNewWindow('toAddTraveler.action?intBillId=<ww:property value="tbBill.id"/>')">添加游客</a>
            <span class="lvtitle2">参保旅客明细</span>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table">
              <tr>
                <td>序号</td>
                <td>姓名</td>
                <td>国籍</td>
                <td>身份证/护照号</td>
              </tr>
              <ww:iterator value="tbTravelerInfoList" status="index">
              <tr>
                <td><ww:property value="#index.index+1"/></td>
                <td><ww:property value="strTravelerName"/></td>
                <td><ww:property value="strCountry"/></td>
                <td><ww:property value="strIndentyNumber"/></td>
              </tr>
              </ww:iterator>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            
            </table>
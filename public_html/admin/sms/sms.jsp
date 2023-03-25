<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="smsControler" />
<table width="100%" border="0px">
<TR>
<TD>
<TABLE align="left" class=adminheading border=0><TBODY><TR><TH class="user"><bean:message key="category.tree.sms" bundle="<%=interfaces%>"/></TH></TR></TBODY></TABLE>
</td>
</tr>
<tr>
    <td id="Idtab">
            <jsp:include page="/admin/sms/tab.jsp" />
    </td>
</tr>
<TR>
    <TD id="mainSms" align="left">
          <jsp:include page="/admin/sms/formPermision.jsp" /> 
    </td>
</tr>
</table>

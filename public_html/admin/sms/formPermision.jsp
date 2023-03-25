<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="sms" >
<table width="100%" border="0px">
<TR>
    <TD id="formSmsId" align="left">
            <jsp:include page="/admin/sms/smsForm.jsp" />
    </td>
</tr>
<TR>
        <TD>
        <jsp:include page="/admin/paging.jsp">
            <jsp:param name="BEANS" value="BLogs"/>
            <jsp:param name="FORM" value="log"/>
            <jsp:param name="METHOD" value="post"/>
        </jsp:include>
        </td>
</tr></table>
<TABLE class=adminlist>
  <TBODY>
<TR>
    <TH class=title noWrap>#</th>
    <TH class=title noWrap>
        <bean:message key="category.sms.fullname" bundle="<%=interfaces%>"/>
    </TH>
    <TH class=title noWrap>    
        <bean:message key="category.sms.day" bundle="<%=interfaces%>"/>
    </TH>
    <TH class=title noWrap>
        <bean:message key="category.sms.week" bundle="<%=interfaces%>"/>
    </TH>
    <TH class=title noWrap>
        <bean:message key="category.sms.month" bundle="<%=interfaces%>"/>
    </TH>
    <TH class=title noWrap width="25px" >#</th>
</TR>
<logic:present name="BSmss">
<%int i=1;%>
<logic:iterate name="BSmss" id="bean" type="com.form.admin.sms.FSms">                       
  <TR class=row0>
    <TD><%=i++%></TD>
    <TD><bean:write name="bean" property="fullName"/></TD>
    <TD><bean:write name="bean" property="day"/></TD>
    <TD><bean:write name="bean" property="week"/></TD>
    <TD><bean:write name="bean" property="month"/></TD>
    <TD nowrap  width="40px">
    <img style="border:0px" src="<%=contextPath%>/images/update.png" title="<bean:message key="action.update" bundle="<%=interfaces%>"/>" onClick="javascript:postAjax('sms','tdMainBody',anchor + ':_PREPARED_EDIT:id:<%=bean.getId()%>');">
    <img style="border:0px" src="<%=contextPath%>/images/delete.png" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete()) postAjax('sms','tdMainBody',anchor + ':_DELETE:id:<%=bean.getId()%>');">
    </TD>
    </TR>
</logic:iterate>
</logic:present>
</TBODY></TABLE>
</html:form>
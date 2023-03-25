<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table width="100%" border="0px"><TR><TD>
</td><TD>
<jsp:include page="/admin/paging.jsp">
    <jsp:param name="BEANS" value="BLogs"/>
    <jsp:param name="FORM" value="logSms"/>
    <jsp:param name="METHOD" value="post"/>
</jsp:include>
</td>
</tr></table>
<html:form action="logSms">
<table width="100%" border="0px">
<TR>
    <TD align="left">
           <html:select name="logSms" property="userId"  style="width:100px" onchange="postAjax('logSms','mainSms',anchor + ':_VIEW');messageImg('mainSms')">
            <html:option value="">-- <bean:message key="category.sms.log.fullname" bundle="<%=interfaces%>"/>--</html:option>
            <logic:present name="BUsers" >  
            <html:options collection="BUsers" property="id" labelProperty="fullName"/>
            </logic:present>
        </html:select>
        
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
    <th>#</th>
    <TH class=title noWrap><bean:message key="category.sms.log.fullname" bundle="<%=interfaces%>"/></TH>
    <TH class=title noWrap><bean:message key="category.sms.log.datesend" bundle="<%=interfaces%>"/></TH>
    <TH class=title noWrap><bean:message key="category.sms.log.phone" bundle="<%=interfaces%>"/></TH>
    <TH class=title noWrap>#</th>
    </TR>
<logic:present name="BLogSmss">
<%int i = 0;%>
<logic:iterate name="BLogSmss" id="bean" type="com.form.admin.sms.FLogSms">                       
  <TR class=row0>
    <td><%=++i%></td>
    <TD><bean:write name="bean" property="fullName"/></TD>
    <TD><bean:write name="bean" property="dateSend"/></TD>
    <TD><bean:write name="bean" property="phoneNumber"/></TD>
    </TR>
</logic:iterate>
</logic:present>
</TBODY></TABLE>
</html:form>
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table width="100%" cellpadding="0" cellspacing="0" border="0">  
    <tr>
        <td align="left" width="16%" style="padding-left:4px">
            <bean:message key="search.listName" bundle="<%=interfaces%>"/>
        </td>
        <td align="left">
            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/> <html:text name="searchdispeople" property="listName" style="width:250px;" />
        </td>              
        <td align="right">
            <html:button property="_CREATE" onclick="return listReport()" styleClass="button">
            <bean:message key="search.listReport.new" bundle="<%=interfaces%>"/>
            </html:button>
        </td>
    </tr> 
</table>
  

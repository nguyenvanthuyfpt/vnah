<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BReports">
<div class="content-calendar-2">
<table width="100%" cellpadding="0" cellspacing="0" border="0">
<logic:iterate name="BReports" id="bean" type="com.form.admin.reportSystem.FReportSystem">                                       
    <tr><td align="left">
    <a href="javascript:post('disabilityReport', anchor + ':_REPORT:id:<%=bean.getId()%>');remove('disabilityReport',anchor);remove('disabilityReport','id')">
        <bean:write name="bean" property="nameOfFileVn" />
    </a></td>
    <td align="left">

    </td>
    </tr>
</logic:iterate>
</table>
</div>
</logic:present> 

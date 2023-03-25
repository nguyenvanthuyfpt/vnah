<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="agenda" method="post" >
<div align="left">
        <html:select name="agenda" property="departmentId" onchange="javascript:postAjax('agenda','idCalendaInDeps',anchor + ':_SEARCH_MAIN')"  style="border:1px solid #CCCCCC;" >
        <html:option value="0"><bean:message key="status.all" bundle="<%=interfaces%>"/></html:option>
        <logic:present name="BDepartments">
        <html:options collection="BDepartments" property="id" labelProperty="name"/>
        </logic:present>
        </html:select> 
</div>
<div id="idCalendaInDeps">
   <jsp:include page="/main/calendarDep.jsp" />
</div>
</html:form>
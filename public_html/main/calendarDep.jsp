<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="agenda" method="post" >
<%if(me.isRole(com.inf.IRoles.rOBAGENDA)){%>    
<div align="left" style="padding:10px">
<Strong><bean:message key="departments.select.title.caption" bundle="<%=interfaces%>"/>:</strong>
        <html:select name="agenda" property="departmentId" onchange="javascript:postAjax('agenda','idCalendaInDeps',anchor + ':_SEARCH_MAIN:calendarType:2')"  style="border:1px solid #CCCCCC;" >
        <logic:present name="BDepartments">
        <html:options collection="BDepartments" property="id" labelProperty="name"/>
        </logic:present>
        </html:select> 
</div>
<%}%>

<div id="idCalendaInDeps">
      <jsp:include page="/calendar/calendarDep.jsp" />
</div>
</html:form>
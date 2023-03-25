<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="agendaXe"  method="post" >
<input type="hidden" name="userIds" id="userIds" value="" />
<div id="idEdit">
  <logic:notEqual name="agendaXe" property="calendarType" value="1" > 
    <jsp:include page="/calendarXe/formPrivate.jsp" />
  </logic:notEqual>
</div>
</html:form>
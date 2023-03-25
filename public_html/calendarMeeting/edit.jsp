<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="agendaMeeting"  method="post" >
<input type="hidden" name="userIds" id="userIds" value="" />
<div id="idEdit">
  <logic:notEqual name="agendaMeeting" property="calendarType" value="1" > 
    <jsp:include page="/calendarMeeting/formPrivate.jsp" />
  </logic:notEqual>
</div>
</html:form>
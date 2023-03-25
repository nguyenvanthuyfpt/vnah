<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/layout/layoutEdit.jsp" flush="true">
      <tiles:put name="tree" value="/calendarMeeting/month/leftContent.jsp" />
      <tiles:put name="content" value="/calendarMeeting/month/main.jsp" />
  </tiles:insert>
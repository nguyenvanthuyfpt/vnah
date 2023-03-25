<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/layout/layoutEdit.jsp" flush="true">
      <tiles:put name="tree" value="/calendar/month/leftContent.jsp" />
      <tiles:put name="content" value="/calendar/month/main.jsp" />
  </tiles:insert>
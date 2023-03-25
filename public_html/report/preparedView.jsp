<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/layout/layoutTree.jsp" flush="true">
      <tiles:put name="tree" value="/report/leftContent.jsp" />
      <tiles:put name="content" value="/report/editForm.jsp" />
  </tiles:insert>
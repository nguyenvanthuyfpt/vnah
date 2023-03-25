<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/layout/layoutTree.jsp" flush="true">
      <tiles:put name="tree" value="/cabin/leftContent.jsp" />
      <tiles:put name="content" value="/cabin/tools.jsp" />
  </tiles:insert>
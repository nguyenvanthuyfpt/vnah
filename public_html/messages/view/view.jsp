<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/layout/layoutTree.jsp" flush="true">
      <tiles:put name="tree" value="/messages/view/leftContent.jsp" />          
      <tiles:put name="content" value="/messages/view/list.jsp" />
  </tiles:insert>

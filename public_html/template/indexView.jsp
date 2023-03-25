<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/layout/layoutTree.jsp" flush="true">
      <tiles:put name="tree" value="/template/left.jsp" />
      <tiles:put name="content" value="/template/content.jsp" />
 </tiles:insert>

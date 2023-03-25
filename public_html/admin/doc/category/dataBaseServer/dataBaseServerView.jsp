<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/admin/layout/layoutTree.jsp" flush="true">
      <tiles:put name="tree" value="/commons/mainTreeDm.jsp" />
      <tiles:put name="content" value="/doc/category/dataBaseServer/context.jsp" />
  </tiles:insert>
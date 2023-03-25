<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/admin/layout/layoutTree.jsp" flush="true">
      <tiles:put name="tree" value="/commons/mainTreeDm.jsp" />
      <tiles:put name="content" value="/report/reportType/Context.jsp" />
  </tiles:insert>
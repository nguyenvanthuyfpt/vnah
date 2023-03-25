<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/layout/layout.jsp" flush="true">
      <tiles:put name="tree" value="/commons/mainTree.jsp" />
      <tiles:put name="leftContent" value="/doc/docssend/leftContent.jsp" />
      <tiles:put name="content" value="/doc/docssend/listView.jsp" />
  </tiles:insert>
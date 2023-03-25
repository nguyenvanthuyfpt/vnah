<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/layout/layoutTree.jsp" flush="true">       
      <tiles:put name="tree" value="/tasks/problem/leftContent.jsp" />
      <tiles:put name="content" value="/tasks/problem/view.jsp" />
  </tiles:insert>

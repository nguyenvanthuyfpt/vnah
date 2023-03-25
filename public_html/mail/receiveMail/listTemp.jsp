<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/layout/layoutTree.jsp" flush="true">
      <tiles:put name="tree" value="/mail/menu/tree.jsp" />
      <tiles:put name="content" value="/mail/receiveMail/list.jsp" />
 </tiles:insert>

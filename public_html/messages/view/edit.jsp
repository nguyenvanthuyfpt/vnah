<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/messages/layout/layoutEdit.jsp" flush="true">
      <tiles:put name="tree" value="/messages/view/tree.jsp" />      
      <tiles:put name="content" value="/messages/view/formViewEdit.jsp" />
      <tiles:put name="footer" value="/commons/footer.jsp" />
  </tiles:insert>

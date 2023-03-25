<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/layout/layoutTree.jsp" flush="true">   
     <tiles:put name="tree" value="/directory/leftContent.jsp" />
      <tiles:put name="content" value="/directory/view.jsp" />
      <tiles:put name="footer" value="/commons/footer.jsp" />
  </tiles:insert>

<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/layout/layoutEdit.jsp" flush="true">    
      <tiles:put name="content" value="/broadcast/listDetail.jsp" />
      <tiles:put name="footer" value="/commons/footer.jsp" />
  </tiles:insert>

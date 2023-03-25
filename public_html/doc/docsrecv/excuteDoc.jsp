<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <input type="hidden" name="secureId" value="<bean:write name="secureId" />" />                       
<span><jsp:include page="/doc/docsrecv/tag.jsp" /></span>
<span id="divReview"><jsp:include page="/doc/docsrecv/listReview.jsp" /></span>  
<span><jsp:include page="/doc/docsrecv/toolcmd.jsp" /></span>


               
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp"%>
<input type="hidden" name="secureId" value="<bean:write name="secureId" />" /> 
<span><jsp:include page="/doc/docssend/tag.jsp"/></span>
<span id="divReview"><jsp:include page="/doc/docssend/listReview.jsp"/></span>
<span><jsp:include page="/doc/docssend/toolcmd.jsp"/></span>

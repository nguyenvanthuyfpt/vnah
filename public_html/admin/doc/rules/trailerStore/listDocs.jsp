<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:equal name="trailerStore" property="workflowId" value="1" >
        <jsp:include page="/admin/doc/rules/trailerStore/docsrecv/list.jsp" />
</logic:equal>
<logic:notEqual name="trailerStore" property="workflowId" value="1" >
        <jsp:include page="/admin/doc/rules/trailerStore/docssend/list.jsp" />
</logic:notEqual>

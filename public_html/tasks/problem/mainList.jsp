 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:equal name="problem" property="type" value="1">
    <jsp:include page="/tasks/problem/listRecv.jsp"/>
</logic:equal>
<logic:notEqual name="problem" property="type" value="1">
    <jsp:include page="/tasks/problem/list.jsp"/>
</logic:notEqual> 

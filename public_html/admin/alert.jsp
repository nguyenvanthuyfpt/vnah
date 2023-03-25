<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<span id="divAlert">
    <html:errors property="alert" bundle="<%=interfaces%>" />
</span>

<script language="javascript">
setTimeout("clear()",4500);
</script>
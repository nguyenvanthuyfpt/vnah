<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BFileScans">
<div>
<%int i=0;%>
<ol >
<logic:iterate name="BFileScans" id="bean" type="java.lang.String">
<li><input type="checkbox" name="idFiles" value="<%=i%>">
<a href="javascript:post('docsrecv',anchor+':_VIEW_FILESCAN:fileId:<%=i++%>');remove('docsrecv',anchor);remove('docsrecv','fileId');"><bean:write name="bean"/></a></li>
</logic:iterate>
</ol>
</div>
</logic:present>
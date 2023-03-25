<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<logic:present name="BSearch">
<logic:iterate name="BSearch" id="bean" type="com.form.doc.docsrecv.FDocsrecv">   
<%=bean.getId()%>###<bean:write name="bean" property="docCode"/>:<bean:write name="bean" property="abstracts"/>|
</logic:iterate>
</logic:present>

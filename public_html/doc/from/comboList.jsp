 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BFroms">
<logic:iterate name="BFroms" id="bean" type="com.form.doc.from.FFrom">   
<%=bean.getId()%>###<bean:write name="bean" property="vnName"/>|
</logic:iterate>
</logic:present>

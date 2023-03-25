<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BMycontacts">
<logic:iterate name="BMycontacts" id="bean" type="com.form.mycontact.FMycontact">
<%=bean.getId()%>###
<logic:notEqual name="bean" property="fullName" value="">
"<bean:write name="bean" property="fullName"/>" 
</logic:notEqual>
&lt;<bean:write name="bean" property="email"/>&gt;|
</logic:iterate>
</logic:present>
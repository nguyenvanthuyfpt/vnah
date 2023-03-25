<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BMycontacts">
<logic:iterate name="BMycontacts" id="bean" type="com.form.admin.users.FUser">
<%=bean.getId()%>###
<logic:notEqual name="bean" property="fullName" value="">
<bean:write name="bean" property="fullName"/>
</logic:notEqual>
|
</logic:iterate>
</logic:present>
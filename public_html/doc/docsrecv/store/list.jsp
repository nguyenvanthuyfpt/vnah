<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BSearchStore">
<logic:iterate name="BSearchStore" id="bean" type="com.form.admin.departments.FDepartment">   
<%=bean.getId()%>###<bean:write name="bean" property="code"/> :<bean:write name="bean" property="name"/>|
</logic:iterate>
</logic:present>

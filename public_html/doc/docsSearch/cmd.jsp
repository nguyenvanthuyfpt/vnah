<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:submit property="_SEARCH" styleClass="button" style="width:80px;">
     <bean:message key="categories.cmd.search" bundle="<%=interfaces%>"/>
</html:submit>
<html:reset property="_RESET"  styleClass="button">
  <bean:message key="form.docs.command.reset" bundle="<%=interfaces%>"/>
</html:reset>



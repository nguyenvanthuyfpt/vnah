<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="quanhe" method="post">
<html:hidden name="quanhe" property="id"/>
<div class="padding-content">
<TABLE align="left">
  <TBODY>
  <TR>
    <TD vAlign=top>
    <logic:present name="quanhe">
<table class="tableForm" cellpadding="0" width="100%" align="center" style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">    
        <TBODY>
        <TR>
          <TD><bean:message key="lydo.edit.name" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD><html:text  name="lydo" property="name" styleClass="inputbox" size="40" /> </TD></TR>
        <TR>
          <TD><bean:message key="lydo.edit.code" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD>
          <html:text  name="lydo" property="code" styleClass="inputbox" size="40" /> 
          </TD></TR>
</TBODY></TABLE>
    </logic:present>
    </TD></TR>
<TR>
    <TD>
        <logic:present name="quanhe">
              <jsp:include page="/disability/categorys/quanhe/cmd.jsp" />
        </logic:present>
    </TD></TR>
          </TBODY></TABLE>
 </div>
</html:form>           

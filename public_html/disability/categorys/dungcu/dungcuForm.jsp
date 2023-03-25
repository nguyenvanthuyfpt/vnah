<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="dungcu" method="post">
<html:hidden name="dungcu" property="id"/>
<div class="padding-content">
<TABLE align="left">
  <TBODY>
  <TR>
    <TD vAlign=top>
    <logic:present name="dungcu">
<table class="tableForm" cellpadding="0" width="100%" align="center" style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">    
        <TBODY>
        <TR>
          <TD><bean:message key="dungcu.edit.name" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD><html:text  name="dungcu" property="name" styleClass="inputbox" size="40" /> </TD></TR>
        <TR>
          <TD><bean:message key="dungcu.edit.code" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD>
          <html:text  name="dungcu" property="code" styleClass="inputbox" size="40" /> 
          </TD></TR>
        <TR>
          <TD vAlign=top><bean:message key="dungcu.edit.parent" bundle="<%=interfaces%>"/></TD>
          <TD>
              <html:select styleClass="inputbox" name="dungcu" property="parentID">
                <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                <html:options collection="BDungcus" property="id" labelProperty="name"/>
              </html:select>   
          </TD></TR>
</TBODY></TABLE>
    </logic:present>
    </TD></TR>
<TR>
    <TD>
        <logic:present name="dungcu">
              <jsp:include page="/disability/categorys/dungcu/cmd.jsp" />
        </logic:present>
    </TD></TR>
          </TBODY></TABLE>
</div>      
</html:form>           

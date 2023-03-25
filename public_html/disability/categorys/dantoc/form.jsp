<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="dantoc" method="post">
<html:hidden name="dantoc" property="id"/>
<div class="padding-content">
<TABLE align="left">
  <TBODY>
  <TR>
    <TD vAlign=top>
    <logic:present name="dantoc">
<table class="tableForm" cellpadding="0" width="100%" align="center" style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">    
        <TBODY>
        <TR>
          <TD><bean:message key="dantoc.edit.name" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD><html:text  name="dantoc" property="name" styleClass="inputbox" size="40" /> </TD></TR>
        <TR>
          <TD><bean:message key="dantoc.edit.code" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD>
          <html:text  name="dantoc" property="code" styleClass="inputbox" size="40" /> 
          </TD></TR>
        <TR>
          <TD vAlign=top><bean:message key="dantoc.edit.parent" bundle="<%=interfaces%>"/></TD>
          <TD>
              <html:select styleClass="inputbox" name="dantoc" property="parentID">
                <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                <html:options collection="BDantocs" property="id" labelProperty="name"/>
              </html:select>   
          </TD></TR>
</TBODY></TABLE>
    </logic:present>
    </TD></TR>
<TR>
    <TD>
        <logic:present name="dantoc">
              <jsp:include page="/disability/categorys/dantoc/cmd.jsp" />
        </logic:present>
    </TD></TR>
          </TBODY></TABLE>
</div>      
</html:form>           

<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="donvi" method="post">
<html:hidden name="donvi" property="id"/>
<div class="padding-content">
<TABLE align="left">
  <TBODY>
  <TR>
    <TD vAlign=top>
    <logic:present name="donvi">
	<table class="tableForm" cellpadding="0" width="100%" align="center" style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">    
        <TBODY>
        <TR>
          <TD><bean:message key="donvi.edit.name" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD><html:text  name="donvi" property="name" styleClass="inputbox" size="40" /> </TD></TR>
        <TR>
          <TD><bean:message key="donvi.edit.code" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD>
          <html:text  name="donvi" property="code" styleClass="inputbox" size="40" /> 
          </TD></TR>
        <TR>
          <TD vAlign=top><bean:message key="donvi.edit.parent" bundle="<%=interfaces%>"/></TD>
          <TD>
              <html:select styleClass="inputbox" name="donvi" property="parentID">
                <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
    			<logic:present name="BTreeDonvis" >
                	<html:options collection="BTreeDonvis" property="id" labelProperty="name"/>
    			</logic:present>
              </html:select>   
          </TD></TR>
		</TBODY>
	</TABLE>
    </logic:present>
    </TD></TR>
<TR>
    <TD>
        <logic:present name="donvi">
              <jsp:include page="/disability/categorys/donvi/cmd.jsp" />
        </logic:present>
    </TD></TR>
          </TBODY></TABLE>
</div>      
</html:form>           

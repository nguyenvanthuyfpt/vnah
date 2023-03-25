<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="danhgia" method="post">
<html:hidden name="danhgia" property="id"/>
<div class="padding-content">
     <TABLE align="left">
  <TBODY>
  <TR>
    <TD vAlign=top>
    <logic:present name="danhgia">
		<table class="tableForm" cellpadding="0" width="100%" align="center" style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">    
        <TBODY>
        <TR>
          <TD><bean:message key="danhgia.edit.name" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD><html:text  name="danhgia" property="name" styleClass="inputbox" size="60" /> </TD></TR>
        <TR>
          <TD><bean:message key="danhgia.edit.code" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD>
          <html:text  name="danhgia" property="code" styleClass="inputbox" size="60" /> 
          </TD></TR>
        <TR>
          <TD vAlign=top><bean:message key="danhgia.edit.parent" bundle="<%=interfaces%>"/></TD>
          <TD>
              <html:select styleClass="inputbox" name="danhgia" property="parentID" style="width:300px">
                <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                <logic:present name="BTreeDanhgias" >
                	<html:options collection="BTreeDanhgias" property="id" labelProperty="name"/>
    			</logic:present>
              </html:select>   
          </TD></TR>
		</TBODY>
		</TABLE>
    </logic:present>
    </TD></TR>
<TR>
    <TD>
        <logic:present name="danhgia">
              <jsp:include page="/disability/categorys/danhgia/cmd.jsp" />
        </logic:present>
    </TD>
</TR>
</TBODY></TABLE>
</div>      
</html:form>           

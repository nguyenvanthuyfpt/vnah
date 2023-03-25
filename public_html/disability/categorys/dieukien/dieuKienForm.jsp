<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="dieukien" method="post">
<html:hidden name="dieukien" property="id"/>
<div class="padding-content">
<TABLE align="left">
  <TBODY>
  <TR>
    <TD vAlign=top>
    <logic:present name="dieukien">
            <table class="tableForm" cellpadding="0" width="100%" align="center" style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">    
            <TR>
                    <TD><bean:message key="dieukien.edit.name" bundle="<%=interfaces%>"/>
                    <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
                    <TD><html:text  name="dieukien" property="name" styleClass="inputbox" size="40" /> </TD>
            </TR>
            <TR>
                    <TD><bean:message key="dieukien.edit.code" bundle="<%=interfaces%>"/>
                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
                    <TD>
                            <html:text  name="dieukien" property="code" styleClass="inputbox" size="40" /> 
                    </TD>
            </TR>
            <TR>
                    <TD vAlign=top><bean:message key="dieukien.edit.parent" bundle="<%=interfaces%>"/></TD>
                    <TD>
                            <html:select styleClass="inputbox" name="dieukien" property="parentID">
                                    <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                                    <html:options collection="BTreeDieuKiens" property="id" labelProperty="name"/>
                            </html:select>   
                    </TD></TR>
            </TABLE>
    </logic:present>
    </TD></TR>
<TR>
    <TD>
        <logic:present name="dieukien">
              <jsp:include page="/disability/categorys/dieukien/cmd.jsp" />
        </logic:present>
    </TD></TR>
          </TBODY></TABLE>
</div>      
</html:form>           

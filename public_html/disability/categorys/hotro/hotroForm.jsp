<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="hotro" method="post">
<html:hidden name="hotro" property="id"/>
<div class="padding-content">
	<TABLE align="left">
	<TR>
	    <TD vAlign=top>
	    <logic:present name="hotro">
			<table class="tableForm" cellpadding="0" width="100%" align="center" style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">    
			<TBODY>
			<TR>
			  <TD><bean:message key="hotro.edit.name" bundle="<%=interfaces%>"/>
			  <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
			  <TD><html:text  name="hotro" property="name" styleClass="inputbox" size="40" /> </TD></TR>
			<TR>
			  <TD><bean:message key="hotro.edit.code" bundle="<%=interfaces%>"/>
			  <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
			  <TD>
			  <html:text  name="hotro" property="code" styleClass="inputbox" size="40" /> 
			  </TD></TR>
			<TR>
			  <TD vAlign=top><bean:message key="hotro.edit.parent" bundle="<%=interfaces%>"/></TD>
			  <TD>
			      <html:select styleClass="inputbox" name="hotro" property="parentID">
			        <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
			        <logic:present name="BTreeHotros" >
			        	<html:options collection="BTreeHotros" property="id" labelProperty="name"/>
					</logic:present>
			      </html:select>   
			  </TD></TR>
			</TBODY>
			</TABLE>
	    </logic:present>
	    </TD>
	</TR>
		
	<TR>
	    <TD>
	        <logic:present name="hotro">
	              <jsp:include page="/disability/categorys/hotro/cmd.jsp" />
	        </logic:present>
	    </TD>
	</TR>
	</TABLE>
</div>      
</html:form>           

<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="tinh">
	<TABLE id="toolbar" cellSpacing="0" cellPadding="0" border="0" width="100%">
	<TR vAlign=center align=middle>
	    <TD>
			<html:button property="_EDIT" styleClass="button" onclick="post('tinh',anchor + ':_CREATE');"   >                 
			<bean:message key="action.insert" bundle="<%=interfaces%>"/>
			</html:button>   
		</TD>
		<logic:greaterThan name="tinh" property="id" value="0">
		<TD>&nbsp;</TD>
		<TD>
			<html:button property="_EDIT" styleClass="button" onclick="post('tinh',anchor + ':_EDIT');"   >                 
			<bean:message key="action.update" bundle="<%=interfaces%>"/>
			</html:button>        
		</TD>
		<TD>&nbsp;</TD>
		<TD>
			<html:button property="_EDIT" styleClass="button" onclick="post('tinh',anchor + ':_DELETE');"   >                 
			<bean:message key="action.delete" bundle="<%=interfaces%>"/>
			</html:button>        
		</TD>
		</logic:greaterThan>
	</TR>
	</TABLE>          
</logic:present> 

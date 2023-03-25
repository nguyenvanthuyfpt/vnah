<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<logic:present name="danhgia">
<TABLE width="100%">
<TR height="30">
	<TD>
		<html:button property="_EDIT" styleClass="button" onclick="post('danhgia',anchor + ':_CREATE');"   >                 
			<bean:message key="action.insert" bundle="<%=interfaces%>"/>
		</html:button>
		
		<logic:greaterThan name="danhgia" property="id" value="0">
			<html:button property="_EDIT" styleClass="button" onclick="post('danhgia',anchor + ':_EDIT');"   >                 
				<bean:message key="action.update" bundle="<%=interfaces%>"/>
			</html:button>        

			<html:button property="_EDIT" styleClass="button" onclick="post('danhgia',anchor + ':_DELETE');"   >                 
				<bean:message key="action.delete" bundle="<%=interfaces%>"/>
			</html:button>
		</logic:greaterThan>     
	</TD>
</TR>
</TABLE>  
</logic:present>
 

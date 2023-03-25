<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="tinh" method="post">
<html:hidden name="tinh" property="id"/>

<div class="padding-content">
	<TABLE align="left">
	<tr>
		<td valign=top>
		<logic:present name="tinh">
			<table class="tableForm" cellpadding="0" width="100%" align="center" style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">    
			<TBODY>
			<TR>
				<TD><bean:message key="tinh.edit.name" bundle="<%=interfaces%>"/>
					<bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
				<TD><html:text  name="tinh" property="name" styleClass="inputbox" size="40" /> </TD>
			</TR>
		
			<TR>
				<TD><bean:message key="tinh.edit.code" bundle="<%=interfaces%>"/>
					<bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
				<TD>
					<html:text  name="tinh" property="code" styleClass="inputbox" size="40" /> 
				</TD>
			</TR>

			<TR>
				<TD valign=top><bean:message key="tinh.edit.parent" bundle="<%=interfaces%>"/></TD>
				<TD>
					<html:select styleClass="inputbox" name="tinh" property="parentID" onchange="post('tinh',anchor + ':_SHOW')">
						<html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
						<html:options collection="BTreeAllTinhs" property="id" labelProperty="name"/>
					</html:select>   
				</TD>
			</TR>
			</TBODY>
			</TABLE>
		</logic:present>
		</TD>
	</TR>

	<TR>
		<TD>
			<logic:present name="tinh">
				  <jsp:include page="/disability/categorys/tinh/cmd.jsp" />
			</logic:present>
		</TD>
	</TR>
	</TBODY>
	</TABLE>
</div>	
			
			
	<!--<div style="height:20px">&nbsp;</div>
		
	<div align="center" id="listId">
		<jsp:include page="/disability/categorys/tinh/tinhList.jsp"/>
 	</div>-->
 

</html:form>       

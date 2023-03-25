<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%
    String bean = "BUser";
    String disabled = me.isRole(com.inf.IRoles.RADMINISTRATOR)?"":"disabled";
%>  
<%!
    String getChecked(int pr, int key_id){
        return (pr>0 && (key_id==(pr & key_id)))?"checked":"";
    }
%>
<logic:present name="BGroup">
    <% bean = "BGroup";%>
</logic:present>
	
<logic:present name="<%=bean%>">
<bean:define name="<%=bean%>" property="role" id="rl" type="java.lang.Integer" />
<% int role=rl.intValue(); %>
<TABLE width="100%" >
<TR>
    <TD nowrap colspan="2">
        <input type="checkbox" name="roles" value="<%=com.inf.IRoles.RADMINISTRATOR%>" <%=disabled%> <%=getChecked(role,com.inf.IRoles.RADMINISTRATOR)%>/>
        <bean:message key="role.administrator" bundle="<%=interfaces%>"/>        

        
        <input type="checkbox" name="roles" value="<%=com.inf.IRoles.rBROADCAST%>" <%=disabled%> <%=getChecked(role,com.inf.IRoles.rBROADCAST)%>/>
        <bean:message key="role.broadcast" bundle="<%=interfaces%>"/>
        
    </TD>
</TR>

<TR>
    <TD nowrap colspan="2">
        <HR>
    </TD>
</TR>
	
<TR>
	<TD nowrap colspan="2">
		<input type="checkbox" name="roles" value="<%=com.inf.IRoles.rCABIN_PUBLIC%>"  <%=getChecked(role,com.inf.IRoles.rCABIN_PUBLIC)%>/>
		<bean:message key="role.cabin.public" bundle="<%=interfaces%>"/>

		<input type="checkbox" name="roles" value="<%=com.inf.IRoles.rCABIN_ONE%>"  <%=getChecked(role,com.inf.IRoles.rCABIN_ONE)%>/>
		<bean:message key="role.cabin.private" bundle="<%=interfaces%>"/>

		<input type="checkbox" name="roles" value="<%=com.inf.IRoles.rCABIN_MUTI%>"  <%=getChecked(role,com.inf.IRoles.rCABIN_MUTI)%>/>
		<bean:message key="role.cabin.privatemuti" bundle="<%=interfaces%>"/>
	</TD>
</TR>
			
<TR>
    <TD nowrap colspan="2">
        <HR>
    </TD>
</TR>
	
<TR>
	<TD nowrap colspan="2">
        <input type="checkbox" name="roles" value="<%=com.inf.IRoles.rDIS_CATEGORY%>"  <%=getChecked(role,com.inf.IRoles.rDIS_CATEGORY)%>/>
        <bean:message key="role.disability.category" bundle="<%=interfaces%>"/>

        <input type="checkbox" name="roles" value="<%=com.inf.IRoles.rDIS_MANAGE_UNIT%>"  <%=getChecked(role,com.inf.IRoles.rDIS_MANAGE_UNIT)%>/>        
        <bean:message key="role.disability.unit" bundle="<%=interfaces%>"/>        
	</TD>
</TR>
	
<logic:notEmpty name="contentTreeTinhs">

<TR>
    <TD nowrap colspan="2">
        <HR>
    </TD>
</TR>
	
<TR>
	<TD nowrap colspan="2">
		<jsp:include page="/admin/role/roleDis.jsp" />
	</TD>
</TR>
</logic:notEmpty>
	
		
</TABLE>
</logic:present>
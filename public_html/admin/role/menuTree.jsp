<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%
    String disabled = me.isRole(com.inf.IRoles.RADMINISTRATOR)?"":"disabled";
%>  
<%int j=0;int i=0;%>
<logic:present name="BMenus">
<table width="100%">
<logic:iterate name="BMenus" id="bean" type="com.form.admin.menu.FMenu" >    
	<%i++;%>
	<logic:equal name="bean" property="level" value="0">       
		<tr>
			<td colspan="2" align="center">
				<input type="checkbox"  name="menus" value="<%=bean.getMenuId()%>" <%=(bean.getChecked()==1?"checked":"")%> <%=disabled%> />
				<Strong><bean:write name="bean" property="title" /></Strong>
			</td>
		</tr>
		<%j=0;%>
	</logic:equal>

	<logic:notEqual name="bean" property="level" value="0">
		<%if(j%2==0){%>
		<tr>
		<%}%>
			<td>
			<input type="checkbox"  name="menus" value="<%=bean.getMenuId()%>" <%=(bean.getChecked()==1?"checked":"")%> <%=disabled%> />
				<bean:write name="bean" property="title" />
			</td>
		<%if(j%2>0){%> 
		</tr> 
		<%};j++;%>
	</logic:notEqual>
</logic:iterate>
</table>
</logic:present>
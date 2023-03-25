<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
	
<html:form action="dangtat" method="post">
<html:hidden name="dangtat" property="id"/>


<div class="padding-content">
<TABLE align="left">
  <TBODY>
  <TR>
    <TD vAlign=top>
    <logic:present name="dangtat">
    
    <bean:define name="dangtat" property="isOther" id="bean" type="java.lang.Integer" />
    <bean:define name="dangtat" property="id" id="dangtatId" type="java.lang.Integer" />	
    <% 	
    	int isOther = bean.intValue();
    	int id = dangtatId.intValue();
   	%>
    
    <table class="tableForm" cellpadding="0" width="100%" align="center" style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">    
	<TR>
		<TD><bean:message key="dangtat.edit.name" bundle="<%=interfaces%>"/>
			<bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
		<TD><html:text name="dangtat" property="name" styleClass="inputbox" size="40" /> </TD>
	</TR>
	
	<TR>
		<TD><bean:message key="dangtat.edit.code" bundle="<%=interfaces%>"/>
			<bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
		<TD><html:text name="dangtat" property="code" styleClass="inputbox" size="40" /></TD>
	</TR>
		
	<!--<TR>
		<TD><bean:message key="dangtat.edit.code" bundle="<%=interfaces%>"/></TD>
		<TD>
			<% String checked = (isOther==1)? "checked":"";
			   int value = (id==0) ? 0 : isOther; %>
			<html:checkbox name="dangtat" property="isOther" />
		</TD>
	</TR>-->
		
	<TR>
		<TD><bean:message key="dangtat.edit.parent" bundle="<%=interfaces%>"/></TD>
		<TD>
			<html:select styleClass="inputbox" name="dangtat" property="parentID">
			<html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
				<logic:present name="BTreeDangTats" >
					<html:options collection="BTreeDangTats" property="id" labelProperty="name"/>
				</logic:present>
			</html:select>
		</TD>
    </TR>
	</TABLE>
    </logic:present>
    </TD>
</TR>
		
<TR>
    <TD>
        <logic:present name="dangtat">
	          <jsp:include page="/disability/categorys/dangtat/cmd.jsp" />
        </logic:present>
    </TD>
</TR>
</TBODY>
</TABLE>
</div>       
</html:form>           

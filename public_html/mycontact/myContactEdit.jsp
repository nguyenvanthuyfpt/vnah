<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script type="text/javascript">
    function checkSubmit(form){
        if(form.fullName.value==''){
            alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
            return false;
        }
        return true;
    }
</script>
<html:form action="pgroup" method="post"/>
<html:form action="formMyContact" method="post">
<html:hidden  name="formMyContact" property="id" /> 
<body onLoad="if(parent.SqueezeBox.presets.target==0){parent.SqueezeBox.close()};">
<TABLE class=adminheading>
  <TBODY>
  <TR>
    <TH class=user>
    <logic:equal name="formMyContact" property="id" value="0">
        <bean:message key="mycontact.myContactadd.caption" bundle="<%=interfaces%>"/>
    </logic:equal>
    <logic:notEqual name="formMyContact" property="id" value="0">
        <bean:message key="mycontact.myContactEdit.caption" bundle="<%=interfaces%>"/>
    </logic:notEqual>
  
    </TH></TR></TBODY></TABLE>
      <TABLE width="98%" align="center">
        <TBODY>        
        <TR>
          <TD nowrap><bean:message key="mycontact.edit.fullname" bundle="<%=interfaces%>"/>
                <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD ><html:text  name="formMyContact" property="fullName" styleClass="inputbox" size="40" /> </TD>
        </TR>        
        <TR>
          <TD nowrap><bean:message key="mycontact.edit.telephone" bundle="<%=interfaces%>"/></TD>
          <TD ><html:text  name="formMyContact" property="phone" styleClass="inputbox" size="40" /></TD></TR>
        <TR>
          <TD nowrap><bean:message key="mycontact.edit.address" bundle="<%=interfaces%>"/></TD>
          <TD ><html:text  name="formMyContact" property="address" styleClass="inputbox" size="40" /></TD></TR>
          <tr>
                <td><bean:message key="mycontact.edit.pgroup" bundle="<%=interfaces%>"/></td>
                <td > 
                <span id="compoPgroup">
                   <jsp:include page="/mycontact/pgroup.jsp" />
               </span>                
               
              </td>
        </tr>
        
       <TR>
          <TD nowrap><bean:message key="mycontact.edit.icq" bundle="<%=interfaces%>"/></TD>
          <TD ><html:text  name="formMyContact" property="icq" styleClass="inputbox" size="40" /></TD>
      </TR>
    <TR>
      <TD nowrap><bean:message key="mycontact.edit.YM" bundle="<%=interfaces%>"/></TD>
      <TD ><html:text  name="formMyContact" property="ym" styleClass="inputbox" size="40" /></TD>
    </TR>
       <TR>
          <TD nowrap><bean:message key="mycontact.edit.MSN" bundle="<%=interfaces%>"/></TD>
          <TD ><html:text  name="formMyContact" property="msn" styleClass="inputbox" size="40" /></TD></TR>
          
       <TR>
          <TD nowrap><bean:message key="mycontact.edit.Gtalk" bundle="<%=interfaces%>"/></TD>
          <TD ><html:text  name="formMyContact" property="gtalk" styleClass="inputbox" size="40" /></TD></TR>
       <TR>
          <TD nowrap><bean:message key="mycontact.edit.Skype" bundle="<%=interfaces%>"/></TD>
          <TD ><html:text  name="formMyContact" property="skype" styleClass="inputbox" size="40" /></TD></TR>
          <TR>
          <TD nowrap><bean:message key="mycontact.edit.active" bundle="<%=interfaces%>"/></TD>
          <TD >
          <html:select name="formMyContact" property="active">
          <html:option value="1"><bean:message key="users.edit.active.enable" bundle="<%=interfaces%>"/></html:option>
          <html:option value="0"><bean:message key="users.edit.active.disable" bundle="<%=interfaces%>"/></html:option>
          </html:select>
            </TD></TR>
            
             <TR>
          <TD nowrap><bean:message key="mycontact.edit.description" bundle="<%=interfaces%>"/></TD>
          <TD ><html:text  name="formMyContact" property="description" styleClass="inputbox" size="40" /></TD></TR>
        <tr>
        <td colspan="3" align="center">
            <jsp:include page="/mycontact/cmd.jsp" />
        </td>
        </tr>  
          </TBODY>
          </TABLE>
  
 </body>         

</html:form>          

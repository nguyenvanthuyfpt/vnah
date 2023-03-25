<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="openMycontact" method="post" >
<html:hidden  name="openMycontact" property="id" />                           
        <TABLE width="100%" align="center" class="openMycontact">
        <TBODY>        
        <TR>
          <TD nowrap align="right"><bean:message key="mycontact.edit.fullname" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD colspan="3"> <html:text  name="openMycontact" property="fullName" styleClass="inputbox" size="40" /></TD>
        </TR>        
        <tr>
                <td align="right"><bean:message key="mycontact.edit.pgroup" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/>                       </td>
                <td >
                      <html:select styleClass="inputbox" style="width:80px" name="openMycontact" property="pgroupId">                                     
                        <logic:present name="BPgroups">
                          <html:options collection="BPgroups" property="id" labelProperty="name"/>
                        </logic:present>
                        </html:select>
                </td> 
                <td align="right" nowrap>         
                        <bean:message key="mycontact.edit.active" bundle="<%=interfaces%>"/>
                </td>
                <td>
                        <html:select style="width:80px" name="openMycontact" property="active">
                                <html:option value="1"><bean:message key="users.edit.active.enable" bundle="<%=interfaces%>"/></html:option>
                                <html:option value="0"><bean:message key="users.edit.active.disable" bundle="<%=interfaces%>"/></html:option>
                        </html:select>
                </td>
        </tr>
        <TR>
          <TD nowrap align="right"><bean:message key="mycontact.edit.address" bundle="<%=interfaces%>"/></TD>
          <TD colspan="3"><html:text  name="openMycontact" property="address" styleClass="inputbox" size="40" /></TD>
        </TR>
           <TR>
                  <td align="right"><bean:message key="mycontact.edit.icq" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                  <td colspan="3"><html:text  name="openMycontact" property="icq" styleClass="inputbox" size="40" /></TD>
          </TR>
          
           <TR>
                  <TD nowrap align="right"><bean:message key="mycontact.edit.telephone" bundle="<%=interfaces%>"/></TD>
                  <TD colspan="3"><html:text  name="openMycontact" property="phone" styleClass="inputbox" size="40" /></td>
          </TR>
       
    <TR>
          <TD nowrap align="right"><bean:message key="mycontact.edit.YM" bundle="<%=interfaces%>"/></TD>
          <TD ><html:text  name="openMycontact" property="ym" styleClass="inputbox" size="10" /></td>
          <td align="right"><bean:message key="mycontact.edit.MSN" bundle="<%=interfaces%>"/></td>
          <td><html:text  name="openMycontact" property="msn" styleClass="inputbox" size="10" /></TD>
    </TR>
      
          
       <TR>
          <TD nowrap align="right"><bean:message key="mycontact.edit.Gtalk" bundle="<%=interfaces%>"/></TD>
          <TD >
                <html:text  name="openMycontact" property="gtalk" styleClass="inputbox" size="10" />
          </td>
                  <td align="right">      
                <bean:message key="mycontact.edit.Skype" bundle="<%=interfaces%>"/>
            </td>
                  <td>
                <html:text  name="openMycontact" property="skype" styleClass="inputbox" size="10" />
          </TD>
    </TR>
         
             <TR>
          <TD nowrap align="right"><bean:message key="mycontact.edit.description" bundle="<%=interfaces%>"/></TD>
          <TD  colspan="3"><html:text  name="openMycontact" property="description" styleClass="inputbox" size="40" /></TD></TR>
        <tr><td colspan="4" class="textalignR">  
                <logic:equal name="openMycontact" property="id" value="0" >
                    <html:button property="_PREPARED_CREATE"  onclick="checkInsertContact(this.form);">
                            <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                    </html:button>
                </logic:equal>                
                <logic:notEqual name="openMycontact" property="id" value="0" >                
                 <html:button property="_PREPARED_CREATE"  onclick="checkUpdateContact(this.form)">
                        <bean:message key="action.update" bundle="<%=interfaces%>"/>
                </html:button>
                </logic:notEqual>    
       
        </td></tr>  
          </TBODY></TABLE>       
</html:form>
       
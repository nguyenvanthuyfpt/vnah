<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<table class="openMycontact" align="center">
 <tr><td colspan="2"><html:errors property="alert"  bundle="<%=interfaces%>" /></td></tr>
  <TR>
        <TD vAlign=top width="58px"><bean:message key="pgroup.group.name.catiom" bundle="<%=interfaces%>"/></TD>
        <TD vAlign=top>
                 <html:select styleClass="inputbox"  name="pgroup" property="id" onchange="javascript:postAjax('pgroup','divFromPgroup',anchor + ':_SELECT')">                                     
                     <html:option value="0"><bean:message key="combo.lammoi" bundle="<%=interfaces%>"/></html:option>
                    <logic:present name="BPgroups">
                      <html:options collection="BPgroups" property="id" labelProperty="name"/>
                    </logic:present>
                    </html:select>     
        </TD>
      </TR>
  <TR>
    <TD vAlign=top><bean:message key="pgroup.edit.name" bundle="<%=interfaces%>"/></TD>
    <TD vAlign=top><html:text  name="pgroup" property="name" styleClass="inputbox" size="40" maxlength="20" /></TD>
  </TR>
  <TR>
    <TD vAlign=top><bean:message key="pgroup.edit.description" bundle="<%=interfaces%>"/></TD>
    <TD vAlign=top ><html:text  name="pgroup" property="description" styleClass="inputbox" size="40" maxlength="500" /></TD>
  </TR>
  <TR>
    <TD vAlign=top colspan="2" align="left" class="textalignR">       
    <logic:equal name="pgroup" property="id" value="0" >
              <html:button property="_PREPARED_CREATE"  onclick="postAjax('pgroup','tdMycontact',anchor + ':_CREATE');addthis_close()">
                    <bean:message key="action.insert" bundle="<%=interfaces%>"/>
            </html:button>
    </logic:equal>
        <logic:notEqual name="pgroup" property="id" value="0" >
            <html:button property="_PREPARED_CREATE"  onclick="postAjax('pgroup','tdMycontact',anchor + ':_EDIT');addthis_close()">
                    <bean:message key="action.update" bundle="<%=interfaces%>"/>
            </html:button>    
        </logic:notEqual>
    </TD>        
  </TR> 
  </table>
  
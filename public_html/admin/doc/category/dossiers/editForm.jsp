<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<Script type="text/javascript">
function checkSubmit(form){
    if(form.name.value=='' || form.code.value==''){
        alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
        return false;
    }
    return true;
}
</Script>

<html:form action="dossiers" method="post" target="_top">

<table cellpadding="0" cellspacing="0" border="0" width="99%" align="left" >
        <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_19.gif" width="8" height="43" /></td>
            <td class="sharebackground" width="100%">
            <Strong><bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/></strong>
            </td>
            <td width="10"><img src="<%=contextPath%>/images/newImages/i_20.gif" width="10" height="43" /></td>
        </tr>
        <tr>
            <td colspan="4" align="center">
<table   style="border-collapse: collapse;line-height:25px;" cellpadding="2" cellspacing="2"  >
            <tr>
                <td  nowrap><bean:message key="form.dossiers.code" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                <td align="left"><html:text name="dossiers" property="code" style="width:220px;" maxlength="100"/></td>
            </tr>            
           <tr>
        <td  nowrap><bean:message key="form.dossiers.name" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
        <td  align="left"><html:text name="dossiers" property="name" style="width:220px;" /></td>
    </tr>
    <tr>
        <td  nowrap><bean:message key="form.dossiers.description" bundle="<%=interfaces%>"/></td>
        <td  align="left"><html:textarea  name="dossiers" property="description"  style="width:220px;height:50px;"  /></td>
    </tr>
        <logic:equal parameter="<%=anchor%>" value="_PREPARED_EDIT">
        <tr>
            <td  nowrap><bean:message key="form.dossiers.status" bundle="<%=interfaces%>"/></td>
            <td  align="left">
             <html:select name="dossiers" property="permission" styleClass="fieldSelect"  style="width:150px;" >
                        <html:option value="0"><bean:message key="form.dossiers.status.0" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="1"><bean:message key="form.dossiers.status.1" bundle="<%=interfaces%>"/></html:option>
                  </html:select>
             </td>
        </tr>
        </logic:equal>
    <tr>
    <td  nowrap colspan="2" align="center" class="toolsDoc">
     
                  <logic:equal parameter="<%=anchor%>" value="_PREPARED_CREATE">
                  <% String funcAdd="javascript:if(checkSubmit(this.form)){post('dossiers',anchor + ':_CREATE');}";%>
                    <html:button property="_CREATE" onclick="<%=funcAdd%>" styleClass="button">
                      <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                    </html:button>
                  </logic:equal>
                  <logic:equal parameter="<%=anchor%>" value="_CREATE">
                    <% String funcAdd="javascript:if(checkSubmit(this.form)){post('dossiers',anchor + ':_CREATE');}";%>
                    <html:button property="_CREATE" onclick="<%=funcAdd%>" styleClass="button">
                      <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                    </html:button>                    
                  </logic:equal>
                  <logic:equal parameter="<%=anchor%>" value="_PREPARED_EDIT">
                    <% String funcEdit="javascript:if(checkSubmit(this.form)){post('dossiers',anchor + ':_EDIT');}";%>
                    <html:button property="_EDIT" onclick="<%=funcEdit%>" styleClass="button">
                      <bean:message key="action.update" bundle="<%=interfaces%>"/>
                    </html:button>                    
                  </logic:equal>            
                  <logic:equal parameter="<%=anchor%>" value="_EDIT">
                    <% String funcEdit="javascript:if(checkSubmit(this.form)){post('dossiers',anchor + ':_EDIT');}";%>
                    <html:button property="_EDIT" onclick="<%=funcEdit%>" styleClass="button">
                      <bean:message key="action.update" bundle="<%=interfaces%>"/>
                    </html:button>                    
                  </logic:equal>                              
    </td>
    </tr>
          </table>
   
  </td>
  </tr>
  </table>
<html:hidden name="dossiers" property="creator" />
<html:hidden name="dossiers" property="timeOpen" />

</html:form>
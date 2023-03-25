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
<html:form action="dossiers" method="post">
<html:hidden name="dossiers"  property="id" />
<table cellpadding="0" cellspacing="0" border="0" width="99%" align="left" >
        <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_19.gif" width="8" height="43" /></td>
            <td class="sharebackground" width="100%">
            <Strong><bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/></strong>
            </td>
            <td width="10"><img src="<%=contextPath%>/images/newImages/i_20.gif" width="10" height="43" /></td>
        </tr>
        <tr>
            <td colspan="4" align="center">
            
    <table  style="border-collapse: collapse;line-height:22px;" cellpadding="0" align="center" >
    <tr>
            <td class="captionEdit" nowrap>
            <bean:message key="form.dossiers.code" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
            <td align="left"><html:text name="dossiers" property="code" style="width:250px;" size="250"/></td>
    </tr>            
    <tr>
            <td class="captionEdit" nowrap><bean:message key="form.dossiers.name" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
            <td  align="left"><html:text name="dossiers" property="name" style="width:250px;" size="250"/></td>
    </tr>
    <tr>
            <td class="captionEdit" nowrap><bean:message key="form.dossiers.description" bundle="<%=interfaces%>"/></td>
            <td  align="left"><html:textarea  name="dossiers" property="description"  style="width:250px;height:100px;" /></td>
    </tr>
    <logic:equal parameter="<%=anchor%>" value="_PREPARED_EDIT">
    <tr>
            <td class="captionEdit" nowrap><bean:message key="form.dossiers.status" bundle="<%=interfaces%>"/></td>
            <td  align="left">
            <html:select name="dossiers" property="permission" styleClass="fieldSelect"  style="width:250px;" >
                    <html:option value="0"><bean:message key="form.dossiers.status.0" bundle="<%=interfaces%>"/></html:option>
                    <html:option value="1"><bean:message key="form.dossiers.status.1" bundle="<%=interfaces%>"/></html:option>
              </html:select>
            </td>
    </tr>
    </logic:equal>
    <tr>
    <td  nowrap colspan="2" align="center">
                    <logic:equal parameter="Anchor" value="_CREATE_DOSS_RECV"> 
                        <% String funcAdd="javascript:if(checkSubmit(this.form)){post('dossiers',anchor + ':_CREATE_ADD_RECV');}";%>
                        <html:button property="_CREATE_ADD_RECV" onclick="<%=funcAdd%>" styleClass="button">
                          <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                        </html:button>                    
                    </logic:equal>
                    <logic:equal parameter="Anchor" value="_CREATE_ADD_RECV"> 
                    <% String funcAdd="javascript:if(checkSubmit(this.form)){post('dossiers',anchor + ':_CREATE_ADD_RECV');}";%>
                        <html:button property="_CREATE_ADD_RECV" onclick="<%=funcAdd%>" styleClass="button">
                          <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                        </html:button>                    
                    </logic:equal>
                    
                    
                    <logic:equal parameter="Anchor" value="_CREATE_DOSS_SEND"> 
                        <% String funcAdd="javascript:if(checkSubmit(this.form)){post('dossiers',anchor + ':_CREATE_ADD_SEND');}";%>
                        <html:button property="_CREATE_ADD_SEND" onclick="<%=funcAdd%>" styleClass="button">
                          <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                        </html:button>                    
                    </logic:equal>
                    
                    <logic:equal parameter="Anchor" value="_CREATE_ADD_SEND"> 
                    <% String funcAdd="javascript:if(checkSubmit(this.form)){post('dossiers',anchor + ':_CREATE_ADD_SEND');}";%>
                        <html:button property="_CREATE_ADD_SEND" onclick="<%=funcAdd%>" styleClass="button">
                          <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                        </html:button>                    
                    </logic:equal>
    </td>
    </tr>
    </table>


</td>
</tr>
</table>
</html:form>
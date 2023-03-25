<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="from" method="post" >
 <script type="text/javascript">
function checkSubmit(form){
    if(form.code.value==''  ||  form.vnName.value==''  ||  form.enName.value==''){
        alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
        return false;
    }
    return true;
}

</script>    
<html:hidden name="from"  property="id" />
    <TABLE cellSpacing=0 cellPadding=0 width="100%" border="0">
            <tr>
                        <td height="24px" style="padding-left:10px"><bean:message key="lable.from.code" bundle="<%=interfaces%>"/> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                        <td><html:text name="from" property="code" style="width:200px" maxlength="50"/></td>
            </tr>
            <tr>
                        <td height="24px" style="padding-left:10px"><bean:message key="lable.from.vnName" bundle="<%=interfaces%>"/> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                        <td><html:text name="from" property="vnName" style="width:200px" maxlength="100"/></td>
            </tr>
            <tr>
                        <td height="24px" style="padding-left:10px"><bean:message key="lable.from.enName" bundle="<%=interfaces%>"/> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                        <td><html:text name="from" property="enName" style="width:200px" maxlength="100"/></td>
            </tr>
            <tr>
                        <td height="24px" style="padding-left:10px"><bean:message key="lable.from.description" bundle="<%=interfaces%>"/></td>
                        <td><html:text name="from" property="description" style="width:200px" maxlength="100"/></td>
            </tr>
            <tr>
            <td><br></td>
                <td height="24px">
                    <logic:equal name="from" property="id" value="0">
                    <html:button property="_CREATE"  onclick="javascript:if(checkSubmit(this.form)){post('from',anchor+':_CREATE');}" styleClass="button">                       
                    <bean:message key="privilege.insert" bundle="<%=interfaces%>"/>
                    </html:button>
                    </logic:equal>  
                    <logic:notEqual name="from" property="id" value="0">
                    <html:button property="_EDIT"  onclick="javascript:if(checkSubmit(this.form)){post('from',anchor + ':_EDIT');}"  styleClass="button">                       
                    <bean:message key="action.edit" bundle="<%=interfaces%>"/>
                    </html:button>
                    </logic:notEqual>  
                </td>
            </tr>
             <tr>
                <td colspan="2" align="center"><jsp:include page="/admin/alert.jsp" /></td>
             </tr>
    </table>
<logic:equal parameter="Anchor" value="_PUT">
<script type="text/javascript">         
        postAjax('dossiers',window.parent.document.getElementById('idDossiers'), anchor + ':_SAVE_NEW');
</script>
</logic:equal> 
 </html:form>        
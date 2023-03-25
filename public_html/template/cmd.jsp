<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script type="text/javascript">
function checkSubmit(form){
    if(form.name.value=='' || (form.templateType_id.value>0==false)){
        alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
        return false;
    }
    return true;
}
</script>
                    <logic:notEmpty name="BTemplateTypes">
                    <logic:equal name="template" property="id" value="0">
                            <% String funcAdd="javascript:if(checkSubmit(this.form)){post('templateEdit',anchor + ':_CREATE');}";%>
                            <html:button property="_CREATE" onclick="<%=funcAdd%>" styleClass="button">
                              <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                            </html:button>
                    </logic:equal>
                    </logic:notEmpty>
                    <logic:notEqual name="template" property="id" value="0">
                    <% String funcEdit="javascript:if(checkSubmit(this.form)){post('templateEdit',anchor + ':_EDIT');}";%>
                    <html:button property="_EDIT" onclick="<%=funcEdit%>" styleClass="button">
                      <bean:message key="action.update" bundle="<%=interfaces%>"/>
                    </html:button>
                    </logic:notEqual>
                    
                    <% String funcEnd="javascript:post('template',anchor + ':_SHOW')";%>
                    <html:button property="end" styleClass="button" onclick="<%=funcEnd%>">
                      <bean:message key="action.close" bundle="<%=interfaces%>"/>
                    </html:button>
                 
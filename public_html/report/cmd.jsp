<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
 <script type="text/javascript">
function checkSubmit(form){
    if(form.name.value==''){
        alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
        return false;
    }
    return true;
}
</script>                   
                    <logic:equal name="reports" property="id" value="0">
                        <% String funcAdd="javascript:if(checkSubmit(this.form)){post('reports',anchor + ':_CREATE');}";%>
                        <html:button property="addNew" onclick="<%=funcAdd%>" styleClass="button">
                          <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                        </html:button>
                    </logic:equal>
                    
                    <logic:notEqual name="reports" property="id" value="0">
                        <% String funcEdit="javascript:if(checkSubmit(this.form)){post('reports',anchor + ':_EDIT');}";%>
                        <html:button property="edit" onclick="<%=funcEdit%>" styleClass="button">
                          <bean:message key="action.update" bundle="<%=interfaces%>"/>
                        </html:button>
                    </logic:notEqual>
                    
                    <% String funcEnd="javascript:post('reports',anchor + ':_SEARCH')";%>
                    <html:button property="end" styleClass="button" onclick="<%=funcEnd%>">
                      <bean:message key="action.close" bundle="<%=interfaces%>"/>
                    </html:button>
                 

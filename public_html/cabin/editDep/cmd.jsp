<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
    function checkCabinCreate(obj){
            post('cabin',anchor + ':_CREATE');
    }
    function checkCabinEdit(obj){
            post('cabin',anchor + ':_EDIT');
    }
</script>
                <logic:equal name="cabin"  property="id"  value="0">
                  <% String funcAdd="javascript:this.disabled=true;checkCabinCreate(this.form)";%>
                    <html:button property="addNew" onclick="<%=funcAdd%>" styleClass="button">
                      <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                    </html:button>
                </logic:equal>
                
                <logic:notEqual name="cabin"  property="id"  value="0">
                    <% String funcEdit="javascript:this.disabled=true;checkCabinEdit(this.form)";%>
                    <html:button property="edit" onclick="<%=funcEdit%>" styleClass="button">
                      <bean:message key="action.update" bundle="<%=interfaces%>"/>
                    </html:button>
                </logic:notEqual>
 
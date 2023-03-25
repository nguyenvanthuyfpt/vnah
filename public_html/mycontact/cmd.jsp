<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <logic:equal name="formMyContact" property="id" value="0" >
                <html:button property="_PREPARED_CREATE" styleClass="button" onclick="if(checkSubmit(this.form)){ parent.SqueezeBox.presets.target=0;post('formMyContact',anchor + ':_CREATE:id:0'); }">
                        <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                </html:button>
                </logic:equal>
      <logic:notEqual name="formMyContact" property="id" value="0" >
             <html:button property="_PREPARED_CREATE" styleClass="button" onclick="if(checkSubmit(this.form)){ parent.SqueezeBox.presets.target=0;post('formMyContact',anchor + ':_EDIT') }">
                    <bean:message key="action.update" bundle="<%=interfaces%>"/>
            </html:button>
        </logic:notEqual>     
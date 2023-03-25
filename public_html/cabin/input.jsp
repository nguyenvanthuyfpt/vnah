<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="cabinInput" method="post">
<html:hidden name="cabinInput" property="id" />
<html:hidden name="cabinInput" property="type" />
<html:hidden name="cabinInput" property="cabinType_id" />
<html:hidden name="cabinInput" property="typeFile" />
<html:hidden name="cabinInput" property="userId" />
<div align="left"><bean:message key="title.cabin.label.cabinType" bundle="<%=interfaces%>"/>:
<html:select name="cabinInput" property="cabinTypeInput_id" styleId="cabinTypeInput_id" styleClass="fieldSelect"  >
            <logic:present name="BCabinTypes">
            <logic:equal name="cabin" property="type" value="3">
                <html:option value="0"><bean:message key="header.cabin.label.deps" bundle="<%=interfaces%>"/></html:option>
            </logic:equal>        
            <logic:equal name="cabin" property="type" value="2">
                <html:option value="0"><bean:message key="header.cabin.label.private" bundle="<%=interfaces%>"/></html:option>
            </logic:equal>
            <logic:equal name="cabin" property="type" value="1">
                <html:option value="0"><bean:message key="header.cabin.label.private" bundle="<%=interfaces%>"/></html:option>
            </logic:equal>
            <logic:equal name="cabin" property="type" value="0">
                <html:option value="0"><bean:message key="header.cabin.label.public" bundle="<%=interfaces%>"/></html:option>
            </logic:equal>

                <html:options collection="BCabinTypes" property="id" labelProperty="name"/>
            </logic:present>
          </html:select>
</div>
<br/>
<div align="center">
            <logic:equal name="cabin" property="typeFile" value="0">
                <html:button property="_CREATE" onclick="inputInToCategery();addthis_close();" styleClass="button">
                            <bean:message key="cmd.cabin.move.ok" bundle="<%=interfaces%>"/>
                </html:button>
            </logic:equal>  
            <logic:equal name="cabin" property="typeFile" value="2">
                <html:button property="_CREATE" onclick="inputInToCategery();addthis_close();" styleClass="button">
                            <bean:message key="cmd.cabin.move.ok" bundle="<%=interfaces%>"/>
                </html:button>
            </logic:equal>  
            <logic:equal name="cabin" property="typeFile" value="1">
                <html:button property="_CREATE" onclick="post('cabinInput',anchor + ':_INPUT_OK');addthis_close();" styleClass="button">
                            <bean:message key="cmd.cabin.move.ok" bundle="<%=interfaces%>"/>
                </html:button>
            </logic:equal>  
            <logic:equal name="cabin" property="typeFile" value="3">
                <html:button property="_CREATE" onclick="post('cabinInput',anchor + ':_INPUT_OK');addthis_close();" styleClass="button">
                            <bean:message key="cmd.cabin.move.ok" bundle="<%=interfaces%>"/>
                </html:button>
            </logic:equal>  
</div>
</html:form>

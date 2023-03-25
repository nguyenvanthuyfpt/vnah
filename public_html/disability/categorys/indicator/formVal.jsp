<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<script type="text/javascript">

</script>
<html:form action="indicator" method="post">
    <html:hidden name="indicator" property="id"/>
    <html:hidden name="indicator" property="dtlId"/>
    <div class="padding-content">
        <table align="left" width="100%">
            <tbody>
                <tr>
                    <td valign="top">
                        <logic:present name="indicator">
                            <table class="tableForm" cellpadding="0" width="100%" align="center"
                                   style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">
                                <tbody>
                                    
                                    <tr>
                                        <td width="25%">
                                            <bean:message key="location" bundle="<%=interfaces%>" />                                             
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td colspan="3">
                                            <html:select styleClass="combobox_w150" name="indicator" property="locationId" onchange="javascript:postAjax('indicator','listId',anchor + ':_CHANGE_OPTION');">
                                                <logic:present name="BTreeTinhs">
                                                    <html:options collection="BTreeTinhs" property="id" labelProperty="name"/>
                                                </logic:present>
                                            </html:select>
                                        </td>
                                    </tr>
                                  
                                    <tr>
                                        <td width="25%">
                                            <bean:message key="indicator.edit.code" bundle="<%=interfaces%>"/> 
                                        </td>
                                        <td width="25%">
                                            <bean:write name="indicator" property="code" />
                                        </td>
                                        <td width="25%">
                                            <bean:message key="common.label.createdate" bundle="<%=interfaces%>"/>                                             
                                        </td>
                                        <td width="25%">
                                            <html:text name="indicator" property="createDate" readonly="true" size="10" styleClass="center"/>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td>
                                            <bean:message key="indicator.edit.name" bundle="<%=interfaces%>"/> 
                                        </td>
                                        <td colspan="3">
                                            <bean:write name="indicator" property="name" />
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td><bean:message key="indicator.edit.description" bundle="<%=interfaces%>"/></td>
                                        <td colspan="3">
                                            <bean:write name="indicator" property="description" />
                                        </td>
                                    </tr>
                                   
                                   
                                    <tr>
                                        <td>
                                            <bean:message key="indicator.common.type" bundle="<%=interfaces%>"/>
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td colspan="3">
                                            <html:select name="indicator" property="type" onchange="javascript:postAjax('indicator','listId',anchor + ':_CHANGE_OPTION');" styleClass="combobox_w100">
                                                <html:option value="-1"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                                                <html:option value="0"><bean:message key="indicator.common.target" bundle="<%=interfaces%>"/></html:option>
                                                <html:option value="1"><bean:message key="indicator.common.baseline" bundle="<%=interfaces%>"/></html:option>                                                
                                            </html:select>
                                        </td>
                                    </tr>
                                    
                                   <tr>
                                      <td colspan="4">
                                          <div id="test">
                                              <jsp:include page="/disability/categorys/indicator/paramValue.jsp" />
                                          </div>
                                      </td>
                                    </tr>
                                </tbody>
                            </table>
                        </logic:present>
                    </td>
                </tr>
                <tr>
                    <td>
                        <logic:present name="indicator">
                            <jsp:include page="/disability/categorys/indicator/cmdVal.jsp"/>
                        </logic:present>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</html:form> 

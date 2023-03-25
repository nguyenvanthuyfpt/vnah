<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<html:form action="object" method="post">
    <html:hidden name="object" property="id"/>
    <div class="padding-content">
        <table align="left" width="100%">
            <tbody>
                <tr>
                    <td valign="top">
                        <logic:present name="object">
                            <table class="tableForm" cellpadding="0" width="100%" align="center"
                                   style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">
                                <tbody>
                                    <tr>
                                        <td>
                                            <bean:message key="object.edit.code" bundle="<%=interfaces%>"/>                                             
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td>
                                            <html:text name="object" property="code" styleClass="inputbox" size="20"/>
                                        </td>
                                        <td>
                                            <bean:message key="common.label.createdate" bundle="<%=interfaces%>"/>                                             
                                        </td>
                                        <td>
                                            <html:text name="object" property="createDate" readonly="true" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td height="57">
                                            <bean:message key="object.edit.name" bundle="<%=interfaces%>"/>                                             
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td colspan="3" height="57">
                                            <html:text name="object" property="name" styleClass="inputbox" size="80"/>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td>
                                            <bean:message key="object.edit.description" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td colspan="3">
                                            <html:textarea name="object" property="description" rows="5" cols="55"/>
                                        </td>
                                    </tr>
                                    
                                    
                                    <tr>
                                        <td valign="top">
                                            <bean:message key="object.edit.parent" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td>
                                            <html:select styleClass="inputbox" name="object" property="parentID" styleClass="combobox_w200">
                                                <html:option value="0">
                                                    <bean:message key="combo.luachon" bundle="<%=interfaces%>"/>
                                                </html:option>
                                                <logic:present name="optObjects">
                                                    <html:options collection="optObjects" property="id" labelProperty="name"/>
                                                </logic:present>
                                            </html:select>
                                        </td>
                                         <td valign="top">
                                            Hi&#7875;n th&#7883;
                                        </td>
                                        <td>
                                            <html:select styleClass="inputbox" name="object" property="type" styleClass="combobox_w200">
                                                <html:option value="0">Danh s&#225;ch ch&#7881; s&#7889;</html:option>
                                                <html:option value="1">Nh&#7853;p th&#244;ng tin</html:option>
                                            </html:select>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </logic:present>
                    </td>
                </tr>
                <tr>
                    <td>
                        <logic:present name="object">
                            <jsp:include page="/disability/categorys/object/cmd.jsp"/>
                        </logic:present>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</html:form> 

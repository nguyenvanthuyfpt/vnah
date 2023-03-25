<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<html:form action="nguonhotro" method="post">
    <html:hidden name="nguonhotro" property="id"/>
    <div class="padding-content">
        <table align="left">
            <tbody>
                <tr>
                    <td valign="top">
                        <logic:present name="nguonhotro">
                            <table class="tableForm" cellpadding="0" width="100%" align="center"
                                   style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">
                                <tbody>
                                    <tr>
                                        <td>
                                            <bean:message key="nguonhotro.edit.name" bundle="<%=interfaces%>"/>                                             
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td>
                                            <html:text name="nguonhotro" property="name" styleClass="inputbox" size="40"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <bean:message key="nguonhotro.edit.code" bundle="<%=interfaces%>"/>                                             
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td>
                                            <html:text name="nguonhotro" property="code" styleClass="inputbox" size="40"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top">
                                            <bean:message key="nguonhotro.edit.parent" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td>
                                            <html:select styleClass="inputbox" name="nguonhotro" property="parentID">
                                                <html:option value="0">
                                                    <bean:message key="combo.luachon" bundle="<%=interfaces%>"/>
                                                </html:option>
                                                <logic:present name="BTreeNguonhotros">
                                                    <html:options collection="BTreeNguonhotros" property="id" labelProperty="name"/>
                                                </logic:present>
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
                        <logic:present name="nguonhotro">
                            <jsp:include page="/disability/categorys/nguonhotro/cmd.jsp"/>
                        </logic:present>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</html:form> 

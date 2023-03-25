<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<html:form action="mucdo" method="post">
    <html:hidden name="mucdo" property="id"/>
    <div class="padding-content">
        <table align="left">
            <tbody>
                <tr>
                    <td valign="top">
                        <logic:present name="mucdo">
                            <table class="tableForm" cellpadding="0" width="100%" align="center"
                                   style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">
                                <tbody>
                                    <tr>
                                        <td>
                                            <bean:message key="mucdo.edit.name" bundle="<%=interfaces%>"/>
                                             
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td>
                                            <html:text name="mucdo" property="name" styleClass="inputbox" size="40"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <bean:message key="mucdo.edit.code" bundle="<%=interfaces%>"/>
                                             
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td>
                                            <html:text name="mucdo" property="code" styleClass="inputbox" size="40"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top">
                                            <bean:message key="mucdo.edit.parent" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td>
                                            <html:select styleClass="inputbox" name="mucdo" property="parentID">
                                                <html:option value="0">
                                                    <bean:message key="combo.luachon" bundle="<%=interfaces%>"/>
                                                </html:option>
                                                <html:options collection="BMucdos" property="id" labelProperty="name"/>
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
                        <logic:present name="mucdo">
                            <jsp:include page="/disability/categorys/mucdo/cmd.jsp"/>
                        </logic:present>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</html:form> 

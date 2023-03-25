<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<html:form action="nguyennhan" method="post">
    <html:hidden name="nguyennhan" property="id"/>
    <div class="padding-content">
        <table align="left">
            <tbody>
                <tr>
                    <td valign="top">
                        <logic:present name="nguyennhan">
                            <table class="tableForm" cellpadding="0" width="100%" align="center"
                                   style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">
                                <tbody>
                                    <tr>
                                        <td>
                                            <bean:message key="nguyennhan.edit.name" bundle="<%=interfaces%>"/>
                                             
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td>
                                            <html:text name="nguyennhan" property="name" styleClass="inputbox"
                                                       size="40"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <bean:message key="nguyennhan.edit.code" bundle="<%=interfaces%>"/>
                                             
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td>
                                            <html:text name="nguyennhan" property="code" styleClass="inputbox"
                                                       size="40"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top">
                                            <bean:message key="nguyennhan.edit.parent" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td>
                                            <html:select styleClass="inputbox" name="nguyennhan" property="parentID">
                                                <html:option value="0">
                                                    <bean:message key="combo.luachon" bundle="<%=interfaces%>"/>
                                                </html:option>
                                                <logic:present name="BTreeNguyennhans">
                                                    <html:options collection="BTreeNguyennhans" property="id" labelProperty="name"/>
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
                        <logic:present name="nguyennhan">
                            <jsp:include page="/disability/categorys/nguyennhan/cmd.jsp"/>
                        </logic:present>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</html:form> 

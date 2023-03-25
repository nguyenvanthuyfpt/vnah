<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<html:form action="kpi_rank" method="post">
    <html:hidden name="rank" property="id"/>
    <div class="padding-content">
        <table align="left" width="100%">
            <tbody>
                <tr>
                    <td valign="top">
                        <logic:present name="rank">
                            <table class="tableForm" cellpadding="0" width="100%" align="center"
                                   style="border-collapse: collapse;padding:20px;" cellspacing="0" border="0">
                                <tbody>
                                    <tr>
                                        <td>
                                            <bean:message key="rank.edit.code" bundle="<%=interfaces%>"/>                                             
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td>
                                            <html:text name="rank" property="code" styleClass="inputbox" size="20"/>
                                        </td>
                                        <td>
                                            <bean:message key="common.label.createdate" bundle="<%=interfaces%>"/>                                             
                                        </td>
                                        <td>
                                            <html:text name="rank" property="createDate" readonly="true" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td height="57">
                                            <bean:message key="rank.edit.name" bundle="<%=interfaces%>"/>                                             
                                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td colspan="3" height="57">
                                            <html:text name="rank" property="name" styleClass="inputbox" size="80"/>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td valign="top">
                                            <bean:message key="rank.edit.parent" bundle="<%=interfaces%>"/>
                                        </td>
                                        <td colspan="3">
                                            <html:select styleClass="inputbox" name="rank" property="parentID" styleClass="combobox_w200">
                                                <html:option value="0">
                                                    <bean:message key="combo.luachon" bundle="<%=interfaces%>"/>
                                                </html:option>
                                                <logic:present name="optRanks">
                                                    <html:options collection="optRanks" property="id" labelProperty="name"/>
                                                </logic:present>
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top">
                                            <bean:message key="rank.edit.report" bundle="<%=interfaces%>"/>
                                        </td>
                                         <td colspan="3">
                                            <html:select styleClass="inputbox" name="rank" property="report" styleClass="combobox_w200">
                                                <html:option value="0"><bean:message key="common.no" bundle="<%=interfaces%>"/></html:option>
                                                <html:option value="1"><bean:message key="common.yes" bundle="<%=interfaces%>"/></html:option>
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
                        <logic:present name="rank">
                            <jsp:include page="/disability/categorys/rank/cmd.jsp"/>
                        </logic:present>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</html:form> 

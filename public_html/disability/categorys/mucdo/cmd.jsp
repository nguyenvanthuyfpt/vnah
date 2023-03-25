<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<logic:present name="mucdo">
    <table id="toolbar" cellspacing="0" cellpadding="0" border="0" width="100%">
        <tr valign="center" align="middle">
            <td>
                <html:button property="_EDIT" styleClass="button" onclick="post('mucdo',anchor + ':_CREATE');">
                    <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                </html:button>
            </td>
            <logic:greaterThan name="mucdo" property="id" value="0">
                <td>&nbsp;</td>
                <td>
                    <html:button property="_EDIT" styleClass="button" onclick="post('mucdo',anchor + ':_EDIT');">
                        <bean:message key="action.update" bundle="<%=interfaces%>"/>
                    </html:button>
                </td>
                <td>&nbsp;</td>
                <td>
                    <html:button property="_EDIT" styleClass="button" onclick="post('mucdo',anchor + ':_DELETE');">
                        <bean:message key="action.delete" bundle="<%=interfaces%>"/>
                    </html:button>
                </td>
            </logic:greaterThan>
        </tr>
    </table>
</logic:present> 

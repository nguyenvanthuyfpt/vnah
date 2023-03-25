<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<logic:present name="indicator">
    <table id="toolbar" cellspacing="0" cellpadding="0" border="0" width="100%">
        <tr valign="center" align="middle">
            <td>                
                <span class="bt_left_Search">
                    <span class="bt_right_Search">
                        <span class="bt_center_Search">
                             <html:button property="_EDIT" styleClass="button" onclick="post('indicator',anchor + ':_CREATE');">
                                  <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                              </html:button>
                        </span>
                    </span>
                </span>
            </td>
            <logic:greaterThan name="indicator" property="id" value="0">
                <td>&nbsp;</td>
                <td>                    
                    <span class="bt_left_Search">
                        <span class="bt_right_Search">
                            <span class="bt_center_Search">
                               <html:button property="_EDIT" styleClass="button" onclick="post('indicator',anchor + ':_EDIT');">
                                    <bean:message key="action.update" bundle="<%=interfaces%>"/>
                                </html:button> 
                            </span>
                        </span>
                    </span>
                </td>
                <td>&nbsp;</td>
                <td>                                        
                    <span class="bt_left_Search">
                        <span class="bt_right_Search">
                            <span class="bt_center_Search">
                                <html:button property="_EDIT" styleClass="button" onclick="post('indicator',anchor + ':_DELETE');">
                                    <bean:message key="action.delete" bundle="<%=interfaces%>"/>
                                </html:button> 
                            </span>
                        </span>
                    </span>
                </td>
            </logic:greaterThan>
        </tr>
    </table>
</logic:present> 

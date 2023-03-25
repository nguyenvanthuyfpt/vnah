<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<script type="text/javascript"> 

   function checkSubmitValue(form){    
        if (form.locationId.value=='0' || form.year.value=='0') {
            alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
            return false;
        }
        return true;
    }
</script>    

<logic:present name="indicator">
    <table id="toolbar" cellspacing="0" cellpadding="0" border="0" width="100%">
        <tr valign="center" align="middle">
            <td>                
                <span class="bt_left_Search">
                    <span class="bt_right_Search">
                        <span class="bt_center_Search">
                             <html:button property="_EDIT" styleClass="button" onclick="javascript:if(checkSubmitValue(this.form)) {post('indicator',anchor + ':_INSERT_VALUE');}">
                                  <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                              </html:button>
                        </span>
                    </span>
                </span>
            </td>
            <logic:greaterThan name="indicator" property="dtlId" value="0">
                <td>&nbsp;</td>
                <td>                    
                    <span class="bt_left_Search">
                        <span class="bt_right_Search">
                            <span class="bt_center_Search">
                               <html:button property="_EDIT" styleClass="button" onclick="javascript:if(checkSubmitValue(this.form)) {post('indicator',anchor + ':_UPDATE_VALUE');}">
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
                                <html:button property="_EDIT" styleClass="button" onclick="post('indicator',anchor + ':_DELETE_VALUE');">
                                    <bean:message key="action.delete" bundle="<%=interfaces%>"/>
                                </html:button> 
                            </span>
                        </span>
                    </span>
                </td>
            </logic:greaterThan>
            <td>                
                <span class="bt_left_Search">
                    <span class="bt_right_Search">
                        <span class="bt_center_Search">
                             <html:button property="_EDIT" styleClass="button" onclick="post('indicator',anchor + ':_CANCEL_VALUE');">
                                  <bean:message key="action.cancel" bundle="<%=interfaces%>"/>
                              </html:button>
                        </span>
                    </span>
                </span>
            </td>
        </tr>
    </table>
</logic:present> 

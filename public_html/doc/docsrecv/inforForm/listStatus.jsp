<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BDocsRecvRead">
 <fieldset class="friendset" style>
    <legend class="LEGEND-DOCSFORM"><bean:message key="form.docs.statusId.excute.caption" bundle="<%=interfaces%>"/></legend>
        <table class="tableForm" cellpadding="0" width="97%" align="center" style="border-collapse: collapse" cellspacing="0" border="0">    
        <tr>
            <td  valign="top">     
                <p>
                 <% String viewAll= com.inf.doc.IKeyDoc.DOC_STATUS_VIEW_ALL+""; %>                           
                <a href="javascript:post('docsrecv',anchor+':_VIEW:statusId1:<%=viewAll%>:view:0');">
                     <bean:message  key="status.all" bundle="<%=interfaces%>"/> (<bean:write name="BDocsRecvRead" property="amount"/>)
                </a>
                          <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkUnReaded" value="1">
                          <logic:present name="BDocsRecvWait">
                              <logic:iterate name="BDocsRecvWait" id="bean" type="com.form.main.FMain"> 
                                  <logic:equal name="bean" property="id" value="0" >
                                              <p>
                                              <% String waitId= com.inf.doc.IKeyDoc.DOC_STATUS_VIEW_WAIT+""; %>                                                          
                                              <a href="javascript:post('docsrecv',anchor+':_VIEW:statusId1:<%=waitId%>');"><bean:message key="status.wait" bundle="<%=interfaces%>"/><span>(<bean:write name="bean" property="amount"/>)</span></a>
                                 </logic:equal>
                              </logic:iterate>
                           </logic:present>
                          </logic:equal>
                        
                          
                            <logic:present name="BDocsRecv">                   
                                <logic:iterate name="BDocsRecv" id="beanStatus" type="com.form.main.FMain">
                                    <p>
                                    <% String waitId=beanStatus.getStatusId()+""; %>                                                
                                    <a href="javascript:post('docsrecv',anchor+':_VIEW:statusId1:<%=waitId%>')">
                                    <bean:write name="beanStatus" property="name" /> (<bean:write name="beanStatus" property="amount" />)
                                    </a>
                                </logic:iterate>
                             </logic:present>
                                
            </td>
        </tr>
        </table>
</fieldset>
 </logic:present>  

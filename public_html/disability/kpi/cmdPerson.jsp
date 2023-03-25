<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<%
    int objId = 0;
%>

<logic:present name="kpi">
<table id="toolbar" cellspacing="0" cellpadding="0" border="0" width="100%">
   <tr valign="center" align="middle">
      <td>
         <span class="bt_left_Search">
            <span class="bt_right_Search">
               <span class="bt_center_Search">
                  <html:button property="_CREATE_PER" styleClass="html:button" onclick="post('kpi',anchor + ':_CREATE_PER');">
                     <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                  </html:button></span></span></span>
      </td>
      <logic:greaterThan name="kpi" property="dtlId" value="0">
         <td>
            <span class="bt_left_Search">
               <span class="bt_right_Search">
                  <span class="bt_center_Search">
                     <html:button property="_EDIT_PER" styleClass="html:button"
                             onclick="post('kpi',anchor + ':_EDIT_PER');">
                        <bean:message key="action.update" bundle="<%=interfaces%>"/>
                     </html:button></span></span></span>
         </td>
         <td>
            <span class="bt_left_Search">
               <span class="bt_right_Search">
                  <span class="bt_center_Search">
                     <html:button property="_DELETE_PER" styleClass="html:button"
                             onclick="javascript:if(messageDelete()){postAjax('kpi','div_person', anchor + ':_PERSON_SEL_DELETE');}">
                        <bean:message key="action.delete" bundle="<%=interfaces%>"/>
                     </html:button></span></span></span>
         </td>
         <td>
             <span class="bt_left_Search">
                <span class="bt_right_Search">
                   <span class="bt_center_Search">
                      <html:button property="_SELECT_PER" styleClass="html:button"
                              onclick="post('kpi',anchor + ':_SELECT_PER');">
                         <bean:message key="action.select" bundle="<%=interfaces%>"/>
                      </html:button></span></span></span>
          </td>
      </logic:greaterThan>
      
      <td>
         <span class="bt_left_Search">
            <span class="bt_right_Search">
               <span class="bt_center_Search">
                  <html:button property="_CANCEL_PER" styleClass="html:button" onclick="post('kpi',anchor + ':_CANCEL_PER:perSel:0');">
                     <bean:message key="btn.cancel" bundle="<%=interfaces%>"/>
                  </html:button>
               </span>
            </span>
         </span>
      </td>     
      
      <td>
         <span class="bt_left_Search">
            <span class="bt_right_Search">
               <span class="bt_center_Search">
                  <html:button property="_CANCEL_PER" styleClass="html:button" onclick="post('disabilityFuntion',anchor + ':_PREPARED_CREATE_KPI');">
                     <bean:message key="btn.back" bundle="<%=interfaces%>"/>
                  </html:button>
               </span>
            </span>
         </span>
      </td>
   </tr>
</table>
</logic:present>
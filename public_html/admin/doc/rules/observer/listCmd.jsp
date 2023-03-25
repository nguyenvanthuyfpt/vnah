 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
    <table class="adminlist" id="rulesTable" style="border-collapse: collapse" cellpadding="0" width="100%">  
     <TBODY>  
            <tr>
                    <th  width="31%" ><bean:message key="doc.onserver.emp.caption" bundle="<%=interfaces%>"/></th>
                    <th  width="31%" ><bean:message key="doc.onserver.workflow.caption" bundle="<%=interfaces%>"/></th>                    
                    <th  colspan="2" ></th>
            </tr>
             <logic:present name="BObserverRules" >          
             <%  int i = 0;%>
             <logic:iterate name="BObserverRules" id="bean" type="com.form.admin.doc.rules.FDocRules"> 
              <%++i;%>
                <tr>
                    <td  style="padding-bottom: 4px;padding-top: 4px">
                        <bean:write name="bean" property="userFullName" />
                    </td>
                    <td  style="padding-bottom: 4px;padding-top: 4px" valign="top">
                       <logic:equal name="bean" property="workflowId" value="1">
                        <bean:message key="doc.onserver.workflow.rev.caption" bundle="<%=interfaces%>"/>
                       
                       </logic:equal>
                       <logic:notEqual name="bean" property="workflowId" value="1">
                         <bean:message key="doc.onserver.workflow.send.caption" bundle="<%=interfaces%>"/>                        
                       </logic:notEqual>
                       
                    </td>    
                    <td align="center" width="5%">
                        <img class="pointer" style="border:0px;" src="<%=contextPath%>/images/delete.png" onClick="javascript:if (messageDelete()){postAjax('observerrule','formList',anchor + ':_DELETE:observerUserId:<%=bean.getObserverUserId()%>:workflowIdObserver:<%=bean.getWorkflowIdObserver()%>');messageImg('formList');}" title="<bean:message key="danhmuc.button.xoa" bundle="<%=interfaces%>"/>" >                        
                    </td>
                </tr>
             </logic:iterate>   
           </logic:present> 
    </tbody>
  </table>   
     
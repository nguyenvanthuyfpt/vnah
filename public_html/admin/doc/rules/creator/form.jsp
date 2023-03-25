 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<% int j=0;%>  
<table class="adminform" id="dytbl_dir" style="border-collapse: collapse" cellpadding="0" width="100%">  
    <TBODY>
          
            <TR>
                <th><bean:message key="doc.onserver.department.caption" bundle="<%=interfaces%>"/>/<bean:message key="doc.creator.workflow.rev.caption" bundle="<%=interfaces%>"/></th>
                <th></th>
           </tr>
            <TR>                           
                <td height="26px">
                    <html:select styleClass="inputbox"  name="creatorrrule" property="departmentID" onchange="javascript:postAjax('creatorrrule','creatorInDep',anchor+':_SHOW_USERS');">                       
                        <logic:present name="BDepartments">
                        <logic:iterate name="BDepartments" id="bean" type="com.form.admin.departments.FDepartment">                
                        <option value="<bean:write name="bean" property="id" />" ><strong> <bean:write name="bean" property="name"/></strong> </option>
                        
                        </logic:iterate>
                        </logic:present>
                    </html:select>
                <td>
                <span id="creatorInDep"><jsp:include page="/admin/doc/rules/observer/userInDep.jsp"/></span>
                       
                </td> 
              </tr>
              <tr>
              <td >
                    <bean:message key="list.trailerstore.workflowId" bundle="<%=interfaces%>"/>
                         <html:select styleClass="inputbox" name="creatorrrule" property="workflowId"  style="width:107px;">
                        <logic:present name="BWorkflows">  
                        <html:options collection="BWorkflows" property="workflowId" labelProperty="title"/>
                        </logic:present>
                        </html:select>                                             
                </td>
                <td>      
                       <html:button property="_CREATE"  onclick="this.disabled=true;postAjax('creatorrrule','mainCreator', anchor + ':_CREATE')"  styleClass="button">                       
                            <bean:message key="cmd.task.add.caption" bundle="<%=interfaces%>"/>
                        </html:button>                              
                </td> 
              </tr>
    </tbody>
</table>


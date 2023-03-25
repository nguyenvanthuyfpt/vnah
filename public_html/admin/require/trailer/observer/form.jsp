 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<% int j=0;%>  
<table class="adminform" id="dytbl_dir" style="border-collapse: collapse" cellpadding="0" align="left">  
    <TBODY>
            <TR>
                <th width="27%" nowrap colspan="2"><bean:message key="doc.onserver.department.caption" bundle="<%=interfaces%>"/>/<bean:message key="require.observer.emp.caption" bundle="<%=interfaces%>"/></th>                
            </tr>            
            <TR>                           
                <td nowrap>
                   <strong><bean:message key="doc.onserver.department.caption" bundle="<%=interfaces%>"/> :</strong>
                    <html:select styleClass="inputbox"  name="frmRequireRule" property="departmentID" onchange="javascript:postAjax('frmRequireRule','usersInDep',anchor+':_SHOW_USERS');"> 
                        <logic:present name="BDepartments">
                        <logic:iterate name="BDepartments" id="bean" type="com.form.admin.departments.FDepartment">                
                            <option value="<bean:write name="bean" property="id" />"><strong> <bean:write name="bean" property="name"/></strong> </option>
                        </logic:iterate>
                        </logic:present>
                    </html:select>
                </td> 
                <td width="25%" nowrap>
                <strong><bean:message key="require.observer.emp.caption" bundle="<%=interfaces%>"/> : </strong>
                 <span id="usersInDep"><jsp:include page="/admin/require/trailer/observer/userInDep.jsp"/></span>
                                      
                </td>                 
            </tr>      
            <tr>
                <td colspan="2">
                    <strong><bean:message key="require.observer.delete.caption" bundle="<%=interfaces%>"/> : </strong>                
                    <html:checkbox name="frmRequireRule" property="delRm" value="1"/>                  
                </td> 
            </tr>
            <tr>                
                <td align="center" colspan="2">                          
                       <html:button property="_CREATE_OBSERVER"  onclick="postAjax('frmRequireRule','formList', anchor + ':_CREATE_OBSERVER')"  styleClass="button">                       
                            <bean:message key="cmd.task.add.caption" bundle="<%=interfaces%>"/>
                        </html:button>                              
                </td> 
                
            </tr>                      
    </tbody>
</table>


<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div id="formEditInf">
<logic:present name="BUserInfor" >          
        <table width="100%" cellpadding="0" cellspacing="0">
            <tr>
                <td>                    
                    <ul class="ulClassDetailMyContact">
                         <li>
                            <bean:message key="users.edit.department" bundle="<%=interfaces%>"/>: <bean:write name="BUserInfor" property="departmentName" />
                        </li>
                        <li>
                            <bean:message key="users.edit.group" bundle="<%=interfaces%>"/>: <bean:write name="BUserInfor" property="groupName" />
                        </li>
                        <logic:notEmpty name="BUserInfor" property="email"  >
                        <li>
                            <bean:message key="users.edit.email" bundle="<%=interfaces%>"/>: <bean:write name="BUserInfor" property="email" />
                        </li>
                        </logic:notEmpty>
                        <logic:notEmpty name="BUserInfor" property="phone" >
                         <li>
                            <bean:message key="mycontact.edit.telephone" bundle="<%=interfaces%>"/>: <bean:write name="BUserInfor" property="phone" />
                        </li>
                         </logic:notEmpty>
                        <logic:notEmpty name="BUserInfor" property="address" >
                        <li>
                            <bean:message key="mycontact.edit.address" bundle="<%=interfaces%>"/> :<bean:write name="BUserInfor" property="address" />
                        </li>  
                        </logic:notEmpty>
                        <li>
                            <bean:message key="users.edit.sex" bundle="<%=interfaces%>"/> :
                              <logic:equal name="BUserInfor" property="sex" value="0">
                                    <bean:message key="users.edit.sex.female" bundle="<%=interfaces%>"/>
                              </logic:equal>   
                              <logic:notEqual name="BUserInfor" property="sex" value="0">
                                    <bean:message key="users.edit.sex.male" bundle="<%=interfaces%>"/>
                              </logic:notEqual>   
                        </li>  
                        
                    </ul>
                </td>               
            </tr>
        </table>
</logic:present>        
 </div>    
    








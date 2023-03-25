 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<% int j=0;%>  
<table class="adminform" style="border-collapse: collapse" cellpadding="0" cellspacing="0">  
    <TBODY>
    <TR>                       
        <th><bean:message key="rules.assignEmp.caption" bundle="<%=interfaces%>"/></th>
         <th><bean:message key="rules.recieverEmp.caption" bundle="<%=interfaces%>"/></th>                                                
    </tr>      
     <TR>                           
        <td  style="padding-top:2px;padding-bottom:2px">
            <strong> <bean:message key="rules.title.caption" bundle="<%=interfaces%>"/> </strong>
            <html:text name="rules" property="title"  style="width:200px" size="250"/>              
        </td>
        
        <td >
           
            <img class="pointer" style="border:0px;" src="<%=contextPath%>/images/empty.gif" height="0" width="4px" />
            <bean:define name="rules" property="ruleId" id="ruleId" type="java.lang.Integer"/>
            <%                                              
            String Onclick = "selectedSubmit(document.rules," + ruleId + ",1)"; 
            String Onclick1 = "selectedSubmit(document.rules," + ruleId + ",0)"; 
            %>
            <html:button property="_CREATE"  onclick="<%=Onclick%>"  styleClass="button">                       
                            <bean:message key="cmd.task.add.caption" bundle="<%=interfaces%>"/>
            </html:button>
            <html:button property="_EDIT"  onclick="<%=Onclick1%>"  styleClass="button">                       
                            <bean:message key="categories.task.cmd.edit" bundle="<%=interfaces%>"/>
            </html:button>
        </td>
     </tr>     
        <tr>
        <td>
         <strong>  <bean:message key="doc.header.active.caption" bundle="<%=interfaces%>"/> </strong>
            <bean:define name="rules" property="active" id="active" type="java.lang.Integer" /> 
            <select  name="active" style="width:120px"> 
                  <option value="0" <%=active.intValue()==0?"selected":""%>><bean:message key="rules.unActive.compo.caption" bundle="<%=interfaces%>"/></option>
                  <option value="1" <%=active.intValue()==1?"selected":""%>><bean:message key="rules.active.compo.caption" bundle="<%=interfaces%>"/></option>
            </select>   
        </td>
        <td></td>
        </tr>
    <TR>                           
        <td >                   
                        <select multiple="multiple" name="bossUsersId"  style="width:250px;height:80px">
                         <logic:notEmpty name="rules" property="listsBoss" >
                             <logic:iterate name="rules" property="listsBoss" id="beanBoss" type="com.form.admin.tasks.rules.FRules"> 
                                    <option value="<bean:write name="beanBoss" property="userId"/>"><bean:write name="beanBoss" property="userFullName"/></option>
                            </logic:iterate>
                        </logic:notEmpty> 
                        </select>     
                        <div>
                        <a href="javascript:removeItem(document.rules.bossUsersId,document.rules,0)"><bean:message key="app.remove.cation" bundle="<%=interfaces%>"/></a> </div>
        </td>          
        <td >               
                        <select multiple="multiple" name="offUsersId" style="width:250px;height:80px"> 
                           <logic:notEmpty name="rules" property="listsOffice" >
                            <logic:iterate name="rules" property="listsOffice" id="beanOffcer" type="com.form.admin.tasks.rules.FRules"> 
                                <option value="<bean:write name="beanOffcer" property="userId"/>"><bean:write name="beanOffcer" property="userFullName"/></option>
                            </logic:iterate>
                           </logic:notEmpty> 
                        </select> 
                        <div></div>
                         <a href="javascript:removeItem(document.rules.offUsersId,document.rules,0)"><bean:message key="app.remove.cation" bundle="<%=interfaces%>"/></a>
        </td>  
        </tr>
    </tbody>
</table>
<table width="100%" cellpadding="0" cellspacing="0">
        <tr>
            <td valign="top" style="padding-top:15px" id="formList">
                <jsp:include page="/admin/tasks/rules/listCmd.jsp"/>                  
          </td>
        </tr>
</table>


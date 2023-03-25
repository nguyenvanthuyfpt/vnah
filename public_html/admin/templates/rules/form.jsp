 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<% int j=0;%>  
<table class="adminform" id="rulesTable" style="border-collapse: collapse" width="100%" cellpadding="0" cellspacing="0">  
    <TBODY>
            <TR>                       
                <th><bean:message key="rules.template.emp.excute.caption" bundle="<%=interfaces%>"/></th>
                <th ><bean:message key="rules.template.category.caption" bundle="<%=interfaces%>"/></th>                                           
                
            </tr>            
            <tr>
                <td colspan="2" style="text-align: right;">
                             <bean:define name="templateRule" property="categoriesId" id="categoriesId" type="java.lang.Integer"/>
                               <%                                              
                                String Onclick = "selectedSubmit(document.templateRule," + categoriesId + ",1)";  
                                 String Onclick1 = "selectedSubmit(document.templateRule," + categoriesId + ",0)";  
                               %>
                                <html:button property="_CREATE"  onclick="<%=Onclick%>"  styleClass="button">                       
                                    <bean:message key="cmd.task.add.caption" bundle="<%=interfaces%>"/>
                                </html:button>
                                 <html:button property="_CREATE"  onclick="<%=Onclick1%>"  styleClass="button">                       
                                    <bean:message key="categories.task.cmd.edit" bundle="<%=interfaces%>"/>
                                </html:button>
                               
                </td>
            </tr>
            <tr>
                 <td style="padding-top:11px">
                      <select multiple="multiple" name="usersId" style="width:250px;height:80px">
                         <logic:notEmpty name="templateRule" property="listsUserid" >                         
                             <logic:iterate name="templateRule" property="listsUserid" id="beanUser" type="com.form.admin.templates.rules.FTemplatesRule"> 
                                    <option  value="<bean:write name="beanUser" property="userId"/>"><bean:write name="beanUser" property="userFullName"/></option>
                            </logic:iterate>
                        </logic:notEmpty> 
                        </select> 
                        <div></div>                                                         
                            <a href="javascript:removeItem(document.templateRule.usersId,document.templateRule,0)"><bean:message key="app.remove.cation" bundle="<%=interfaces%>"/></a>
                 </td>
                 <td>
                      <html:select multiple="multiple" name="templateRule" property="categoriesIds" style="width:250px;height:80px">
                         <logic:present name="BCategories">                         
                             <logic:iterate name="BCategories" id="beanCategory" type="com.form.admin.templates.templateType.FTemplateType"> 
                                    <option <%=categoriesId.intValue()==beanCategory.getId()?"selected":""%> value="<bean:write name="beanCategory" property="id"/>"><bean:write name="beanCategory" property="name"/></option>
                            </logic:iterate>
                         </logic:present>    
                        </html:select> 
                        <br>
                 </td>
            </tr>   
    </tbody>
</table>

<table width="100%" cellpadding="0" cellspacing="0">
    <tr>
          <td valign="top" style="padding-top:15px" id="formList">
                <jsp:include page="/admin/templates/rules/listCmd.jsp"/>                  
         </td> 
    </tr>
</table>


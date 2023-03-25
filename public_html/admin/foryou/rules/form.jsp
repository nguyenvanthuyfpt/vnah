 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<% int j=0;%>  
<table class="adminform"  cellpadding="0" cellspacing="0"  width="100%">  
    <TBODY>
            <TR>                       
                <th ><bean:message key="rules.assignEmp.caption" bundle="<%=interfaces%>"/></th>
                <th ><bean:message key="rules.recieverEmp.caption" bundle="<%=interfaces%>"/></th> 
                
            </tr>     
            <tr>
                <td  style="padding-top:2px;padding-bottom:2px">
                        <bean:message key="rules.title.caption" bundle="<%=interfaces%>"/> 
                        <html:text name="ruleforyou" property="title"  style="width:200px" size="250"/>  
                </td>
                <td nowrap>
                <img class="pointer" style="border:0px;" src="<%=contextPath%>/images/empty.gif" height="0" width="4px" />
                                 <Strong><bean:message key="doc.header.workflow.caption" bundle="<%=interfaces%>"/></strong>
                                <html:select styleClass="inputbox" name="ruleforyou" property="workflowId"  style="width:107px;">
                                    <html:options collection="BWorkflows" property="workflowId" labelProperty="title"/>
                                    
                               </html:select>
     
                </td>
            </tr>
            <tr>
                        <td> 
                        
                                <bean:message key="doc.header.active.caption" bundle="<%=interfaces%>"/>
                                <bean:define name="ruleforyou" property="active" id="active" type="java.lang.Integer" /> 
                                <select  name="active" style="width:120px"> 
                                <option value="0" <%=active.intValue()==0?"selected":""%>><bean:message key="rules.unActive.compo.caption" bundle="<%=interfaces%>"/></option>
                                <option value="1" <%=active.intValue()==1?"selected":""%>><bean:message key="rules.active.compo.caption" bundle="<%=interfaces%>"/></option>
                                </select>   
                        </td>
                        <td>
                                <bean:define name="ruleforyou" property="ruleId" id="ruleId" type="java.lang.Integer"/>
                                <%                                              
                                String Onclick = "selectedSubmit(this,document.ruleforyou," + ruleId + ",1)"; 
                                String Onclick1 = "selectedSubmit(this,document.ruleforyou," + ruleId + ",0)"; 
                                %>
                                <html:button property="_CREATE"  onclick="<%=Onclick%>"  styleClass="button">                       
                                <bean:message key="cmd.task.add.caption" bundle="<%=interfaces%>"/>
                                </html:button>
                                <html:button property="_EDIT"  onclick="<%=Onclick1%>"  styleClass="button">                       
                                <bean:message key="categories.task.cmd.edit" bundle="<%=interfaces%>"/>
                                </html:button>
                 </td>
            </tr>
            <TR>                           
                <td  valign="top">                               
                                <select multiple="multiple" name="bossUsersId" size="4" style="width:250px;height:80px">
                                 <logic:notEmpty name="ruleforyou" property="listsBoss" >
                                     <logic:iterate name="ruleforyou" property="listsBoss" id="beanBoss" type="com.form.admin.foryou.rules.FRule"> 
                                            <option value="<bean:write name="beanBoss" property="userId"/>"><bean:write name="beanBoss" property="userFullName"/></option>
                                    </logic:iterate>
                                </logic:notEmpty> 
                                </select> 
                                <div></div>
                                 <a href="javascript:removeItem(document.ruleforyou.bossUsersId,document.ruleforyou,0)"><bean:message key="app.remove.cation" bundle="<%=interfaces%>"/></a> 
                              
                </td>   
                <td  style="padding-top:4px;padding-bottom: 4px">
               
                                <select multiple="multiple" name="offUsersId" style="width:250px;height:80px"> 
                                   <logic:notEmpty name="ruleforyou" property="listsOffice" >
                                    <logic:iterate name="ruleforyou" property="listsOffice" id="beanOffcer" type="com.form.admin.foryou.rules.FRule"> 
                                        <option value="<bean:write name="beanOffcer" property="userId"/>"><bean:write name="beanOffcer" property="userFullName"/></option>
                                    </logic:iterate>
                                   </logic:notEmpty> 
                                </select>  
                                <div></div>
                                 
                                 <a href="javascript:removeItem(document.ruleforyou.offUsersId,document.ruleforyou,0)"><bean:message key="app.remove.cation" bundle="<%=interfaces%>"/></a>
                                
                         
                </td>                  
               
            </tr>   
    </tbody>
</table>

<table width="100%" cellpadding="0" cellspacing="0">
<tr>
          <td valign="top" style="padding-top:15px" id="formList" colspan="2">
                      <jsp:include page="/admin/foryou/rules/listCmd.jsp"/>            
          </td> 
     </tr>
</table>
  








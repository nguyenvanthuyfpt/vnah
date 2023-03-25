 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<% int j=0;%>  
<table class="adminform" id="rulesTable" style="border-collapse: collapse" cellpadding="0">  
    <TBODY>
            <TR>                       
                <th><bean:message key="report.header.peopleBoss.caption" bundle="<%=interfaces%>"/></th>
                <th><bean:message key="report.header.peopleOffice.caption" bundle="<%=interfaces%>"/></th>                                           
                
            </tr>                           
            <tr>
                 <td  style="padding-top:2px;padding-bottom:2px">
                      <strong>  <bean:message key="rules.title.caption" bundle="<%=interfaces%>"/> </strong>
                        <html:text name="reportRule" property="title"  style="width:200px" size="1000"/>
                 </td>
                 <td >
                         <bean:message key="doc.header.active.caption" bundle="<%=interfaces%>"/>
                         <bean:define name="reportRule" property="active" id="active" type="java.lang.Integer" />                             
                               <select  name="active" style="width:120px"> 
                                    <option value="0" <%=active.intValue()==0?"selected":""%> ><bean:message key="rules.unActive.compo.caption" bundle="<%=interfaces%>"/></option>
                                    <option value="1" <%=active.intValue()==1?"selected":""%>><bean:message key="rules.active.compo.caption" bundle="<%=interfaces%>"/></option>
                               </select>
                               
                                <img class="pointer" style="border:0px;" src="<%=contextPath%>/images/empty.gif" height="0" width="4px" />
                             <bean:define name="reportRule" property="ruleId" id="ruleId" type="java.lang.Integer"/>
                               <%                                              
                                String Onclick = "selectedSubmit(document.reportRule," + ruleId + ",1)"; 
                                String Onclick1 = "selectedSubmit(document.reportRule," + ruleId + ",0)"; 
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
                <td >                                              
                        <select multiple="multiple" name="bossUsersId" style="width:250px;height:80px">
                         <logic:notEmpty name="reportRule" property="listsBoss" >
                             <logic:iterate name="reportRule" property="listsBoss" id="beanBoss" type="com.form.admin.reports.rules.FReportRule"> 
                                    <option value="<bean:write name="beanBoss" property="userId"/>"><bean:write name="beanBoss" property="userFullName"/></option>
                            </logic:iterate>
                        </logic:notEmpty> 
                        </select> 
                        <div></div>
                        <a href="javascript:removeItem(document.reportRule.bossUsersId,document.reportRule,0)"><bean:message key="app.remove.cation" bundle="<%=interfaces%>"/></a>
                </td>                                   
                <td >
                
                                <select multiple="multiple" name="offUsersId" style="width:250px;height:80px"> 
                                   <logic:notEmpty name="reportRule" property="listsOffice" >
                                    <logic:iterate name="reportRule" property="listsOffice" id="beanOffcer" type="com.form.admin.reports.rules.FReportRule"> 
                                        <option value="<bean:write name="beanOffcer" property="userId"/>"><bean:write name="beanOffcer" property="userFullName"/></option>
                                    </logic:iterate>
                                   </logic:notEmpty> 
                                </select>  
                                <div></div>
                                <a href="javascript:removeItem(document.reportRule.offUsersId,document.reportRule,0)"><bean:message key="app.remove.cation" bundle="<%=interfaces%>"/></a>
                </td>
             </tr>  
             
    </tbody>
</table>
<table width="100%" cellspacing="0" cellpadding="0"> 
    <tr>
            <td style="padding-top:10px" id="formList">
            <jsp:include page="/admin/reports/rules/listCmd.jsp"/>
            </td>
     </tr>
</table>     


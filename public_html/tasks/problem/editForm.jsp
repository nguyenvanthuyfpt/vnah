<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<div class="content-calendar">
 <table class="tableForm" cellspacing="0" cellpadding="0" width="100%" style="border-collapse: collapse;" border="0spx" >
                    <tr>
                         <td height="24px" style="padding-left:10px"  align="left"  nowrap><bean:message key="problem.title" bundle="<%=interfaces%>"/> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                         </td>
                         <td colspan="2">
                                <html:select  styleClass="inputbox" style="width:120px" name="problem" property="categoriesId">
                                <option value="0"> <bean:message key="problem.categories" bundle="<%=interfaces%>"/> </option>
                                <logic:present name="BCategories">
                                    <html:options collection="BCategories" property="id" labelProperty="title"/>          
                                </logic:present>
                                </html:select>  
                                <html:text name="problem" property="title" style="width:400px" maxlength="1000"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <td height="24px" style="padding-left:10px"  align="left"><bean:message key="problem.date" bundle="<%=interfaces%>"/></td>
                        <td  align="left">
                                <bean:message key="problem.fromdate" bundle="<%=interfaces%>"/>: <html:text name="problem" property="fromDate" styleId="fromDate" style="width:70px"/><span>&nbsp;&nbsp;</span><input type="image" src="<%=contextPath%>/images/ew_calendar.gif" alt='"/>' onClick="popUpCalendar(this,'fromDate','dd/mm/yyyy');return false;">
                                &nbsp;&nbsp;<bean:message key="problem.todate" bundle="<%=interfaces%>"/>: <html:text name="problem" property="toDate" styleId="toDate" style="width:70px"/><span>&nbsp;&nbsp;</span><input type="image" src="<%=contextPath%>/images/ew_calendar.gif" alt='"/>' onClick="popUpCalendar(this,'toDate','dd/mm/yyyy');return false;">
                        </td>
                        <td></td>
                    </tr>
                    
                    <tr>
                        <td height="24px" style="padding-left:10px"  align="left"><bean:message key="problem.file" bundle="<%=interfaces%>"/></td>
                        <td  align="left"><html:file name="problem" property="fileUplaod" style="width:250px"/></td>                        
                        <td></td>
                    </tr>
                    
                    <tr>
                        <td height="24px"  align="left" style="padding-left:10px" valign="top" width="18%" nowrap><bean:message key="problem.incharge" bundle="<%=interfaces%>"/> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                        <td valign="top"  align="left">
                             <html:select styleClass="inputbox"  name="problem" property="incharge"  style="width:250px">
                                     <logic:present name="userOption">
                                        <html:options collection="userOption" property="id" labelProperty="fullName"/>          
                                    </logic:present>                   
                            </html:select>
                        </td>
                        <td>
                            <logic:notEqual name="problem" property="problemId" value="0" > 
                                <logic:notEqual name="problem" property="type" value="1" > 
                                    <bean:message key="problem.usersAssign" bundle="<%=interfaces%>"/> 
                                    <bean:message key="problem.usersAssign.new.caption" bundle="<%=interfaces%>"/>
                                    <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="app.remove.cation" bundle="<%=interfaces%>"/>" onClick="javascript:removeItem(document.problem.usersIdNew,document.problem,1);">                              
                                </logic:notEqual>
                            </logic:notEqual>
                        </td>
                    </tr>
                    <tr>
                        <td height="24px"  align="left" style="padding-left:10px" valign="top">                        
                        <logic:notEqual name="problem" property="problemId" value="0" > 
                            <logic:equal name="problem" property="type" value="1" > 
                                 <bean:message key="problem.usersAssign" bundle="<%=interfaces%>"/>
                            </logic:equal>
                            <logic:notEqual name="problem" property="type" value="1" > 
                                <bean:message key="problem.usersAssign.exits.caption" bundle="<%=interfaces%>"/>
                            </logic:notEqual>
                        </logic:notEqual>    
                        
                        <logic:equal name="problem" property="problemId" value="0" > 
                            <bean:message key="problem.usersAssign" bundle="<%=interfaces%>"/>
                        </logic:equal>    
                            <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                            <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="app.remove.cation" bundle="<%=interfaces%>"/>" onClick="javascript:removeItem(document.problem.usersId,document.problem,1)">                              
                         </td>
                         
                        <td  align="left" style="padding-bottom: 5px">
                             <html:select styleClass="inputbox" multiple="multiple" name="problem" property="usersId" style="height:80px;width:250px">                                    
                                     <logic:present name="userOption">
                                        <html:options collection="userOption" property="id" labelProperty="fullName"/>          
                                    </logic:present>                                                                 
                            </html:select>
                             <logic:equal name="problem" property="problemId" value="0" >                             
                            </logic:equal>
                        </td> 
                        <td>
                            <logic:notEqual name="problem" property="problemId" value="0" > 
                            <logic:notEqual name="problem" property="type" value="1" > 
                            <html:select styleClass="inputbox" multiple="multiple" name="problem" property="usersIdNew" style="height: 80px; width: 250px;" >
                            </html:select>                           
                            </logic:notEqual>
                            </logic:notEqual> 
                        </td>
                    </tr>
                   
                    
                    <tr>                       
                        <td  align="left" colspan="3">  
                        <div><bean:message key="problem.problem" bundle="<%=interfaces%>"/></div>
                            <textarea id="elm1" name="elm1" style="width:98%;height:250px">
		                    <bean:write name="problem" property="problem"/>
                            </textarea>    
                        </td>
                    </tr>
                    
                    <tr>
                        <td colspan="3" class="toolCmd" height="26px" style="padding-top:4px;text-align:left" >
                     
                      <logic:equal name="problem" property="problemId" value="0" >  
                           <logic:notEqual parameter="<%=anchor%>" value="_PREPARED_EDIT" >  
                                 <html:button property="_CREATE"  onclick="if(checkSubmit(this.form)){this.disabled=true;checkedAll(document.problem.usersId);post('problem',anchor+':_CREATE');}" styleClass="button">                       
                                     <bean:message key="menu.top.rule.tasks.caption" bundle="<%=interfaces%>"/>
                                 </html:button>
                          </logic:notEqual>        
                     </logic:equal>     
                      

                         <logic:notEqual name="problem" property="problemId" value="0" >    
                            
                             <logic:notEqual name="problem" property="type" value="1" >                                
                                 <html:button property="_EDIT"  onclick="if(checkSubmit(this.form)){this.disabled=true;checkedAll(document.problem.usersId);checkedAll(document.problem.usersIdNew);post('problem',anchor + ':_EDIT');}"  styleClass="button">                       
                                     <bean:message key="menu.top.rule.tasks.caption" bundle="<%=interfaces%>"/>
                                 </html:button>                               
                              </logic:notEqual> 
                           
                                <logic:equal name="problem" property="type" value="1">  
                                         <html:button property="_CREATE"  onclick="if(checkSubmit(this.form)){this.disabled=true;checkedAll(document.problem.usersId);checkedAll(document.problem.usersIdNew);post('problem',anchor+':_TASK_ASSIGN_CREATE');}" styleClass="button">                       
                                             <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                                         </html:button>                                 
                               </logic:equal>                           
                       </logic:notEqual>                            
                          
                       
                        </td>
                    </tr>
            </table>        
</div>
 
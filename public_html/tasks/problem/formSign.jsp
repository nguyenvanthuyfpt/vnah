<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<script type="text/javascript" src="<%=contextPath%>/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/tinymce.js"></script>
<div class="content-calendar">
<html:form action="problem" enctype="multipart/form-data" method="post"> 
<html:hidden name="problem" property="type" />
<html:hidden name="problem" property="secureId" />
<html:hidden name="problem" property="problem" /> 
 <table class="tableForm" cellspacing="0" cellpadding="0" width="100%" style="border-collapse: collapse;" >
                    <tr>
                        <td height="24px" style="padding-left:10px"  align="left" nowrap><bean:message key="problem.categories" bundle="<%=interfaces%>"/></td>
                        <td  align="left" width="280px" nowrap>                                
                                <html:select  styleClass="inputbox" style="width:250px" name="problem" property="categoriesId">
                                   <option value="0"> <bean:message key="problem.select.option" bundle="<%=interfaces%>"/> </option>
                                    <logic:present name="BCategories">
                                        <html:options collection="BCategories" property="id" labelProperty="title"/>          
                                    </logic:present>                                   
                                </html:select>  
                        </td>  
                    </tr>
                    <tr>
                         <td height="24px" style="padding-left:10px"  align="left"><bean:message key="problem.title" bundle="<%=interfaces%>"/> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                         <td  align="left"><html:text name="problem" property="title" style="width:245px" maxlength="1000"/></td>
                    </tr>
                    
                    <tr>
                        <td height="24px" style="padding-left:10px"  align="left"><bean:message key="problem.date" bundle="<%=interfaces%>"/></td>
                        <td  align="left">
                                <bean:message key="problem.fromdate" bundle="<%=interfaces%>"/>: <html:text name="problem" property="fromDate" styleId="fromDate" style="width:70px"/><span>&nbsp;&nbsp;</span><input type="image" src="<%=contextPath%>/images/ew_calendar.gif" alt='"/>' onClick="popUpCalendar(this,'fromDate','dd/mm/yyyy');return false;">
                                &nbsp;&nbsp;<bean:message key="problem.todate" bundle="<%=interfaces%>"/>: <html:text name="problem" property="toDate" styleId="toDate" style="width:70px"/><span>&nbsp;&nbsp;</span><input type="image" src="<%=contextPath%>/images/ew_calendar.gif" alt='"/>' onClick="popUpCalendar(this,'toDate','dd/mm/yyyy');return false;">
                        </td>                        
                    </tr>
                    <tr>
                        <td height="24px" style="padding-left:10px"  align="left"><bean:message key="problem.file" bundle="<%=interfaces%>"/></td>
                        <td  align="left"><html:file name="problem" property="fileUplaod" style="width:250px"/></td>                        
                    </tr>
                    
                    <tr>
                        <td height="24px"  align="left" style="padding-left:10px" valign="top" width="18%" nowrap><bean:message key="problem.incharge" bundle="<%=interfaces%>"/> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                        <td valign="top"  align="left">
                             <html:select styleClass="inputbox"  name="problem" property="incharge"  style="width:250px">
                                                   
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td height="24px"  align="left" style="padding-left:10px" valign="top">                                                
                             <bean:message key="problem.usersAssign" bundle="<%=interfaces%>"/>                          
                            <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="app.remove.cation" bundle="<%=interfaces%>"/>" onClick="javascript:removeItem(document.problem.usersId,document.problem,1)">                              
                         </td>
                        <td  align="left" style="padding-bottom: 5px">
                             <html:select styleClass="inputbox" multiple="multiple" name="problem" property="usersId" style="height:80px;width:250px">                                    
                                                                                                     
                            </html:select>
                             <logic:equal name="problem" property="problemId" value="0" >                             
                            </logic:equal>
                        </td> 
                        
                    </tr>
                  
                    
                    <tr>                       
                        <td  align="left" colspan="2">                                 
                        <div><bean:message key="problem.problem" bundle="<%=interfaces%>"/></div>
                            <textarea id="elm1" name="elm1" style="width:98%;height:250px">
		                   <bean:write name="problem" property="problem"/>
                            </textarea>    
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" class="toolCmd" height="26px" style="text-align:left" align="left">                                
                                 <html:button property="_CREATE"  onclick="this.disabled=true;checkedAll(document.problem.usersId);post('problem',anchor+':_TASK_ASSIGN_CREATE');" styleClass="button">                       
                                     <bean:message key="menu.top.rule.tasks.caption" bundle="<%=interfaces%>"/>
                                 </html:button>                                 
                        </td>
                    </tr>
            </table>     
</html:form>
</div>
 
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<script language="javascript">  
    function navigator(pageIndex){     
        postAjax('report','mainReportList',anchor + ':_SEARCH_PAGE:pageIndex:'+ pageIndex);
      
    }    
</script>
<html:form action="report" enctype="multipart/form-data" method="post">
<html:hidden name="report" property="assignId"/>
<html:hidden name="report" property="incharge"/>
<html:hidden name="report" property="problemId"/>
<html:hidden name="report" property="creatorName"/>    
<html:hidden name="report" property="secureId"/> 
<bean:define name="report" property="complate" id="complate"  type="java.lang.Integer"/>
<html:hidden name="report" property="type"/>
<table width="100%" noborder style="border-collapse: collapse"  cellpadding="0" cellspacing="0">
<tr><td  class="title-01" nowrap>
    <a href="#" class="li-title-09">
       <bean:message key="problem.emp.report.result.caption" bundle="<%=interfaces%>"/>
    </a>
</td>
</tr>
</table>
<table width="100%" cellpadding="0" cellspacing="0">
    <tr>
       <td valign="top" >                
                <TABLE cellSpacing=0 class="adminformEdit" cellPadding=0  border="0">                         
                    <tr>                       
                        <td colspan="2">
                                 <ul class="ulClassDoc">
                                   <li>
                                       <strong> <bean:message key="problem.title" bundle="<%=interfaces%>"/> : </strong> 
                                        <bean:write name="BReport" property="title"/>
                                    </li>
                                 
                                    <li>
                                           <strong>  <bean:message key="problem.fromdate" bundle="<%=interfaces%>"/> :  </strong>
                                            <bean:write name="BReport" property="fromDate"/>
                                           <strong>   <bean:message key="problem.todate" bundle="<%=interfaces%>"/> : </strong>
                                             <bean:write name="BReport" property="toDate"/>
                                       
                                    </li>
                                 
                                    <li>
                                         <strong>   <bean:message key="problem.complate" bundle="<%=interfaces%>"/>: </strong> 
                                           <bean:write name="BReport" property="complete"/> %
                                       
                                    </li>
                                   <logic:notEqual name="BReport" property="problem" value="">
                                    <li>
                                             <strong> <bean:message key="problem.problem" bundle="<%=interfaces%>"/>: </strong> 
                                             <bean:define name="BReport" property="problem" id="problem"/>
                                             <%=problem%>
                                    </li>
                                    </logic:notEqual>
                                  </ul>  
                        </td>
                    </tr>
                    
                    <tr>
                        <td valign="top" style="padding-left: 30px;padding-right: 8px;padding-top: 10px;"><bean:message key="report.name.caption" bundle="<%=interfaces%>"/></td>
                        <td> <html:textarea name="report" property="report" style="width:380px;height:100px"/></td>
                    </tr>
                     <tr>
                        <td valign="top" style="padding-left: 30px;padding-right: 8px"><bean:message key="problem.complate" bundle="<%=interfaces%>"/></td>
                        <td >
                                <select  styleClass="inputbox" style="width:60px" name="complate">                                   
                                    <%for (int i=0;i<=100;i+=5){
                                        String selected = complate.intValue() == i?"selected":"";                                     
                                    %>                                    
                                      <option  value="<%=i%>" <%=selected%>><%=i%> %</option>
                                    <%}%>
                                </select>
                        </td>
                    </tr>
                     
                    <tr>
                        <td style="padding-left: 30px;padding-right: 8px"><bean:message key="report.file.caption" bundle="<%=interfaces%>"/></td>
                        <td ><html:file name="report" property="fileUplaod" size="40"/> </td>
                    </tr>                    
                    <tr>
                        <td><br></td>
                        <td height="24px">                        
                             <html:button property="_EDIT"  onclick="this.disabled=true;post('report',anchor+':_CREATE')" styleClass="button">                       
                                 <bean:message key="categories.task.cmd.edit" bundle="<%=interfaces%>"/>
                             </html:button>                            
                                    <html:errors property="alert" bundle="<%=interfaces%>" />
                        </td>
                    </tr>
            </table>        
       </td>        
    </tr>
    <tr>
        <td valign="top" id="mainReportList"><jsp:include page="/tasks/report/list.jsp"/></td>
    </tr>
</table>         
</html:form>
            
            
  
 
 
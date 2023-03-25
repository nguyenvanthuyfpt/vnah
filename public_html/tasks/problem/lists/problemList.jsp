<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>  
<html:form action="report"/>
<html:form action="problem"/>
 <bean:define name="problem" property="problemId" id="problemId" type="java.lang.Integer"/>
 <bean:define name="problem" property="assignId" id="assignId" type="java.lang.Integer"/> 
<table width="100%" noborder style="border-collapse: collapse"  cellpadding="0" cellspacing="0">
<tr><td  class="title-01" nowrap>
    <a href="#" class="li-title-09">
     <bean:message key="problem.list.report.result.caption" bundle="<%=interfaces%>"/>
    </a>
</td>
</tr>
</table>  
<div class="ct-celendar">   
<table width="100%" border="0px" cellpadding="0" cellspacing="0">        
         <bean:define name="BAllProblem" id="beans" type="com.form.FBeans"/>
        <logic:present name="BAllProblem"> 
        <%  int i = 0;%>
        <logic:iterate name="BAllProblem" id="bean" type="com.form.tasks.problem.FProblem">               
        <tr>
        <td valign="top">       
                <table  id="table6" style="border-collapse: collapse" cellpadding="0" width="100%">
                    <TBODY>                         
                        <tr>
                            <td style="padding-top:4px;padding-bottom:4px" colspan="4" align="left">
                                 <ul class="ulClassDoc">
                                   <li>
                                       <strong> <bean:message key="problem.title" bundle="<%=interfaces%>"/> :</strong> 
                                        <bean:write name="bean" property="title"/>
                                    <logic:equal name="problem" property="type" value="1">   
                                        <logic:equal name="problem" property="accepted" value="1">
                                             <img style="border:0px;" src="<%=contextPath%>/images/tick.png" title="<bean:message key="problem.accepted" bundle="<%=interfaces%>"/>">
                                        </logic:equal>
                                         <logic:notEqual name="problem" property="accepted" value="1">
                                            <img style="border:0px;cursor: pointer" src="<%=contextPath%>/images/newImages/i_17.gif" title="<bean:message key="problem.accepted.not" bundle="<%=interfaces%>"/>" onClick="javascript:post('problem',anchor + ':_TASK_ASSIGN_ACCEPTED:problemId:<%=problemId%>:accepted:1:assignId:<%=assignId%>');">
                                        </logic:notEqual>
                                     </logic:equal>   
                                    </li>
                                    <li>
                                           <strong>  <bean:message key="problem.fromdate" bundle="<%=interfaces%>"/> :  </strong>
                                            <bean:write name="bean" property="fromDate"/>
                                           <strong>   <bean:message key="problem.todate" bundle="<%=interfaces%>"/> : </strong>
                                             <bean:write name="bean" property="toDate"/>
                                       
                                    </li>
                                     <li>
                                           <strong>  <bean:message key="problem.incharge" bundle="<%=interfaces%>"/> :  </strong>
                                            <bean:write name="bean" property="inchargeName"/>
                                          
                                       
                                    </li>
                                 
                                    <li>
                                         <strong>   <bean:message key="problem.complate" bundle="<%=interfaces%>"/>: </strong> 
                                           <bean:write name="bean" property="complete"/> % 
                                            <strong>  <bean:message key="doc.file.caption" bundle="<%=interfaces%>"/>:</strong> 
                                            <logic:notEqual name="bean" property="fileName" value="">                                    
                                                <a href="javascript:post('problem',anchor + ':_GET:problemId:<bean:write name="bean" property="problemId" />');remove('problem',anchor)">
                                                     <bean:write name="bean" property="fileName"/>
                                                </a>                                                                 
                                            </logic:notEqual> 
                                    </li>
                                    <li>
                                             <strong> <bean:message key="problem.problem" bundle="<%=interfaces%>"/>: </strong> 
                                             <logic:notEqual name="bean" property="problem" value="">
                                             <bean:define name="bean" property="problem" id="problem"/>
                                             <%=problem%>
                                             </logic:notEqual>
                                             
                                    </li>                                    
                                  </ul>  
                            </td>
                        </tr> 
                        
                        </tbody>
                       </table>
             </td>
           </tr>
           <tr>
                <td class="tdheader" style="text-align:left">
                    <bean:message key="report.review.caption" bundle="<%=interfaces%>"/>: 
                       <bean:write name="bean" property="nameCreator"/>                                                                                       
                  </td>
           </tr>
           
              <tr >                                               
                     <td  class="taskcontent" style="padding-bottom:10px"> 
                       <logic:iterate name="bean" property="reports" id="beanRe" type="com.form.tasks.report.FReport">  
                          <ul class="ulClass">
                          <li>
                            <strong> <bean:message key="report.timeReport.date.caption" bundle="<%=interfaces%>"/> :</strong>
                          <bean:write name="beanRe" property="timeReport"/>
                          </li>
                           <li>
                          <strong>  <bean:message key="report.name.caption" bundle="<%=interfaces%>"/>:</strong>
                          <bean:write name="beanRe" property="report"/>
                           <li>
                          <strong> <bean:message key="problem.complate" bundle="<%=interfaces%>"/>:</strong>
                          <bean:write name="beanRe" property="complate"/> %
                         </li>                                                              
                                     <logic:notEqual name="beanRe" property="fileName" value="">
                                      <li>
                                        <strong>  <bean:message key="doc.file.caption" bundle="<%=interfaces%>"/>:</strong>                                                                   
                                         <a href="javascript:post('report',anchor + ':_SAVE:id:<%=beanRe.getId()%>');remove('report',anchor)"><bean:write name="beanRe" property="fileName"/></a>                                                                    
                                       </logic:notEqual>  
                         </ul> 
                          </logic:iterate>
                    </td> 
              </tr>
                <% int count = 0;%>
                  <logic:iterate name="bean" property="asigns" id="beanA" type="com.form.tasks.problem.FProblem"> 
                  <% count++; %>
                      <tr>
                         <td  class="tdheader" style="text-align:left" colspan="2">                                               
                           <bean:message key="app.emp.excute.caption" bundle="<%=interfaces%>"/>:  
                            <span class='<%=beanA.getWorker()==beanA.getIncharge()?"colorIncharge":""%>'> 
                                     <bean:write name="beanA" property="workerName"/>      
                              </span>                                                                                                                                   
                         </td>                                                                                                 
                    </tr>
                     <tr>                                               
                        <td class="<%=(count%2)==0?"taskcontent":"taskcontent1"%>" style="padding-bottom:10px">                                                 
                          <logic:iterate name="beanA" property="reports" id="beanR" type="com.form.tasks.report.FReport">   
                            <ul class="ulClass">
                              <li>
                                <strong> <bean:message key="report.timeReport.caption" bundle="<%=interfaces%>"/> :</strong>
                              <bean:write name="beanR" property="timeReport"/>
                              </li>
                               <li>
                              <strong>  <bean:message key="report.name.caption" bundle="<%=interfaces%>"/>:</strong>
                              <bean:write name="beanR" property="report"/>
                               <li>
                              <strong> <bean:message key="problem.complate" bundle="<%=interfaces%>"/>:</strong>
                              <bean:write name="beanR" property="complate"/> %
                             </li>                                                              
                                         <logic:notEqual name="beanR" property="fileName" value="">
                                          <li>
                                              <strong>  <bean:message key="doc.file.caption" bundle="<%=interfaces%>"/>: </strong>                                                                   
                                             <a href="javascript:post('report',anchor + ':_SAVE:id:<%=beanR.getId()%>');remove('report',anchor)"><bean:write name="beanR" property="fileName"/></a>                                                                    
                                           </logic:notEqual>  
                             </ul> 
                             </logic:iterate>
                        </td>
                    </tr> 
                                            
                                
          </logic:iterate>
          <% i++;%>
                 </logic:iterate>
        </logic:present>
          
        </table>  
  <table align="right">
      <tr>
            <td> <jsp:include page="/messages/view/paging.jsp"/>  </td>
      </tr>
  </table>
</div>
  
 
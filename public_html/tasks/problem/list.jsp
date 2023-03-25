 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>      
  <table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
    <TBODY>
    
        <TR>
            <th  width="5%" ><bean:message key="problem.stt" bundle="<%=interfaces%>"/></th>
            <th  style="cursor: pointer;" width="3%" nowrap>&nbsp;<img src="<%=contextPath%>/images/attach.gif" /></th>
            <th><bean:message key="problem.title" bundle="<%=interfaces%>"/></th>          
            <th  width="7%" nowrap> <bean:message key="problem.fromdate" bundle="<%=interfaces%>"/> </th>
            <th  width="7%" nowrap> <bean:message key="problem.todate" bundle="<%=interfaces%>"/> </th>
            <th  width="5%" nowrap> <bean:message key="problem.complate.stop.complate.caption" bundle="<%=interfaces%>"/></th>
            <th  nowrap></th>
        </tr>       
        <bean:define name="problem" property="type" id="type" type="java.lang.Integer"/>
        <logic:present name="BProblems">
         <bean:define name="BProblems" id="beans" type="com.form.FBeans"/>
        <%  int i = beans.getFirstRecord();%>
           <logic:iterate name="BProblems" id="bean" type="com.form.tasks.problem.FProblem"> 
           <%i++;%>
            <tr class="content">
                <td  style="text-align:center;" ><span class="index"><%=i-1%>.</span></td>
                 <td  align="center">                  
                     <logic:notEqual name="bean" property="fileName" value="">
                        <img style="cursor: pointer;" border="0" title="<bean:message key="doc.file.caption" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/attach.gif" onclick="javascript:post('problem',anchor + ':_GET:problemId:<%=bean.getProblemId()%>');remove('problem',anchor)">
                     </logic:notEqual>
                </td>
                
                <td   style="text-align: left">                    
                 <div style="float:left">    
                 
                 <a  href="javascript:showDetail('formProblem','showId',':_VIEW_DETAIL:problemId:<%=bean.getProblemId()%>',<%=bean.getProblemId()%>)" > 
                            <logic:equal name="bean" property="stop" value="1"> 
                              <span style="color: Red;"><bean:write name="bean" property="title"/></span>   
                            </logic:equal>   
                            <logic:equal name="bean" property="stop" value="0">
                                 <bean:write name="bean" property="title"/>
                            </logic:equal>
                 </a>
                 <logic:equal name="bean" property="checkHaveReport" value="1">
                 <img title="<bean:message key="problem.have.review" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/bookmark.gif"/>
                 </logic:equal>
                    </div>   
                 
                     <div style="text-align: right;padding-top: 2px;padding-bottom: 2px;padding-right: 4px;float:right">    
                        <input style="font-size: 9px;" type="button" value="Xem &#253; ki&#7871;n"  onclick="javascript:showDetail('formProblem','showId',':_REVIEW_REPORT:problemId:<%=bean.getProblemId()%>:assignId:<%=bean.getAssignId()%>:worker:<%=bean.getWorker()%>',<%=bean.getProblemId()%>)" />                          
                        <logic:notEqual name="bean" property="complete" value="100"> 
                             <% if (bean.getCreator()==(int) me.getId()){%>  
                                <input style="font-size: 9px;" type="button" value="<bean:message key="report.review.caption" bundle="<%=interfaces%>"/>"   onclick="javascript:showReport('showId<%=bean.getProblemId()%>',<%=bean.getAssignId()%>,<%=bean.getProblemId()%>)"/>                          
                            <%}%>
                             
                           <logic:equal name="bean" property="stop" value="1">                          
                                <input style="font-size: 9px;" type="button" value="<bean:message key="problem.unStop.caption" bundle="<%=interfaces%>"/>"  onclick="javascript:postAjax('problem','MainProblem',anchor + ':_TASK_PROBLEM_STOP:problemId:<%=bean.getProblemId()%>:stop:0');messageImg('MainProblem');"/>
                            </logic:equal>   
                            <logic:equal name="bean" property="stop" value="0">
                                <input style="font-size: 9px;" type="button" value="<bean:message key="problem.stop.caption" bundle="<%=interfaces%>"/>"  onclick="javascript:postAjax('problem','MainProblem',anchor + ':_TASK_PROBLEM_STOP:problemId:<%=bean.getProblemId()%>:stop:1');messageImg('MainProblem');"/>
                            </logic:equal>                   
                        </logic:notEqual>
                    </div>            
                </td>                
                <td  style="padding-right: 4px;"><bean:write name="bean" property="fromDate"/></td>
                <td  style="padding-right: 4px;"><bean:write name="bean" property="toDate"/></td>
                <td  style="text-align: center">                   
                     
                        <strong><bean:write name="bean" property="complete"/>%</strong>
                     
                </td>
                <td  style="text-align:center"  width="8%" nowrap>
                  <logic:notEqual name="bean" property="complete" value="100"> 
                  <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/newImages/edit.gif" alt="<bean:message key="danhmuc.button.sua" bundle="<%=interfaces%>"/>" onClick="javascript:post('problem',anchor + ':_PREPARED_EDIT:problemId:<%=bean.getProblemId()%>')">                                    
                 <% if (bean.getCreator()==(int) me.getId()){%>
                       <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/newImages/i_17.gif" alt="<bean:message key="danhmuc.button.xoa" bundle="<%=interfaces%>"/>" onClick="javascript:postAjax('problem','MainProblem',anchor + ':_DELETE:problemId:<%=bean.getProblemId()%>');messageImg('MainProblem');">                        
                  <%}%>                  
                  </logic:notEqual>
                </td>               
            </tr>
            <tr>
                <td colspan="7" valign="top"  id="showId<%=bean.getProblemId()%>"></td>
            </tr> 
                    <tr>
                      <td colspan="7" valign="top"    nowrap  style="padding-right:4px;padding-bottom:0px">
                             <bean:define name="bean" property="asigns" id="beansrecv" type="com.form.FBeans"/>
                             <logic:iterate name="bean" property="asigns" id="beanAssign" type="com.form.tasks.problem.FProblem">  
                             <%String worker2=beanAssign.getWorker()+""; %>
                             <logic:equal name="beanAssign" property="incharge" value="<%=worker2%>">    
                             <table width="100%" cellpadding="0" cellspacing="0" border="0" >
                                        <tr>
                                            <td width="100%" align="left" style="margin:0px">
                                       <ul class="MainUlTask" style="border:0px;margin:0px" >
                                        <li>
                                             <span  class='<%=beanAssign.getWorker()==beanAssign.getIncharge()?"colorIncharge":""%>' style="cursor: pointer;"   onclick="javascript:showDetail('formProblem','detailTask',':_VIEW_REPORT:problemId:<%=bean.getProblemId()%>:readed:1:assignId:<%=beanAssign.getAssignId()%>:worker:<%=beanAssign.getWorker()%>',<%=beanAssign.getAssignId()%>)"   >
                                               <bean:write name="beanAssign" property="timeCreateAssign"/> : 

                                               <logic:equal name="bean" property="stop" value="1">    
                                               <span style="color: Red;"><bean:write name="beanAssign" property="workerName"/></span>
                                               </logic:equal>

                                               <logic:notEqual name="bean" property="stop" value="1">  
                                                          <logic:equal name="beanAssign" property="stop" value="1"> 
                                                            <span style="color: Red;"><bean:write name="beanAssign" property="workerName"/></span>                                                      
                                                          </logic:equal>
                                                          <logic:notEqual name="beanAssign" property="stop" value="1"> 
                                                            <bean:write name="beanAssign" property="workerName"/>                                                      
                                                          </logic:notEqual>
                                               </logic:notEqual>
                                                                                            
                                             </span>
                                             
                                             <% if (beanAssign.getWorker()!=(int) me.getId()){%>  
                                             <logic:equal name="beanAssign" property="checkHaveReport" value="1">
                                                 <img title="<bean:message key="problem.have.review" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/bookmark.gif"/>
                                             </logic:equal>
                                             <%}%>
                                             
                                        (
                                        <logic:equal name="beanAssign" property="accepted" value="1">
                                        <bean:write name="beanAssign" property="completeAssign"/>%
                                        </logic:equal>
                                        <logic:notEqual name="beanAssign" property="accepted" value="1">
                                        <logic:notEqual name="bean" property="stop" value="1">  
                                                          <logic:equal name="beanAssign" property="stop" value="1"> 
                                                          <img title="<bean:message key="problem.have.review" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/bookmark.gif"/>
                                                          </logic:equal>
                                                          <logic:notEqual name="beanAssign" property="stop" value="1">
                                                            <span style="color:Red" alt="<bean:message key="problem.unAcception.caption" bundle="<%=interfaces%>"/>">Ch&#432;a nh&#7853;n</span>                  
                                                          </logic:notEqual>
                                        </logic:notEqual>
                                        
                                        </logic:notEqual>
                                        )
                                        </li>
                                    </ul>
                                    </td>
                                    <td align="right" nowrap>
                                    <%if(beansrecv.size()>1){%>
                                        <a href="javascript:hideshow('listRecvId<%=bean.getProblemId()%>')">
                                        <span style="color:blue"><bean:message key="problem.list.recv.worder" bundle="<%=interfaces%>"/>
                                        (<%=beansrecv.size()%>)
                                        </span>
                                        </a>  
                                    <%}%>
                                    </td>
                                    <td width="5px" align="right">
                                        <logic:notEqual name="bean" property="complete" value="100"> 
                                        <logic:equal name="problem" property="type" value="0">
                                             
                                                  <logic:equal name="beanAssign" property="stop" value="0"> 
                                                   <input style="font-size: 9px;" type="button" value="<bean:message key="problem.stop.caption" bundle="<%=interfaces%>"/>"  onclick="javascript:postAjax('problem','MainProblem',anchor + ':_TASK_ASSIGN_STOP:assignId:<%=beanAssign.getAssignId()%>:stop:1');messageImg('MainProblem');"/>
                                                  </logic:equal>  
                                                  
                                                  <logic:notEqual name="beanAssign" property="stop" value="0"> 
                                                   <input style="font-size: 9px;" type="button" value="<bean:message key="problem.unStop.caption" bundle="<%=interfaces%>"/>"  onclick="javascript:postAjax('problem','MainProblem',anchor + ':_TASK_ASSIGN_STOP:assignId:<%=beanAssign.getAssignId()%>:stop:0');messageImg('MainProblem');"/>                                                
                                                    
                                                  </logic:notEqual>  
                                                </logic:equal>
                                        </logic:notEqual>  
                                    </td>
                                </tr>
                                <tr>
                                 <td colspan="3" valign="top"  id="detailTask<%=beanAssign.getAssignId()%>"></td>
                                </tr>
                            </table>
                             </logic:equal>
                             </logic:iterate>
                             
                             <div id="listRecvId<%=bean.getProblemId()%>" style="display:none" >
                             <logic:iterate name="bean" property="asigns" id="beanAssign" type="com.form.tasks.problem.FProblem">  
                             <%String worker1=beanAssign.getWorker()+"";%>
                             <logic:notEqual name="beanAssign" property="incharge" value="<%=worker1%>">    
                             <table width="100%" cellpadding="0" cellspacing="0" >
                                        <tr>
                                            <td width="70%" align="left" style="margin:0px">
                                       <ul class="MainUlTask" style="border:0px;margin:0px" >
                                        <li>
                                             <span  class='<%=beanAssign.getWorker()==beanAssign.getIncharge()?"colorIncharge":""%>' style="cursor: pointer;"   onclick="javascript:showDetail('formProblem','detailTask',':_VIEW_REPORT:problemId:<%=bean.getProblemId()%>:readed:1:assignId:<%=beanAssign.getAssignId()%>:worker:<%=beanAssign.getWorker()%>',<%=beanAssign.getAssignId()%>)"   >                                                                                                                                                                  
                                               <bean:write name="beanAssign" property="timeCreateAssign"/> : 

                                               <logic:equal name="bean" property="stop" value="1">    
                                               <span style="color: Red;"><bean:write name="beanAssign" property="workerName"/></span>
                                               </logic:equal>

                                               <logic:notEqual name="bean" property="stop" value="1">  
                                                          <logic:equal name="beanAssign" property="stop" value="1"> 
                                                            <span style="color: Red;">  <bean:write name="beanAssign" property="workerName"/></span>                                                      
                                                          </logic:equal>
                                                          <logic:notEqual name="beanAssign" property="stop" value="1"> 
                                                            <bean:write name="beanAssign" property="workerName"/>                                                      
                                                          </logic:notEqual>
                                               </logic:notEqual>
                                                                                            
                                             </span>
                                             <% if (beanAssign.getWorker()!=(int) me.getId()){%>  
                                             <logic:equal name="beanAssign" property="checkHaveReport" value="1">
                                                    <img title="<bean:message key="problem.have.review" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/bookmark.gif"/>
                                             </logic:equal>
                                             <%}%>
                                        (
                                        <logic:equal name="beanAssign" property="accepted" value="1">
                                        <bean:write name="beanAssign" property="completeAssign"/>%
                                        </logic:equal>
                                        <logic:notEqual name="beanAssign" property="accepted" value="1">
                                        <logic:notEqual name="bean" property="stop" value="1">  
                                                          <logic:equal name="beanAssign" property="stop" value="1"> 
                                                            <span style="color: Red;">&#272;&#227; b&#7883; d&#7915;ng</span>
                                                          </logic:equal>
                                                          <logic:notEqual name="beanAssign" property="stop" value="1"> 
                                                            <span style="color:Red" alt="<bean:message key="problem.unAcception.caption" bundle="<%=interfaces%>"/>">Ch&#432;a nh&#7853;n</span>
                                                          </logic:notEqual>
                                         </logic:notEqual>
                                               
                                        
                                        </logic:notEqual>
                                        )
                                        </li>
                                    </ul>
                                    
                                    
                                    </td>
                                    <td>
                                        
                                    </td>
                                    <td width="10px">
                                    <logic:notEqual name="bean" property="complete" value="100"> 
                                    <logic:equal name="problem" property="type" value="0">
                                         
                                              <logic:equal name="beanAssign" property="stop" value="0"> 
                                               <input style="font-size: 9px;" type="button" value="<bean:message key="problem.stop.caption" bundle="<%=interfaces%>"/>"  onclick="javascript:postAjax('problem','MainProblem',anchor + ':_TASK_ASSIGN_STOP:assignId:<%=beanAssign.getAssignId()%>:stop:1');messageImg('MainProblem');"/>
                                              </logic:equal>  
                                              
                                              <logic:notEqual name="beanAssign" property="stop" value="0"> 
                                               <input style="font-size: 9px;" type="button" value="<bean:message key="problem.unStop.caption" bundle="<%=interfaces%>"/>"  onclick="javascript:postAjax('problem','MainProblem',anchor + ':_TASK_ASSIGN_STOP:assignId:<%=beanAssign.getAssignId()%>:stop:0');messageImg('MainProblem');"/>                                                
                                                
                                              </logic:notEqual>  
                                            </logic:equal>
                                    </logic:notEqual>  
                                    </td>
                                </tr>
                                 <td colspan="3" valign="top"  id="detailTask<%=beanAssign.getAssignId()%>"></td>
                            </table>
                             </logic:notEqual>
                             </logic:iterate>
                             
                             
                             </div>
                    </td>
            </tr>
         
          </logic:iterate>
        </logic:present>
    </tbody>
  </table>     
 <logic:present name="BProblems"> 
<table width="100%">
    <td align="left"> <Strong><bean:message key="page.caption.total" bundle="<%=interfaces%>"/> <bean:write name="beans" property="totalRows"/></strong>        
                 <% String params = anchor + ":_SEARCH_PAGE";%></td>
        <td align="right">
            <jsp:include page="/paging.jsp">
            <jsp:param name="BEANS" value="BProblems"/>
            <jsp:param name="PARAMS" value="<%=params%>"/>
            <jsp:param name="POSITION" value="MainProblem"/>
            <jsp:param name="FORM" value="problem"/>
            <jsp:param name="METHOD" value="postAjax"/>
            </jsp:include>   
    </td>
</table>
 </logic:present>  

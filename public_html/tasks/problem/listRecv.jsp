 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
   <table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
    <TBODY>
        <TR>
            <th  width="5%" ><bean:message key="problem.stt" bundle="<%=interfaces%>"/></th>
            <th  style="cursor: pointer;" width="10px" ><img src="<%=contextPath%>/images/attach.gif" /></th>
            <th><bean:message key="problem.title" bundle="<%=interfaces%>"/></th>                    
            <th  width="7%" nowrap> <bean:message key="problem.fromdate" bundle="<%=interfaces%>"/> </th>
            <th  width="7%" nowrap> <bean:message key="problem.todate" bundle="<%=interfaces%>"/> </th>
            <th  width="5%" nowrap> <bean:message key="problem.complate.stop.complate.caption" bundle="<%=interfaces%>"/></th>           
            <th  width="8%" nowrap><bean:message key="problem.detail.caption" bundle="<%=interfaces%>"/></th>
        </tr>       
     
        <bean:define name="problem" property="type" id="type" type="java.lang.Integer"/>
        <logic:present name="BProblems">
         <bean:define name="BProblems" id="beans" type="com.form.FBeans"/>
        <%  int i = beans.getFirstRecord();%>
           <logic:iterate name="BProblems" id="bean" type="com.form.tasks.problem.FProblem"> 
           <%i++;%>
           <tr class="content">
                <td  style="text-align:center;" ><span class="index"><%=i-1%>.</span></td>
                 <td  style="padding-left:0px" >      
                     <logic:notEqual name="bean" property="fileName" value="">
                        <img style="cursor: pointer;padding-left:-4px"  border="0" title="<bean:message key="doc.file.caption" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/attach.gif" onclick="javascript:post('problem',anchor + ':_GET:problemId:<%=bean.getProblemId()%>');remove('problem',anchor)">
                     </logic:notEqual>  
                </td>
                
                <td   style="text-align: left">                    
                          <a  href="javascript:showDetail('formProblem','showId',':_VIEW_DETAIL:problemId:<%=bean.getProblemId()%>',<%=bean.getProblemId()%>)" > 
                               <logic:equal name="bean" property="stopAssign" value="1"> 
                                     <span style="color: Red;"> 
                                        <bean:write name="bean" property="title"/>
                                    </span>
                               </logic:equal> 
                               <logic:equal name="bean" property="stopAssign" value="0">                                
                                         <logic:equal name="bean" property="readed" value="1"> 
                                            <bean:write name="bean" property="title"/>
                                        </logic:equal>
                                        <logic:notEqual name="bean" property="readed" value="1"> 
                                           <strong><bean:write name="bean" property="title"/></strong>
                                        </logic:notEqual>  
                             </logic:equal> 
                        </a>    
                </td>                           
                <td  style="padding-right: 4px;"><bean:write name="bean" property="fromDate"/></td>
                <td  style="padding-right: 4px;"><bean:write name="bean" property="toDate"/></td>
                <td  style="text-align: center">
                    <strong> <bean:write name="bean" property="completeAssign"/>%</strong>
                </td>          
               
                <td  style="text-align:center"  width="8%" nowrap>                 
                  <logic:equal name="bean" property="accepted" value="1">  
                        <logic:notEqual name="bean" property="stop" value="1">  
                             <logic:notEqual name="bean" property="stopAssign" value="1">  
                                <div style="padding:2px">              
                                   <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/newImages/edit.gif" alt="<bean:message key="menu.top.rule.tasks.caption" bundle="<%=interfaces%>"/>" onClick="javascript:post('problem',anchor + ':_PREPARED_EDIT:problemId:<%=bean.getProblemId()%>')">                                    
                                </div>
                            </logic:notEqual>
                         </logic:notEqual>
                  </logic:equal>
                </td>               
            </tr>
            <tr>
                <td colspan="8" id="showId<%=bean.getProblemId()%>" ></td>   
            </tr>
            <tr>
                      <td colspan="8" valign="top"  nowrap style="padding:0px;border:1px solid #CCCCCC;">  
                                    <!--Nguoi giao viec!-->
        <table width="100%" cellpadding="0" cellspacing="0" >
        <tr><td>
        <ul class="MainUl" style="border:0px;margin-top:0px;margin-bottom:0px"><li>                                                                                                  
                                <bean:write name="bean" property="timeCreate"/> :                                                   
                                <span class="colorCreatorSign" onclick="javascript:showDetail('formProblem','detailTask',':_REVIEW_REPORT:problemId:<%=bean.getProblemId()%>:readed:1:assignId:<%=bean.getAssignId()%>:worker:<%=bean.getWorker()%>',<%=bean.getProblemId()%>)" >
                                    <bean:message key="rules.assignEmp.caption" bundle="<%=interfaces%>"/>  
                                      (<bean:write name="bean" property="nameCreator"/>)  
                                </span>
                                <logic:equal name="bean" property="checkHaveReview" value="1" >
                                        <img title="<bean:message key="problem.have.review" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/bookmark.gif"/>
                                </logic:equal>
                                
        </li></ul>
        </td></tr>
        <tr><td id="detailTask<%=bean.getProblemId()%>"></td></tr>
                               <!--End Nguoi giao viec!-->
                               
                               <!--Begin Me!-->
                                <bean:define name="bean" property="asigns" id="beansrecv" type="com.form.FBeans"/>
                               <logic:iterate name="bean" property="asigns" id="beanAssign" type="com.form.tasks.problem.FProblem">  
                               <% if (beanAssign.getWorker()==(int) me.getId()){%>  
                               <tr><td width="100%">
                               <table width="100%" cellpadding="0" cellspacing="0" >
                               <tr><td width="100%">
                               <ul class="MainUl" style="border:0px;margin-top:0px;margin-bottom:0px"><li>
                                            
                                            <span  class='<%=beanAssign.getWorker()==beanAssign.getIncharge()?"colorIncharge":""%>' >
                                            <bean:write name="beanAssign" property="timeCreateAssign"/> : 
                                            
                                            <% if (beanAssign.getWorker()==(int) me.getId()){%>  
                                            <a  href="javascript:showDetail('formProblem','detailTask',':_VIEW_REPORT:problemId:<%=bean.getProblemId()%>:readed:1:assignId:<%=beanAssign.getAssignId()%>:worker:<%=beanAssign.getWorker()%>','<%=bean.getProblemId()%>@<%=beanAssign.getWorker()%>')" > 
                                            <%}%>
                                            
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
                                           
                                           <% if (beanAssign.getIncharge()==(int) me.getId()){%>  
                                           </a>                         
                                           <%}%>
                                           &nbsp;&nbsp;&nbsp;
                                             <logic:equal name="beanAssign" property="accepted" value="1">       
                                                <Strong>(<bean:write name="beanAssign" property="completeAssign"/>%)</strong>
                                             </logic:equal>
                                                 <% if (beanAssign.getWorker()==(int) me.getId()){%>  

                                                              <logic:equal name="bean" property="accepted" value="1">  
                                                                    <logic:notEqual name="bean" property="stop" value="1">  
                                                                         <logic:notEqual name="bean" property="stopAssign" value="1">  
                                                                              <input style="width:50px;font-size: 9px;" type="button" value="<bean:message key="problem.report" bundle="<%=interfaces%>"/>"  onclick="javascript:checkedInnerHtml();showReport('detailTask<%=bean.getProblemId()%>@<%=beanAssign.getWorker()%>',<%=bean.getAssignId()%>,<%=bean.getProblemId()%>)"/>
                                                                        </logic:notEqual>
                                                                     </logic:notEqual>
                                                              </logic:equal>
                                                              
                                                              <logic:equal name="bean" property="accepted" value="0">                              
                                                                   <logic:equal name="bean" property="stopAssign" value="0">  
                                                                     <span style="color:red;cursor:pointer;" onclick="javascript:postAjax('problem','MainProblem',anchor + ':_ACTIVE:problemId:<%=bean.getProblemId()%>:accepted:<%=bean.getAccepted()>0?0:1%>:assignId:<%=bean.getAssignId()%>');messageImg('MainProblem');">(Ti&#7871;p nh&#7853;n)</span>
                                                                   </logic:equal>                             
                                                              </logic:equal>
                                                              
                                                <%}else{%>
                                                       <logic:notEqual name="bean" property="stop" value="1">  
                                                              <logic:equal name="beanAssign" property="stop" value="1"> 
                                                                <span style="color: Red;">
                                                                    <Strong>(B&#7883; D&#7915;ng vi&#7879;c)</strong>
                                                                </span>
                                                              </logic:equal>
                                                       </logic:notEqual>
                                                        <logic:equal name="beanAssign" property="stop" value="0"> 
                                                         <logic:equal name="beanAssign" property="accepted" value="0">       
                                                         <span style="color:red">(<bean:message key="problem.unAcception.caption" bundle="<%=interfaces%>"/>)</span>
                                                         </logic:equal>
                                                        </logic:equal>
                                                <%}%>
                                             </span>
                              </li></ul>
                              
                              </td>
                              <td width="100px" nowrap>
                              <%if(beansrecv.size()>1){%>
                                        <a href="javascript:hideshow('listRecvId<%=bean.getProblemId()%>')">
                                        <span style="color:blue"><bean:message key="problem.list.recv.worder" bundle="<%=interfaces%>"/>
                                        (<%=beansrecv.size()%>)
                                        </span>
                                        </a>  
                                    <%}%>
                              </td>
                              </tr>
                              </table>
                              </td></tr>
                              <tr><td  id="detailTask<%=bean.getProblemId()%>@<%=beanAssign.getWorker()%>"></td></tr>
                              <%}%>
                              </logic:iterate>
         
         <tr><td style="text-align:left" align="left" >   
                         <div id="listRecvId<%=bean.getProblemId()%>" style="display:none;text-align:left" align="left">
                         <table align="left" border="0px" width="100%" cellpadding="0" cellspacing="0">
                         <logic:iterate name="bean" property="asigns" id="beanAssign" type="com.form.tasks.problem.FProblem">  
                          <% if (beanAssign.getWorker()!=(int) me.getId()){%>
                          <tr><td align="left" style="text-align:left">
                               <ul class="MainUl" style="border:0px;margin-top:0px;margin-bottom:0px"><li style="padding-top:0px;padding-bottom:0px">
                                             <span  class='<%=beanAssign.getWorker()==beanAssign.getIncharge()?"colorIncharge":""%>' >
                                               <bean:write name="beanAssign" property="timeCreateAssign"/> : 
                                            
                                            <% if (beanAssign.getIncharge()==(int) me.getId()){%>  
                                            <a  href="javascript:showDetail('formProblem','detailTask',':_VIEW_REPORT:problemId:<%=bean.getProblemId()%>:readed:1:assignId:<%=beanAssign.getAssignId()%>:worker:<%=beanAssign.getWorker()%>','<%=bean.getProblemId()%>@<%=beanAssign.getWorker()%>')" > 
                                            <%}%>
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
                                           <% if (beanAssign.getIncharge()==(int) me.getId()){%>  
                                           </a>                         
                                           &nbsp;&nbsp;&nbsp;
                                           <%}%>
                                           
                                             <logic:equal name="beanAssign" property="accepted" value="1">       
                                                <Strong>(<bean:write name="beanAssign" property="completeAssign"/>%)</strong>
                                             </logic:equal>
                                                 <% if (beanAssign.getWorker()==(int) me.getId()){%>  

                                                              <logic:equal name="bean" property="accepted" value="1">  
                                                                    <logic:notEqual name="bean" property="stop" value="1">  
                                                                         <logic:notEqual name="bean" property="stopAssign" value="1">  
                                                                               
                                                                               <input style="width:50px;font-size: 9px;" type="button" value="<bean:message key="problem.report" bundle="<%=interfaces%>"/>"  onclick="javascript:checkedInnerHtml();showReport('showId<%=bean.getProblemId()%>',<%=bean.getAssignId()%>,<%=bean.getProblemId()%>)"/>
                                                                               
                                                                        </logic:notEqual>
                                                                     </logic:notEqual>
                                                              </logic:equal>
                                                              
                                                              <logic:equal name="bean" property="accepted" value="0">                              
                                                                   <logic:equal name="bean" property="stopAssign" value="0">  
                                                                     <span style="color:red;cursor:pointer;" onclick="javascript:postAjax('problem','MainProblem',anchor + ':_ACTIVE:problemId:<%=bean.getProblemId()%>:accepted:<%=bean.getAccepted()>0?0:1%>:assignId:<%=bean.getAssignId()%>');messageImg('MainProblem');">(Ti&#7871;p nh&#7853;n)</span>
                                                                   </logic:equal>                             
                                                              </logic:equal>
                                                              
                                                <%}else{%>
                                                       <logic:notEqual name="bean" property="stop" value="1">  
                                                              <logic:equal name="beanAssign" property="stop" value="1"> 
                                                                <span style="color: Red;">
                                                                    <Strong>(B&#7883; D&#7915;ng vi&#7879;c)</strong>
                                                                </span>
                                                              </logic:equal>
                                                       </logic:notEqual>
                                                        <logic:equal name="beanAssign" property="stop" value="0"> 
                                                         <logic:equal name="beanAssign" property="accepted" value="0">       
                                                         <span style="color:red">(<bean:message key="problem.unAcception.caption" bundle="<%=interfaces%>"/>)</span>
                                                         </logic:equal>
                                                        </logic:equal>
                                                <%}%>
                                             </span>
                              </li></ul> 
                           </td></tr>
                           <tr><td align="left" style="text-align:left" id="detailTask<%=bean.getProblemId()%>@<%=beanAssign.getWorker()%>" style="display:block" >
                           </td></tr>
                           <%}%>
                           </logic:iterate>
                           </table>
          </div>
          </td>
          </tr>
          </table>
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



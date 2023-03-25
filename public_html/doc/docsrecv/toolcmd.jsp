<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp"%>
<logic:notPresent name="tracking" >
<%String CHECK_RULE_DOCSRECV=com.lib.AppConfigs.CHECK_RULE_DOCSRECV;%>
<logic:notEqual name="BDocsrecvs" property="forYouId" value="0">
        <%CHECK_RULE_DOCSRECV="BRuleForYouRecv";%>
</logic:notEqual>
<bean:define name="BDocsrecvs" id="beanRecv"  type="com.form.doc.docsrecv.FDocsrecv" />
<bean:define name="<%=CHECK_RULE_DOCSRECV%>" id="beanRule"  type="com.form.doc.assign.FDocAssign" />
<bean:define name="docsrecv" property="obServer"  id="obServer"  type="java.lang.Integer" />
<html:hidden name="BDocsrecvs" property="changeId" /> 
  <%          
   int open = 0;
    if (obServer!=1){ 
       if (beanRecv.getViews()==0 || beanRecv.getViews()==-1 || (beanRecv.getViews()>0 && beanRule.getCheckExcuteNotView()!=1)){         
           if (beanRecv.getCheckForYou()==1 && beanRule.getCheckForyouAssign()==1){
                open = 1;
           }else  if (beanRecv.getCheckForYou()==1 && beanRule.getCheckForyouAssign()==0){
                open = 0;
           }else {
                if (beanRecv.getReaded()==1 && beanRecv.getStatusId()==0){
                        open = 1;
                }else if (beanRecv.getReaded()==0 && beanRecv.getStatusId()!=0 && beanRecv.getStatusId()!=-1 && (beanRule.getCheckReply()==1 || beanRule.getCheckSelectRecv()==1 || beanRule.getCheckDirect()==1)){
                        open = 1;
                }else if (beanRecv.getReaded()==1 && beanRecv.getStatusId()!=-1 && beanRule.getCheckReadOnly()==0 ){
                        open = 1;
                }
            }
       }
       
       if (beanRecv.getForYouId()>0 && beanRule.getActiveForyou()==0){
            open = 0;
       }
   }
   
%>
            <%if(open==1){int openHr=0; %>
                        <logic:equal name="<%=CHECK_RULE_DOCSRECV%>" property="checkReply" value="1">
                        <%openHr=1;%>
                        </logic:equal>
                        <logic:equal name="<%=CHECK_RULE_DOCSRECV%>" property="checkSelectRecv" value="1">
                        <%openHr=1;%>
                        </logic:equal>
                        <logic:equal name="<%=CHECK_RULE_DOCSRECV%>" property="checkDirect" value="1">
                        <%openHr=1;%>
                        </logic:equal>
                        <%if(beanRecv.getStatusId()!=-1 && beanRule.getCheckStore()!=-2 && beanRecv.getReaded()==0){%>
                        <%openHr=1;%>
                        <%}%>
        <%if(openHr==1){%>
              <hr size="0" style="width:100%">
        <%}%>
                     <div style="padding-left:20px;magin-left:20px;padding-bottom:10px;float:left;" class="toolsDoc" align="left">  
                                           <% String wrongWay="wrongWayRecv(" + beanRecv.getId() + " )";%>
                                        <html:button property="_CREATE_REVIEW" styleClass="button" onclick="<%=wrongWay%>">                                                                                        
                                             <bean:message key="doc.form.wrongWay" bundle="<%=interfaces%>"/>
                                        </html:button>  
                                        <logic:equal name="<%=CHECK_RULE_DOCSRECV%>" property="checkReply" value="1">                                        
                                                    <% String insertReview="insertReviewPost("+ beanRecv.getId() +","+  beanRecv.getForYouId() + "," + 0 + ")";%>
                                                    <html:button property="_CREATE_REVIEW" styleClass="button" onclick="<%=insertReview%>" >                                                                                        
                                                        <logic:notEqual name="<%=CHECK_RULE_DOCSRECV%>" property="checkRecaption" value="" >
                                                                  <bean:write name="<%=CHECK_RULE_DOCSRECV%>" property="checkRecaption" />
                                                        </logic:notEqual>
                                                        <logic:equal name="<%=CHECK_RULE_DOCSRECV%>" property="checkRecaption" value="" >
                                                                 <bean:message key="command.doc.review.cmd.send.caption" bundle="<%=interfaces%>"/>   
                                                        </logic:equal>
                                                    </html:button>
                                        </logic:equal>        
                                
                                    <logic:equal name="<%=CHECK_RULE_DOCSRECV%>" property="checkSelectRecv" value="1">
                                    <% String insertReview="insertReviewPost("+ beanRecv.getId() + "," + beanRecv.getForYouId() + "," + 1 + ")";%>
                                        <a class="modal-button" href="docsrecv<%=extention%>?<%=anchor%>=_PREPARE_CHOSE_RECV_FROM_INFOR&id=<%=beanRecv.getId()%>&forYouId=<%=beanRecv.getForYouId()%>" rel="{handler: 'iframe', size: {x:390, y: 345},bookmark:'if(SqueezeBox.presets.target==0){<%=insertReview%>;}'}">
                                                <html:button property="_DOC_ASSIGN_CREATE" styleClass="button" >
                                                  <bean:message key="doc.header.selectRecv.caption" bundle="<%=interfaces%>"/>
                                                </html:button>
                                        </a>
                                    </logic:equal>
                                            
                                    <logic:equal name="<%=CHECK_RULE_DOCSRECV%>" property="checkDirect" value="1">
                                            <% String assignDirect="assignDirect(this.form.departmentId,"+ beanRecv.getId() +","+ beanRecv.getForYouId() +")";%>
                                            <html:button property="_DOC_ASSIGN_CREATE" styleClass="button"  onclick="<%=assignDirect%>" >
                                               <logic:notEmpty name="<%=CHECK_RULE_DOCSRECV%>" property="checkTcaption" >
                                                  <bean:write name="<%=CHECK_RULE_DOCSRECV%>" property="checkTcaption" />
                                                </logic:notEmpty>
                                                <logic:empty name="<%=CHECK_RULE_DOCSRECV%>" property="checkTcaption" >
                                                     <bean:message key="doc.assign.direct.cmd.caption" bundle="<%=interfaces%>"/>  
                                                </logic:empty>
                                            </html:button>
                                            
                                            <logic:equal name="<%=CHECK_RULE_DOCSRECV%>" property="checkSelectDept" value="1">
                                                        <logic:present name="BDepartments">
                                                            <select name="departmentId">
                                                             <option value="0"><bean:message key="problem.dep.select.caption" bundle="<%=interfaces%>"/></option>
                                                              <logic:iterate name="BDepartments" id="beanDep" type="com.form.admin.departments.FDepartment">
                                                              <option value="<%=beanDep.getId()%>"><%=beanDep.getName()%></option>
                                                              </logic:iterate>
                                                            </select>
                                                        </logic:present>
                                            </logic:equal>
                                            
                                    </logic:equal>
                                   
                                   
                              </div>
                                <%}%> 
                            <%if (beanRecv.getObServer()!=1){%>
                             <logic:notPresent name="tracking" >                                        
                             <%if((beanRecv.getReaded()==0 && beanRule.getCheckStore()!=-2) || (beanRecv.getStatusId()!=-1 && beanRule.getCheckStore()!=-2)){
                                 int store = beanRule.getCheckStore()==-3?beanRule.getStatusId():beanRule.getCheckStore();
                             %>
                              <div style="float:right;" class="toolsDoc" align="right">                                                                                                           
                                                <% String updateStatus="javascript:if(messageEnd()) excuteEndDoc("+ beanRecv.getId() +","+  beanRecv.getForYouId() + "," + store + ")";%>
                                                <html:button styleClass="button" property="_PREPARED_CREATE"  onclick="<%=updateStatus%>" >
                                               <bean:message key="action.close" bundle="<%=interfaces%>"/>
                                                </html:button>
                                </div>
                            <%}%>   
                            </logic:notPresent>
                            <%}%>
                                
                               
 </logic:notPresent>

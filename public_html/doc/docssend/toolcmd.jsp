<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp"%>
<% String CHECK_RULE_SEND = com.lib.AppConfigs.CHECK_RULE_DOCSSEND ; %>
<logic:notEqual name="BDocssends" property="forYouId" value="0">        
       <% CHECK_RULE_SEND = "BRuleForYou"; %>
</logic:notEqual>
<bean:define name="BDocssends" property="id"  id="id" type="java.lang.Integer" />
<bean:define name="<%=CHECK_RULE_SEND%>"  id="beanRule" type="com.form.doc.assign.FDocAssign" />
<bean:define name="BDocssends"  id="beanSend" type="com.form.doc.docssend.FDocssend" />
<bean:define name="BDocssends" property="obServer"  id="obServer"  type="java.lang.Integer" />
<html:hidden name="BDocssends" property="changeId" /> 
<logic:notPresent name="tracking">
<%
    int open = 0;
    if (obServer!=1){ 
     if (beanSend.getViews()==0 || beanSend.getViews()==-1 || (beanSend.getViews()==1 && beanRule.getCheckExcuteNotView()!=1)){
        if (beanSend.getCheckForYou()==1 && beanRule.getCheckForyouAssign()==1){
            open = 1;
        }else  if (beanSend.getCheckForYou()==1 && beanRule.getCheckForyouAssign()==0){
            open = 0;
        }else {
       
            if (beanSend.getReaded()==1 && beanSend.getStatusId()==0){
                    open = 1;
            }else if (beanSend.getReaded()==0 && beanSend.getStatusId()!=0 && beanSend.getStatusId()!=-1 && (beanRule.getCheckReply()==1 || beanRule.getCheckSelectRecv()==1 || beanRule.getCheckDirect()==1)){
                    open = 1;
            }else if (beanSend.getReaded()==1 && beanSend.getStatusId()!=-1 && beanRule.getCheckReadOnly()==0 ){
                    open = 1;
            }
        }
    }
    
    if (beanSend.getForYouId()>0 && beanRule.getActiveForyou()==0){
        open = 0;
   }
   }
%>
       <%   if (open==1){ int openHr=0; %>
                        <logic:equal name="<%=CHECK_RULE_SEND%>" property="checkReply" value="1">
                        <%openHr=1;%>
                        </logic:equal>
                        <logic:equal name="<%=CHECK_RULE_SEND%>" property="checkSelectRecv" value="1">
                        <%openHr=1;%>
                        </logic:equal>
                        <logic:equal name="<%=CHECK_RULE_SEND%>" property="checkDirect" value="1">
                        <%openHr=1;%>
                        </logic:equal>
                        <%if(beanRule.getStatusId()!=-1 && beanRule.getCheckStore()!=-2 && beanSend.getReaded()==0){%>
                        <%openHr=1;%>
                        <%}%>
        <%if(openHr==1){%>
            <hr size="0" style="width:100%">
        <%}%>
        <div style="padding-left:20px;magin-left:20px;padding-bottom:5px;float:left;" class="toolsDoc" align="left">
                <logic:equal name="<%=CHECK_RULE_SEND%>" property="checkReply" value="1">
                <bean:message key="doc.form.wrongWay" bundle="<%=interfaces%>"/><input type="checkbox" name="wrongWay"  id="wrongWay" value="1" />
                            <% String insertReview="insertReviewPost("+ id +","+ beanRule.getForYouId() + "," + 0  +")";%>
                            <html:button property="_CREATE_REVIEW" styleClass="button" onclick="<%=insertReview%>" >                                                                                        
                                <logic:notEmpty name="<%=CHECK_RULE_SEND%>" property="checkRecaption" >
                                          <bean:write name="<%=CHECK_RULE_SEND%>" property="checkRecaption" />
                                </logic:notEmpty>
                                <logic:empty name="<%=CHECK_RULE_SEND%>" property="checkRecaption" >
                                         <bean:message key="command.doc.review.cmd.send.caption" bundle="<%=interfaces%>"/>   
                                </logic:empty>
                            </html:button>
                </logic:equal>        
            <logic:equal name="<%=CHECK_RULE_SEND%>" property="checkSelectRecv" value="1">
            <% String insertReview="insertReviewPost("+ id +","+beanRule.getForYouId() + "," + 1 +")";%>
                <a class="modal-button" href="docssend<%=extention%>?<%=anchor%>=_PREPARE_CHOSE_RECV_FROM_INFOR&id=<%=id%>" rel="{handler: 'iframe', size: {x:390, y: 345},bookmark:'if(SqueezeBox.presets.target==0){<%=insertReview%>;}'}">
                        <html:button property="_DOC_ASSIGN_CREATE" styleClass="button">
                          <bean:message key="doc.header.selectRecv.caption" bundle="<%=interfaces%>"/>
                        </html:button>
                </a>
            </logic:equal>
            
            <logic:equal name="<%=CHECK_RULE_SEND%>" property="checkDirect" value="1">
                    <% String assignDirect="assignDirect(this.form.departmentId,"+ id +","+beanRule.getForYouId()+")";%>
                    <html:button property="_DOC_ASSIGN_CREATE" styleClass="button"  onclick="<%=assignDirect%>" >
                       <logic:notEmpty name="<%=CHECK_RULE_SEND%>" property="checkTcaption" >
                          <bean:write name="<%=CHECK_RULE_SEND%>" property="checkTcaption" />
                        </logic:notEmpty>
                        <logic:empty name="<%=CHECK_RULE_SEND%>" property="checkTcaption" >
                                 <bean:message key="doc.assign.direct.cmd.caption" bundle="<%=interfaces%>"/>  
                        </logic:empty>
                    </html:button>
                    <logic:equal name="<%=CHECK_RULE_SEND%>" property="checkSelectDept" value="1">
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
                <%if (beanSend.getObServer()!=1){%>
                     <logic:notPresent name="tracking" >                                        
                     <%if((beanSend.getReaded()==0  && beanRule.getCheckStore()!=-2) || (beanSend.getStatusId()!=-1 && beanRule.getCheckStore()!=-2)){
                           int store = beanRule.getCheckStore()==-3?beanRule.getStatusId():beanRule.getCheckStore();
                     %>
                      <div style="float:right;" class="toolsDoc" align="right">                                                           
                               <% String updateStatus="javascript:if(messageEnd()) excuteEndDoc("+ beanSend.getId() +","+  beanSend.getForYouId() + "," + store + ")";%>
                               <html:button styleClass="button" property="_PREPARED_CREATE"  onclick="<%=updateStatus%>" >
                                <bean:message key="action.close" bundle="<%=interfaces%>"/>
                               </html:button>
                        </div>
                    <%}%>   
                </logic:notPresent>
           <%}%>  
</logic:notPresent>







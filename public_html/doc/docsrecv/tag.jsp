<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp"%>
<%String CHECK_RULE_DOCSRECV=com.lib.AppConfigs.CHECK_RULE_DOCSRECV;%>
<logic:notEqual name="BDocsrecvs" property="forYouId" value="0">
        <%CHECK_RULE_DOCSRECV="BRuleForYouRecv";%>
</logic:notEqual>
<bean:define name="BDocsrecvs" id="beanRecv"  type="com.form.doc.docsrecv.FDocsrecv" />
<bean:define name="<%=CHECK_RULE_DOCSRECV%>" id="beanRule"  type="com.form.doc.assign.FDocAssign" />
<%
int open=0; int hr=0;
if(beanRecv.getViews()==0){//la cong van xu ly
    open=1;
}else if(beanRule.getCheckExcuteNotView()==0){//xu ly de biet
    open=1;
}%>
<%if(open==1 && beanRecv.getObServer()!=1){%>
                     <table class="tab-review" cellpadding="0" cellspacing="0" width="100%" >
                       <tr valign="bottom">
                        <td  nowrap="nowrap" width="200px" >
                                    <%if(beanRule.getCheckViewReview()!=2 || beanRule.getCheckReview()==1 || (beanRecv.getCheckForYou()==1 && beanRule.getCheckForyouAssign()==1)){  hr=1;%>
                                    <span class="tabactive1"  onclick="mdotab(this,'_DOC_REVIEW:statusId:<%=beanRecv.getStatusId()%>:id:<%=beanRecv.getId()%>:forYouId:<%=beanRecv.getForYouId()%>','divReview');">
                                         <bean:message key="doc.assign.excute.caption" bundle="<%=interfaces%>"/>
                                    </span>
                                                
                                                <% if (beanRule.getCheckDocTranfer()==1 || beanRule.getCheckDocTranfer()==2){ %>
                                                |
                                                <%}%>
                                                
                                                <logic:equal name="<%=CHECK_RULE_DOCSRECV%>" property="checkDocTranfer" value="0">
                                                <logic:equal name="<%=CHECK_RULE_DOCSRECV%>" property="checkDetail" value="1">
                                                |
                                                </logic:equal>
                                                </logic:equal>
                                                
                                     
                                    <%}%>           
                                         <% if (beanRule.getCheckDocTranfer()==1 || beanRule.getCheckDocTranfer()==2){ %>
                                        <span class="tab1" onclick="mdotab(this,'_PREPARED_SAVE:statusId:<%=beanRecv.getStatusId()%>:id:<%=beanRecv.getId()%>:forYouId:<%=beanRecv.getForYouId()%>','divReview');">
                                            <bean:message key="docs.tab.header.trailer" bundle="<%=interfaces%>"/>
                                        </span>
                                                    <logic:equal name="<%=CHECK_RULE_DOCSRECV%>" property="checkDetail" value="1">
                                                    |
                                                    </logic:equal>   
                                      <%}%>    
                                <logic:equal name="<%=CHECK_RULE_DOCSRECV%>" property="checkDetail" value="1">
                                <span class="tab1"  onclick="mdotab(this,'_DETAIL:statusId:<%=beanRecv.getStatusId()%>:id:<%=beanRecv.getId()%>:forYouId:<%=beanRecv.getForYouId()%>','divReview');">
                                    <bean:message key="form.docs.detail" bundle="<%=interfaces%>"/>
                                </span>                            
                                </logic:equal>
                         </td>
    
                        <td nowrap="nowrap" style="text-align: right;" align="right">
                             
                                <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkDossier" value="1"> 
                                <%hr=1;%>
                                 <a class="modal-button" href="docsrecv<%=extention%>?<%=anchor%>=_CREATE_DOSS_RECV" rel="{handler: 'iframe', size: {x: 370, y: 260},bookmark:'postAjax(\'dossiers\',\'idDossiers\',anchor + \':_SAVE_NEW_RECV\')'}">   
                                <img style="border:0px;cursor: pointer;" src="<%=contextPath%>/images/i_13.gif" title="<bean:message key="dossiers.add.caption" bundle="<%=interfaces%>"/>">  
                                </a>
                                <Strong><bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/> : </strong>
                                <span id="idDossiers">
                                <html:select name="BDocsrecvs" property="dossierId" styleId="dossierId"  styleClass="fieldSelect" >
                                    <html:option value="0"> <bean:message key="form.dossiers.select.cation" bundle="<%=interfaces%>"/> </html:option>        
                                    <logic:present name="BDossiers">
                                    <html:options collection="BDossiers" property="id" labelProperty="name"/>
                                    </logic:present>
                                </html:select>
                                </span>
                                <input type="button"   onclick="javascript:if(document.docReviewRecv.dossierId.value>0){ postAjax('docsrecv','docMain',anchor + ':_SAVE_DOC:dossierId_doc:' + document.docReviewRecv.dossierId.value );}else{ return false;}" value='<bean:message key="cmd.doc.update.dossier" bundle="<%=interfaces%>"/>' />
                                </logic:equal>
                                <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkDocReply" value="1"> 
                                    <span><input  type="button" value="<bean:message key="command.doc.review.anwear.send.caption" bundle="<%=interfaces%>"/>"  onclick="post('docssend',anchor + ':_PREPARED_CREATE:id:<%=beanRecv.getId()%>:type:2:anwearDt:1:docIdRecv:<%=beanRecv.getId()%>')" /> </span>
                                </logic:equal>
                        </td>
                       </tr>
                     </table>
<%}else if (beanRecv.getObServer()==1){%>
             <table class="tab-review" cellpadding="0" cellspacing="0" width="100%" >
                       <tr valign="bottom">
                        <td  nowrap="nowrap" width="200px">
                                  
                                    <span class="tabactive1"  onclick="mdotab(this,'_DOC_REVIEW:statusId:<%=beanRecv.getStatusId()%>:id:<%=beanRecv.getId()%>:forYouId:<%=beanRecv.getForYouId()%>','divReview');">
                                         <bean:message key="doc.assign.excute.caption" bundle="<%=interfaces%>"/>
                                    </span> | 
                                    <span class="tab1" onclick="mdotab(this,'_PREPARED_SAVE:statusId:<%=beanRecv.getStatusId()%>:id:<%=beanRecv.getId()%>:forYouId:<%=beanRecv.getForYouId()%>','divReview');">
                                        <bean:message key="docs.tab.header.trailer" bundle="<%=interfaces%>"/>
                                    </span> |
                                    <span class="tab1"  onclick="mdotab(this,'_DETAIL:statusId:<%=beanRecv.getStatusId()%>:id:<%=beanRecv.getId()%>:forYouId:<%=beanRecv.getForYouId()%>','divReview');">
                                        <bean:message key="form.docs.detail" bundle="<%=interfaces%>"/>
                                    </span>                            
                                
                         </td>
                       </tr>
                     </table>
<%}%>
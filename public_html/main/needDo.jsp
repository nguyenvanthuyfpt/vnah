<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<Script language="javascript">
    function showDoNeed(obj,option){
        for(i=0;i<obj.parentNode.childNodes.length;i++){
                if(obj.parentNode.childNodes[i].className=='tabactive1') obj.parentNode.childNodes[i].className='tab1';
        }
        obj.className='tabactive1';
        if(option=='onlineId'){
            getObj('onlineId').style.display='block';
            document.getElementById('doneedId').style.display='none';
            postAjax('main','onlineId',anchor + ':_POST_USER_ONLINE');
        }else{
            getObj('onlineId').innerHTML='';
            getObj('doneedId').style.display='block';
            document.getElementById('onlineId').style.display='none';
        }
    }
</Script>
<div class="col1-ctn1 clearfix">
                                <table cellpadding="0" cellspacing="0" border="0" width="100%" align="left">
                                    <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_19.gif" width="8" height="43" /></td>
                                        <td class="sharebackground"><img align="left" src="<%=contextPath%>/images/newImages/clock.gif" /><div class="textbold">
                                          
                                             <span class="tabactive1"  onclick="showDoNeed(this,'doneedId');" ><bean:message key="main.needDo.portlet" bundle="<%=interfaces%>"/></span> | 
                                             <span class="tab1" onclick="showDoNeed(this,'onlineId');"><bean:message key="main.needDo.portlet.userOnline" bundle="<%=interfaces%>"/></span>
                                          
                                        </div></td>
                                        <td width="15" class="sharebackground"></td>
                                        <td width="10"><img src="<%=contextPath%>/images/newImages/i_20.gif" width="10" height="43" /></td>
                                    </tr>
                                </table>
                                
                                <div style="clear:both"></div>
                                <div class="line-bottom">
                                    <div class="line-left">
                                        <div class="line-right">
                                            <div class="goc-left">
                                                <div class="goc-right">
                                                    <div class="content clearfix">
                                                        <div class="goc_1">
                                                        	<div class="goc_2">
                                                            	<div class="goc_3">
                                                                 <div class="goc_4">
                                                                        <div class="div-content" style="margin:0px;padding:0px;border-top:1px solid #c2c2c2" >
                                                                         <span id="onlineId" style="display:none" ></span>
                                                                         <div id="doneedId" style="margin-left:20px;">
                                                                            
                                                                            <%  if(request.getSession().getAttribute("01.01") !=null){ %>
                                                                                        <logic:present name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>">
                                                                                        <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSRECV%>" property="checkUnReaded" value="1">
                                                                                                <logic:present name="BDocsRecvWait">
                                                                                                <logic:iterate name="BDocsRecvWait" id="bean" type="com.form.main.FMain"> 
                                                                                                <logic:equal name="bean" property="id" value="0" >
                                                                                                <logic:notEqual name="bean" property="amount" value="0" >
                                                                                                        <div class="icon-needdo-recv">
                                                                                                        <span class="textnone">
                                                                                                        <a  href="javascript:post('change',anchor + ':_DOCS_RECV_LIST:statusId:<%=com.inf.doc.IKeyDoc.DOC_STATUS_VIEW_WAIT%>:views:0:readed:0')">                                                                
                                                                                                        <strong><bean:message key="menu.top.doc.recivers.caption" bundle="<%=interfaces%>"/><span> (<bean:write name="bean" property="amount"/>)</span></strong>
                                                                                                        </a>
                                                                                                        </span>
                                                                                                        </div>
                                                                                                </logic:notEqual>
                                                                                                </logic:equal>
                                                                                                </logic:iterate>
                                                                                                </logic:present>
                                                                                        </logic:equal>
                                                                                        </logic:present>   
                                                                            <%}%>
                                                                            
                                                                            <%  if(request.getSession().getAttribute("01.02") !=null){ %>
                                                                                            <logic:present name="BDocsSendWait">
                                                                                            <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkUnReaded" value="1">
                                                                                            <logic:iterate name="BDocsSendWait" id="bean" type="com.form.main.FMain">
                                                                                            <logic:equal name="bean" property="id" value="0">
                                                                                            <logic:notEqual name="bean" property="amount" value="0" >
                                                                                                    <div class="icon-needdo-send">
                                                                                                    <span class="textnone">
                                                                                                    <a href="javascript:post('change',anchor + ':_DOCS_SEND_LIST:statusId:<%=com.inf.doc.IKeyDoc.DOC_STATUS_VIEW_WAIT%>')">
                                                                                                    <Strong><bean:message key="menu.top.doc.send.caption" bundle="<%=interfaces%>"/><span> (<bean:write name="bean" property="amount"/>)</span></strong>
                                                                                                    </a></span>
                                                                                                    </div>
                                                                                            </logic:notEqual>        
                                                                                            </logic:equal>
                                                                                            </logic:iterate>
                                                                                            </logic:equal>
                                                                                            </logic:present>
                                                                                
                                                                            <%}%>
                                                                            
                                                                            <logic:greaterThan  name="BAmountDeadLineSend" value="0">
                                                                                <div class="icon-needdo-task">
                                                                                <span class="textnone">
                                                                                                <a href="javascript:post('change', anchor + ':_TASKS_ASSIGN:type:0:amountDeadline:<bean:write name="BAmountDeadLineSend"/>');">
                                                                                                    <span><bean:message key="problem.detail.caption" bundle="<%=interfaces%>"/>(<bean:write name="BAmountDeadLineSend"/>)
                                                                                                    <img src="<%=contextPath%>/images/ring.gif"/>
                                                                                                    </span>
                                                                                                </a>
                                                                                                <logic:greaterThan  name="BCheckHaveReport" value="0">
                                                                                                    <img style="cursor:pointer" onclick="post('change', anchor + ':_TASKS_ASSIGN:type:0')" title="<bean:message key="task.cmd.report.caption" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/bookmark.gif"/>(<bean:write name="BCheckHaveReport"/>)
                                                                                               </logic:greaterThan>
                                                                                </span>
                                                                                </div>
                                                                            </logic:greaterThan>
                                                                            
                                                                            <logic:greaterThan  name="BAmountDeadLineRecv" value="0">
                                                                                <div class="icon-needdo-task">
                                                                                <span class="textnone">
                                                                                                <a href="javascript:post('change', anchor + ':_TASKS_ASSIGN:type:1:amountDeadline:<bean:write name="BAmountDeadLineRecv"/>');">
                                                                                                <span><bean:message key="doc.reci.title.caption" bundle="<%=interfaces%>"/>(<bean:write name="BAmountDeadLineRecv"/>)
                                                                                                <img src="<%=contextPath%>/images/ring.gif"/>
                                                                                                </span>
                                                                                                </a>
                                                                                                <logic:greaterThan  name="BCheckHaveReview" value="0">
                                                                                                    
                                                                                                    <img style="cursor:pointer" onclick="post('change', anchor + ':_TASKS_ASSIGN:type:1')" title="<bean:message key="problem.have.review" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/bookmark.gif"/>(<bean:write name="BCheckHaveReview"/>)
                                                                                                     
                                                                                                    
                                                                                               </logic:greaterThan>
                                                                                </span>
                                                                                </div>
                                                                            </logic:greaterThan>
                                                                            
                                                                            <%  if(request.getSession().getAttribute("10") !=null && request.getSession().getAttribute("10.01")!=null){ %>
                                                                            <html:form action="control" method="post" />
                                                                             <logic:present name="BTotalEmailUnReaded">
                                                                             <logic:greaterThan  name="BTotalEmailUnReaded" value="0">
                                                                                <div class="icon-needdo-email">
                                                                                <span class="textnone">
                                                                                                <a href="javascript:post('control', anchor + ':_INBOX:folderName:INBOX');">
                                                                                                <span><bean:message key="mail.tree.recvMessage.main" bundle="<%=interfaces%>"/> (<bean:write name="BTotalEmailUnReaded"/>)
                                                                                                </span>
                                                                                                </a>
                                                                                </span>
                                                                                </div>
                                                                            </logic:greaterThan>
                                                                            </logic:present>
                                                                            <%}%>
                                                                              <%  if(request.getSession().getAttribute("03.01") !=null){ %>
                                                                             
                                                                                        <logic:present name="03.01">   
                                                                                        <logic:present name="BAmount">                    
                                                                                                    <logic:present name="BAmount">                    
                                                                                                    <logic:notEqual name="BAmount" property="amountRevUnRead" value="0"> 
                                                                                                <div class="icon-needdo-message">
                                                                                                <span class="textnone">
                                                                                                    <a href="javascript:post('change', anchor + ':_MESSAGES:type:1')">                
                                                                                                    <strong><bean:message key="app.inbox.messages.main" bundle="<%=interfaces%>"/> <span >(<bean:write name="BAmount" property="amountRevUnRead"/>)</span></strong>
                                                                                                    </a>
                                                                                                    </span></div>
                                                                                                    </logic:notEqual>
                                                                                                    </logic:present> 
                                                                                                
                                                                                                
                                                                                        </logic:present>
                                                                                        </logic:present>
                                                                            <%}%>
                                                                            <%  if(request.getSession().getAttribute("12") !=null){ %>
                                                                                <logic:present name="BExcuteWailt">
                                                                                <logic:greaterThan  name="BExcuteWailt" value="0" >
                                                                                     <div class="icon-needdo-require">
                                                                                        <span class="textnone">
                                                                                                <a href="javascript:post('main', anchor + ':_REQUIRE_LIST:rmStatus:<%=com.inf.doc.IKeyDoc.RM_STATUS_UNREAD%>');">
                                                                                               <span><bean:message key="require.manager.caption" bundle="<%=interfaces%>"/> (<bean:write name="BExcuteWailt"/><logic:present name="BTotalRM">/<bean:write name="BTotalRM"/></logic:present>)</span>        
                                                                                               </a>
                                                                                        </span>
                                                                                    </div>
                                                                                </logic:greaterThan>
                                                                                </logic:present>
                                                                            <%}%>
                                                                            </div>
                                                                 
                                                                 </div>
                                                                </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

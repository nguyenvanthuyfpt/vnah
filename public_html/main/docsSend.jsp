<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
<%  if(request.getSession().getAttribute("01.02") !=null){ %>
<logic:notEqual name="01.02" value="2">
<%  int[] menuActiveTemp = (int[])request.getSession().getAttribute("menuActive");%>
<div class="right-01 clearfix">
                                <table cellpadding="0" cellspacing="0" border="0" width="100%" align="left">
                                    <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_36.gif" align="left" /></td>
                                        <td class="report">
                                            <div class="textbold">
                                                        <a href="javascript:post('change',anchor + ':_DOCS_SEND_LIST:views:-1:statusId:<%=com.inf.doc.IKeyDoc.DOC_STATUS_VIEW_ALL%>:app:<%=menuActiveTemp[0]%>')" class="li-title-03">
                                                        <bean:message key="menu.top.doc.send.caption" bundle="<%=interfaces%>"/>            
                                                        </a> 
                                                        <span>
                                                        <logic:present name="BDocsSendRead">
                                                        (<bean:write name="BDocsSendRead" property="amount"/>) 
                                                        </logic:present>
                                                        </span>
                                            </div></td>
                                        <td width="15" class="report">
                                            <logic:notEqual name="01.02" value="0" >
                                            <img src="<%=contextPath%>/images/newImages/mini.gif" width="9" height="12" onclick="javascript:postAjax('main','01.02',anchor +':_MINIMIZE:minimize:0:menuId:01.02:app:<%=menuActiveTemp[0]%>')" title="<bean:message key="title.main.hill.close.minimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                            </logic:notEqual>
                                            <logic:equal name="01.02"  value="0" >
                                            <img src="<%=contextPath%>/images/newImages/max.gif" width="9" height="12" onclick="javascript:postAjax('main','01.02',anchor +':_MINIMIZE:minimize:1:menuId:01.02:app:<%=menuActiveTemp[0]%>')" title="<bean:message key="title.main.hill.close.maxnimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                            </logic:equal>
                                        </td>
                                        <td width="15" class="report">
                                            <img src="<%=contextPath%>/images/newImages/del.gif" width="9" height="12" onclick="javascript:document.getElementById('01.02').innerHTML='';postAjax('main','openCombo',anchor +':_CLOSE_PORTLET:minimize:2:menuId:01.02:app:<%=menuActiveTemp[0]%>')" title="<bean:message key="title.main.hill.close.portlet" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                        </td>
                                        <td width="9"><img src="<%=contextPath%>/images/newImages/i_38.gif" align="left" /></td>
                                    </tr>
                                </table>
                                <div style="clear:both"></div>
                                <div class="line-bottom">
                                    <div class="line-left">
                                        <div class="line-right">
                                            <div class="goc-left">
                                                <div class="goc-right">
                                                    <div class="ct clearfix">
                                                        <div class="ct">
                                                            
                                                                        <div style="padding-left:10px">
                                                                        <%  if(Integer.parseInt(request.getSession().getAttribute("01.02").toString())==1){ %>
                                                                        <ul class="MainUl">                            
                                                                        <logic:present name="BDocsSendWait">
                                                                        <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkUnReaded" value="1">
                                                                        <logic:iterate name="BDocsSendWait" id="bean" type="com.form.main.FMain">
                                                                        <logic:equal name="bean" property="id" value="0">
                                                                        <li>
                                                                        <a href="javascript:post('change',anchor + ':_DOCS_SEND_LIST:statusId:<%=com.inf.doc.IKeyDoc.DOC_STATUS_VIEW_WAIT%>:app:<%=menuActiveTemp[0]%>')">
                                                                        <logic:notEqual name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkRedcaption" value="">
                                                                         <logic:equal name="bean" property="amount" value="0"> 
                                                                                <bean:write name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkRedcaption"/><span> (0)</span>
                                                                        </logic:equal>
                                                                        <logic:notEqual name="bean" property="amount" value="0"> 
                                                                                <strong><bean:write name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkRedcaption"/><span> (<bean:write name="bean" property="amount"/>)</span></strong>
                                                                        </logic:notEqual>
                                                                        </logic:notEqual>
                                                                        
                                                                        <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkRedcaption" value="">
                                                                             <logic:equal name="bean" property="amount" value="0"> 
                                                                                    <bean:message key="status.wait" bundle="<%=interfaces%>"/><span> (0)</span>
                                                                             </logic:equal>
                                                                              <logic:notEqual name="bean" property="amount" value="0"> 
                                                                                    <strong><bean:message key="status.wait" bundle="<%=interfaces%>"/><span> (<bean:write name="bean" property="amount"/>)</span></strong>
                                                                              </logic:notEqual>
                                                                        
                                                                        </logic:equal>
                                                                        </a>
                                                                        </li>
                                                                        </logic:equal>
                                                                        </logic:iterate>
                                                                        </logic:equal>
                                                                        </logic:present>
                                                                        
                                                                        <logic:present name="BDocsSend">    
                                                                        <logic:iterate name="BDocsSend" id="bean" type="com.form.main.FMain">        
                                                                        <li>
                                                                        <a href="javascript:post('change',anchor + ':_DOCS_SEND_LIST:statusId:<%=bean.getStatusId()%>:app:<%=menuActiveTemp[0]%>')"><bean:write name="bean" property="name" /> (<bean:write name="bean" property="amount" />)</a>
                                                                        </li>
                                                                        </logic:iterate>             
                                                                        </logic:present>
                                                                        
                                                                        <logic:present name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>">    
                                                                        <logic:equal name="<%=com.lib.AppConfigs.CHECK_RULE_DOCSSEND%>" property="checkNotIncharge" value="1">
                                                                        <logic:present name="BDocsSendWait">    
                                                                        <logic:iterate name="BDocsSendWait" id="bean" type="com.form.main.FMain">
                                                                        <logic:notEqual name="bean" property="id" value="0">
                                                                        <li>
                                                                        <a href="javascript:post('change',anchor + ':_DOCS_SEND_LIST:statusId:-5:views:<%=bean.getId()%>:readed:0:app:<%=menuActiveTemp[0]%>')">
                                                                        <bean:write name="bean" property="name" />
                                                                        </a> (<bean:write name="bean" property="amount" />)
                                                                        </li>
                                                                        </logic:notEqual>
                                                                        </logic:iterate>     
                                                                        </logic:present>
                                                                        </logic:equal>
                                                                        </logic:present>
                                                                        </ul>                          
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
  </logic:notEqual>
<%}%>    
    

  
   

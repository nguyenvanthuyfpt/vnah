<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
 <%  if(request.getSession().getAttribute("02") !=null){ %>
                       <logic:notEqual name="02" value="2">
<%  int[] menuActiveTemp = (int[])request.getSession().getAttribute("menuActive");%>
<div class="right-01 clearfix">
                                <table cellpadding="0" cellspacing="0" border="0" width="100%" align="left">
                                    <tr>
                                        <td width="8"><img src="<%=contextPath%>/images/newImages/i_29.gif" width="8" height="44" /></td>
                                        <td class="repeatbackground"><div class="textbold"><span class="li-title-09"><bean:message key="menu.tree.task.title.caption" bundle="<%=interfaces%>"/></span></div></td>
                                        
                                         <td width="30px" class="repeatbackground">
                                                    <img src="<%=contextPath%>/images/newImages/links.gif"  onclick="javascript:post('change', anchor + ':_TASKS_ASSIGN:type:1:app:<%=menuActiveTemp[2]%>',true);" title="<bean:message key="title.main.hill.close.links" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                    <logic:notEqual name="02" value="0" >
                                                    <img src="<%=contextPath%>/images/newImages/mini.gif" width="9" height="12" onclick="javascript:postAjax('main','02',anchor +':_MINIMIZE:minimize:0:menuId:02:app:<%=menuActiveTemp[2]%>')" title="<bean:message key="title.main.hill.close.minimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                    </logic:notEqual>
                                                    <logic:equal name="02"  value="0" >
                                                    <img src="<%=contextPath%>/images/newImages/max.gif" width="9" height="12" onclick="javascript:postAjax('main','02',anchor +':_MINIMIZE:minimize:1:menuId:02:app:<%=menuActiveTemp[2]%>')" title="<bean:message key="title.main.hill.close.maxnimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                    </logic:equal>
                                         </td>
                                        <td width="15" class="repeatbackground"><img src="<%=contextPath%>/images/newImages/del.gif" width="9" height="12" onclick="javascript:document.getElementById('02').innerHTML='';postAjax('main','openCombo',anchor +':_CLOSE_PORTLET:minimize:2:menuId:02:app:<%=menuActiveTemp[2]%>')" title="<bean:message key="title.main.hill.close.portlet" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  /></td>
                                        <td width="7"><img src="<%=contextPath%>/images/newImages/i_30.gif" width="7" height="44" /></td>
                                    </tr>
                                </table>
                                <div style="clear:both"></div>
                                <div class="line-bottom">
                                    <div class="line-left">
                                        <div class="line-right">
                                            <div class="goc-left">
                                                <div class="goc-right">
                                                    <div class="ct">
                                                                            <logic:equal name="02"  value="1" >
                                                                            <div style="padding-left:10px">
                                                                            <ul class="MainUl">
                                                                            <logic:present name="02.01">   
                                                                            <li>
                                                                            <a href="javascript:post('change', anchor + ':_TASKS_ASSIGN:type:0:app:<%=menuActiveTemp[2]%>',true);"><bean:message key="problem.detail.caption" bundle="<%=interfaces%>"/> <logic:present name="BAmountTask"> (<span ><bean:write name="BAmountTask" property="amountSend"/>/<bean:write name="BAmountTask" property="amountTotalsSend"/></span>)
                                                                            
                                                                           <logic:greaterThan  name="BCheckHaveReport" value="0">
                                                                                <img style="cursor:pointer" onclick="post('change', anchor + ':_TASKS_ASSIGN:type:0')" title="<bean:message key="task.cmd.report.caption" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/bookmark.gif"/>(<bean:write name="BCheckHaveReport"/>)
                                                                           </logic:greaterThan>
                                                                            
                                                                            </logic:present> </a>
                                                                            <logic:greaterThan  name="BAmountDeadLineSend" value="0">
                                                                            <div style="padding-left:10px">
                                                                                    <a href="javascript:post('change', anchor + ':_TASKS_ASSIGN:type:0:amountDeadline:<bean:write name="BAmountDeadLineSend"/>');">
                                                                                    <span><bean:message key="main.portlet.ring" bundle="<%=interfaces%>"/>(<bean:write name="BAmountDeadLineSend"/>)
                                                                                    <img src="<%=contextPath%>/images/ring.gif"/>
                                                                                    </span>
                                                                                    </a>
                                                                                    
                                                                                    
                                                                            </div>
                                                                            </logic:greaterThan >
                                                                            </li>
                                                                            </logic:present>
                                                                            <logic:present name="02.02">   
                                                                            <li>
                                                                            <a href="javascript:post('change', anchor + ':_TASKS_ASSIGN:type:1',true);"><bean:message key="doc.reci.title.caption" bundle="<%=interfaces%>"/>  <logic:present name="BAmountTask"> <span >(<bean:write name="BAmountTask" property="amountRecv"/>/<bean:write name="BAmountTask" property="amountTotalsRecv"/>)</span></logic:present></a>
                                                                            <logic:greaterThan  name="BCheckHaveReview" value="0">
                                                                               <img style="cursor:pointer" onclick="post('change', anchor + ':_TASKS_ASSIGN:type:1')" title="<bean:message key="problem.have.review" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/bookmark.gif"/>(<bean:write name="BCheckHaveReview"/>)
                                                                            </logic:greaterThan>
                                                                            
                                                                            <logic:greaterThan  name="BAmountDeadLineRecv" value="0">
                                                                            <div style="padding-left:10px">
                                                                                    <a href="javascript:post('change', anchor + ':_TASKS_ASSIGN:type:1:amountDeadline:<bean:write name="BAmountDeadLineRecv"/>');">
                                                                                    <span><bean:message key="main.portlet.ring" bundle="<%=interfaces%>"/>(<bean:write name="BAmountDeadLineRecv"/>)
                                                                                    <img src="<%=contextPath%>/images/ring.gif"/>
                                                                                    </span>
                                                                                    </a>
                                                                            </div>
                                                                            </logic:greaterThan >
                                                                            </li>
                                                                            </logic:present>
                                                                            <logic:present name="02.03">   
                                                                            <li>
                                                                            <a href="javascript:post('change', anchor + ':_TASKS_ASSIGN_CATEGORY:type:2:app:<%=menuActiveTemp[2]%>',true);"><bean:message key="problem.categories" bundle="<%=interfaces%>"/>  <logic:present name="BAmountTask"> (<span ><bean:write name="BAmountTask" property="amountCate"/></span>)</logic:present></a>
                                                                            </li>
                                                                            
                                                                            </logic:present>
                                                                            </ul>
                                                                            </div>
                                                                            </logic:equal>
                                                                            
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </logic:notEqual>
                         <%}%>
   
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
 <%  if(request.getSession().getAttribute("03") !=null){ %>
 
<logic:notEqual name="03" value="2">
<%  int[] menuActiveTemp = (int[])request.getSession().getAttribute("menuActive");%>
<div class="right-01 clearfix">
                                <table cellpadding="0" cellspacing="0" border="0" width="100%" align="left">
                                    <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_29.gif" width="8" height="44" /></td>
                                        <td class="repeatbackground"><div class="textbold"><span class="li-title-05"><bean:message key="app.folder.messages" bundle="<%=interfaces%>"/></span></div></td>
                                        <td width="30px" class="repeatbackground">
                                                <img src="<%=contextPath%>/images/newImages/links.gif"  onclick="javascript:post('change', anchor + ':_MESSAGES:type:1:app:<%=menuActiveTemp[1]%>')" title="<bean:message key="title.main.hill.close.links" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                <logic:notEqual name="03" value="0" >
                                                <img src="<%=contextPath%>/images/newImages/mini.gif" width="9" height="12" onclick="javascript:postAjax('main','03',anchor +':_MINIMIZE:minimize:0:menuId:03:app:<%=menuActiveTemp[1]%>')" title="<bean:message key="title.main.hill.close.minimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                </logic:notEqual>
                                                <logic:equal name="03"  value="0" >
                                                <img src="<%=contextPath%>/images/newImages/max.gif" width="9" height="12" onclick="javascript:postAjax('main','03',anchor +':_MINIMIZE:minimize:1:menuId:03:app:<%=menuActiveTemp[1]%>')" title="<bean:message key="title.main.hill.close.maxnimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                </logic:equal>
                                        </td>        
                                        <td width="15" class="repeatbackground"><img src="<%=contextPath%>/images/newImages/del.gif" width="9" height="12" onclick="javascript:document.getElementById('03').innerHTML='';postAjax('main','openCombo',anchor +':_CLOSE_PORTLET:minimize:2:menuId:03:app:4')" title="<bean:message key="title.main.hill.close.portlet" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  /></td>
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
                                                                        <logic:equal name="03" value="1" >
                                                                        <div style="padding-left:10px">
                                                                        <ul class="MainUl">
                                                                        <logic:present name="03.01">   
                                                                        <li>
                                                                        <a href="javascript:post('change', anchor + ':_MESSAGES:type:1:app:<%=menuActiveTemp[1]%>')">                
                                                                        <logic:present name="BAmount">                    
                                                                        <logic:equal name="BAmount" property="amountRevUnRead" value="0"> 
                                                                                <bean:message key="app.inbox.messages" bundle="<%=interfaces%>"/>
                                                                                <span >(<bean:write name="BAmount" property="amountRevUnRead"/>)</span>
                                                                         </logic:equal>

                                                                         <logic:notEqual name="BAmount" property="amountRevUnRead" value="0"> 
                                                                                <strong><bean:message key="app.inbox.messages" bundle="<%=interfaces%>"/> <span >(<bean:write name="BAmount" property="amountRevUnRead"/>)</span></strong>
                                                                         </logic:notEqual>

                                                                        </logic:present> </a>
                                                                        </li>
                                                                        </logic:present>
                                                                        <logic:present name="03.02">   
                                                                        <li>
                                                                            <a href="javascript:post('change', anchor + ':_MESSAGES:type:2:app:<%=menuActiveTemp[1]%>')"><bean:message key="app.send.item.messages" bundle="<%=interfaces%>"/> <logic:present name="BAmount"> (<span ><bean:write name="BAmount" property="amountSend"/></span>)</logic:present></a>
                                                                        </li>
                                                                        </logic:present>
                                                                        <logic:present name="03.03">  
                                                                        <li>                               
                                                                        <a href="javascript:post('change', anchor + ':_MESSAGES:type:3:app:<%=menuActiveTemp[1]%>')"><bean:message key="app.send.item.delete.caption" bundle="<%=interfaces%>"/><logic:present name="BAmount"> (<span ><bean:write name="BAmount" property="amountDel"/></span>)</logic:present>  </a>
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
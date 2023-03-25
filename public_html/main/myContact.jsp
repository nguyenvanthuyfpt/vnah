<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %> 
 <%  if(request.getSession().getAttribute("05.02") !=null){ %>  
 <logic:notEqual name="05.02" value="2">
 <div class="right-01 clearfix">
                                <table cellpadding="0" cellspacing="0" border="0" width="100%" align="left">
                                    <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_29.gif" width="8" height="44" /></td>
                                        <td class="repeatbackground"><img src="<%=contextPath%>/images/newImages/i_40.gif" align="left" />
                                            <div class="textbold">
                                                                                <table>
                                                                                <tr>                     
                                                                                <logic:notEqual name="05.02"  value="0" >
                                                                                        <td id="_EMPLOYEE_DAOTAO" nowrap="nowrap" class="userOnlineActive" style="cursor: pointer;" onclick="mdotabMycaontact(this,'change','_USERS_ONLINE');">
                                                                                            <bean:message key="users.list.online.caption" bundle="<%=interfaces%>"/>
                                                                                        </td>
                                                                                        <td width="2px"></td>
                                                                                </logic:notEqual>
                                                                                <logic:equal name="05.02"  value="0" >
                                                                                      <td id="_EMPLOYEE_DAOTAO" nowrap="nowrap" class="userOnlineActive">
                                                                                        <bean:message key="users.list.online.caption" bundle="<%=interfaces%>"/> 
                                                                                      </td> 
                                                                                      <td width="2px"></td>
                                                                                      <td id="_EMPLOYEE_LYLICH" nowrap="nowrap" class="userOnlineNoneActive" >
                                                                                         <bean:message key="mycontact.title.caption" bundle="<%=interfaces%>"/>          
                                                                                      </td>
                                                                                </logic:equal>
                                                                                 <td nowrap="nowrap">&nbsp;</td>
                                                                                </table>

                                            </div></td>
                                        <td width="30px" class="repeatbackground">
                                                                                <img src="<%=contextPath%>/images/newImages/links.gif"   title="<bean:message key="title.main.hill.close.links" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                                                <logic:notEqual name="05.02" value="0" >
                                                                                <img src="<%=contextPath%>/images/newImages/mini.gif" width="9" height="12" onclick="javascript:postAjax('main','05.02',anchor +':_MINIMIZE:minimize:0:menuId:05.02')" title="<bean:message key="title.main.hill.close.minimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                                                </logic:notEqual>
                                                                                <logic:equal name="05.02"  value="0" >
                                                                                <img src="<%=contextPath%>/images/newImages/max.gif" width="9" height="12" onclick="javascript:postAjax('main','05.02',anchor +':_MINIMIZE:minimize:1:menuId:05.02')" title="<bean:message key="title.main.hill.close.maxnimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                                                </logic:equal>                                
                                        </td>
                                        <td width="15" class="repeatbackground">
                                                                                <img src="<%=contextPath%>/images/newImages/del.gif" width="9" height="12" onclick="javascript:document.getElementById('05.02').innerHTML='';postAjax('main','openCombo',anchor +':_CLOSE_PORTLET:minimize:2:menuId:05.02')" title="<bean:message key="title.main.hill.close.portlet" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                        </td>
                                        <td width="7"><img src="<%=contextPath%>/images/newImages/i_30.gif" width="7" height="44" /></td>
                                    </tr>
                                </table>
                                <div style="clear:both"></div>
                                <div class="line-bottom">
                                    <div class="line-left">
                                        <div class="line-right">
                                            <div class="goc-left">
                                                <div class="goc-right">
                                                    <div class="ct" id="tdMycontact">
                                                            <jsp:include page="/main/listUserOnline.jsp" />               
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
</logic:notEqual>
 <%}%>

  
   

<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%  if(request.getSession().getAttribute("04") !=null){ %>
                        <logic:notEqual name="04" value="2">
                        <div class="right-01 clearfix">
                                <table cellpadding="0" cellspacing="0" border="0" width="100%" align="left">
                                    <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_29.gif" width="8" height="44" /></td>
                                        <td class="repeatbackground">
                                            <div class="textbold"><span class="li-title-library"><bean:message key="title.template.label.cabin" bundle="<%=interfaces%>"/></span></div></td>
                                        <td width="30px" class="repeatbackground">
                                                    <img src="<%=contextPath%>/images/newImages/links.gif" onclick="javascript:post('change',anchor + ':_CABIN:type:1')"  title="<bean:message key="title.main.hill.close.links" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                    <logic:notEqual name="04" value="0" >
                                                    <img src="<%=contextPath%>/images/newImages/mini.gif" width="9" height="12" onclick="javascript:postAjax('main','04',anchor +':_MINIMIZE:minimize:0:menuId:04:app:4')" title="<bean:message key="title.main.hill.close.minimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                    </logic:notEqual>
                                                    <logic:equal name="04"  value="0" >
                                                    <img src="<%=contextPath%>/images/newImages/max.gif" width="9" height="12" onclick="javascript:postAjax('main','04',anchor +':_MINIMIZE:minimize:1:menuId:04:app:4')" title="<bean:message key="title.main.hill.close.maxnimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                    </logic:equal>
                                        </td>
                                        <td width="15" class="repeatbackground">
                                        <img src="<%=contextPath%>/images/newImages/del.gif" width="9" height="12" onclick="javascript:document.getElementById('04').innerHTML='';postAjax('main','openCombo',anchor +':_CLOSE_PORTLET:minimize:2:menuId:04:app:4')" title="<bean:message key="title.main.hill.close.portlet" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  /></td>
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
                                                    <logic:equal name="04" value="1" >
                                                      <div style="padding-left:10px">
                                                            <ul class="MainUl" >
                                                                <%  if(request.getSession().getAttribute("04.01") !=null){ %>
                                                                <li>
                                                                <a href="javascript:post('change',anchor + ':_CABIN:type:0:cabinType_id:0')" class="li-title-cabin"><bean:message key="header.cabin.label.public" bundle="<%=interfaces%>"/></a>
                                                                </li>
                                                                <%}%>
                                                                
                                                                <%  if(request.getSession().getAttribute("04.04") !=null){ %>
                                                                <li>
                                                                        <a href="javascript:post('change',anchor + ':_CABIN:type:3:cabinType_id:0')" class="li-title-cabin"><bean:message key="header.cabin.label.deps" bundle="<%=interfaces%>"/></a>
                                                                </li>
                                                                <%}%>
                                                                
                                                                <%  if(request.getSession().getAttribute("04.03") !=null){ %>
                                                                <li>
                                                                        <a href="javascript:post('change',anchor + ':_CABIN:type:2:cabinType_id:0')" class="li-title-cabin"><bean:message key="header.cabin.label.shere" bundle="<%=interfaces%>"/></a>
                                                                </li>
                                                                <%}%>
                                                                
                                                                <%  if(request.getSession().getAttribute("04.02") !=null){ %>
                                                                <li>
                                                                        <a href="javascript:post('change',anchor + ':_CABIN:type:1:cabinType_id:0')" class="li-title-cabin"><bean:message key="header.cabin.label.private" bundle="<%=interfaces%>"/></a>
                                                                </li>
                                                                <%}%>
                                                                
                                                                
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


  
   
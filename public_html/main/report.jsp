<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%  if(request.getSession().getAttribute("08") !=null){ %>
                        <logic:notEqual name="08" value="2">
                        <div class="right-01 clearfix">
                                <table cellpadding="0" cellspacing="0" border="0" width="100%" align="left">
                                    <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_29.gif" width="8" height="44" /></td>
                                        <td class="repeatbackground">
                                            <div class="textbold"><span class="li-title-library"><bean:message key="report.list.caption" bundle="<%=interfaces%>"/></span></div></td>
                                        <td width="30px" class="repeatbackground">
                                                    <img src="<%=contextPath%>/images/newImages/links.gif" onclick="javascript:post('change',anchor + ':_REPORT_ALL:type:0:optionmenu:0')"  title="<bean:message key="title.main.hill.close.links" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                    <logic:notEqual name="08" value="0" >
                                                    <img src="<%=contextPath%>/images/newImages/mini.gif" width="9" height="12" onclick="javascript:postAjax('main','08',anchor +':_MINIMIZE:minimize:0:menuId:08:app:4')" title="<bean:message key="title.main.hill.close.minimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                    </logic:notEqual>
                                                    <logic:equal name="08"  value="0" >
                                                    <img src="<%=contextPath%>/images/newImages/max.gif" width="9" height="12" onclick="javascript:postAjax('main','08',anchor +':_MINIMIZE:minimize:1:menuId:08:app:4')" title="<bean:message key="title.main.hill.close.maxnimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                    </logic:equal>
                                        </td>
                                        <td width="15" class="repeatbackground"><img src="<%=contextPath%>/images/newImages/del.gif" width="9" height="12" onclick="javascript:document.getElementById('08').innerHTML='';postAjax('main','openCombo',anchor +':_CLOSE_PORTLET:minimize:2:menuId:08:app:4')" title="<bean:message key="title.main.hill.close.portlet" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  /></td>
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
                                                    <logic:equal name="08" value="1" >
                                                      <div style="padding-left:10px">
                                                            <ul class="MainUl" >
                                                                <%  if(request.getSession().getAttribute("08.01") !=null){ %>
                                                                <li>
                                                                <a href="javascript:post('change',anchor + ':_REPORT_ALL:type:0')" class="li-title-cabin"><bean:message key="report.list.caption" bundle="<%=interfaces%>"/></a>
                                                                </li>
                                                                <%}%>
                                                                <%  if(request.getSession().getAttribute("08.02") !=null){ %>
                                                                <li>
                                                                        <a href="javascript:post('change',anchor + ':_REPORT_ALL:type:1')" class="li-title-cabin"><bean:message key="recv.report.list.caption" bundle="<%=interfaces%>"/></a>
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


  
   
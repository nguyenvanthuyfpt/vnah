<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="broadcast" method="post" >
<input type="hidden" name="createtime" id="createtime" value="" />
<%  if(request.getSession().getAttribute("09.01") !=null){ %>  
<logic:notEqual name="09.01" value="2">
                        <div class="right-01 clearfix">
                                <table cellpadding="0" cellspacing="0" border="0" width="100%" align="left">
                                    <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_29.gif" width="8" height="44" /></td>
                                        <td class="repeatbackground">
                                            <div class="textbold">
                                                        <span class="li-title-10">
                                                        <bean:message key="broadcast.main.caption" bundle="<%=interfaces%>"/>
                                                        </span>                                
                                                        <span style="font-size:10px;cursor:pointer;" id="showDate" onclick="popUpCalendar(this,'createtime','dd/mm/yyyy','postDate()');return false;">
                                                        <logic:present name="broadcast">
                                                            <bean:write name="broadcast" property="createtime" />
                                                        </logic:present>
                                                        </span>
                                            </div></td>
                                        <td width="30px" class="repeatbackground">
                                                        <%if(me.isRole(com.inf.IRoles.rBROADCAST)){%>
                                                        <img src="<%=contextPath%>/images/newImages/links.gif"  onclick="javascript:post('change',anchor + ':_PREPARE_BROADCAST')" title="<bean:message key="title.main.hill.close.links" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                        <%}%>
                                                        <logic:notEqual name="09.01" value="0" >
                                                        <img src="<%=contextPath%>/images/newImages/mini.gif" width="9" height="12" onclick="javascript:postAjax('main','09.01',anchor +':_MINIMIZE:minimize:0:menuId:09.01')" title="<bean:message key="title.main.hill.close.minimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                        </logic:notEqual>
                                                        <logic:equal name="09.01"  value="0" >
                                                        <img src="<%=contextPath%>/images/newImages/max.gif" width="9" height="12" onclick="javascript:postAjax('main','09.01',anchor +':_MINIMIZE:minimize:1:menuId:09.01')" title="<bean:message key="title.main.hill.close.maxnimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                        </logic:equal> 
                                        </td>
                                        <td width="15" class="repeatbackground"><img src="<%=contextPath%>/images/newImages/del.gif" width="9" height="12" onclick="javascript:document.getElementById('09.01').innerHTML='';postAjax('main','openCombo',anchor +':_CLOSE_PORTLET:minimize:2:menuId:09.01')" title="<bean:message key="title.main.hill.close.portlet" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  /></td>
                                        <td width="7"><img src="<%=contextPath%>/images/newImages/i_30.gif" width="7" height="44" /></td>
                                    </tr>
                                </table>
                                <div style="clear:both"></div>
                                <div class="line-bottom">
                                    <div class="line-left">
                                        <div class="line-right">
                                            <div class="goc-left">
                                                <div class="goc-right">
                                                     <div class="ct" id="mainList">
                                                            <logic:equal name="09.01"  value="1" >
                                                            <jsp:include page="/main/list.jsp" />
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
</html:form>
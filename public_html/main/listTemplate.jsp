<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="template" method="post" >
<%  if(request.getSession().getAttribute("07") !=null){ %>
                        <logic:notEqual name="07" value="2">
                        <div class="right-01 clearfix">
                                <table cellpadding="0" cellspacing="0" border="0" width="100%" align="left">
                                    <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_29.gif" width="8" height="44" /></td>
                                        <td class="repeatbackground">
                                            <div class="textbold"><span class="li-title-template"><bean:message key="title.template.label.header" bundle="<%=interfaces%>"/></span></div></td>
                                        <td width="30px" class="repeatbackground">
                                                   <img src="<%=contextPath%>/images/newImages/links.gif"  onclick="javascript:post('change',anchor + ':_TEMPLATE_ALL:type:1')" title="<bean:message key="title.main.hill.close.links" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                    <logic:notEqual name="07" value="0" >
                                                    <img src="<%=contextPath%>/images/newImages/mini.gif" width="9" height="12" onclick="javascript:postAjax('main','07',anchor +':_MINIMIZE:minimize:0:menuId:07:app:4')" title="<bean:message key="title.main.hill.close.minimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                    </logic:notEqual>
                                                    <logic:equal name="07"  value="0" >
                                                    <img src="<%=contextPath%>/images/newImages/max.gif" width="9" height="12" onclick="javascript:postAjax('main','07',anchor +':_MINIMIZE:minimize:1:menuId:07:app:4')" title="<bean:message key="title.main.hill.close.maxnimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                                    </logic:equal>
                                        </td>
                                        <td width="15" class="repeatbackground"><img src="<%=contextPath%>/images/newImages/del.gif" width="9" height="12" onclick="javascript:document.getElementById('07').innerHTML='';postAjax('main','openCombo',anchor +':_CLOSE_PORTLET:minimize:2:menuId:07:app:4')" title="<bean:message key="title.main.hill.close.portlet" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  /></td>
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
                                                                            <logic:equal name="07" value="1" >
                                                                            
                                                                            <logic:present name="BTemplates" >  
                                                                            <div style="padding-left:20px;padding-right:20px" class="clearfix">
                                                                                    
                                                                                    <html:select name="template" property="departmentId" onchange="javascript:postAjax('template','templateList',anchor + ':_SEARCH_MAIN:type:1')" style="width:100%"  >
                                                                                    <html:option value="0"><bean:message key="status.all" bundle="<%=interfaces%>"/></html:option>
                                                                                    <logic:present name="BDepartments">
                                                                                    <html:options collection="BDepartments" property="id" labelProperty="name"/>
                                                                                    </logic:present>
                                                                                    </html:select> 
                                                                                    
                                                                            </div>
                                                                            <div id="templateList">
                                                                            <jsp:include page="/main/contentTemplate.jsp" />
                                                                            </div>
                                                                            </logic:present>
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

  
   
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<%  if(request.getSession().getAttribute("01") !=null){ %>
<logic:notEqual name="01"  value="2" >
<%  int[] menuActiveTemp = (int[])request.getSession().getAttribute("menuActive");%>
<div class="right-01 clearfix">
                                <table cellpadding="0" cellspacing="0" border="0" width="100%" align="left">
                                    <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_36.gif" align="left" /></td>
                                        <td class="report">
                                            <div class="textbold">
                                                    <span class="li-title-02">
                                                    <bean:message key="main.docs.title.caption" bundle="<%=interfaces%>"/>
                                                    </span>
                                            </div></td>
                                        <td width="30px" class="report">
                                            <img src="<%=contextPath%>/images/newImages/links.gif"   title="<bean:message key="title.main.hill.close.links" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                            <logic:notEqual name="01" value="0" >
                                            <img src="<%=contextPath%>/images/newImages/mini.gif" width="9" height="12" onclick="javascript:postAjax('main','01',anchor +':_MINIMIZE:minimize:0:menuId:01:app:<%=menuActiveTemp[0]%>')" title="<bean:message key="title.main.hill.close.minimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                            </logic:notEqual>
                                            <logic:equal name="01"  value="0" >
                                            <img src="<%=contextPath%>/images/newImages/max.gif" width="9" height="12" onclick="javascript:postAjax('main','01',anchor +':_MINIMIZE:minimize:1:menuId:01:app:<%=menuActiveTemp[0]%>')" title="<bean:message key="title.main.hill.close.maxnimize" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
                                            </logic:equal>
                                        </td>
                                        <td width="15" class="report">
                                            <img src="<%=contextPath%>/images/newImages/del.gif" width="9" height="12" onclick="javascript:document.getElementById('01').innerHTML='';postAjax('main','openCombo',anchor +':_CLOSE_PORTLET:minimize:2:menuId:01:app:<%=menuActiveTemp[0]%>')" title="<bean:message key="title.main.hill.close.portlet" bundle="<%=interfaces%>"/>" style="cursor: pointer;"  />
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
                                                <div class="ct-news" style="text-align:left">
                                                
                                                   <logic:equal name="01" value="1" >
                                                        
                                                            <div class="textlink" >
                                                                <img src="<%=contextPath%>/images/newImages/doc/recv.gif" />
                                                                <a class="doc-link-main" href="javascript:post('change',anchor + ':_DOCS_RECV_LIST:statusId:<%=com.inf.doc.IKeyDoc.DOC_STATUS_VIEW_ALL%>:app:<%=menuActiveTemp[0]%>')" >
                                                                <b><bean:message key="menu.top.doc.recivers.caption" bundle="<%=interfaces%>"/>
                                                                        <logic:present name="BDocsRecvRead">
                                                                                (<bean:write name="BDocsRecvRead" property="amount"/>)
                                                                        </logic:present></b>
                                                                </a>
                                                            </div>
                                                            <div class="textlink">
                                                                   <img src="<%=contextPath%>/images/newImages/doc/send.gif" />
                                                                        <a class="doc-link-main" href="javascript:post('change',anchor + ':_DOCS_SEND_LIST:views:-1:statusId:<%=com.inf.doc.IKeyDoc.DOC_STATUS_VIEW_ALL%>:app:<%=menuActiveTemp[0]%>')" >
                                                                                <b><bean:message key="menu.top.doc.send.caption" bundle="<%=interfaces%>"/>            
                                                                                <logic:present name="BDocsSendRead">
                                                                                (<bean:write name="BDocsSendRead" property="amount"/>) 
                                                                                </logic:present></b>
                                                                        </a>
                                                            </div>
                                                            <div class="textlink">
                                                              <img src="<%=contextPath%>/images/newImages/doc/report.gif" />
                                                            <a class="doc-link-main" href="javascript:post('change',anchor + ':_DOCS_REPORTS');"><b><bean:message key="menu.top.report.caption" bundle="<%=interfaces%>"/></b></a></div>
                                                            <div class="textlink">
                                                              <img src="<%=contextPath%>/images/newImages/doc/dossier.gif" />
                                                            <a class="doc-link-main" href="javascript:post('change',anchor + ':_DOSSIERS_LIST');"><b><bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/></b></a></div>
                                                            <div class="textlink">
                                                              <img src="<%=contextPath%>/images/newImages/doc/init.gif" />
                                                            <a class="doc-link-main" href="javascript:post('change',anchor + ':_UNITS_LIST');"><b><bean:message key="main.docs.fromId" bundle="<%=interfaces%>"/></b></a></div>
                                                        
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

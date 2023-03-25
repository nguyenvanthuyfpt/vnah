<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table width="100%" style="border-collapse:collapse" cellpadding="0" cellspacing="0"  class="inforDetail" border="0" >
                    <tr>
                            <td>
                            <logic:equal name="error" value="1">
                                <div class="msgs-mail-sussec">
                                    <bean:message key="messages.insert.sussecss" bundle="<%=interfaces%>"/>
                                </div>
                            </logic:equal>
                            <logic:equal name="error" value="0">
                                <div class="msgs-mail-error">
                                    <bean:message key="errors.sendMail.fail" bundle="<%=interfaces%>"/>
                                </div>
                                <div class="msgs-mail-error-detail">
                                    <bean:message key="errors.sendMail.fail.detail" bundle="<%=interfaces%>"/>
                                </div>
                            </logic:equal>
                            </td>
                    </tr>
                    <logic:notEqual name="sendMail" property="subject" value="">
                    <tr>
                        <td align="left">
                          <bean:message key="mail.list.subject" bundle="<%=interfaces%>"/>:
                                <Strong><bean:write name="sendMail" property="subject" /></strong>
                        </td>
                    </tr>
                    </logic:notEqual>
                    
                    <tr>
                        <td valign="top">
                                <logic:notEqual name="sendMail" property="toAddress" value="">
                                <logic:notEmpty name="sendMail" property="toAddress" >
                                    <div align="left">
                                    <Strong><bean:message key="mail.list.userRecv" bundle="<%=interfaces%>"/>:</strong>
                                    
                                    <bean:define name="sendMail" property="toAddress" id="toAddress" type="java.lang.String" />
                                    <%
                                    if(toAddress.indexOf(";")>=0){
                                              String[] tos=toAddress.split(";");
                                              if(tos!=null && tos.length>0){
                                                for(int i=0;i<tos.length;i++){
                                                    if(!tos[i].equals("")){
                                                    String toTemp=tos[i].replaceAll("<","&lt;").replaceAll(">","&gt;");
                                                    %>
                                                    <%=toTemp%>
                                                    <%toTemp=toTemp.replaceAll("\"","");%>
                                                    <img title="<bean:message key='mail.header.address.addnew' bundle='<%=interfaces%>'/>" src="<%=contextPath%>/images/addUser.gif" onClick="javascript:checkedInnerHtml();addthis_open(this,'','','','',1);postAjax('formMyContact','at_share',anchor + ':_ADD_MYCONTACT:email:<%=toTemp%>');"/>
                                                    <%
                                                }}
                                              }
                                    }else{%>
                                        <bean:write name="sendMail" property="toAddress" />
                                        <img title="<bean:message key="mail.header.address.addnew" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/addUser.gif" onClick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key="mail.header.address.addnew" bundle="<%=interfaces%>"/>','','','',1);postAjax('formMyContact','at_share',anchor + ':_ADD_MYCONTACT:email:<bean:write name="sendMail" property="toAddress" />');"/>
                                    <%}%>
                                    </div>
                                </logic:notEmpty>
                                </logic:notEqual>
                                
                        </td>
                    </tr>

                    <logic:notEqual name="sendMail" property="cc" value="">
                    <logic:notEmpty name="sendMail" property="cc">
                    <tr>
                        <td>
                            <div align="left">
                            <Strong><bean:message key="mail.list.cc" bundle="<%=interfaces%>"/>:</strong>
                            
                                <bean:define name="sendMail" property="cc" id="cc" type="java.lang.String" />
                                    <%if(cc.indexOf(";")>=0){
                                                  String[] ccs=cc.split(";");
                                                  if(ccs!=null && ccs.length>0){
                                                    for(int i=0;i<ccs.length;i++){
                                                        if(!ccs[i].equals("")){
                                                        String ccTemp=ccs[i].replaceAll("<","&lt;").replaceAll(">","&gt;");
                                                        %>
                                                        <%=ccTemp%>
                                                    <%ccTemp=ccTemp.replaceAll("\"","");%>
                                                        <img title="<bean:message key="mail.header.address.addnew" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/addUser.gif" onClick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key="mail.header.address.addnew" bundle="<%=interfaces%>"/>','','','',1);postAjax('formMyContact','at_share',anchor + ':_ADD_MYCONTACT:email:<%=ccTemp%>');"/>
                                                        <%
                                                    }}
                                                  }
                                    }else{%>
                                        <bean:write name="sendMail" property="cc" />
                                        <img title="<bean:message key="mail.header.address.addnew" bundle="<%=interfaces%>"/>" src="<%=contextPath%>/images/addUser.gif" onClick="javascript:checkedInnerHtml();addthis_open(this,'<bean:message key="mail.header.address.addnew" bundle="<%=interfaces%>"/>','','','',1);postAjax('formMyContact','at_share',anchor + ':_ADD_MYCONTACT:email:<bean:write name="sendMail" property="cc" />');"/>
                                    <%}%>
                            </div>
                        </td>
                    </tr>
                    </logic:notEmpty>
                    </logic:notEqual>
                    <tr>
                        <td align="left">
                        <%String cmdCheckEmail="post('control',anchor + ':_INBOX:folderName:"+ com.inf.mail.IKeyMail.FOLDER_INBOX +"')";%>
                                <html:button property="_SEND" onclick="<%=cmdCheckEmail%>" styleClass="button">
                                <bean:message key="cmd.mail.check.inbox" bundle="<%=interfaces%>"/>
                        </html:button>
                        
                        <%String cmdPrepare="post('sendMail',anchor + ':_REPLY_PREPARE')";%>
                                <html:button property="_SEND" onclick="<%=cmdPrepare%>" styleClass="button">
                                <bean:message key="cmd.mail.check.reply.create" bundle="<%=interfaces%>"/>
                        </html:button>
                        
                        
                        <html:button property="_COMPLETE" onclick="javascript:closeDetailEmail();" styleClass="button">
                                <bean:message key="cmd.mail.check.complete" bundle="<%=interfaces%>"/>
                        </html:button>
                        
                        
                         <html:hidden name="sendMail" property="content" />       
                        </td>
                    </tr>
                    </table>
                   
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<html:form action="createMessage" enctype="multipart/form-data" method="post">
<div style="padding-right:5px;">
<table width="100%" cellpadding="0" class="TITLECLASS" cellspacing="0" ><tr><td class="LEFT" align="right"></td><td width="7px" class="RIGHT"></td></tr></table>
<div class="padding-content" style="margin-right:5px">
        <div class="tabmenu" id="container-1" >
            <div id="fragment-1">
               <div class="content-calendar" id="MainDirectory">      
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
                                <bean:message key="errors.sendMail" bundle="<%=interfaces%>"/>
                            </div>
                            </logic:equal>
                            </td>
                    </tr>
                    
                    
                    <logic:notEqual name="messsagesList" property="name" value="">
                    <tr>
                        <td align="left">
                                <bean:message key="mail.list.subject" bundle="<%=interfaces%>"/>:
                                <Strong><bean:write name="messsagesList" property="name" /></strong>
                        </td>
                    </tr>
                    </logic:notEqual>
                    
                    <logic:notEqual name="messsagesList" property="emps" value="">
                    <tr>
                        <td align="left">
                          <Strong><bean:message key="messages.list.empRecv" bundle="<%=interfaces%>"/>:</strong>
                                <bean:write name="messsagesList" property="emps" />
                        </td>
                    </tr>
                    </logic:notEqual>

                        <tr>
                            <td align="left">
                                <%String cmdCheckEmail="post('messsagesList',anchor + ':_CREATE_SUCCESS:type:1')";%>
                                <html:button property="_SEND" onclick="<%=cmdCheckEmail%>" styleClass="button">
                                <bean:message key="cmd.mail.check.inbox" bundle="<%=interfaces%>"/>
                                </html:button>
                                
                                <%String cmdPrepare="post('createMessage',anchor + ':_REPLY_PREPARE')";%>
                                <html:button property="_SEND" onclick="<%=cmdPrepare%>" styleClass="button">
                                <bean:message key="cmd.mail.check.reply.create" bundle="<%=interfaces%>"/>
                                </html:button>
                        
                                
                                <%String cmdcoplete="postAjax('messsagesList','right',anchor + ':_PREPARED_SAVE:type:1')";%>
                                <html:button property="_SEND" onclick="<%=cmdcoplete%>" styleClass="button">
                                <bean:message key="cmd.mail.check.complete" bundle="<%=interfaces%>"/>
                                </html:button>
                                
                            </td>
                        </tr>
                    </table>
                    <br/>
               </div>
            </div>
        </div>   
</div>
</div>
</html:form>
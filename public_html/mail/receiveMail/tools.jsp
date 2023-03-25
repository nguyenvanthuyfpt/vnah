<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<TABLE width="100%" cellSpacing=0 cellPadding=0 border=0 style="color:#FFFFFF" >
        <TR>
                <TD nowrap align="left" >
                    <Strong>
                    <logic:equal name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_INBOX%>">    
                    <bean:message key="mail.tree.recvMessage" bundle="<%=interfaces%>"/>
                    </logic:equal>
                    <logic:equal name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_SENT%>">    
                    <bean:message key="mail.tree.sendMessage" bundle="<%=interfaces%>"/>
                    </logic:equal>
                    <logic:equal name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_DRAFT%>">    
                    <bean:message key="mail.tree.draftMessage" bundle="<%=interfaces%>"/>
                    </logic:equal>
                    
                    <logic:equal name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_TRASH%>">    
                    <bean:message key="mail.tree.deleteMessage" bundle="<%=interfaces%>"/>
                    </logic:equal>
                    
                    <logic:equal name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_SPAM%>">    
                    <bean:message key="mail.tree.spamMessage" bundle="<%=interfaces%>"/>
                    </logic:equal>
                    
                    <bean:define name="BInBoxs" id="beans" type="com.form.FBeans"/>
                    (<bean:write name="beans" property="totalRows"/>)
                    </strong>:
                
                <html:radio name="sendMail" property="readed" value="0" onclick="javascript:post('sendMail',anchor+':_INBOX');"  />
                <bean:message key="mail.header.readedAll" bundle="<%=interfaces%>"/>
                
                
                <html:radio name="sendMail" property="readed" value="1" onclick="javascript:post('sendMail',anchor+':_INBOX');"  />
                <bean:message key="mail.header.unReaded" bundle="<%=interfaces%>"/>
                </TD>
                <TD nowrap align="right" class="linkEmail" >
                <A  href="javascript:checkHaveIdChecked('_PREPARE_REPLY');"><bean:message key="mail.header.replyMessage" bundle="<%=interfaces%>"/></A> |
                <A class="#" href="javascript:checkHaveIdChecked('_PREPARE_REPLY_ALL');"><bean:message key="mail.header.replyAllMessage" bundle="<%=interfaces%>"/></A> |
                <A class="#" href="javascript:checkHaveIdChecked('_PREPARE_FORWARD');"><bean:message key="mail.header.forwardMessage" bundle="<%=interfaces%>"/></A> |
                <A class="#" href="javascript:checkHaveIdChecked('_DELETE');"><bean:message key="mail.header.deleteMessage" bundle="<%=interfaces%>"/></A> |
                <A class="#" href="javascript:checkHaveIdChecked('_SPAM');"><bean:message key="mail.header.alert.message.spam" bundle="<%=interfaces%>"/></A>
                <logic:equal name="sendMail" property="folderName" value="<%=com.inf.mail.IKeyMail.FOLDER_TRASH%>">
                        <select name="restoreFolder" id="restoreFolder" onchange="javascript:if(this.value!=''){ checkHaveIdChecked('_RESTORE');}else{ return false} " style="width:80px">
                            <option value=""><bean:message key="mail.header.restoreMessage" bundle="<%=interfaces%>"/></option>
                            <option value="<%=com.inf.mail.IKeyMail.FOLDER_INBOX%>">--<bean:message key="mail.tree.recvMessage" bundle="<%=interfaces%>"/></option>
                            <option value="<%=com.inf.mail.IKeyMail.FOLDER_SENT%>">--<bean:message key="mail.tree.sendMessage" bundle="<%=interfaces%>"/></option>
                            <option value="<%=com.inf.mail.IKeyMail.FOLDER_DRAFT%>">--<bean:message key="mail.tree.draftMessage" bundle="<%=interfaces%>"/></option>
                        </select>
                </logic:equal>
                
               </td>
</TR></TABLE>

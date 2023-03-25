<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<bean:define name="BMessages" id="beans" type="com.form.FBeans"/>
<html:hidden name="createMessage" property="idRec" />
<html:hidden  name="createMessage" property="type" /> 
<TABLE width="100%" cellSpacing=0 cellPadding=0 border=0 style="color:#FFFFFF" >
<tr><td align="left">
<strong>
<logic:equal name="createMessage" property="type" value="1">
<bean:message key="app.inbox.messages" bundle="<%=interfaces%>"/>            
</logic:equal>

<logic:equal name="createMessage" property="type" value="2">
<bean:message key="app.send.item.messages" bundle="<%=interfaces%>"/>
</logic:equal>

<logic:equal name="createMessage" property="type" value="3">
 <bean:message key="app.delete.item.messages" bundle="<%=interfaces%>"/>          
</logic:equal>
        (<bean:write name="beans" property="totalRows"/>)</strong>
    
    <html:radio name="createMessage" property="readed" value="0" onclick="javascript:postAjax('createMessage','right',anchor+':_PREPARED_SAVE');"  />
    <bean:message key="mail.header.readedAll" bundle="<%=interfaces%>"/>
    <html:radio name="createMessage" property="readed" value="1" onclick="javascript:postAjax('createMessage','right',anchor+':_PREPARED_SAVE');"  />
    <bean:message key="mail.header.unReaded" bundle="<%=interfaces%>"/>
</td>
<TD nowrap align="right" class="linkEmail" >
<a style="cursor:pointer" onclick="checkHaveIdChecked('_PREPARED_EDIT',1)"><bean:message key="messages.form.send.rep" bundle="<%=interfaces%>"/></a>
|
<a style="cursor:pointer" onclick="checkHaveIdChecked('_REPLY_ALL',0)"><bean:message key="mail.header.replyAllMessage" bundle="<%=interfaces%>"/></a>
|
<a style="cursor:pointer" onclick="checkHaveIdChecked('_FORWARD',0)"><bean:message key="mail.header.forwardMessage" bundle="<%=interfaces%>"/></a>
<logic:notEqual name="messsagesList" property="type" value="3"> 
|
<a href="#"  onclick="javascript:postAjax('createMessage','MainMessage',anchor + ':_DELETE:delete:0');messageImg('MainMessage');">
<bean:message key="mail.header.deleteMessage" bundle="<%=interfaces%>"/> 
</a>
</logic:notEqual>

<logic:equal name="messsagesList" property="type" value="3"> 
| 
<a href="#"  onclick="javascript:postAjax('createMessage','MainMessage',anchor + ':_PREPARED_DELETE');messageImg('MainMessage');">
<bean:message key="messages.uncontent.caption" bundle="<%=interfaces%>"/>  
</a>                                 
|
<a href="#" onclick="javascript:postAjax('createMessage','MainMessage',anchor + ':_DELETE:delete:1');messageImg('MainMessage');">
<bean:message key="mail.header.deleteMessage" bundle="<%=interfaces%>"/> 
</a>
</logic:equal>
                           
</td>
</tr>
</table>

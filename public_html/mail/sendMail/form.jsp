<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
    function checkSendMail(obj,accountId){
    getObj('till').value=getObj('tillId').innerHTML;
        if(obj.form.toAddress.value==''){
            alert(<bean:message key="alert.error.toaddress.isNull.in.javascript" bundle="<%=interfaces%>"/>);
            return false;
        }else{
            obj.disabled=true;
            messageImg(obj);
            post('sendMail',anchor +':_SEND:accountId:'+accountId);
            
        }
    }
    function checkStoreMail(obj,accountId){
            obj.disabled=true;
            messageImg(obj);
            post('sendMail',anchor +':_STORE_TEMP:accountId:'+accountId);
    }
    
</script>

<script type="text/javascript" src="<%=contextPath%>/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/tinymce.js"></script>
<html:form action="sendMail" method="post" enctype="multipart/form-data" >
<bean:define name="sendMail" property="accountId" id="accountId" />
<div style="padding-right:5px;">
<table width="100%" cellpadding="0" class="TITLECLASS" cellspacing="0" >
    <tr><td class="LEFT" align="left"> </td><td width="7px" class="RIGHT"></td></tr>
</table>
<div class="padding-content" style="margin-right:5px">
        <div class="tabmenu" id="container-1" >
            <div id="fragment-1">
               <div class="content-calendar">      

<table class="tableForm" style="border-collapse: collapse"  width="100%"  cellpadding="0" cellspacing="0">
<tr><td class="toolCmd" style="text-align:left" nowrap colspan="2">
    <%String cmdSend="checkSendMail(this," + accountId + ")";%>
    <html:button property="_SEND" onclick="<%=cmdSend%>" styleClass="button">
    <bean:message key="cmd.mail.sendemail" bundle="<%=interfaces%>"/>
    </html:button>
    
    <%String cmdTemp="checkStoreMail(this," + accountId + ")";%>
    <html:button property="_SEND" onclick="<%=cmdTemp%>" styleClass="button">
        <bean:message key="mail.tree.draftMessage" bundle="<%=interfaces%>"/>
    </html:button>


    <span class="supportToolTip">
    <bean:message key="mail.lable.tip.on.form.input.support.address" bundle="<%=interfaces%>"/>    
    </span>
    <span style="padding-left:10px;">
    <a style="color:blue" href="javascript:hideshow('idCcs');hideshow('idAddress')"><bean:message key="mail.lable.tip.on.form.input.hideshow" bundle="<%=interfaces%>"/></a></span>
</td>
</tr>
<tr>
    <td nowrap valign="top" style="padding-top:10px"><bean:message key="mail.list.userRecv" bundle="<%=interfaces%>"/>:</td>
    <td width="100%" style="padding-top:10px">
        <jsp:include page="/mail/sendMail/toAddress.jsp"/>
    </td>
</tr>
<logic:notPresent name="replyAll">
<tr>
    <td nowrap valign="top">
    <div style="display:none" id="idCcs">
    <bean:message key="mail.list.cc" bundle="<%=interfaces%>"/>:
    </div>
    </td>
    <td >
    <div style="display:none" id="idAddress">
        <jsp:include page="/mail/sendMail/address.jsp"/>
    </div>
    </td>
</tr>
</logic:notPresent>

<logic:present name="replyAll">
<tr>
    <td nowrap valign="top">
    <div style="display:block" id="idCcs">
    <bean:message key="mail.list.cc" bundle="<%=interfaces%>"/>:
    </div>
    </td>
    <td >
    <div style="display:block" id="idAddress">
        <jsp:include page="/mail/sendMail/address.jsp"/>
    </div>
    </td>
</tr>
</logic:present>

<tr>
    <td nowrap><bean:message key="mail.list.subject" bundle="<%=interfaces%>"/>:</td>
    <td>
    <logic:notEmpty  name="sendMail" property="subject">
        <bean:define name="sendMail" property="subject" id="subject" />
        <logic:equal name="sendMail" property="type" value="1">
        <%subject="Re: " + subject;%>
        </logic:equal>
        <logic:equal name="sendMail" property="type" value="2">
        <%subject="Fwd: " + subject;%>
        </logic:equal>
        <input type="text" name="subject" id="subject" value="<%=subject%>" style="width:95%"  />
    </logic:notEmpty>
    
    <logic:empty  name="sendMail" property="subject">
        <input type="text" name="subject" id="subject" value="" style="width:95%" />
    </logic:empty>
    </td>
</tr>
<tr>
    <td nowrap valign="top"><bean:message key="mail.download" bundle="<%=interfaces%>"/>:</td>
    <td style="padding-left:0px;">
    <jsp:include page="/mail/sendMail/upload.jsp" />
    </td>
</tr>
</table>
<div onmouseover="autoHideList(event)">
     <logic:equal name="sendMail" property="type" value="0">
            <textarea  id="description" name="content" style="width:100%;height:300px" >
            <p>
            <bean:write name="sendMail" property="content"/>
            </p>
            </textarea>
            
    </logic:equal>
    
    <logic:notEqual name="sendMail" property="type" value="0">
        <textarea id="description" name="content" style="width:100%;height:300px" >
                <div id="301083id" align="right">
                    <div style="padding-left:5px;padding-right:5px;color:#787777;background:#f6f6f6;width:100px;position:relative;"  align="center">
                        <bean:message key="systen.out.email.till" bundle="<%=interfaces%>"/>
                    </div>
                </div>
            <br/><br/><br/>
            <br><span><bean:write name="sendMail" property="dateSend" />;</span>
            <Strong><bean:write name="sendMail" property="from" /></strong>
            
            <div style="margin:10px;padding:5px;border-left:1px solid #CCCCCC;">
            <bean:write name="sendMail" property="content"/>
            </div>
        </textarea>
    </logic:notEqual>
    
</div>

<table class="tableForm" style="border-collapse: collapse"  width="100%"  cellpadding="0" cellspacing="0">
<tr><td class="toolCmd" style="text-align:left" colspan="2">
<html:button property="_SEND" onclick="<%=cmdSend%>" styleClass="button">
<bean:message key="cmd.mail.sendemail" bundle="<%=interfaces%>"/>
</html:button>
    <html:button property="_SEND" onclick="<%=cmdTemp%>" styleClass="button">
        <bean:message key="mail.tree.draftMessage" bundle="<%=interfaces%>"/>
    </html:button>
    
    <span><input type="checkbox" name="flagged" id="flagged" value="1" />
    <bean:message key="mail.from.caption.priority" bundle="<%=interfaces%>"/>
    </span>
    
</td></tr>
</table>
</div>
</div>
</div>
</div>
</div>

<div id="tillId" style="display:none">
    <div id="301083id" align="right">
        <div style="padding-left:5px;padding-right:5px;color:#787777;background:#f6f6f6;width:100px;position:relative;"  align="center">
            <bean:message key="systen.out.email.till" bundle="<%=interfaces%>"/>
        </div>
    </div>
</div>

<html:hidden name="sendMail" property="till" styleId="till" value=""/>
</html:form>

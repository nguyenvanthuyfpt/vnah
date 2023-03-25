<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script type="text/javascript">
function checkSubmit(form){
    if(form.fullName.value==''  ||  form.email.value==''){
        return false;
    }
    return true;
}
</script>  
<div class="menuClass">
    <html:button property="_SEND" onclick="javascript:post('sendMail',anchor +':_PREPARE_SEND');" styleClass="button">
    <bean:message key="mail.header.createMessage" bundle="<%=interfaces%>"/>
    </html:button>
    <html:button property="_SEND" onclick="javascript:post('sendMail',anchor +':_INBOX');messageImg('QFrameTree');messageImg('tdMainBody');" styleClass="button">
    <bean:message key="mail.header.inputEmail" bundle="<%=interfaces%>"/>
    </html:button>
    <html:select name="sendMail" property="accountId" styleClass="fieldSelect" onchange="javascript:alert('dang phat trien tiep ')" >
            <logic:present name="BAccounts">
                <html:options collection="BAccounts" property="id" labelProperty="userMail"/>
            </logic:present>
          </html:select>
</div>

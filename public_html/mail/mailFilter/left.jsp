<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="control" method="post" />
<script language=javascript>
    function getInfor(ac){
      post('control',anchor + ':_INBOX:folderName:' + ac);
      messageImg('tdMainBody');
    }
    function checkSubmit(form){
    if(form.fullName.value==''  ||  form.email.value==''){
        return false;
    }
    return true;
}
</script>
<html:form action="mailFilter" method="post">        
<div id="left">
     <div class="ctn-left">
        <div class="title clearfix"><img src="<%=contextPath%>/images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic"><Strong><bean:message key="category.mailAccount" bundle="<%=interfaces%>"/></strong></div></div>
         <div class="csstable">
             <table cellpadding="0" cellspacing="0" border="0">
                <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_47.gif" /></td>
                    <td bgcolor="#a0aec2" style="padding:0 7px"><span class="add">
                            <a href="javascript:post('mailFilter',anchor +':_PREPARED_CREATE');"><img src="<%=contextPath%>/images/newImages/i_16.gif" align="left" /><Strong><bean:message key="category.mailAccount.register" bundle="<%=interfaces%>"/></strong></a>
                    </span></td>
                    <td width="8"><img src="<%=contextPath%>/images/newImages/i_46.gif" width="8" /></td>
                </tr>
             </table>
        </div>
                <div class="status" id="idEdit">
                    <jsp:include page="/mail/mailFilter/edit.jsp" />
                </div>
        </div>
</div>
</html:form>    
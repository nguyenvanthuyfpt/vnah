<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:select styleClass="inputbox" name="trailerStore" property="userSendId" onchange="javascript:postAjax('trailerStore','listDocs',anchor+':_VIEW');"  style="width:150px;" >
    <logic:present name="BUsersSend">
            <html:options collection="BUsersSend" property="id" labelProperty="fullName"/>
    </logic:present>
</html:select>
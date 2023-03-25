<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:select styleClass="inputbox" name="trailerStore" property="userRecvId" style="width:150px;" >
    <logic:present name="BUsersRecv">
            <html:options collection="BUsersRecv" property="id" labelProperty="fullName"/>
    </logic:present>
</html:select>
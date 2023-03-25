<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:select name="docsrecv" property="fromId" styleClass="fieldSelect" >
    <logic:present name="BFroms">
        <html:options collection="BFroms" property="id" labelProperty="vnName"/>
    </logic:present>
</html:select>

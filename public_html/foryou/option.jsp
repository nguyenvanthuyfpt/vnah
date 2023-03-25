<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:select name="forYou" property="userIdTo" styleClass="fieldSelect">
    <html:options collection="BUsers" property="id" labelProperty="fullName"/>
</html:select>     
        
     
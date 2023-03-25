<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:select name="BDate" property="userId" onchange="javascript:post('agenda',anchor + ':_CALENDAR_WEEK')" style="width:150px;" >
    <logic:present name="BUsers">
     <html:options collection="BUsers" property="id" labelProperty="fullName"/>
     </logic:present>
</html:select>
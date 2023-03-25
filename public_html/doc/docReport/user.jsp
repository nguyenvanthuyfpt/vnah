<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:select styleClass="inputbox" style="width:140px" name="docreport" property="userId" onchange="javascript:post('docreportLeft',anchor+':_VIEW');"  >
<logic:present name="BUsers">
<html:options collection="BUsers" property="id" labelProperty="fullName"/>
</logic:present>
</html:select>
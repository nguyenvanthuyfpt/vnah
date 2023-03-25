<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:select styleClass="inputbox" name="relative" property="idRelativeNkt">
<logic:present name="BRelativeNkts" >
<html:options collection="BRelativeNkts" property="id" labelProperty="nkt"/>
</logic:present>
</html:select> 

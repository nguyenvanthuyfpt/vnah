<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:select name="docssend" property="dossierId" styleClass="fieldSelect" >
<html:option value="0"> <bean:message key="form.dossiers.select.cation" bundle="<%=interfaces%>"/> </html:option>        
    <logic:present name="BDossiers">
     <html:options collection="BDossiers" property="id" labelProperty="name"/>
     </logic:present>
</html:select>
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
 <html:select styleClass="inputbox"  name="formMyContact" property="pgroupId">                                     
<logic:present name="BPgroups">
  <html:options collection="BPgroups" property="id" labelProperty="name"/>
</logic:present>
</html:select> 
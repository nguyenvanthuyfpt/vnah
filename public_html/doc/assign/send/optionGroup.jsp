<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BGroups">
  <html:select styleClass="inputbox"  name="docAssign" property="groupId" onchange="javascript:postAjax('docAssignSend','mainUsers',anchor+':_SHOW_GROUP')">                         
        <html:options collection="BGroups" property="id" labelProperty="name"/>                                          
</html:select> 
</logic:present>
 <div id="mainUsers">
    <jsp:include page="/doc/assign/send/userList.jsp"/>    
</div>
 
 
   

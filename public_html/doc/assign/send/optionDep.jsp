<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BDepartments">
  <html:select styleClass="inputbox"  name="docAssign" property="departmentId" onchange="javascript:postAjax('docAssignSend','mainUsers',anchor+':_SHOW')">                         
        <html:options collection="BDepartments" property="id" labelProperty="name"/>                                          
</html:select> 
</logic:present>   
<div id="mainUsers">
<jsp:include page="/doc/assign/send/userList.jsp"/>    
</div> 


 

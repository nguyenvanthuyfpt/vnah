 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>  
  <logic:present name="BSigns" >           
     <%  int i = 0;%>
     <logic:iterate name="BSigns" id="bean" type="com.form.admin.doc.rules.FDocRules"> 
<div style="cursor:pointer;padding-left:8px;padding-top:2px" onmouseover="this.className='mainTableMoveOver'"  onmouseout="this.className='#'" onclick="javascript:getObj('signer').value='<bean:write name="bean" property="userFullName" />';">
    <span> <bean:write name="bean" property="userFullName" /></Span>
</div>
 </logic:iterate>   
   </logic:present> 
 
     
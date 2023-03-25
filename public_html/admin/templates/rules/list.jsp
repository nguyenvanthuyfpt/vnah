 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <div id="winPopup" class="popup" align="center"></div>    
<html:form action="templateRule" method="POST">
<table width="100%" cellpadding="0" cellspacing="0">   
   <tr>
       <td valign="top" id="formEdit" width="62%"><jsp:include page="/admin/templates/rules/form.jsp"/></td>    
       <td valign="top" width="30%" style="padding-left:4px"> <jsp:include page="/admin/templates/rules/option.jsp"/> </td>
   </tr>            
</table>
</html:form>  
<br>
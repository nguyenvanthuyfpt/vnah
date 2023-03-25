 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="rules" method="POST">
<html:hidden name="rules" property="app"/>
<table width="100%" cellpadding="0" cellspacing="0">      
    <tr>
           <td valign="top" id="formEdit"><jsp:include page="/admin/tasks/rules/form.jsp"/></td>
           <td valign="top"  width="30%" style="padding-left:6px"><jsp:include page="/admin/tasks/rules/option.jsp"/></td> 
     </tr>    
</table>
</html:form>  

 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="ruleforyou" method="POST">
<table width="100%" cellpadding="0" cellspacing="0">          
    <tr>
           <td valign="top" id="formEdit">                                       
               <jsp:include page="/admin/foryou/rules/form.jsp"/>                 
           </td>
            <td valign="top" width="38%" style="padding-left:6px">                                       
               <jsp:include page="/admin/foryou/rules/option.jsp"/>                                         
           </td>
     </tr>
</table>
</html:form>

<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="observerrule" method="POST">
<table width="100%" cellpadding="0" cellspacing="0">       
    <tr>
           <td valign="top" id="formEdit">                                       
               <jsp:include page="/admin/doc/rules/observer/form.jsp"/>                                         
           </td>
     </tr>
       <tr>
        <td nowrap align="left"><jsp:include page="/admin/alert.jsp" /></td>
    </tr>  
     <tr>
          <td valign="top" style="padding-top:15px" id="formList">
                <jsp:include page="/admin/doc/rules/observer/listCmd.jsp"/>                  
         </td> 
    </tr>
</table>
</html:form> 

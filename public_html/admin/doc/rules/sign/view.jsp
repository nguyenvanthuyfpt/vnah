<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
 <html:form action="sign" method="POST">
    <table width="100%" cellpadding="0" cellspacing="0">       
        <tr>
               <td valign="top" id="formEditCreator">                                       
                   <jsp:include page="/admin/doc/rules/sign/form.jsp"/>                                         
               </td>
         </tr>          
        
         <tr>
              <td valign="top" style="padding-top:15px" id="formListSign">
                    <jsp:include page="/admin/doc/rules/sign/listCmd.jsp"/>                  
             </td> 
        </tr>
    </table>
</html:form>

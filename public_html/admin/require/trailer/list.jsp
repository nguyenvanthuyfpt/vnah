 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table width="100%" cellpadding="0" cellspacing="0">         
   <tr>
           <td valign="top" id="formEdit">                                       
               <jsp:include page="/admin/require/trailer/form.jsp"/>                                         
           </td>
     </tr>
       <tr>
        <td nowrap align="left" style="padding-left:6px;padding-right:6px;"><jsp:include page="/admin/alert.jsp" /></td>
    </tr>  
     <tr>
          <td valign="top" style="padding-top:15px" id="formList">
                <jsp:include page="/admin/require/trailer/listCmd.jsp"/>                  
         </td> 
    </tr>
</table>
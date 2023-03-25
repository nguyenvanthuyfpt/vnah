 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <div id="winPopup" class="popup" align="center"></div>    
<html:form action="reportRule" method="POST">
<table width="100%" cellpadding="0" cellspacing="0">       
    <tr>
           <td valign="top" id="formEdit">                                       
               <jsp:include page="/admin/reports/rules/form.jsp"/>                                         
           </td>
           <td valign="top"  width="30%" style="padding-left:6px">
                <jsp:include page="/admin/reports/rules/option.jsp"/>                  
         </td>
     </tr>     
</table>
</html:form>  
<br>
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table class="tablecontent" id="table6" style="border: 1px solid #77BBDD;z-index: 20;"  cellpadding="0" cellspacing="0" width="453px" align="center">   
  <tr>
        <TH class="tdheader">
           <strong><bean:message key="listReport.editname" bundle="<%=interfaces%>"/></strong>
        </TH></tr>
     <tr>
 <tr>
   <td>           
         <table class="popupWinInner" width="100%" cellpadding="0" cellspacing="0" border="0">
            <tr>
                    <td height="8px"></td>
            </tr>

             <tr>
                 <td id="tdMainName">                     
                          <jsp:include page="/list/editName.jsp"/>
                 </td>    
             </tr>
             <tr><td height="6px"></td></tr>
         </table>
   </td>
 </tr>     
</table> 

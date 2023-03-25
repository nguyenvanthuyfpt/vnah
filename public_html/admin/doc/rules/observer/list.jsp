 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<table width="100%" cellpadding="0" cellspacing="0">
    <tr>
            <td valign="top" id="mainObserver" width="36%">
                 <jsp:include page="/admin/doc/rules/observer/view.jsp"/>        
            </td>
            <td valign="top" style="padding-left:4px" id="mainCreator" width="37%">            
              <jsp:include page="/admin/doc/rules/creator/view.jsp"/>  
            </td>
            <td valign="top" style="padding-left:4px" id="mainSigiger" >            
             <jsp:include page="/admin/doc/rules/sign/view.jsp"/>  
            </td>
    </tr>
</table>

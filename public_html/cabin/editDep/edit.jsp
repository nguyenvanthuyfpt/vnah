<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table class="tableForm" cellpadding="0" align="center" style="border-collapse: collapse" cellspacing="0" border="0">    
           <tr>
                <td><bean:message key="title.cabin.label.name" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                <td align="left" colspan="3"><html:text name="cabin" property="name"/></td>
            </tr>  
            
             <tr>
                <td ><bean:message key="title.cabin.label.upFile" bundle="<%=interfaces%>"/> : 
                </td>
                <td colspan="2">
                <html:file name="cabin" property="upFile" style="width:220px;" /></td>
            </tr>   
    
     <tr>
            <td  class="toolCmd" style="padding-left:10px"  colspan="3">                
            <jsp:include page="/cabin/editDep/cmd.jsp" />
            </td>
        </tr> 
</table>

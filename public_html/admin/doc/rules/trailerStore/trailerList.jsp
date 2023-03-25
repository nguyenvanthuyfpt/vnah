<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="/trailerStore" method="POST">
<table class="adminform"  style="border-collapse: collapse" cellpadding="0" cellspacing="0" width="100%">
     <tr>
        <td align="left" colspan="4">
                <html:select styleClass="inputbox" name="trailerStore" property="workflowId"  style="width:107px;" onchange="postAjax('trailerStore','MainDocRules',anchor + ':_STORE_TRAILER_LOG');messageImg('MainDocRules');">
                 <html:options collection="BWorkflows" property="workflowId" labelProperty="title"/>
                </html:select>
        </td>
        <td align="right" colspan="3" id="buttonRestore" style="display:none;" >
                        <input type="button" name="_RESTORE" class="button" onclick="javascript:if(restore())postAjax('trailerStore','MainDocRules',anchor + ':_RESTORE')" value="<bean:message key="title.cabin.restore" bundle="<%=interfaces%>"/>" />
        </td>
    </tr>
</table>
<div  style="padding-top:6px"><jsp:include page="/admin/doc/rules/trailerStore/list.jsp" /></div>
</html:form>

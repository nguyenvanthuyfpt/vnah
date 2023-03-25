<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table width="100%" noborder style="border-collapse: collapse"  cellpadding="0" cellspacing="0">
<tr><td  class="title-01" nowrap>
    <a href="#" class="li-title-02">
        <bean:message key="mycontact.title.caption" bundle="<%=interfaces%>"/> 
    </a>
</td>
</tr>
</table>
<div class="ct-celendar"><jsp:include page="/mycontact/manager/form.jsp"/></div>
<div class="ct-celendar" style="text-align:center" id="MainDirectory"> 
   <jsp:include page="/mycontact/manager/list.jsp"/>
</div>
<br>

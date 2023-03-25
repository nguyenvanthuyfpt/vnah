<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table width="100%"  style="border-collapse: collapse"  cellpadding="0" cellspacing="0">
<tr><td  class="title-01" nowrap>
    <a href="#" class="li-title-02">
 <bean:message key="form.searchDocs.header" bundle="<%=interfaces%>"/>
 </a>
</td>
</tr>
</table>

<br>
<html:form action="docsSearch" method="post" onsubmit="javascript:post('docsSearch',anchor + ':_SEARCH');" >
             <jsp:include page="/doc/docsSearch/form.jsp"/>
</html:form>



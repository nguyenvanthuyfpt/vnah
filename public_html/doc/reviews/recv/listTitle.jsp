<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<logic:present name="BThePen" >
<logic:iterate name="BThePen" id="bean" indexId="i" type="com.form.admin.doc.category.thePen.FThePen">        
<div style="cursor:pointer;" onmouseover="this.className='mainTableMoveOver'"  onmouseout="this.className='#'">
    <span  onclick="javascript:document.docReviewRecv.title.value='<bean:write name="bean" property="name" />';"><bean:write name="bean" property="name" /></Span>
</div>
</logic:iterate>
</logic:present>


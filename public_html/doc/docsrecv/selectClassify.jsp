<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
 <% if (com.inf.doc.IKeyDoc.DOC_CLASSIFY_ACTIVE==1) {%> 
<p><font style="color:#a0aec2"><b><bean:message key="categoryclassify.classify" bundle="<%=interfaces%>"/></b></font></p>
<html:select name="docsrecv" property="classifyId" onchange="javascript:post('docsrecv',anchor+':_VIEW');" styleClass="fieldSelect" style="width:100px;" >
    <html:option value="0" key="status.all" bundle="<%=interfaces%>"/>
    <logic:present name="BClassify">
        <html:options collection="BClassify" property="id" labelProperty="name"/>
    </logic:present>
    
</html:select>
<%}%> 

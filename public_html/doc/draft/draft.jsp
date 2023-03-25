<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%if(me.getExtTagString()==null || me.getExtTagString().equals("")){%>
<html:file name="docAssign" property="fileUpload" size="30"/>
<%}else{%>
<a href="javascript:post('docReviewSend',anchor+':_VIEW_DRAFT');remove('docReviewSend',anchor);"><%=me.getExtTagString()%></a>
<%}%>
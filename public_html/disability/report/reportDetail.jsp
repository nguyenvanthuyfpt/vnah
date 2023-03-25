<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<tiles:insert page="/layout/layoutHome.jsp" flush="true">
    <tiles:put name="tree" value="/disability/rightContent.jsp" />          
    <tiles:put name="content" value="/disability/report/detail.jsp" /> 
</tiles:insert> 

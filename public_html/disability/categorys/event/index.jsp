<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>

<tiles:insert page="/layout/layoutVnah.jsp" flush="true">
    <tiles:put name="tree" value="/disability/leftContent.jsp" />          
    <tiles:put name="content" value="/disability/categorys/event/listHome.jsp" /> 
</tiles:insert> 
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
	
<html:form action="dr_unit" method="post">
<div>
    <ul id="tree">
        <li class="">
            <div class="bgr5"><a href="javascript:post('disabilityFuntion',anchor + ':_UNIT')">Th&#244;ng tin n&#417;i cung c&#7845;p DV</a></div>
        </li>	
    </ul>
</div>

<br/>	
	
<div class="content-calendar">
    <div align="center"><jsp:include page="/disability/unit/form.jsp"/></div>
    <div align="center" id="listId"><jsp:include page="/disability/unit/list.jsp"/></div>  
</div>
</html:form>
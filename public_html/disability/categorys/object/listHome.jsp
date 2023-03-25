<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
	
<html:form action="object" method="post">
<div>
    <ul id="tree">
        <li class="">
            <div class="bgr5"><a href="#"><bean:message key="object.title.caption" bundle="<%=interfaces%>"/></a></div>
        </li>	
    </ul>
</div>

<br/>	
	
<div class="content-calendar">
    <div align="center"><jsp:include page="/disability/categorys/object/form.jsp"/></div>
    <div align="center" id="listId"><jsp:include page="/disability/categorys/object/list.jsp"/></div>  
</div>
</html:form>
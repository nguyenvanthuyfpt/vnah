<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="danSoTinhList" method="post"  />
<html:form action="danSoTinh" method="post"  >
<div><jsp:include page="/disability/danSoTinh/form.jsp"/></div>
<div style="height:20px"></div>
<div align="center" id="listIddanSoTinh">
    <jsp:include page="/disability/danSoTinh/list.jsp"/>
</div>  
</html:form>
 

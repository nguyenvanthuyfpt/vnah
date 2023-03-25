<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div id="left">
     <div class="ctn-left">
        <div class="title clearfix">
            <img src="<%=contextPath%>/images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic"><bean:message key="drectory.title.caption" bundle="<%=interfaces%>"/> </div>
        </div>
        <jsp:include page="/directory/form.jsp"/>
    </div>
</div>

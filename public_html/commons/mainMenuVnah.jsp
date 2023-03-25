<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script type="text/javascript">buildpopup();
function changeImager(imageToFind,imageToReplace){

}
</script>
<span class="bt_left_linkNav">
<span class="bt_right_linkNav">
    <a href="#"><%=me.getFullName()%></a>.
    <a class="modal-button" href="login<%=extention%>?<%=anchor%>=_PREPARED_EDIT" rel="{handler: 'iframe', size: {x:250, y: 180}}"><bean:message key="login.change.password" bundle="<%=interfaces%>"/></a>.
    <!--<a href="javascript:post('change',anchor + ':_HELP')"><bean:message key="app.help" bundle="<%=interfaces%>"/></a>.-->
    <% 
        int userId = (int)me.getId();
        String path = (userId==375)?"admin":"user";
    %>
    <a href="help/<%=path%>/UserGuide_v1.2.doc"><bean:message key="app.help" bundle="<%=interfaces%>"/></a>.
    <a href="javascript:post('login',anchor + ':_LOGOUT');"><bean:message key="logout.caption" bundle="<%=interfaces%>"/></a>
	<!--<html:link page="/admin/home/"><bean:message key="logout.caption" bundle="<%=interfaces%>"/></html:link>-->
</span>
</span>

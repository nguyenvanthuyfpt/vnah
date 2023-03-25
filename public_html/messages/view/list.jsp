<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="createMessage"  method="post"> 
    <div id="mailcol">
        <div class="tabmenu" id="container-1" >
            <div style="clear:both"></div>
            <ul id="ui-tabs-nav" style="padding-top:10px;padding-right:10px;">
            <jsp:include page="/messages/view/menu.jsp" />
            </ul>
            <div id="fragment-1" >
                    <div class="content-calendar" >
                           <div id="MainMessage" valign="top" >
                                    <jsp:include page="/messages/view/listView.jsp"/>
                            </div>
                    </div>
    </div>
    </div>
</div>
</html:form>
 
 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="from" method="post" >
        <div id="mailcol">
            <div class="tabmenu" id="container-1" >
                <div style="clear:both"></div>
                <jsp:include page="/commons/menuDoc.jsp"><jsp:param name="optionmenu" value="4"/></jsp:include>
                <div id="fragment-1">
                     <div class="content-calendar">                                               
                         <jsp:include page="/doc/from/list.jsp"/>                              
                    </div>
                </div>      
            </div>
        </div>                 
</html:form>

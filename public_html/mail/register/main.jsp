<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div class="padding-content">
    <div id="mailcol">
        <div class="tabmenu" id="container-1" >
            <div style="clear:both"></div>
            <jsp:include page="/mail/register/tab.jsp"><jsp:param name="optionmenu" value="0"/></jsp:include>
            <div id="fragment-1">
               <div class="content-calendar">      
                  <jsp:include page="/mail/register/list.jsp" />               
                </div>
            </div>
                  
        </div>
    </div>               
</div>

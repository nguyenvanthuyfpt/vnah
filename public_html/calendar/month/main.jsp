<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div id="winPopup" class="popup"></div>
<html:form action="agenda" method="post">
<div class="padding-content">     
<div id="mailcol">
    <div class="tabmenu" id="container-1" >
        <div style="clear:both"></div>
        <div>   
        <jsp:include page="/calendar/tag.jsp" ><jsp:param name="optionmenu" value="0"/></jsp:include>
        </div>
        <div id="fragment-1">           
            <div class="content-calendar" id="vtCalendar">
              <jsp:include page="/calendar/month/calendarMonth.jsp" />
            </div>
        </div>              
    </div>
</div>    
</div>
<html:hidden name="agenda" property="type" styleId="type"  />
 </html:form>

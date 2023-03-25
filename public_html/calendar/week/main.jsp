<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
        function mdotabOption(obj,params){
        if(obj.className=='tab'){
                for(i=0;i<obj.parentNode.cells.length-1;i++){
                    obj.parentNode.cells[i].className='tab';
                }
                obj.className='tabactive';       
                post('agenda',anchor + ':' + params);
        }    
        }

</script>
<div id="winPopup" class="popup"></div>
<html:form action="agenda" method="post">
<div class="padding-content">
<div id="mailcol">
    <div class="tabmenu" id="container-1" >
        <div style="clear:both"></div>
        <div>   
        <jsp:include page="/calendar/tag.jsp" ><jsp:param name="optionmenu" value="1"/></jsp:include>
        </div>
        <div id="fragment-1">            
            <div class="content-calendar" id="vtCalendar">
              <jsp:include page="/calendar/week/week.jsp" />
            </div>
        </div>
              
    </div>
</div> 
</div>
</html:form>

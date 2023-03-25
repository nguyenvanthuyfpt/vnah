<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script type="text/javascript"> 
    function postTab(obj,params){
        if(obj.className==''){
            for(i=0;i<obj.parentNode.parentNode.childNodes.length;i++){
                if(obj.parentNode.parentNode.childNodes[i].className=='ui-tabs-selected') 
                    obj.parentNode.parentNode.childNodes[i].className='';
            }
            obj.parentNode.className='ui-tabs-selected';
            postAjax('kpi','MainCate',anchor + ':' + params);
            messageImg('MainCate');
        }
    }
    
    function changeTab(nktId) {
        document.getElementById('li_0').className= '';
        messageImg('MainCate');
        document.getElementById('li_1').className= 'ui-tabs-selected';
    }
</script>

<div>
    <ul id="tree">
        <li class="">
            <div class="bgr5"><a href="#"><bean:message key="kpi.title.caption" bundle="<%=interfaces%>"/></a></div>
        </li>	
    </ul>
</div>

<br/>	
<html:form action="kpi" method="post" onsubmit="return false;">    
    <bean:define name="kpi" property="type" id="typeVal"  type="java.lang.Integer" />    
    <%
        String strType = String.valueOf(typeVal);        
    %>    
    <jsp:include page="/disability/kpi/form.jsp"/>    
</html:form>
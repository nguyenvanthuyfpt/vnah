<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript"> 
function postTabMessages(obj,params){
if(obj.className==''){
        for(i=0;i<obj.parentNode.parentNode.childNodes.length;i++){
                if(obj.parentNode.parentNode.childNodes[i].className=='ui-tabs-selected') obj.parentNode.parentNode.childNodes[i].className='';
        }
        obj.parentNode.className='ui-tabs-selected';
        post('change',anchor +  params);      
    }
}
</script>
<%int optionTab=0;
if (request.getParameter("optionmenu")!=null && !request.getParameter("optionmenu").toString().equals("")){
  optionTab = Integer.parseInt(request.getParameter("optionmenu").toString());
}
%>
<ul id="ui-tabs-nav">  
    <li class="<%=optionTab==0?"ui-tabs-selected":"optionTab"%>"><a href="#" onclick="postTabMessages(this,':_MESSAGES:type:1');messageImg('MainMessage');"> <bean:message key="app.inbox.messages" bundle="<%=interfaces%>"/></a></li>
    <li class="<%=optionTab==1?"ui-tabs-selected":"optionTab"%>"><a href="#" onclick="postTabMessages(this,':_MESSAGES:type:2');messageImg('MainMessage');"><bean:message key="app.send.item.messages" bundle="<%=interfaces%>"/></a></li>
    <li class="<%=optionTab==2?"ui-tabs-selected":"optionTab"%>"><a href="#" onclick="postTabMessages(this,':_MESSAGES:type:3');messageImg('MainMessage');"><bean:message key="app.delete.item.messages" bundle="<%=interfaces%>"/></a></li>
</ul>
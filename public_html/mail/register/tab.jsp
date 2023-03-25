<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript"> 
function postTab(obj,formName,params){
if(obj.className==''){
        for(i=0;i<obj.parentNode.parentNode.childNodes.length;i++){
                if(obj.parentNode.parentNode.childNodes[i].className=='ui-tabs-selected') obj.parentNode.parentNode.childNodes[i].className='';
        }
        obj.parentNode.className='ui-tabs-selected';
        post(formName,anchor + ':' + params);
    }
}
</script>
<%int optionTab=0;%>
<%
if (request.getParameter("optionmenu")!=null && !request.getParameter("optionmenu").toString().equals("")){
  optionTab = Integer.parseInt(request.getParameter("optionmenu").toString());
}
%>
<ul id="ui-tabs-nav">
    
    <li class="<%=(optionTab==0)?"ui-tabs-selected":""%>"><a href="#" onclick="javascrip:postTab(this,'main','_LIST_ACCOUNT');"><bean:message key="category.mailAccount" bundle="<%=interfaces%>"/></a></li>
    
    <li class="<%=(optionTab==1)?"ui-tabs-selected":""%>"><a href="#" onclick="javascrip:postTab(this,'main','_LIST_FILTER');"><bean:message key="category.mailAccount.config.filter" bundle="<%=interfaces%>"/></a></li>
</ul>
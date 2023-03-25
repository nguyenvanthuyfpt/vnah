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
<logic:notEmpty name="problem" property="type" >
 <bean:define name="problem" property="type" id="type" type="java.lang.Integer"/>
    <% optionTab = type; %>
</logic:notEmpty>
<%  int[] menuActiveTemp = (int[])request.getSession().getAttribute("menuActive");%>
<ul id="ui-tabs-nav">  
    <li class="<%=optionTab==0?"ui-tabs-selected":"optionTab"%>"><a href="#" onclick="postTabMessages(this,':_TASKS_ASSIGN:type:0:app:<%=menuActiveTemp[2]%>');messageImg('MainMessage');"> <bean:message key="problem.detail.caption" bundle="<%=interfaces%>"/></a></li>
    <li class="<%=optionTab==1?"ui-tabs-selected":"optionTab"%>"><a href="#" onclick="postTabMessages(this,':_TASKS_ASSIGN:type:1:app:<%=menuActiveTemp[2]%>');messageImg('MainMessage');"><bean:message key="doc.reci.title.caption" bundle="<%=interfaces%>"/></a></li>
    <li class="<%=optionTab==2?"ui-tabs-selected":"optionTab"%>"><a href="#" onclick="postTabMessages(this,':_TASKS_ASSIGN_CATEGORY:type:2:app:<%=menuActiveTemp[2]%>');messageImg('MainMessage');"><bean:message key="problem.categories" bundle="<%=interfaces%>"/></a></li>
</ul>
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp"%>
<script language="javascript">
function mdotabOption(obj,params){
if(obj.className==''){
        for(i=0;i<obj.parentNode.parentNode.childNodes.length;i++){
                if(obj.parentNode.parentNode.childNodes[i].className=='ui-tabs-selected') obj.parentNode.parentNode.childNodes[i].className='';
        }
        obj.parentNode.className='ui-tabs-selected';
        post('agendaXe',anchor + ':' + params);
    }
}
</script>
<%int optionTab=0;%>
<logic:present parameter="optionmenu">
<%optionTab = Integer.parseInt(request.getParameter("optionmenu").toString());%>
</logic:present>
<table width="100%" cellpadding="0" cellspacing="0">
    <tr>
        <td valign="top" width="180px">
                <div class="ctn-left">
                    <div class="title clearfix"><img src="<%=contextPath%>/images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic">
                      <bean:message key="key.calendar.xe.title" bundle="<%=interfaces%>"/>
                    </div></div>
                </div>  
        </td>
        <td valign="top" >
            <ul id="ui-tabs-nav">
                <li class="<%=(optionTab==0)?"ui-tabs-selected":""%>"><a href="#" onclick="mdotabOption(this,'_CALENDAR_MONTH');">
                <bean:message key="label.module.calendar.motnh" bundle="<%=interfaces%>"/>
                </a></li>
                <li class="<%=(optionTab==1)?"ui-tabs-selected":""%>" ><a href="#" onclick="mdotabOption(this,'_CALENDAR_WEEK');">
                <bean:message key="label.module.calendar.weeks" bundle="<%=interfaces%>"/>
                </a></li>
            </ul>
        </td>
    </tr>
</table>
            
            
            
 

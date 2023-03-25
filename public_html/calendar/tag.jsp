<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp"%>
<script language="javascript">
function mdotabOption(obj,params){
if(obj.className==''){
        for(i=0;i<obj.parentNode.parentNode.childNodes.length;i++){
                if(obj.parentNode.parentNode.childNodes[i].className=='ui-tabs-selected') obj.parentNode.parentNode.childNodes[i].className='';
        }
        obj.parentNode.className='ui-tabs-selected';
        post('agenda',anchor + ':' + params);
    }
}
</script>
<%int optionTab=0;%>
<logic:present parameter="optionmenu">
<%optionTab = Integer.parseInt(request.getParameter("optionmenu").toString());%>
</logic:present>
<bean:define name="agenda" property="type" id="type" type="java.lang.Integer" />

<table width="100%" cellpadding="0" cellspacing="0">
    <tr>
        <td valign="top" width="180px">
                <div class="ctn-left">
                    <div class="title clearfix"><img src="<%=contextPath%>/images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic">
                      <html:select name="agenda" property="type" styleClass="calendarCombo"  onchange="javascript:post('change',anchor + ':_CALENDA_PRIVATE:type:'+this.value)">
                        <html:option value="0"><bean:message key="agenda.form.edit.title.hearder" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="1"><bean:message key="publicAgenda.form.edit.title.hearder" bundle="<%=interfaces%>"/></html:option>
                        <html:option value="2"><bean:message key="main.calendar.in.department" bundle="<%=interfaces%>"/></html:option>
                      </html:select>
                    </div></div>
                </div>  
        </td>
        <td valign="top" >
            <ul id="ui-tabs-nav">
                <li class="<%=(optionTab==0)?"ui-tabs-selected":""%>"><a href="#" onclick="mdotabOption(this,'_CALENDAR_MONTH:type:<%=type%>');">
                <bean:message key="label.module.calendar.motnh" bundle="<%=interfaces%>"/>
                </a></li>
                <li class="<%=(optionTab==1)?"ui-tabs-selected":""%>" ><a href="#" onclick="mdotabOption(this,'_CALENDAR_WEEK:type:<%=type%>');">
                <bean:message key="label.module.calendar.weeks" bundle="<%=interfaces%>"/>
                </a></li>
            </ul>
        </td>
    </tr>
</table>
            
            
            
 

<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<input type="hidden" name="typeCalendar" id="typeCalendar" value="1" />
<script language="javascript">
function showTT(){
    post('agendaXe',anchor + ':_CALENDAR_MONTH');
}
function prepareCalendar(timeValue,dateValue,id){
        var times=timeValue.replace(":","#")
        var typeCalendar=getObj('typeCalendar').value;
        var values=':times:'+times+':timeEvent:'+dateValue+':id:'+id+':typeCalendar:'+typeCalendar;
        openWindow('agendaXe',anchor + ':_PREPARED_CREATE'+values);
}

function getDataByDate(obj,date){
    Tip('<div id=calendarExt></div>', TITLE,date, WIDTH,250, SHADOW, true, STICKY, 1, CLOSEBTN, true, CLICKCLOSE, true);
    postAjax('agendaXe','calendarExt',anchor + ':_DETAIL_CALENDAR:timeEvent:'+date);
}


</script>
               <bean:define name="agendaXe" id="beanDate" type="com.form.calendarXe.FCalendarXe" />
               <html:hidden name="beanDate" property="calendarType" />
               <%int monthSelect=beanDate.getMonth(beanDate.stringToSqlDate(beanDate.getSelectDate()));%>
                <% int withForm =430;int heightForm =530;%>

<table style="border-collapse: collapse"  cellspacing="0" cellpadding="0" width="100%" class="hearde-calenda" >
<tr>
                <td width="300px" align="left" >
                <div id="combobox">
                <html:hidden  name="beanDate" property="selectDate" styleId="selectDate" />
                <a style="cursor:pointer" onclick="popUpCalendar(this,'selectDate','dd/mm/yyyy','showTT()');getObj('lblToday').innerHTML=getObj('idHomeCurrent').innerHTML;">
                <img src="images/ew_calendar.gif" />
                <bean:message key="agenda.label.option.day" bundle="<%=interfaces%>"/>
                </a>
                 - 
                <Strong><bean:write name="beanDate" property="selectDate" /></strong>
                    </div>
                </td>
                <td align="right">
                <div id="idHomeCurrent" style="display:none">
                <a href="javascript:post('agendaXe',anchor + ':_HOME_CURRENT');"><bean:message key="agenda.label.today" bundle="<%=interfaces%>"/>,<bean:message key="agenda.form.edit.timeEvent" bundle="<%=interfaces%>"/> <%=beanDate.getDate(beanDate.getCurrentSqlDate())%>  <bean:message key="agenda.label.month" bundle="<%=interfaces%>"/> <%=beanDate.getMonth(beanDate.getCurrentSqlDate())%> <bean:message key="agenda.label.year" bundle="<%=interfaces%>"/> <%=beanDate.getYear(beanDate.getCurrentSqlDate())%></a>
                </div>
                </td>
</tr>
</table>
<table style="border-collapse: collapse"  cellspacing="0" cellpadding="0" width="100%" class="popcal" >
<tr bgcolor="#d5d5d5">
                <th   width="7%" ><bean:message key="agenda.label.week" bundle="<%=interfaces%>"/></th>
		<th  width="13%"><bean:message key="agenda.label.two" bundle="<%=interfaces%>"/></th>
		<th  width="13%"><bean:message key="agenda.label.three" bundle="<%=interfaces%>"/></th>
		<th  width="13%"><bean:message key="agenda.label.for" bundle="<%=interfaces%>"/></th>
		<th  width="13%"><bean:message key="agenda.label.file" bundle="<%=interfaces%>"/></th>
		<th  width="13%"><bean:message key="agenda.label.six" bundle="<%=interfaces%>"/></th>
                <th  width="13%"><bean:message key="agenda.label.serven" bundle="<%=interfaces%>"/></th>
                <th  width="13%" ><bean:message key="agenda.label.cn" bundle="<%=interfaces%>"/></th>
</tr>
<%int i=0;%>
<logic:iterate name="BEvents" id="bean" type="com.form.calendarXe.FCalendarXe">
    <%if(i%7==0){%>
    <tr><td style="text-align:center" >
    <a href="javascript:post('agendaXe',anchor + ':_SELECT_WEEK:id_week:<%=bean.getDayId()%>')"><bean:message key="agenda.label.week" bundle="<%=interfaces%>"/> <%=i/7+1%></a>
    </td>
    <%}%>
    <%if(bean.getMonth(bean.getDay())!=monthSelect){%>
                <td class="empty" valign="top" >
                    
                    <div class="calendarDays" onclick="javascript:prepareCalendar('','<%=bean.getTimeEvent()%>',0)">
                        <%= bean.getDate(bean.getDay())+"/"+bean.getMonth(bean.getDay())%>
                    </div>
                    
                   <logic:iterate name="bean" property="eventsInDay"  id="beanInDay" indexId="k" type="com.form.calendarXe.FCalendarXe">
                    <%if(k<3){%>
                    <div  class="calendarPortlet" >
                          <bean:write name="beanInDay" property="times" />-<bean:write name="beanInDay" property="toTimes" /> : <bean:write name="beanInDay" property="baseEmp" />
                    </div>  
                  <%}%>
                   <%if(k==3){%>
                    <div><a href="javascript:getDataByDate(this,'<%=beanInDay.getTimeEvent()%>')">+ Detail</a></div>
                   <%}%>
                    </logic:iterate>

                    
                </td>
    <%}else if((i+1)%7==0){%>
               <td class="weekend" valign="top" >
                    <div class="calendarDays" onclick="javascript:prepareCalendar('','<%=bean.getTimeEvent()%>',0)">
                        <%= bean.getDate(bean.getDay())+"/"+bean.getMonth(bean.getDay())%>
                    </div>

                   <logic:iterate name="bean" property="eventsInDay"  id="beanInDay" indexId="k" type="com.form.calendarXe.FCalendarXe">
                    <%if(k<3){%>
                    <div  class="calendarPortlet" >
                          <span onclick="javascript:prepareCalendar('<%=beanInDay.getTimes()%>','<%=beanInDay.getTimeEvent()%>',<%=beanInDay.getEventId()%>)"><bean:write name="beanInDay" property="times" />-<bean:write name="beanInDay" property="toTimes" /> : <bean:write name="beanInDay" property="baseEmp" /></span>
                    </div>  
                   <%}%><%if(k==3){%>
                    <div><a href="javascript:getDataByDate(this,'<%=beanInDay.getTimeEvent()%>')">+ Detail</a></div>
                   <%}%>
                    </logic:iterate>
                </td>
    <%}else{%>
                        <%if(bean.getDayId()==beanDate.getDayId()){%>
                            <td class="current" valign="top" >
                            <div class="calendarDays" onclick="javascript:prepareCalendar('','<%=bean.getTimeEvent()%>',0)">
                            <%= bean.getDate(bean.getDay())+"/"+bean.getMonth(bean.getDay())%>
                            </div>
                            
                   <logic:iterate name="bean" property="eventsInDay"  id="beanInDay" indexId="k" type="com.form.calendarXe.FCalendarXe">
                    <%if(k<3){%>
                    <div  class="calendarPortlet" >
                          <span onclick="javascript:prepareCalendar('<%=beanInDay.getTimes()%>','<%=beanInDay.getTimeEvent()%>',<%=beanInDay.getEventId()%>)"><bean:write name="beanInDay" property="times" />-<bean:write name="beanInDay" property="toTimes" /> : <bean:write name="beanInDay" property="baseEmp" /></span>
                    </div>  
                   <%}%>
                   <%if(k==3){%>
                    <div><a href="javascript:getDataByDate(this,'<%=beanInDay.getTimeEvent()%>')">+ Detail</a></div>
                   <%}%>
                    </logic:iterate>

                        </td> 
                        <%}else{%>
                            <td valign="top" >
                            <div class="calendarDays" onclick="javascript:prepareCalendar('','<%=bean.getTimeEvent()%>',0)">
                            <%= bean.getDate(bean.getDay())+"/"+bean.getMonth(bean.getDay())%>
                            </div>
                   <logic:iterate name="bean" property="eventsInDay"  id="beanInDay" indexId="k" type="com.form.calendarXe.FCalendarXe">
                    <%if(k<3){%>
                    <div  class="calendarPortlet" >
                          <span onclick="javascript:prepareCalendar('<%=beanInDay.getTimes()%>','<%=beanInDay.getTimeEvent()%>',<%=beanInDay.getEventId()%>)"><bean:write name="beanInDay" property="times" />-<bean:write name="beanInDay" property="toTimes" /> : <bean:write name="beanInDay" property="baseEmp" /></span>
                    </div>  
                  <%}%><%if(k==3){%>
                   <div><a href="javascript:getDataByDate(this,'<%=beanInDay.getTimeEvent()%>')">+ Detail</a></div>
                   <%}%>
                    </logic:iterate>
                                
                            </td> 
                        <%}%>
    <%}%>
    <% i++;if(i%7==0){%>
    </tr>
    <%};%>
</logic:iterate>  
</table>
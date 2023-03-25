<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<bean:define name="calendarDay" id="bean" type="com.form.calendar.agenda.FAgenda" />
<% String fromTime=bean.getTimes();
                int time1=bean.stringToInt(fromTime.split(":")[0]);
                int militi1=bean.stringToInt(fromTime.split(":")[1]);
                String toTime=bean.getToTimes();
                int time2=bean.stringToInt(toTime.split(":")[0]);
                int militi2=bean.stringToInt(toTime.split(":")[1]);
                int phut=militi2-militi1;
                if(phut<0){
                    phut=phut*(-1);
                }
               phut+=(time2-time1)*60;
               %>
<div class="calendarDayPortlet" onclick="prepareCalendar('<%=bean.getTimes()%>','<%=bean.getTimeEvent()%>',<%=bean.getEventId()%>)" style="height:<%=(phut*25)/30%>px"  >
    <div class="calendarTitle"><bean:write name="bean" property="times" />-<bean:write name="bean" property="toTimes" /></div>
    <bean:write name="bean" property="what" />
</div>

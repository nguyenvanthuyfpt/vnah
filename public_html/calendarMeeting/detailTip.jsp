<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
     <logic:iterate name="BEventTimes" id="bean" type="com.form.calendar.agenda.FAgenda" >
       <ul>
       <logic:iterate name="bean" property="eventsInDay" id="beanInDay" type="com.form.calendar.agenda.FAgenda">
           <logic:notEqual name="beanInDay" property="what" value="" >
               <li class="contentCalenda" onclick="prepareCalendar('<%=beanInDay.getTimes()%>','<%=beanInDay.getTimeEvent()%>',<%=beanInDay.getEventId()%>)" >
                    <bean:write name="beanInDay" property="times" /> : <bean:write name="beanInDay" property="what" /> - <bean:write name="beanInDay" property="where" />                       
               </li>
           </logic:notEqual>
       </logic:iterate>
       </ul>
    </logic:iterate>

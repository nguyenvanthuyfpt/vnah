<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:hidden name="agenda" property="type" styleId="type"  />
<script language="javascript">
function prepareCalendar(timeValue,dateValue,id){
        var times=timeValue.replace(":","#")
        var type=getObj('type').value;
        var typeCalendar=getObj('typeCalendar').value;
        var values=':times:'+times+':timeEvent:'+dateValue+':id:'+id+':type:'+type+':typeCalendar:'+typeCalendar;
        openWindow('agenda',anchor + ':_PREPARED_CREATE'+values);
       
}
</script>
<%
com.dao.calendar.DCalendarLib dao=new  com.dao.calendar.DCalendarLib();
int withForm =430;int heightForm =530;%>
<bean:define name="agenda" id="beanDate" type="com.form.calendar.agenda.FAgenda" />
<%int idCurrentDate=dao.convertDateToID(beanDate.getCurrentSqlDate());%>
<table style="border-collapse: collapse"  cellspacing="0" cellpadding="0" width="100%" class="popcalWeed" >
<tr bgcolor="#DDDDDD">
                <th width="50px"><bean:message key="users.log.Timelogin" bundle="<%=interfaces%>"/></th>
		<th ><bean:message key="agenda.label.two" bundle="<%=interfaces%>"/>-<%=beanDate.dateToString(beanDate.getDay())%></th>
		<th ><bean:message key="agenda.label.three" bundle="<%=interfaces%>"/>-<%=beanDate.dateToString(beanDate.addDays(beanDate.getDay(),1))%></th>
		<th ><bean:message key="agenda.label.for" bundle="<%=interfaces%>"/>-<%=beanDate.dateToString(beanDate.addDays(beanDate.getDay(),2))%></th>
		<th ><bean:message key="agenda.label.file" bundle="<%=interfaces%>"/>-<%=beanDate.dateToString(beanDate.addDays(beanDate.getDay(),3))%></th>
		<th ><bean:message key="agenda.label.six" bundle="<%=interfaces%>"/>-<%=beanDate.dateToString(beanDate.addDays(beanDate.getDay(),4))%></th>
                <th ><bean:message key="agenda.label.serven" bundle="<%=interfaces%>"/>-<%=beanDate.dateToString(beanDate.addDays(beanDate.getDay(),5))%></th>
                <th ><bean:message key="agenda.label.cn" bundle="<%=interfaces%>"/>-<%=beanDate.dateToString(beanDate.addDays(beanDate.getDay(),6))%></th>
               
</tr>
<tr >
<bean:define name="agenda"  id="beanT" type="com.form.calendar.agenda.FAgenda" />
            <td>
                <%
                String[] timeValues=com.inf.agenda.IKeyAgenda.CALENDAR_TIMES_VALUES;
                for(int i=0;i<timeValues.length;i++){%>
                <div class="indexCalendar">
                   <%=timeValues[i]%> 
                </div>
                <%}
                %>
            </td>
            <logic:iterate name="BEvents" id="bean" type="com.form.calendar.agenda.FAgenda">
            <td >
               <logic:iterate name="bean" property="eventsInDay" id="beanInDay" type="com.form.calendar.agenda.FAgenda">
               <%String className="contentCalenda";
                    if(bean.getDayId()==idCurrentDate){
                                 className="contentCalendaCurrent";           
                    }else if(bean.getDay(bean.stringToSqlDate(bean.getTimeEvent()))==1){
                                 className="contentCalendaCN";           
                    }
                String fromTime=beanInDay.getTimes();
                int time1=beanInDay.stringToInt(fromTime.split(":")[0]);
                int militi1=beanInDay.stringToInt(fromTime.split(":")[1]);
                String toTime=beanInDay.getToTimes();
                int time2=beanInDay.stringToInt(toTime.split(":")[0]);
                int militi2=beanInDay.stringToInt(toTime.split(":")[1]);
                int phut=militi2-militi1;
                if(phut<0){
                    phut=phut*(-1);
                }
               phut+=(time2-time1)*60;
               %>
               <logic:equal name="beanInDay" property="what" value="" >    
               <div class="<%=className%>"  onclick="prepareCalendar('<%=beanInDay.getTimes()%>','<%=beanInDay.getTimeEvent()%>',0)" >
               
               </div>
               </logic:equal>
               <logic:notEqual name="beanInDay" property="what" value="" >    
               
               <div class="<%=className%>" >
                   <div class="calendarDayPortlet" onclick="prepareCalendar('<%=beanInDay.getTimes()%>','<%=beanInDay.getTimeEvent()%>',<%=beanInDay.getEventId()%>)" style="height:<%=(phut*25)/30%>px"  >
                   <div class="calendarTitle"><bean:write name="beanInDay" property="times" />-<bean:write name="beanInDay" property="toTimes" /></div>
                   <bean:write name="beanInDay" property="what" />
                   </div>
               </div>
               
               </logic:notEqual>    
               
               </logic:iterate>
            </td>
            </logic:iterate>
</tr>
</table>
<html:hidden name="agenda" property="id_week" />
<div id="combobox"></div>

<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<input type="hidden" name="typeCalendar" id="typeCalendar" value="0" />
<script language="javascript">
function showTT(){
    post('agendaMeeting',anchor + ':_CALENDAR_WEEK');
}
</script>
<bean:define name="agendaMeeting" id="beanDate" type="com.form.calendarMeeting.agenda.FAgendaMeeting" />
<html:hidden name="beanDate" property="calendarType" />
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
                <a href="javascript:post('agendaMeeting',anchor + ':_WEEK_CURENT');"><bean:message key="agenda.label.today" bundle="<%=interfaces%>"/>,<bean:message key="agenda.form.edit.timeEvent" bundle="<%=interfaces%>"/> <%=beanDate.getDate(beanDate.getCurrentSqlDate())%>  <bean:message key="agenda.label.month" bundle="<%=interfaces%>"/> <%=beanDate.getMonth(beanDate.getCurrentSqlDate())%> <bean:message key="agenda.label.year" bundle="<%=interfaces%>"/> <%=beanDate.getYear(beanDate.getCurrentSqlDate())%></a>
                </div>
                </td>
                <td align="right">
<%com.dao.calendar.DCalendarLib dao=new  com.dao.calendar.DCalendarLib();%>
<table style="border-collapse: collapse"  cellspacing="0" cellpadding="0" width="250px" class="hearde-calenda" >
<tr>
<td width="5%" align="center"><img style="cursor: pointer" width="15px" src="<%=contextPath%>/images/record-prev.png" onclick="javascript:post('agendaMeeting',anchor + ':_PREW_WEEK');" /></td>
<td  align="center"><bean:message key="agenda.label.from" bundle="<%=interfaces%>"/> : <Strong><%=dao.convertIDToString(beanDate.getId_week())%></strong> <bean:message key="agenda.label.to" bundle="<%=interfaces%>"/> : <Strong><%=beanDate.dateToString(beanDate.addDays(dao.convertIDToDate(beanDate.getId_week()),6))%></strong></td>
<td width="5%" align="right"><img style="cursor: pointer" width="15px" src="<%=contextPath%>/images/record-next.png" onclick="javascript:post('agendaMeeting',anchor + ':_NEXT_WEEK');" /></td>
</tr>
</table>
                </td>
</tr>
</table>
<div>
    <jsp:include page="/calendarMeeting/week/weekPrivate.jsp" />
</div>

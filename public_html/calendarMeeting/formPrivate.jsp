<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="calendarMeeting" method="post" >
<table class="popupWin" cellpadding="2" cellspacing="2"  width="420x" >
    <tr>
        <TH class="tdheader" align="left"><bean:message key="key.calendar.meeting.title" bundle="<%=interfaces%>"/></TH>
        <th class="tdheader" align="right" valign="middle"><a href="javascript:closeWindow()"><img src="<%=contextPath%>/images/closeFormCalendar.gif" alt='Close' /></a></th>
     </tr>
     <tr>
    <td>
<table cellpadding="2" cellspacing="2" class="popupWinInner" >
  <tr>
    <td align="left" nowrap><bean:message key="key.calendar.meeting.baseEmp" bundle="<%=interfaces%>"/> :</td>
    <td align="left"><html:text name="calendarMeeting" property="baseEmp" style="width:300px" /></td>
  </tr>
  <tr>
    <td align="left" nowrap><bean:message key="key.calendar.meeting.content" bundle="<%=interfaces%>"/> :</td>
    <td align="left"><html:text name="calendarMeeting" property="content" style="width:300px" /></td>
  </tr>
  
  <tr>
    <td align="left" nowrap><bean:message key="key.calendar.meeting.device" bundle="<%=interfaces%>"/> :</td>
    <td align="left"><html:text name="calendarMeeting" property="device" style="width:300px" /></td>
  </tr>
  
  <tr>
    <td align="left" nowrap><bean:message key="label.module.calendar.time" bundle="<%=interfaces%>"/> :</td>
    <td align="left" nowrap>
    <html:text name="calendarMeeting" property="timeEvent" styleId="timeEvent" style="width:70px" />
    <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'timeEvent','dd/mm/yyyy');return false;">
    <bean:message key="label.module.calendar.fromtime" bundle="<%=interfaces%>"/> : 
    <html:select name="calendarMeeting" property="times" style="width:60px" >
        <%
            String[] timeValues=com.inf.agenda.IKeyAgenda.CALENDAR_TIMES_VALUES;
            for(int i=0;i<timeValues.length;i++){%>
            <html:option value="<%=timeValues[i]%>"><%=timeValues[i]%></html:option>
        <%}%>
    </html:select>
    
    <bean:message key="label.module.calendar.totime" bundle="<%=interfaces%>"/> : 
    <html:select name="calendarMeeting" property="toTimes" style="width:60px" >
        <%
            String[] timeValues=com.inf.agenda.IKeyAgenda.CALENDAR_TIMES_VALUES;
            for(int i=0;i<timeValues.length;i++){%>
            <html:option value="<%=timeValues[i]%>"><%=timeValues[i]%></html:option>
        <%}%>
    </html:select>
    
    </td>
  </tr>
  
  <tr>
    <td align="center" colspan="2" nowrap>
    <%boolean block=false;%>
    <%if(me.isRole(com.inf.IRoles.rOFFICER)){%>    
        <%block=true;%>
    <%}%>
    <%if(block==true){%>
            <% String funcAdd="javascript:post('calendarMeeting',anchor + ':_SAVE')";%>
            <html:button property="addNew" onclick="<%=funcAdd%>" styleClass="button">
              <bean:message key="action.save" bundle="<%=interfaces%>"/>
            </html:button>
            <% String funcDelete="javascript:post('calendarMeeting',anchor + ':_DELETE');";%>
            <html:button property="delete" onclick="<%=funcDelete%>" styleClass="button">
              <bean:message key="action.delete" bundle="<%=interfaces%>"/>
            </html:button>               
    <%}%>        
    </td>
  </tr>
</table>
</td>
</tr>
</table>

<html:hidden name="calendarMeeting" property="typeCalendar" styleId="typeCalendar" />
<html:hidden name="calendarMeeting" property="eventId" styleId="eventId" />
</html:form>
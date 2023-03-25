<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="calendar" method="post" >

<table class="popupWin" cellpadding="2" cellspacing="2"  width="420x" >
    <tr>
        <TH class="tdheader" align="left">Nh&#7853;p l&#7883;ch</TH>
        <th class="tdheader" align="right" valign="middle"><a href="javascript:closeWindow()"><img src="<%=contextPath%>/images/closeFormCalendar.gif" alt='Close' /></a></th>
     </tr>
     <tr>
    <td>
<table cellpadding="2" cellspacing="2" class="popupWinInner" >
  <tr>
    <td align="left" nowrap><bean:message key="label.module.calendar.what" bundle="<%=interfaces%>"/> :</td>
    <td align="left"><html:text name="calendar" property="what" style="width:300px" /></td>
  </tr>
  <tr>
    <td align="left" nowrap><bean:message key="label.module.calendar.where" bundle="<%=interfaces%>"/> :</td>
    <td align="left"><html:text name="calendar" property="where" style="width:300px" /></td>
  </tr>
  <tr>
    <td align="left" nowrap><bean:message key="label.module.calendar.time" bundle="<%=interfaces%>"/> :</td>
    <td align="left" nowrap>
    
    
    
    <html:text name="calendar" property="timeEvent" styleId="timeEvent" style="width:70px" />
    <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'timeEvent','dd/mm/yyyy');return false;">
    <bean:message key="label.module.calendar.fromtime" bundle="<%=interfaces%>"/> : 
    <html:select name="calendar" property="times" style="width:60px" >
        <%
            String[] timeValues=com.inf.agenda.IKeyAgenda.CALENDAR_TIMES_VALUES;
            for(int i=0;i<timeValues.length;i++){%>
            <html:option value="<%=timeValues[i]%>"><%=timeValues[i]%></html:option>
        <%}%>
    </html:select>
    
    <bean:message key="label.module.calendar.totime" bundle="<%=interfaces%>"/> : 
    <html:select name="calendar" property="toTimes" style="width:60px" >
        <%
            String[] timeValues=com.inf.agenda.IKeyAgenda.CALENDAR_TIMES_VALUES;
            for(int i=0;i<timeValues.length;i++){%>
            <html:option value="<%=timeValues[i]%>"><%=timeValues[i]%></html:option>
        <%}%>
    </html:select>
    
    </td>
  </tr>
  <tr>
    <td colspan="2" align="left" nowrap>
    <%if(me.isRole(com.inf.IRoles.rOFFICER)){%>    
        <html:radio  name="calendar"  property="type" value="1" />
        <bean:message key="header.cabin.label.public" bundle="<%=interfaces%>"/>
     <%}%>   
    <%if(me.isRole(com.inf.IRoles.rCALENDARDEP)){%>        
        <html:radio  name="calendar"  property="type" value="2" />
        <bean:message key="header.cabin.label.deps" bundle="<%=interfaces%>"/>
    <%}%>
    <logic:equal name="calendar" property="type" value="0">
        <html:radio  name="calendar"  property="type" value="0" />
        <bean:message key="header.cabin.label.private" bundle="<%=interfaces%>"/>    
    </logic:equal>    
    </td>
  </tr>
  <tr>
    <td align="center" colspan="2" nowrap>
    <%boolean block=false;%>
    
    <logic:equal name="calendar" property="type" value="0" >
        <%block=true;%>
    </logic:equal>
    
    <logic:equal name="calendar" property="type" value="1" >
    <%if(me.isRole(com.inf.IRoles.rOFFICER)){%>    
        <%block=true;%>
    <%}%>
    </logic:equal>
    
    <logic:equal name="calendar" property="type" value="2" >
    <%if(me.isRole(com.inf.IRoles.rCALENDARDEP)){%>    
        <%block=true;%>
    <%}%>
    </logic:equal>
    
    <%if(block==true){%>
            <% String funcAdd="javascript:post('calendar',anchor + ':_SAVE')";%>
            <html:button property="addNew" onclick="<%=funcAdd%>" styleClass="button">
              <bean:message key="action.save" bundle="<%=interfaces%>"/>
            </html:button>
            <% String funcDelete="javascript:post('calendar',anchor + ':_DELETE');";%>
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

<html:hidden name="calendar" property="typeCalendar" styleId="typeCalendar" />
<html:hidden name="calendar" property="eventId" styleId="eventId" />
</html:form>
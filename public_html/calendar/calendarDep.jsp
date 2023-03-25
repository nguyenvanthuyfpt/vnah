<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BCalendaInDeps" >
<logic:notEmpty name="BCalendaInDeps" >
 <div class="tblnews-calendar" align="left">
                <logic:iterate name="BCalendaInDeps" id="bean" indexId="i" type="com.form.calendar.agenda.FAgenda">
                                <div class="mainCalendarTitle">
                                <bean:message key="label.module.calendar.what" bundle="<%=interfaces%>"/>
                                : <bean:write name="bean" property="what" /></div>
                                <div><bean:message key="label.module.calendar.time" bundle="<%=interfaces%>"/> : <bean:write name="bean" property="times" /> - <bean:write name="bean" property="toTimes" /> <bean:message key="label.module.calendar.timeevent" bundle="<%=interfaces%>"/> <bean:write name="bean" property="timeEvent" /></div>
                                <div><bean:message key="label.module.calendar.where" bundle="<%=interfaces%>"/> : <bean:write name="bean" property="where" /></div>                    
                </logic:iterate>
                </div>
                </logic:notEmpty>
                </logic:present>
